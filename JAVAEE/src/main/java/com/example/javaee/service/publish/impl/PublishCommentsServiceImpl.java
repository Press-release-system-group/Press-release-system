package com.example.javaee.service.publish.impl;

import com.example.javaee.dao.SelectDao;
import com.example.javaee.dao.UpdateDao;
import com.example.javaee.entity.Comments;
import com.example.javaee.entity.News;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.publish.IPublishCommentsService;
import com.example.javaee.service.publish.IPublishNewsService;
import com.example.javaee.vo.CommentsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishCommentsServiceImpl implements IPublishCommentsService {
    @Autowired
    SelectDao selectDao;

    @Autowired
    UpdateDao updateDao;

    @Override
    public List<CommentsDetail> findComments(int news_id, int user_id) {
       News news= selectDao.newsSelectByNewsId(news_id);

       if (news==null||news.getState()!=3){
           throw new BusinessException(ExceptionEnum.News_新闻不存在);
       }
       if(news.getAuthor_id()!=user_id){
           throw new BusinessException(ExceptionEnum.News_该新闻不属于你);
       }
       List<Comments> commentsList=selectDao.commentsSelectByNews_id(news_id);
       List<CommentsDetail> commentsDetailList=new ArrayList<>();
       for(int i=0;i<commentsList.size();i++)
       {
           Comments comments=commentsList.get(i);
           if(comments.getState()==1)continue;
           CommentsDetail commentsDetail=new CommentsDetail();
           commentsDetail.setComment_id(comments.getComment_id());
           commentsDetail.setContent(comments.getContent());
           commentsDetail.setCreate_time(comments.getCreate_time());
           commentsDetail.setNews_id(comments.getNews_id());
           commentsDetail.setNews_title(selectDao.newsSelectByNewsId(comments.getNews_id()).getTitle());
           commentsDetail.setState(comments.getState());
           commentsDetail.setUser_id(comments.getNews_id());
           commentsDetailList.add(commentsDetail);
       }
        return commentsDetailList;
    }

    @Override
    public boolean changeComments(int comment_id, int user_id) {
        Comments comments = selectDao.commentsSelectByComment_id(comment_id);
        if (comments==null||comments.getState()==1)throw new BusinessException(ExceptionEnum.Comment_该评论不存在);
        News news=selectDao.newsSelectByNewsId(comments.getNews_id());
        if (news.getAuthor_id()!=user_id){
            throw new BusinessException(ExceptionEnum.News_该新闻不属于你);
        }
        updateDao.commentsUpdateByComment_id(1,comment_id);
        return true;
    }
}
