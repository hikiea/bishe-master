package com.lzy.bishe.modules.comment.service;

import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.comment.mapper.CommentDao;
import com.lzy.bishe.modules.comment.model.entry.Comment;
import com.lzy.bishe.modules.tie.service.TieService;
import com.lzy.bishe.modules.user.service.UserService;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private TieService tieService;

/*
    @Autowired
    private NotificationService notificationService;
*/

    @Autowired
    private CommentService commentService;

    public ResultDTO doPublishComment(Comment comment){
        /*获取当前时间*/
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        comment.setCommentTime(nowTime);
        /* 放入一级评论标志 */
        comment.setCommentTypes(0);
        commentDao.publishComment(comment);
        return ResultDTO.successOf("发送成功",comment);
    }

    public ResultDTO selectTeiComment(Integer tieId) {
        List<Comment> comments = commentDao.selectTeiComment(tieId);
        PageInfo pageInfo = new PageInfo(comments);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO deleteTieComment(Integer commentId) {
        commentDao.deleteTieComment(commentId);
        return ResultDTO.successOf("删除评论成功");
    }

    public ResultDTO likeComment(Integer commentId) {
        Comment comment = commentDao.selectOneComment(commentId);
        Integer commentLikes = comment.getCommentLikes() + 1;
        commentDao.updateCommentLikes(commentId,commentLikes);
        return ResultDTO.successOf("点赞成功");
    }

    public ResultDTO notLikeComment(Integer commentId) {
        Comment comment = commentDao.selectOneComment(commentId);
        Integer commentLikes = comment.getCommentLikes() - 1;
        commentDao.updateCommentLikes(commentId,commentLikes);
        return ResultDTO.successOf("取消点赞成功");
    }

    public Comment selectCommentByReplyCommentId(Integer replyCommentId) {
        Comment comment = commentDao.selectCommentByReplyCommentId(replyCommentId);
        return comment;
    }

    public Comment selectByComplaintId(Integer complaintId) {
        Comment comment = commentDao.selectOneComment(complaintId);
        return comment;
    }

}
