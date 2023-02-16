package com.example.javaee.service.admin;

import com.example.javaee.entity.News;
import com.example.javaee.vo.SimpleNews;

import java.util.List;

public interface IAdminNewsService {

    //寻找简略新闻集合
    List<SimpleNews> getSimpleNews(List<Integer> list);

    //查找某个详细新闻
    News getNewsById(int id);
}
