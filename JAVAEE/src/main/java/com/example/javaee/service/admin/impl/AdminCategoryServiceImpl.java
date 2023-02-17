package com.example.javaee.service.admin.impl;

import com.example.javaee.dao.InsertDao;
import com.example.javaee.dao.SelectDao;
import com.example.javaee.dao.UpdateDao;
import com.example.javaee.dao.UserDao;
import com.example.javaee.entity.Category;
import com.example.javaee.entity.News;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.admin.IAdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminCategoryServiceImpl implements IAdminCategoryService {
    @Autowired
    SelectDao selectDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UpdateDao updateDao;

    @Autowired
    InsertDao insertDao;
    @Override
    public boolean changeCategoryState(int category_id) {
        Category category = selectDao.categorySelectByCategory_id(category_id);
        if(category==null)
        {
            throw new BusinessException(ExceptionEnum.Category_没有该类别Id);
        }
        updateDao.CategoryUpdateByCategory_id(category_id,1);//设置该新闻类别为删除状态
        List<News> newsList=selectDao.newsSelectByCategory_id(category_id);
        if(newsList==null||newsList.size()==0){
            throw new BusinessException(ExceptionEnum.Category_该类别下没有新闻);
        }

        for (int i=0;i<newsList.size();i++)
        {
            int news_id=newsList.get(i).getNews_id();
            updateDao.newsStateUpdateByNews_id(news_id,2);//删除该新闻

            updateDao.commentsUpdateByNews_id(1,news_id);//删除该新闻所有评论

            updateDao.likesUpdateByNews_id(1,news_id);//删除该新闻所有点赞

        }

        return true;
    }

    @Override
    public boolean addCategory(String category_name) {
        if(selectDao.categorySelectByName(category_name)!=null){
            throw new BusinessException(ExceptionEnum.Category_类别名字已存在);
        }
        Category category=new Category();
        category.setName(category_name);
        category.setState(0);
        category.setCreate_time(new Date());
        int i=insertDao.categoryInsert(category);//加进去
        return true;
    }
}
