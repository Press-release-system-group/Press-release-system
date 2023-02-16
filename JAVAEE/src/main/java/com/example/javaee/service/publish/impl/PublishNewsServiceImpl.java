package com.example.javaee.service.publish.impl;

import com.example.javaee.dao.*;
import com.example.javaee.entity.Category;
import com.example.javaee.entity.News;
import com.example.javaee.vo.SimpleNews;
import com.example.javaee.service.publish.IPublishNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PublishNewsServiceImpl implements IPublishNewsService {
    @Autowired
    private InsertDao insertDao;
    @Autowired
    private SelectDao selectDao;
    @Autowired
    private UpdateDao updateDao;
    @Autowired
    private DeleteDao deleteDao;


    public boolean categoryExist(String categoryName)
    {
        Category category = selectDao.categorySelectByName(categoryName);
        return category != null;
    }

    public boolean NewsBelongsToUser(int user_id, int news_id)
    {
        News news = selectDao.getNewsByUserIdAndNewsId(news_id);
        return news.getAuthor_id() == user_id;
    }

    public void createNews(int user_id, String title, String content, String categoryName)
    {
        java.util.Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        Category category = selectDao.categorySelectByName(categoryName);
        insertDao.insertNews(user_id, title, content, category.getCategory_id(), time, time, 0);
    }

    public List<SimpleNews> getAllSimpleNews(int user_id)
    {
        return selectDao.selectAllSimpleNewsByUserId(user_id);
    }

    // 查看自己的某个详细新闻
    public News getNews(int user_id, int news_id)
    {
        return selectDao.getNewsByUserIdAndNewsId(news_id);
    }

//    // 修改自己的新闻
//    public News updateNewsByUser(int newsId, News news) {
//        News oldNews = newsDao.findById(newsId).orElse(null);
//        if (oldNews == null) {
//            throw new EntityNotFoundException("News not found with id: " + newsId);
//        }
//        if (oldNews.getStatus() != NewsStatus.SAVED.getValue()) {
//            throw new IllegalStateException("News can only be updated in SAVED status");
//        }
//        news.setId(newsId);
//        news.setStatus(oldNews.getStatus());
//        return newsDao.save(news);
//    }
//
//    // 发布自己的某个新闻
//    public News publishNewsByUser(int newsId) {
//        News news = newsDao.findById(newsId).orElse(null);
//        if (news == null) {
//            throw new EntityNotFoundException("News not found with id: " + newsId);
//        }
//        if (news.getStatus() != NewsStatus.SAVED.getValue()) {
//            throw new IllegalStateException("News can only be published in SAVED status");
//        }
//        news.setStatus(NewsStatus.PENDING.getValue());
//        return newsDao.save(news);
//    }



}
