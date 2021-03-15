package com.lzy.bishe.modules.comment.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.comment.model.entry.Comment;
import com.lzy.bishe.modules.comment.model.entry.SecondComment;
import com.lzy.bishe.modules.comment.model.entry.V_CommentUser;
import com.lzy.bishe.modules.comment.service.CommentService;
import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.modules.tie.mapper.TieDao;
import com.lzy.bishe.modules.tie.model.entry.V_TieUser;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/comment")
@Api(tags = {"CommentController"}, description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private TieDao tieDao;

    @PostMapping("/first/do")
    @UserLoginToken
    @CrossOrigin
    @ApiOperation(value = "发表一级评论", notes = "发表一级评论")
    public ResultDTO doPublishComment(@RequestBody Comment comment,
                                      HttpServletRequest httpServletRequest){
        V_TieUser tie = tieDao.selectOneTie(comment.getTieId());
        notifyService.send(
                httpServletRequest,
                JWTInfo.getUserNickName(httpServletRequest)+" 评论了你：" + comment.getCommentContent(),
                tie.getUserId(),tie.getId());
        ResultDTO resultDTO = commentService.doPublishComment(comment,httpServletRequest);
        return resultDTO;
    }

    @ApiOperation(value = "查看贴子的一级评论", notes = "查看贴子的一级评论")
    @GetMapping("/first/{tieId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO tieComment(@PathVariable("tieId") Integer tieId,
                                @RequestParam(name = "page",defaultValue = "1") Integer page,
                                @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = commentService.selectTeiComment(tieId,page,size);
        return resultDTO;
    }

    @ApiOperation(value = "删除一级评论,二级评论 ", notes = "删除一级评论,二级评论 ")
    @PostMapping("/delete/{commentId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO deDeleteTieComment(@PathVariable("commentId") Integer commentId){
        ResultDTO resultDTO = commentService.deleteTieComment(commentId);
        return resultDTO;
    }

    @ApiOperation(value = "发布二级评论", notes = "发布二级评论")
    @PostMapping("/second")
    @UserLoginToken @CrossOrigin
    public ResultDTO doPublishSecondComment(@RequestBody SecondComment secondComment,
                                            HttpServletRequest httpServletRequest){
        if (secondComment.getCommentContent().equals("")){
            return ResultDTO.errorOf(500,"评论不能为空");
        }
        V_CommentUser info = commentService.selectByComplaintId(secondComment.getReplyCommentId());
        secondComment.setCommentUserId(JWTInfo.getUserId_int(httpServletRequest));
        ResultDTO resultDTO = commentService.doPublishSecondComment(secondComment);
        notifyService.send(
                httpServletRequest,
                JWTInfo.getUserNickName(httpServletRequest)+" 评论了你：" + secondComment.getCommentContent(),
                info.getCommentUserId(),
                info.getTieId());
        return resultDTO;
    }

    @ApiOperation(value = "查询一条评论的二级评论", notes = "查询一条评论的二级评论")
    @GetMapping("/second/{replyCommentId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doSelectSecondComment(@PathVariable("replyCommentId") Integer replyCommentId){
        ResultDTO resultDTO = commentService.doSelectSecondComment(replyCommentId);
        return resultDTO;
    }

    @PostMapping("/like/{commentId}")
    @ApiOperation(value = "点赞一级评论", notes = "点赞一级评论")
    @UserLoginToken @CrossOrigin
    public ResultDTO doLikeComment(@PathVariable("commentId") Integer commentId) {
        ResultDTO resultDTO = commentService.likeComment(commentId);
        return resultDTO;
    }

    @ApiOperation(value = "取消点赞一级评论", notes = "取消点赞一级评论")
    @PostMapping("/notLike/{commentId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doNotLikeComment(@PathVariable("commentId") Integer commentId) {
        ResultDTO resultDTO = commentService.notLikeComment(commentId);
        return resultDTO;
    }
}
