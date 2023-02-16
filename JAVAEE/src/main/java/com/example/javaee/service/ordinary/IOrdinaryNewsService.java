package com.example.javaee.service.ordinary;

import com.example.javaee.entity.News;
import com.example.javaee.entity.Category;
import com.example.javaee.vo.SimpleNews;
import java.util.List;
import com.example.javaee.vo.NewsDetails;

public interface IOrdinaryNewsService {
    List<Category> findAllCategory();

    List<SimpleNews> findSimpleNews(List<Integer> list);

    NewsDetails findDetailNews(int news_id);
}
