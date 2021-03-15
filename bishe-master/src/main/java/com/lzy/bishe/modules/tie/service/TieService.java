package com.lzy.bishe.modules.tie.service;

import com.auth0.jwt.JWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.common.TieStatusCode;
import com.lzy.bishe.modules.tie.mapper.TieDao;
import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.tie.model.entry.V_TieUser;
import com.lzy.bishe.modules.user.mapper.UserMapper;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TieService {

    @Autowired
    private TieDao tieDao;

    @Autowired
    private UserMapper userMapper;

    public ResultDTO publish(Tie tie, HttpServletRequest httpServletRequest){
        tie.setPublishTime(LocalDateTime.now());
        tie.setUserId(JWTInfo.getUserId_int(httpServletRequest));
        User user = userMapper.findUserById(JWTInfo.getUserId_int(httpServletRequest));
        if (user.getPublishStatus() == 1){
            return ResultDTO.errorOf(500,"您已被禁言，不能发贴");
        }else{
            tieDao.publish(tie);
            return ResultDTO.successOf("发贴成功");
        }
    }

    public ResultDTO delete(Integer tieId){
        tieDao.deleteTie(tieId);
        return ResultDTO.successOf("删贴成功");
    }

    public ResultDTO update(Tie tie) {
        tieDao.updateTie(tie);
        return ResultDTO.successOf("修改成功",tie);
    }

    public ResultDTO selectAllTie(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<V_TieUser> ties = tieDao.selectAllTie();
        PageInfo pageInfo = new PageInfo(ties);
        return ResultDTO.successOf("查询成功",pageInfo);
    }

    public ResultDTO selectPersonTie(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<V_TieUser> ties = tieDao.selectPersonTie(userId);
        PageInfo pageInfo = new PageInfo(ties);
        return ResultDTO.successOf("查询成功",pageInfo);
    }

    public ResultDTO selectOneTie(Integer tieId) {
        V_TieUser tie = tieDao.selectOneTie(tieId);
        int sum = tie.getBrowse()+1;
        tieDao.rememberBrowse(sum,tieId);
        V_TieUser tie2 = tieDao.selectOneTie(tieId);
        return ResultDTO.successOf("查询成功",tie2);
    }

    public ResultDTO selectCommunityTie(String communityId, Integer page, Integer size, String status) {
        PageHelper.startPage(page,size);
        List<V_TieUser> ties = tieDao.selectCommunityTie(communityId,status);
        PageInfo pageInfo = new PageInfo(ties);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO likeTie(Integer tieId) {
        V_TieUser tie = tieDao.selectOneTie(tieId);
        Integer likes = tie.getLikes() + 1;
        tieDao.likeTie(likes,tieId);
        return ResultDTO.successOf("点赞成功");
    }

    public ResultDTO NotLikeTie(Integer tieId) {
        V_TieUser tie = tieDao.selectOneTie(tieId);
        Integer likes = tie.getLikes() - 1;
        tieDao.likeTie(likes,tieId);
        return ResultDTO.successOf("取消点赞成功");
    }

    public boolean findTieByTieId(Integer tieId,Integer userId) {
        V_TieUser tie = tieDao.selectOneTie(tieId);
        System.out.println(tie);
        System.out.println(userId);
        if ((tie.getUserId().equals(userId))){
            return true;
        }else{
            return false;
        }
    }


}
