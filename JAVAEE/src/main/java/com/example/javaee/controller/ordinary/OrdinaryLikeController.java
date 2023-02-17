package com.example.javaee.controller.ordinary;
import com.example.javaee.entity.User;
import com.example.javaee.entity.News;
import com.example.javaee.entity.Likes;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.UserService;
import com.example.javaee.utils.JwtUtil;
import com.example.javaee.utils.Result;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.javaee.service.ordinary.IOrdinaryLikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("ordinary")

@Api(tags = "这是用户点赞类接口")

public class OrdinaryLikeController {

    @Autowired
    IOrdinaryLikeService ordinaryLike;
    UserService userService;

    @PostMapping("/addlike")
    @ResponseBody
    public Result addLike(HttpServletRequest request,Integer news_id){
        int user_id = GetUserID(request);
        if(!userService.user_idExist(user_id))
            throw new BusinessException(ExceptionEnum.ID_无效);
        if (news_id==null){
            throw   new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        ordinaryLike.addlikeNews(user_id,news_id);
        return new Result(200,"添加点赞新闻成功");
    }

    @PostMapping("/checklike")
    @ResponseBody
    public Result checkLike(HttpServletRequest request){
        int user_id = GetUserID(request);
        if(!userService.user_idExist(user_id))
            throw new BusinessException(ExceptionEnum.ID_无效);
        return new Result(200,"查看自己简略新闻成功",ordinaryLike.checklikeNews(user_id));
    }

    public int GetUserID(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        Claims claims= JwtUtil.getClaim(token);
        return (int)claims.get("userId");
    }
}
