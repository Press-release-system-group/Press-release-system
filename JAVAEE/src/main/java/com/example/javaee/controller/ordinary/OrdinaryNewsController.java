package com.example.javaee.controller.ordinary;

import com.example.javaee.entity.Category;
import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.ordinary.IOrdinaryNewsService;
import com.example.javaee.utils.*;
import com.example.javaee.vo.NewsDetails;
import com.example.javaee.vo.SimpleNews;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("ordinary") //映射请求

@Api(tags = "用户新闻类接口")
public class OrdinaryNewsController
{

    @Autowired//就是创建一个已被初始化的bean，且创建初始化是根据无参构造函数，spring自动将匹配到的属性值进行注入，之后就可以正常使用这个对象的方法
    IOrdinaryNewsService ordinaryNews;

    @PostMapping("/newscategory")
    @ResponseBody //个人理解：该方法的返回的结果直接写入 HTTP 响应正文
    @ApiOperation(value = "用户 查看简略新闻类别 ", notes = "需要一个或多个新闻类别 id")
    public Result<List<Category>> newscategory(@RequestBody Map<String , String> categoryInfo)
    {
//        暂时没想到该功能的错误拦截情况
        //todo 这里需要改
        return new Result<>(200,"获取新闻类别成功",ordinaryNews.findAllCategory());
    }


    @PostMapping("/briefnews")
    @ResponseBody
//    允许用户通过标签名来访问对应的简略新闻
    @ApiOperation(value = "用户 查看简略新闻 ", notes = "需要一个或多个新闻类别 id")
    public Result<List<SimpleNews>> briefnews(@RequestParam("categorylist") List<Integer> categorylist){
        if(categorylist==null||categorylist.size()==0) { //无类别id时会报错
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        return new Result<>(200,"获取简略新闻成功",ordinaryNews.findSimpleNews(categorylist));
    }

    @PostMapping("/detailnews")
    @ResponseBody
    @ApiOperation(value = "用户 某个详细新闻 ", notes = "需要一个新闻id  news_id")
    public Result<NewsDetails> detailnews(Integer news_id){
        if (news_id==null){
            throw   new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        return new Result<>(200,"获取该详细新闻成功",ordinaryNews.findDetailNews(news_id));
    }
}
