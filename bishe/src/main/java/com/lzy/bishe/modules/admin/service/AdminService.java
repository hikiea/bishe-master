package com.lzy.bishe.modules.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.admin.mapper.AdminMapper;
import com.lzy.bishe.modules.comment.mapper.CommentDao;
import com.lzy.bishe.modules.comment.model.entry.Comment;
import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.modules.tie.mapper.TieDao;
import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.tie.service.TieService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import com.lzy.bishe.modules.user.mapper.UserMapper;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author lizhongyi
 */
@Service
@Slf4j
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TieService tieService;

    @Autowired
    private TieDao tieDao;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private CommentDao commentDao;

    public ResultDTO adminLogin(UserLoginDTO userLoginInfo) {
        User user = userMapper.checkEqualUsername(userLoginInfo.getUsername());
        if (user == null){
            return ResultDTO.errorOf(500,"用户不存在");
        }else if(user.getPower().equals("admin") && user != null ){
            return userService.checkLogin(userLoginInfo);
        }else{
            return ResultDTO.errorOf(403,"权限不足");
        }
    }

    public ResultDTO getAllUserInfo(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<User> allUserInfo = adminMapper.getAllUserInfo();
        PageInfo pageInfo = new PageInfo(allUserInfo);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO updateUserStatus(Integer id,Integer status) {
        adminMapper.updateUserStatus(id, status);
        return ResultDTO.successOf("修改状态成功");
    }

    public ResultDTO deleteUser(Integer id) {
        adminMapper.deleteUser(id);
        return ResultDTO.successOf("删除用户成功");
    }

    public ResultDTO deleteTie(Integer tieId, HttpServletRequest httpServletRequest) {
        String userName = JWTInfo.getUserName(httpServletRequest);
        tieService.delete(tieId);
        Tie tie = tieDao.selectOneTie(tieId);
        String data = "您的帖子已被管理员："+ userName + "删除";
        notifyService.send(httpServletRequest,data,tie.getUserId());
        return ResultDTO.successOf("帖子已删除成功，并通知当事人");
    }

    public ResultDTO deleteComment(Integer id, HttpServletRequest httpServletRequest) {
        String userName = JWTInfo.getUserName(httpServletRequest);
        commentDao.deleteTieComment(id);
        Comment comment = commentDao.selectOneComment(id);
        String data = "您的回复已被管理员："+ userName + "删除";
        notifyService.send(httpServletRequest,data,comment.getCommentUserId());
        return ResultDTO.successOf("评论已删除成功，并通知当事人");
    }
}
