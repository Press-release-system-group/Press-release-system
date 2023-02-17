package com.example.javaee.service.ordinary;

import com.example.javaee.entity.Comments;
import com.example.javaee.vo.CommentsDetail;

import java.util.List;

public interface IOrdinaryCommentsService {

    //查看某个新闻下所有评论
    List<CommentsDetail> getAllCommentsByNewsId(int news_id);

    //评论某条新闻
    boolean addComments(int news_id,int user_id,String content);

    //查找自己的所有评论
    List<CommentsDetail> findAllComments(int user_id);

    //将自己的某条新闻变成删除状态
    boolean changeStateById(int user_id,int comments_id);
}
