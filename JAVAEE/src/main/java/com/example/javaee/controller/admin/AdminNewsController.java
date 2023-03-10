package com.example.javaee.controller.admin;

import com.example.javaee.entity.News;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.admin.IAdminNewsService;
import com.example.javaee.utils.Result;
import com.example.javaee.vo.NewsDetails;
import com.example.javaee.vo.SimpleNews;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("admin")
@Api(tags = "管理员 新闻相关接口")
public class AdminNewsController {

    @Autowired
    IAdminNewsService newsService;

    @ApiOperation(value = "管理员查看某些状态的所有简略新闻", notes = "需要提供多个int 类型 状态集合 ")
    @PostMapping("getSimpleNewsByStatus")
    //查看某些状态的所有简略新闻
    public Result<List<SimpleNews>> getSimpleNewsByStatus(@RequestParam(value = "list")List<Integer> list){

        if(list==null||list.size()==0||list.size()>4){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)<0||list.get(i)>3)throw new BusinessException(ExceptionEnum.PARAMS_接收状态参数非法);
        }

      List<SimpleNews> list1=  newsService.getSimpleNews(list);
            Result result=new Result();
            result.setCode(200);
            result.setMsg("查找成功");
            result.setData(list1);
            return result;
    }

//查看某个具体新闻
    @PostMapping("getNewsDetailByNews_id")
    @ApiOperation(value = "管理员 查看某个具体新闻", notes = "需要 news_id  ")
    public Result<NewsDetails> getNewsDetailById(Integer news_id){
        if (news_id==null){
            throw   new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }

       NewsDetails newsDetails=newsService.getNewsById(news_id);
        Result result=new Result();
        result.setCode(200);
        result.setMsg("查找某个详细新闻成功");
        result.setData(newsDetails);
        return result;
    }


    //审核新闻，其实就是改变新闻的状态，如果要改变状态是删除，其他的东西也要跟着改
    @ApiOperation(value = "管理员 审核新闻，有两种可能,第一种: 比如审核新闻原本状态为1（也就是审核中状态的新闻）的新闻," +
            "此时传来的state参数可以为  0（就是未通过，变成保存中状态），或者为  3 （变成通过状态），第二种："+
            "新闻原本状态为3，state参数为2，也就是将通过状态的新闻改成删除状态", notes = "需要 news_id （int）， int 类型 state ")
    @PostMapping("changeNewsState")
        public Result<Void> changeNewsState(Integer news_id,Integer state){
        if(news_id==null||state==null){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        if(state<0||state>3){
            throw new BusinessException(ExceptionEnum.PARAMS_接收状态参数非法);
        }
        newsService.changeNewsState(news_id,state);
        Result result=new Result();
        result.setCode(200);
        result.setData("操作成功");
        return result;
    }




}
