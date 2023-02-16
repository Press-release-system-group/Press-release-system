package com.example.javaee.service.ordinary.impl;

import com.example.javaee.dao.SelectDao;
import com.example.javaee.service.ordinary.IOrdinaryNewsService;

import com.example.javaee.entity.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdinaryNewsServiceImpl implements IOrdinaryNewsService {
    @Autowired
    private SelectDao selectDao;


    @Override
    public List<Category> findAllCategory() {
        return selectDao.categorySelectAll();
    }
}
