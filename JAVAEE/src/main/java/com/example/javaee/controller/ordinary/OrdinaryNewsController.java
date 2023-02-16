package com.example.javaee.controller.ordinary;

import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.ordinary.IOrdinaryNewsService;
import com.example.javaee.utils.*;
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

@Controller("ordinary")

@Api(tags = "用户新闻类接口")
public class OrdinaryNewsController
{

    @Autowired
    IOrdinaryNewsService ordinaryNews;

    @PostMapping("/newscategory")
    @ResponseBody
    public Result newscategory(@RequestBody Map<String , String> categoryInfo)
    {
//        暂时没想到该功能的错误拦截情况
        return new Result<>(200,"获取新闻类别成功",ordinaryNews.findAllCategory());
    }

    @PostMapping("/briefnews")
    @ResponseBody
//    允许用户通过标签名来访问对应的简略新闻
    public Result briefnews(List<Integer> categorylist){
        if(categorylist==null||categorylist.size()==0) { //无类别id时会报错
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        return new Result<>(200,"获取简略新闻成功",ordinaryNews.findSimpleNews(categorylist));
    }

    @PostMapping("/detailnews")
    @ResponseBody
    public Result detailnews(Integer news_id){
        if (news_id==null){
            throw   new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        return new Result<>(200,"获取该详细新闻成功",ordinaryNews.findDetailNews(news_id));
    }
}
