package com.example.javaee.controller.ordinary;

import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.ordinary.IOrdinaryNewsService;
import com.example.javaee.utils.*;
import io.swagger.annotations.*;
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

@Api(tags = "用户新闻类接口")
public class OrdinaryNewsController
{

    @Autowired
    IOrdinaryNewsService ordinaryNews;

    @PostMapping("/user/newscategory")
    @ResponseBody
    public Result<User> newscategory(@RequestBody Map<String , String> categoryInfo)
    {
//        暂时没想到该功能的错误拦截情况
        return new Result(200,"获取成功",ordinaryNews.findAllCategory());
    }
}
