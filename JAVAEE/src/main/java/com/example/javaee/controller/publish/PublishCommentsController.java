package com.example.javaee.controller.publish;


import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.publish.IPublishCommentsService;
import com.example.javaee.utils.JwtUtil;
import com.example.javaee.utils.Result;
import com.example.javaee.vo.CommentsDetail;
import com.example.javaee.vo.SimpleNews;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/publisher")
@Api(tags = "新闻发布者 关于评论接口")
public class PublishCommentsController {

    @Autowired
    IPublishCommentsService commentsService;

    @PostMapping("findAllCommentsOfMe")
    @ApiOperation(value = "发布者 查看自己的某个新闻的所有评论", notes = "需要新闻id   news_id   int类型")
    //查看某个新闻评论
    public Result<List<CommentsDetail>> findAllCommentsOfMe(Integer news_id, HttpServletRequest request){

        if(news_id==null){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        String token=request.getHeader("token");
        int user_id= Integer.parseInt(JwtUtil.getClaim("token").get("userId").toString());
       List<CommentsDetail> list= commentsService.findComments(news_id,user_id);
       Result result=new Result();
       result.setCode(200);
       result.setMsg("查看成功");
       result.setData(list);
       return result;

    }

    //删除自己的新闻下的某条新闻评论
    @PostMapping("deleteComment")
    @ApiOperation(value = "发布者 删除自己的新闻下的某条新闻评论", notes = "需要评论id   comment_id   int类型")
        public Result<Void> deleteComment(Integer comment_id, HttpServletRequest request){
            if(comment_id==null){
                throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
            }
            String token=request.getHeader("token");
            int user_id= Integer.parseInt(JwtUtil.getClaim("token").get("userId").toString());
            commentsService.changeComments(comment_id,user_id);
        Result result=new Result();
            result.setCode(200);
            result.setMsg("删除成功");
            return result;
        }


}
