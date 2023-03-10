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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor =RuntimeException.class)
public class OrdinaryNewsServiceImpl implements IOrdinaryNewsService {
    //就是创建一个已被初始化的bean，且创建初始化是根据无参构造函数，spring自动将匹配到的属性值进行注入，之后就可以正常使用这个对象的方法
    @Autowired
    private SelectDao selectDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Category> findAllCategory() {
        List<Category> allCategory1 = new ArrayList<Category>();
        allCategory1 = selectDao.categorySelectAll();
        List<Category> allCategory=new ArrayList<>();
        int size = allCategory1.size();
        for (int i = 0 ; i < size;++i){
            if(allCategory1.get(i).getState()!=1){
               allCategory.add(allCategory1.get(i));
            }
        }
        return allCategory;
    }

    @Override
    public List<SimpleNews> findSimpleNews(List<Integer> list) {
        List<News> listnews1 = new ArrayList<News>();
        for (int i = 0; i < list.size();i++){

            //todo 这里要改
            if(selectDao.categorySelectById(list.get(i))==null||selectDao.categorySelectById(list.get(i)).getState()==1) throw new BusinessException(ExceptionEnum.Category_没有该类别);

            listnews1.addAll(selectDao.newsSelectByCategory_id(list.get(i)));
        }
        List<News> listnews=new ArrayList<>();
        int size = listnews1.size();
        for (int i = 0;i < size;i++){
            if(listnews1.get(i).getState() == 3){
//                listnews.remove(listnews.get(i));
                listnews.add(listnews1.get(i));
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
            throw new BusinessException(ExceptionEnum.News_新闻不存在);
        }
        detailnews.setNews_id(news_id);
        detailnews.setTitle(news.getTitle());
        detailnews.setAuthor_name((userDao.findByUserId(news.getAuthor_id())).getUsername());
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
