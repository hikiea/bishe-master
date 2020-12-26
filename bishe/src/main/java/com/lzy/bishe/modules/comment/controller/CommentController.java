package com.lzy.bishe.modules.comment.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.comment.model.entry.Comment;
import com.lzy.bishe.modules.comment.model.entry.SecondComment;
import com.lzy.bishe.modules.comment.service.CommentService;
import com.lzy.bishe.modules.comment.service.SecondCommentService;
import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.tie.service.TieService;
import com.lzy.bishe.modules.user.service.UserService;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private SecondCommentService secondCommentService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private TieService tieService;

    @Autowired
    private UserService userService;

    /* 发表一级评论 */
    @PostMapping("/first/comment")
    @UserLoginToken
    @CrossOrigin
    public ResultDTO doPublishComment(@RequestBody Comment comment,
                                      HttpServletRequest httpServletRequest){
        Tie tie = tieService.selectByTieId(comment.getTieId());
        notifyService.send(
                httpServletRequest,
                comment.getCommentUsername()+" 评论了你：" + comment.getCommentContent(),
                tie.getUserId());
        ResultDTO resultDTO = commentService.doPublishComment(comment);
        return resultDTO;
    }

    /* 查看一级评论 */
    @GetMapping("/first/comment/{tieId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO tieComment(@PathVariable("tieId") Integer tieId){
        ResultDTO resultDTO = commentService.selectTeiComment(tieId);
        return resultDTO;
    }

    /* 删除一级评论,二级评论 */
    @DeleteMapping("/comment/{commentId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO deDeleteTieComment(@PathVariable("commentId") Integer commentId){
        ResultDTO resultDTO = commentService.deleteTieComment(commentId);
        return resultDTO;
    }

    /*发布二级评论*/
    @PostMapping("/second/comment")
    @UserLoginToken @CrossOrigin
    public ResultDTO doPublishSecondComment(@RequestBody SecondComment secondComment,
                                            HttpServletRequest httpServletRequest){
        Comment info = commentService.selectByComplaintId(secondComment.getCommentId());
        notifyService.send(
                httpServletRequest,
                secondComment.getCommentUsername()+" 评论了你：" + secondComment.getCommentContent(),
                info.getCommentId());
        ResultDTO resultDTO = secondCommentService.doPublishSecondComment(secondComment);
        return resultDTO;
    }

    /* 查询一条评论的二级评论 */
    @GetMapping("/second/comment/{replyCommentId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doSelectSecondComment(@PathVariable("replyCommentId") Integer replyCommentId){
        ResultDTO resultDTO = secondCommentService.doSelectSecondComment(replyCommentId);
        return resultDTO;
    }

    /* 点赞一级评论 */
    @PutMapping("/like/comment/{commentId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doLikeComment(@PathVariable("commentId") Integer commentId) {
        ResultDTO resultDTO = commentService.likeComment(commentId);
        return resultDTO;
    }

    /* 取消点赞一级评论 */
    @PutMapping("/notLike/comment/{commentId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doNotLikeComment(@PathVariable("commentId") Integer commentId) {
        ResultDTO resultDTO = commentService.notLikeComment(commentId);
        return resultDTO;
    }
}
