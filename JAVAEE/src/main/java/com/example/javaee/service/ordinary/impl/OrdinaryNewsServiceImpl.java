package com.example.javaee.service.ordinary.impl;

import com.example.javaee.dao.SelectDao;
import com.example.javaee.dao.UserDao;
import com.example.javaee.entity.News;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.ordinary.IOrdinaryNewsService;

import com.example.javaee.entity.Category;
import com.example.javaee.vo.NewsDetails;
import com.example.javaee.vo.SimpleNews;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdinaryNewsServiceImpl implements IOrdinaryNewsService {
    @Autowired
    private SelectDao selectDao;
    private UserDao userDao;

    @Override
    public List<Category> findAllCategory() {
        List<Category> allCategory = new ArrayList<Category>();
        allCategory = selectDao.categorySelectAll();
        int size = allCategory.size();
        for (int i = 0 ; i < size;++i){
            if(allCategory.get(i).getState()==1){
                allCategory.remove(allCategory.get(i));
            }
        }
        return allCategory;
    }

    @Override
    public List<SimpleNews> findSimpleNews(List<Integer> list) {
        List<News> listnews = new ArrayList<News>();
        for (int i = 0; i < list.size();++i){

            listnews.addAll(selectDao.newsSelectByCategory_id(list.get(i)));
        }
        int size = listnews.size();
        for (int i = 0;i < size;++i){
            if(listnews.get(i).getState() != 3){
                listnews.remove(listnews.get(i));
            }
        }
        List<SimpleNews> listresult = new ArrayList<SimpleNews>();
        for (int i = 0;i < listnews.size();++i){
            SimpleNews x = new SimpleNews();
            x.setNews_id(listnews.get(i).getNews_id());
            x.setTitle(listnews.get(i).getTitle());
            x.setCategory_id(listnews.get(i).getCategory_id());
            x.setUpdate_time(listnews.get(i).getUpdate_time());
            x.setState(listnews.get(i).getState());
            listresult.add(i,x);
        }
        return listresult;
    }

    @Override
    public NewsDetails findDetailNews(int news_id) {
        News news=selectDao.newsSelectByNewsId(news_id);
        NewsDetails detailnews = new NewsDetails();
        if(news==null){
            throw new BusinessException(ExceptionEnum.News_新闻不存在);
        }
        if(news.getState()!=3){
            throw new BusinessException(ExceptionEnum.News_该新闻不属于你);
        }
        detailnews.setNews_id(news_id);
        detailnews.setTitle(news.getTitle());
        detailnews.setAuthor_name((userDao.findByUserId(news.getAuthor_id())).getName());
        detailnews.setCategory_name((selectDao.categorySelectByCategory_id(news.getCategory_id())).getName());
        detailnews.setContent(news.getContent());
        detailnews.setLikes_cnt(selectDao.likesCountByNews_id(news_id));
        detailnews.setCreate_time(news.getCreate_time());
        detailnews.setUpdate_time(news.getUpdate_time());
        detailnews.setState(news.getState());
        detailnews.setAuthor_id(news.getAuthor_id());
        detailnews.setCategory_id(news.getCategory_id());

        return detailnews;
    }
}
