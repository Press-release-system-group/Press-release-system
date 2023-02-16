package com.example.javaee.controller.publish;

import com.example.javaee.entity.News;
import com.example.javaee.utils.JwtUtil;
import com.example.javaee.vo.SimpleNews;
import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.UserService;
import com.example.javaee.service.publish.IPublishNewsService;
import com.example.javaee.utils.Result;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @PostMapping ("/createNews")
    @ResponseBody
    @ApiOperation(value = "创建新闻", notes = "无需参数")
    public Result createNews(HttpServletRequest request, @RequestBody Map<String, String> createNewsInfo)
    {
        String token = request.getHeader("token");
        Claims claims= JwtUtil.getClaim(token);
        int user_id=(int)claims.get("userId");

        if(!userService.user_idExist(user_id))
            throw new BusinessException(ExceptionEnum.ID_无效);

        String title = createNewsInfo.get("title");
        String content = createNewsInfo.get("content");
        String category = createNewsInfo.get("category");

        if(category == null || category.isEmpty())
            throw new BusinessException(ExceptionEnum.News_类别为空);

        if(!publishNewsService.categoryExist(category))
            throw new BusinessException(ExceptionEnum.News_类别不存在);

        publishNewsService.createNews(user_id, title, content, category);
        return new Result<>(200,"新闻创建成功");
    }


    // 查看自己的所有新闻简略
    @GetMapping("/getAllSimpleNews")
    @ResponseBody
    public Result<List<SimpleNews>> getAllSimpleNews(HttpServletRequest request,@RequestBody Map<String, String> userInfo)
    {
        try
        {
            String token = request.getHeader("token");
            Claims claims= JwtUtil.getClaim(token);
            int user_id=(int)claims.get("userId");

            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            return new Result(200,"查询简略新闻成功",publishNewsService.getAllSimpleNews(user_id));
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    // 查看自己的某个新闻详情
    @GetMapping("/getNews")
    @ResponseBody
    public Result<News> getNews(HttpServletRequest request,@RequestBody Map<String, String> getNewsInfo) {
        try
        {
            String token = request.getHeader("token");
            Claims claims= JwtUtil.getClaim(token);
            int user_id=(int)claims.get("userId");
            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            int news_id = Integer.parseInt(getNewsInfo.get("news_id"));
            if(!publishNewsService.newsBelongsToUser(user_id, news_id))
                throw new BusinessException((ExceptionEnum.News_不属于));

            return new Result(200,"查看新闻成功",publishNewsService.getNews(user_id,news_id));
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    @PutMapping ("/saveNews")
    @ResponseBody
    @ApiOperation(value = "修改后保存新闻", notes = "你需要发送新闻ID、标题、内容和新闻类别")
    public Result saveNews(HttpServletRequest request, @RequestBody Map<String, String> saveNewsInfo)
    {
        String token = request.getHeader("token");
        Claims claims= JwtUtil.getClaim(token);
        int user_id=(int)claims.get("userId");

        if(!userService.user_idExist(user_id))
            throw new BusinessException(ExceptionEnum.ID_无效);

        try
        {
            int news_id = Integer.parseInt(saveNewsInfo.get("news_id"));

            if(!publishNewsService.newsIsEditable(news_id))
                throw new BusinessException(ExceptionEnum.News_不可编辑);

            if(!publishNewsService.newsBelongsToUser(user_id, news_id))
                throw new BusinessException((ExceptionEnum.News_不属于));

            String title = saveNewsInfo.get("title");
            String content = saveNewsInfo.get("content");
            String category = saveNewsInfo.get("category");

            if(title == null || title.isEmpty())
                throw new BusinessException(ExceptionEnum.News_标题为空);

            if(content == null || content.isEmpty())
                throw new BusinessException(ExceptionEnum.News_内容为空);

            if(category == null || category.isEmpty())
                throw new BusinessException(ExceptionEnum.News_类别为空);

            if(!publishNewsService.categoryExist(category))
                throw new BusinessException(ExceptionEnum.News_类别不存在);

            publishNewsService.saveNews(news_id, title, content, category);
            return new Result<>(200,"新闻保存成功");
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.News_ID不合法);
        }
    }

    // 发布自己的某个新闻
//    @PostMapping("/{newsId}/publish")
//    public Result publishNewsByUser(HttpServletRequest request, @RequestBody Map<String, String> publishNewsInfo) {
//        try
//        {
//            String token = request.getHeader("token");
//            Claims claims= JwtUtil.getClaim(token);
//            int user_id=(int)claims.get("userId");
//
//            if(!userService.user_idExist(user_id))
//                throw new BusinessException(ExceptionEnum.ID_无效);
//
//            String title = publishNewsInfo.get("title");
//            String content = publishNewsInfo.get("content");
//            String category = publishNewsInfo.get("category");
//
//            if(title == null || title.isEmpty())
//                throw new BusinessException(ExceptionEnum.News_标题为空);
//
//            if(content == null || content.isEmpty())
//                throw new BusinessException(ExceptionEnum.News_内容为空);
//
//            if(category == null || category.isEmpty())
//                throw new BusinessException(ExceptionEnum.News_类别为空);
//
//            if(!publishNewsService.categoryExist(category))
//                throw new BusinessException(ExceptionEnum.News_类别不存在);
//
//            publishNewsService.createNews(user_id, title, content, category);
//            return new Result<>(200,"新闻发布成功");
//        }
//        catch (NumberFormatException e)
//        {
//            throw new BusinessException(ExceptionEnum.ID_不合法);
//        }
//    }
//
//    // 删除自己的某个新闻
//    @DeleteMapping("/{newsId}")
//    public News deleteNewsByUser(@PathVariable("newsId") int newsId) {
//        return newsService.deleteNewsByUser(newsId);
//    }




}
