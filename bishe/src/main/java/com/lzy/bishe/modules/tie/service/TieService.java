package com.lzy.bishe.modules.tie.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.common.TieStatusCode;
import com.lzy.bishe.modules.tie.mapper.TieDao;
import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.tie.model.entry.V_TieUser;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TieService {

    @Autowired
    private TieDao tieDao;

    /* 发帖操作 */
    public ResultDTO publish(Tie tie){
        /*获取当前时间*/
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        tie.setPublishTime(nowTime);
        tie.setTieStatus(TieStatusCode.NORMAL.getMessage());
        tieDao.publish(tie);
        return ResultDTO.successOf("发帖成功");
    }

    /*删帖操作*/
    public ResultDTO delete(Integer tieId){
        tieDao.deleteTie(tieId);
        return ResultDTO.successOf("删帖成功");
    }

    /* 修改操作 */
    public ResultDTO update(Tie tie) {
        tieDao.updateTie(tie);
        return ResultDTO.successOf("修改成功",tie);
    }

    /* 查询全部帖子操作 */
    public ResultDTO selectAllTie(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<V_TieUser> ties = tieDao.selectAllTie();
        PageInfo pageInfo = new PageInfo(ties);
        return ResultDTO.successOf("查询成功",pageInfo);
    }

    /* 查询个人的所有帖子 */
    public ResultDTO selectPersonTie(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<V_TieUser> ties = tieDao.selectPersonTie(userId);
        PageInfo pageInfo = new PageInfo(ties);
        return ResultDTO.successOf("查询成功",pageInfo);
    }

    /* 查询一个帖子 */
    public ResultDTO selectOneTie(Integer tieId) {
        V_TieUser tie = tieDao.selectOneTie(tieId);
        int sum = tie.getBrowse()+1;
        tieDao.rememberBrowse(sum,tieId);
        return ResultDTO.successOf("查询成功",tie);
    }

    /*查询小区的帖子*/
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
