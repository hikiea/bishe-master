package com.lzy.bishe.modules.comment.service;

import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.comment.mapper.CommentDao;
import com.lzy.bishe.modules.comment.model.entry.Comment;
import com.lzy.bishe.modules.comment.model.entry.SecondComment;
import com.lzy.bishe.modules.comment.model.entry.V_CommentUser;
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
        List<V_CommentUser> comments = commentDao.selectTeiComment(tieId);
        PageInfo pageInfo = new PageInfo(comments);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO deleteTieComment(Integer commentId) {
        commentDao.deleteTieComment(commentId);
        return ResultDTO.successOf("删除评论成功");
    }

    public ResultDTO likeComment(Integer commentId) {
        V_CommentUser comment = commentDao.selectOneComment(commentId);
        Integer commentLikes = comment.getCommentLikes() + 1;
        commentDao.updateCommentLikes(commentId,commentLikes);
        return ResultDTO.successOf("点赞成功");
    }

    public ResultDTO notLikeComment(Integer commentId) {
        V_CommentUser comment = commentDao.selectOneComment(commentId);
        Integer commentLikes = comment.getCommentLikes() - 1;
        commentDao.updateCommentLikes(commentId,commentLikes);
        return ResultDTO.successOf("取消点赞成功");
    }

    public V_CommentUser selectCommentByReplyCommentId(Integer replyCommentId) {
        V_CommentUser comment = commentDao.selectCommentByReplyCommentId(replyCommentId);
        return comment;
    }

    public V_CommentUser selectByComplaintId(Integer complaintId) {
        V_CommentUser comment = commentDao.selectOneComment(complaintId);
        return comment;
    }

    public ResultDTO doPublishSecondComment(SecondComment secondComment) {
        /*获取当前时间*/
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        secondComment.setCommentTime(nowTime);
        /* 放入二级评论标志 */
        secondComment.setCommentTypes(1);
        commentDao.publishSecondComment(secondComment);
        return ResultDTO.successOf("发送成功",secondComment);
    }

    public ResultDTO doSelectSecondComment(Integer replyCommentId) {
        int commentTypes = 1;
        List<V_CommentUser> secondComments = commentDao.doSelectSecondComment(replyCommentId, commentTypes);
        return ResultDTO.successOf("获取二级评论成功",secondComments);
    }

}
