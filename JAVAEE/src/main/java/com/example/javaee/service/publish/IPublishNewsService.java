package com.example.javaee.service.publish;

import com.example.javaee.entity.News;
import com.example.javaee.vo.NewsDetails;
import com.example.javaee.vo.SimpleNews;

import java.util.List;

public interface IPublishNewsService
{
    public boolean newsExist(int news_id);
    public boolean categoryExist(String category);
    public boolean newsBelongsToUser(int user_id, int news_id);
    public boolean newsIsEditable(int news_id);
    public boolean newsIsDeletable(int news_id);



    public void createNews(int user_id, String title, String content, String categoryName);
    public List<SimpleNews> getAllSimpleNews(int user_id);
    public NewsDetails getNewsDetail(int news_id);
    public void saveNews(int news_id, String title, String content, String categoryName);
    public void publishNews(int news_id, String title, String content, String categoryName);
    public void deleteNews(int news_id);
}
