package com.lzy.bishe.modules.comment.service;

import com.lzy.bishe.modules.comment.mapper.CommentDao;
import com.lzy.bishe.modules.comment.model.entry.SecondComment;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SecondCommentService {

    @Autowired
    private CommentDao commentDao;

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
        List<SecondComment> secondComments = commentDao.doSelectSecondComment(replyCommentId, commentTypes);
        return ResultDTO.successOf("获取二级评论成功",secondComments);
    }
}
