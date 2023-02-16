package com.example.javaee.service.admin.impl;

import com.example.javaee.dao.SelectDao;
import com.example.javaee.entity.News;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.admin.IAdminNewsService;
import com.example.javaee.vo.SimpleNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Service
public class AdminNewsServiceImpl implements IAdminNewsService {

    @Autowired
    SelectDao selectDao;
    @Override
    public List<SimpleNews> getSimpleNews(List<Integer> list) {
        return selectDao.newsSelectByStatus(list);
    }

    @Override
    public News getNewsById(int id) {
        News news=selectDao.getNewsByNewsId(id);
        if(news==null){
            throw new BusinessException(ExceptionEnum.News_新闻不存在);
        }
        return news;
    }
}
