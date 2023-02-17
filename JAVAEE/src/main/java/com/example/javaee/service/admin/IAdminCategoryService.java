package com.example.javaee.service.admin;

public interface IAdminCategoryService {
    //删除某个新闻类别，并且删除它所有新闻以及新闻评论以及点赞;
    boolean changeCategoryState(int Category_id);

    //添加某个新闻类别，需要注意对类别名不能重复
    boolean addCategory(String category_name);
}
