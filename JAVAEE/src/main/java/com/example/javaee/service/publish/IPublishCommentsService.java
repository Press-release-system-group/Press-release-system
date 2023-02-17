package com.example.javaee.service.publish;

import com.example.javaee.vo.CommentsDetail;

import java.util.List;

public interface IPublishCommentsService {

    //查看自己某个新闻下所有评论
    List<CommentsDetail> findComments(int news_id,int user_id);

    //删除自己新闻下的某个评论
    boolean changeComments(int comment_id,int user_id);
}
