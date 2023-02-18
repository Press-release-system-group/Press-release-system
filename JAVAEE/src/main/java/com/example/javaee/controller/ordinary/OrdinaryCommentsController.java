package com.example.javaee.controller.ordinary;

import com.example.javaee.entity.Comments;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.ordinary.IOrdinaryCommentsService;
import com.example.javaee.utils.JwtUtil;
import com.example.javaee.utils.Result;
import com.example.javaee.utils.Role;
import com.example.javaee.vo.CommentsDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("ordinary")
@Api(tags = "用户  评论相关接口")
public class OrdinaryCommentsController {
    @Autowired
    IOrdinaryCommentsService commentsService;


    //获取某新闻下所有评论
    @PostMapping("getAllCommentsByNewsId")
    @ApiOperation(value = "用户查看某条新闻的所有评论", notes = "需要提供 news_id ")
    public Result<List<CommentsDetail>> getAllCommentsByNewsId(Integer news_id){
        if (news_id==null){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
   List<CommentsDetail> commentsDetailList= commentsService.getAllCommentsByNewsId(news_id);
        Result result=new Result();
        result.setCode(200);
        result.setData("查看成功");
        result.setData(commentsDetailList);
        return result;
    }


    //评论某个新闻
    @PostMapping("addCommnets")
    @ApiOperation(value = "用户评论某个新闻", notes = "需要提供 news_id,字符串类型的 content ")
    public Result addComments( String content, Integer news_id, HttpServletRequest request){
        String token=request.getHeader("token");
        if(news_id==null||content==null||content.equals("")||content.length()==0){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
       int user_id=Integer.parseInt(JwtUtil.getClaim(token).get("userId").toString()) ;


        commentsService.addComments(news_id,user_id,content);

        Result result=new Result();
        result.setCode(200);
        result.setMsg("评论成功");
        return result;
    }

    //查询所有自己的评论
    @GetMapping("findMyAllComments")
    @ApiOperation(value = "用户查询所有自己的评论", notes = "不需要参数")
    public Result<List<CommentsDetail>> findMyAllComments( HttpServletRequest request){
        String token=request.getHeader("token");
        int user_id=Integer.parseInt(JwtUtil.getClaim(token).get("userId").toString()) ;

       List<CommentsDetail> list= commentsService.findAllComments(user_id);
       Result result=new Result(200,"查询成功",list);
       return result;
    }

    //删除自己的某个评论
    @PostMapping("deleteMyComments")
    @ApiOperation(value = "用户删除自己的某个评论", notes = "需要评论id   comments_id   int类型")
    public Result deleteMyComments(Integer comments_id, HttpServletRequest request){
        if(comments_id==null)throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        String token=request.getHeader("token");
        int user_id=Integer.parseInt(JwtUtil.getClaim(token).get("userId").toString()) ;
        commentsService.changeStateById(user_id,comments_id);
        Result result=new Result();
        result.setMsg("删除成功");
        result.setCode(200);
        return result;
    }


}
