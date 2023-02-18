package com.example.javaee.service.admin.impl;

import com.example.javaee.dao.InsertDao;
import com.example.javaee.dao.SelectDao;
import com.example.javaee.dao.UpdateDao;
import com.example.javaee.dao.UserDao;
import com.example.javaee.entity.*;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.admin.IAdminNewsService;
import com.example.javaee.vo.NewsDetails;
import com.example.javaee.vo.SimpleNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor =RuntimeException.class)
public class AdminNewsServiceImpl implements IAdminNewsService {

    @Autowired
    SelectDao selectDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UpdateDao updateDao;

    @Autowired
    InsertDao insertDao;

    @Override
    public List<SimpleNews> getSimpleNews(List<Integer> list) {
        return selectDao.newsSelectByStatus(list);
    }

    @Override
    public NewsDetails getNewsById(int id) {
        News news=selectDao.newsSelectByNewsId(id);
        if(news==null){
            throw new BusinessException(ExceptionEnum.News_新闻不存在);
        }
        int author_id=news.getAuthor_id();
        int category_id = news.getCategory_id();

        String  author_name= userDao.findByUserId(author_id).getName();
        String category_name = selectDao.categorySelectByCategory_id(category_id).getName();

        NewsDetails newsDetails=new NewsDetails();
        newsDetails.setAuthor_id(author_id);
        newsDetails.setAuthor_name(author_name);
        newsDetails.setCategory_id(category_id);
        newsDetails.setCategory_name(category_name);
        newsDetails.setContent(news.getContent());
        newsDetails.setCreate_time(news.getCreate_time());
        newsDetails.setLikes_cnt(selectDao.likesCountByNews_id(id));
        newsDetails.setNews_id(id);
        newsDetails.setState(news.getState());
        newsDetails.setTitle(news.getTitle());
        newsDetails.setUpdate_time(news.getUpdate_time());


        return newsDetails;
    }

    @Override
    public boolean changeNewsState(int news_id, int state) {
       News news= selectDao.newsSelectByNewsId(news_id);
        if(news==null){
            throw new BusinessException(ExceptionEnum.News_新闻不存在);
        }
        if((state==3||state==0)&&news.getState()==1){//就是从审核中转换保存中或者通过
           updateDao.newsStateUpdateByNews_id(news_id,state);
        }else if(state==2&&news.getState()==3){//删除某条通过的新闻
           updateDao.newsStateUpdateByNews_id(news_id,state);

          List<Comments> commentsList= selectDao.commentsSelectByNews_id(news_id);
          if(commentsList==null||commentsList.size()==0)return true;

          updateDao.commentsUpdateByNews_id(1,news_id);//删除评论

          updateDao.likesUpdateByNews_id(1,news_id);//删除点赞

        }else {
            throw new BusinessException(ExceptionEnum.PARAMS_接收状态参数非法);
        }
        return true;
    }


}
