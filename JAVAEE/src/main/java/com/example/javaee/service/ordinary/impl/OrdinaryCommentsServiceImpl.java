package com.example.javaee.service.ordinary.impl;

import com.example.javaee.dao.InsertDao;
import com.example.javaee.dao.SelectDao;
import com.example.javaee.dao.UpdateDao;
import com.example.javaee.dao.UserDao;
import com.example.javaee.entity.Comments;
import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.ordinary.IOrdinaryCommentsService;
import com.example.javaee.vo.CommentsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor =RuntimeException.class)
public class OrdinaryCommentsServiceImpl implements IOrdinaryCommentsService {
    @Autowired
    SelectDao selectDao;

    @Autowired
    UserDao userDao;

    @Autowired
    InsertDao insertDao;

    @Autowired
    UpdateDao updateDao;
    @Override
    public List<CommentsDetail> getAllCommentsByNewsId(int news_id) {
        if(selectDao.newsSelectByNewsId(news_id)==null||selectDao.newsSelectByNewsId(news_id).getState()!=3)
            throw new BusinessException(ExceptionEnum.News_新闻不存在);


       List<Comments>  commentsList= selectDao.commentsSelectByNews_id(news_id);

       if(commentsList==null)return null;

       List<CommentsDetail> commentsDetailArrayList=new ArrayList<>();
       for(int i=0;i<commentsList.size();i++)
       {

           Comments comments=commentsList.get(i);
           if(comments.getState()==1)continue;//如果是删除状态的数据就跳过

           CommentsDetail detail=new CommentsDetail();
          String user_name= userDao.findByUserId(commentsList.get(i).getUser_id()).getName();
          detail.setComment_id(comments.getComment_id());
          detail.setContent(comments.getContent());
          detail.setCreate_time(comments.getCreate_time());
          detail.setNews_id(comments.getNews_id());
          detail.setState(comments.getState());
          detail.setUser_id(comments.getUser_id());
          detail.setUser_name(user_name);
          commentsDetailArrayList.add(detail);
       }
       return commentsDetailArrayList;
    }

    @Override
    public boolean addComments(int news_id, int user_id, String content) {
        if(selectDao.newsSelectByNewsId(news_id)==null||selectDao.newsSelectByNewsId(news_id).getState()!=3)
        {
            throw new BusinessException(ExceptionEnum.News_新闻不存在);
        }
        Comments comments=new Comments();
        comments.setContent(content);
        comments.setNews_id(news_id);
        comments.setCreate_time(new Date());
        comments.setState(0);
        comments.setUser_id(user_id);

        insertDao.commentsInsert(comments);
        return true;
    }

    @Override
    public List<CommentsDetail> findAllComments(int user_id) {
       List<Comments> commentsList= selectDao.commentsSelectByUser_id(user_id);
       List<CommentsDetail> detailList=new ArrayList<>();
       for (int i=0;i<commentsList.size();i++){
           if(commentsList.get(i).getState()==0)
           {
               Comments comments=commentsList.get(i);
               CommentsDetail commentsDetail=new CommentsDetail();
               commentsDetail.setComment_id(comments.getComment_id());
               commentsDetail.setContent(comments.getContent());
               commentsDetail.setCreate_time(comments.getCreate_time());
               commentsDetail.setState(comments.getState());
               commentsDetail.setNews_id(comments.getNews_id());
               commentsDetail.setNews_title(selectDao.newsSelectByNewsId(comments.getNews_id()).getTitle());
               detailList.add(commentsDetail);
           }
       }

       return detailList;
    }

    @Override
    public boolean changeStateById(int user_id, int comments_id) {
       Comments comments= selectDao.commentsSelectByComment_id(comments_id);
       if(comments==null||comments.getState()==1){
           throw new BusinessException(ExceptionEnum.Comment_该评论不存在);
       }
       if(comments.getUser_id()!=user_id){
           throw new BusinessException(ExceptionEnum.Comment_该评论不属于你);
       }
       updateDao.commentsUpdateByComment_id(1,comments_id);
       return true;

    }


}
