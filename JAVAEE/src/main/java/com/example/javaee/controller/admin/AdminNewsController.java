package com.example.javaee.controller.admin;

import com.example.javaee.entity.News;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.admin.IAdminNewsService;
import com.example.javaee.utils.Result;
import com.example.javaee.vo.SimpleNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("admin")
public class AdminNewsController {

    @Autowired
    IAdminNewsService newsService;


    @PostMapping("getSimpleNewsByStatus")
    //查看某些状态的所有简略新闻
    public Result<SimpleNews> getSimpleNewsByStatus(List<Integer> list){

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
    public Result<News> getNewsDetailById(Integer news_id){
        if (news_id==null){
            throw   new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }

       News  news=newsService.getNewsById(news_id);
        Result result=new Result();
        result.setCode(200);
        result.setMsg("查找某个详细新闻成功");

        return result;
    }

}
