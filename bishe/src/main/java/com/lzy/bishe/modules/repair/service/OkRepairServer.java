package com.lzy.bishe.modules.repair.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.modules.repair.mapper.RepairDao;
import com.lzy.bishe.modules.repair.model.entry.Repair;
import com.lzy.bishe.modules.repair.model.entry.V_ReplaceUser;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OkRepairServer {

    @Autowired
    private RepairDao repairDao;

    @Autowired
    private NotifyService notifyService;

    public ResultDTO doSelectNoRepair(Integer page, Integer size, HttpServletRequest httpServletRequest) {
        String id = JWTInfo.getUserCommunityId(httpServletRequest);
        PageHelper.startPage(page,size);
        List<V_ReplaceUser> repairs = repairDao.doSelectNoRepair(id);
        PageInfo pageInfo = new PageInfo(repairs);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO doAcceptRepair(Repair repair, HttpServletRequest httpServletRequest) {
        String data = "您的报修已被接单,请等待工作人员联系";
        V_ReplaceUser repairUser = repairDao.findRepairByRepairId(repair.getRepairId());
        notifyService.send(httpServletRequest,data,repairUser.getRepairUserId(),null);
        repairDao.doAcceptRepair(repair);
        return ResultDTO.successOf("接单成功",repair);
    }

    public ResultDTO doSelectAcceptRepair(Integer okRepairUserId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<V_ReplaceUser> repairs = repairDao.doSelectAcceptRepair(okRepairUserId);
        PageInfo pageInfo = new PageInfo(repairs);
        return ResultDTO.successOf("获取成功",pageInfo);
    }
}
