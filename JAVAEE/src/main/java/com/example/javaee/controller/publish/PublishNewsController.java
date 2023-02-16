package com.example.javaee.controller.publish;

import com.example.javaee.entity.News;
import com.example.javaee.vo.SimpleNews;
import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.UserService;
import com.example.javaee.service.publish.IPublishNewsService;
import com.example.javaee.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/publisher")
@Api(tags = "新闻发布相关接口")
public class PublishNewsController
{
    @Autowired
    IPublishNewsService publishNewsService;
    @Autowired
    UserService userService;

    @PutMapping ("/createNews")
    @ResponseBody
    @ApiOperation(value = "创建新闻并保存", notes = "你需要发送用户ID、标题、内容和新闻类别")
    public Result createNews(@RequestBody Map<String, String> createNewsInfo)
    {
        //TODO:本来是从token里拿userid的，但是token校验暂时关闭
//        String token = request.getHeader("token");
//        Claims claims= JwtUtil.getClaim(token);
//        claims.get("userID");
        try
        {
            int user_id = Integer.parseInt(createNewsInfo.get("user_id"));

            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            String title = createNewsInfo.get("title");
            String content = createNewsInfo.get("content");
            String category = createNewsInfo.get("category");

            if(title == null || title.isEmpty())
                throw new BusinessException(ExceptionEnum.News_标题为空);

            if(content == null || content.isEmpty())
                throw new BusinessException(ExceptionEnum.News_内容为空);

            if(category == null || category.isEmpty())
                throw new BusinessException(ExceptionEnum.News_类别为空);

            if(!publishNewsService.categoryExist(category))
                throw new BusinessException(ExceptionEnum.News_类别不存在);

            publishNewsService.createNews(user_id, title, content, category);
            return new Result<>(200,"新闻保存成功");
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }


    // 查看自己的简略新闻
    @GetMapping("/getAllSimpleNews")
    @ResponseBody
    public Result<List<SimpleNews>> getAllSimpleNews(@RequestBody Map<String, String> userInfo)
    {
        try
        {
            //TODO:本来是从token里拿userid的，但是token校验暂时关闭
            int user_id = Integer.parseInt(userInfo.get("user_id"));

            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            return new Result(200,"查询简略新闻成功",publishNewsService.getAllSimpleNews(user_id));
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    // 查看自己的某个详细新闻
    @GetMapping("/getNews")
    @ResponseBody
    public Result<News> getNews(@RequestBody Map<String, String> getNewsInfo) {
        try
        {
            //TODO:本来是从token里拿userid的，但是token校验暂时关闭
            int user_id = Integer.parseInt(getNewsInfo.get("user_id"));
            int news_id = Integer.parseInt(getNewsInfo.get("news_id"));

            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            if(!publishNewsService.NewsBelongsToUser(user_id, news_id))
                throw new BusinessException((ExceptionEnum.News_不属于));

            return new Result(200,"查看新闻成功",publishNewsService.getNews(user_id,news_id));
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

//    // 修改自己的新闻
//    @PutMapping("/{newsId}")
//    public News updateNewsByUser(@PathVariable("newsId") int newsId, @RequestBody News news) {
//        return newsService.updateNewsByUser(newsId, news);
//    }
//
//    // 发布自己的某个新闻
//    @PostMapping("/{newsId}/publish")
//    public News publishNewsByUser(@PathVariable("newsId") int newsId) {
//        return newsService.publishNewsByUser(newsId);
//    }
//
//    // 删除自己的某个新闻
//    @DeleteMapping("/{newsId}")
//    public News deleteNewsByUser(@PathVariable("newsId") int newsId) {
//        return newsService.deleteNewsByUser(newsId);
//    }




}
