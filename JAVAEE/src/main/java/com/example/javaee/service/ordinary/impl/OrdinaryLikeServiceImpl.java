package com.example.javaee.service.ordinary.impl;

import com.example.javaee.dao.SelectDao;
import com.example.javaee.dao.InsertDao;
import com.example.javaee.entity.News;
import com.example.javaee.service.ordinary.IOrdinaryLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.javaee.entity.Likes;
import com.example.javaee.vo.SimpleNews;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdinaryLikeServiceImpl implements IOrdinaryLikeService {

    @Autowired
    private SelectDao selectDao;
    @Autowired
    private InsertDao insertDao;


    @Override
    public Integer addlikeNews(int user_id,int news_id) {
        java.util.Date date = new Date();
        Timestamp create_time = new Timestamp(date.getTime());
        Likes likes = new Likes();
        likes.setNews_id(news_id);
        likes.setState(0);
        likes.setUser_id(user_id);
        likes.setCreate_time(create_time);
        insertDao.likesInsert(likes);
        return 0;
    }

    @Override
    public List<SimpleNews> checklikeNews(int user_id) {
        List<Likes> likenews = new ArrayList<Likes>();
        List<SimpleNews> simpleNewsList = new ArrayList<SimpleNews>();
        likenews = selectDao.likeSelectByStateAndUser_id(0,user_id);
        for (int i = 0 ; i < likenews.size();++i){

            News new_data = selectDao.newsSelectByNewsId(likenews.get(i).getNews_id());
            SimpleNews simpleNews=new SimpleNews();
            simpleNews.setState(0);
                    simpleNews.setNews_id(new_data.getNews_id());
                    simpleNews.setTitle(new_data.getTitle());
                    simpleNews.setCategory_id(new_data.getCategory_id());
                    simpleNews.setUpdate_time(new_data.getCreate_time());
          simpleNewsList.add(simpleNews);
        }
        return simpleNewsList;
    }
}
