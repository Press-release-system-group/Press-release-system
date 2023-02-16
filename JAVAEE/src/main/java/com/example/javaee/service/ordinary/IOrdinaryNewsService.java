package com.example.javaee.service.ordinary;

import com.example.javaee.entity.News;
import com.example.javaee.entity.Category;
import java.util.List;

public interface IOrdinaryNewsService {
    List<Category> findAllCategory();
}
