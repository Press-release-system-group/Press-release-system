package com.example.javaee.service.ordinary;

import io.swagger.models.auth.In;
import com.example.javaee.vo.SimpleNews;
import java.util.List;

public interface IOrdinaryLikeService {
    Integer addlikeNews(int user_id ,int news_id);

    List<SimpleNews> checklikeNews(int user_id);
}
