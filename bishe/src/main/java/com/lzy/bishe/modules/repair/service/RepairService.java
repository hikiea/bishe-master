package com.lzy.bishe.modules.repair.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.repair.mapper.RepairDao;
import com.lzy.bishe.modules.repair.model.entry.Repair;
import com.lzy.bishe.modules.repair.model.entry.V_ReplaceUser;
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
public class RepairService {

    @Autowired
    private RepairDao repairDao;

    public ResultDTO publishRepair(Repair repair, HttpServletRequest httpServletRequest){
        repair.setRepairUserId(JWTInfo.getUserId_int(httpServletRequest));
        repair.setRepairTime(LocalDateTime.now().plusHours(8));
        repair.setRepairStatus("等待维修人员联系");
        repairDao.publishRepair(repair);
        return ResultDTO.successOf("发表成功",repair);
    }

    public ResultDTO deleteRepair(Integer repairId) {
        repairDao.deleteRepair(repairId);
        return ResultDTO.successOf("已删除",repairId);
    }

    public ResultDTO repairFinished(Integer repairId) {
        String finish = "完成维修";
        repairDao.updateRepairStatus(repairId,finish);
        return ResultDTO.successOf("已完成维修",repairId);
    }

    public ResultDTO doSelectMyRepair(Integer repairUserId, Integer page, Integer num) {
        PageHelper.startPage(page,num);
        List<V_ReplaceUser> v_replaceUsers = repairDao.doSelectMyRepair(repairUserId);
        PageInfo pageInfo = new PageInfo(v_replaceUsers);
        return ResultDTO.successOf("查询成功",pageInfo);
    }

    public boolean findRepairByUserId(Integer userId) {
        V_ReplaceUser repairUserId = repairDao.findRepairUserId(userId);
        if (repairUserId.getRepairUserId() != userId){
            return false;
        }
        return true;
    }

    public boolean findRepairByRepairId(Integer repairId, Integer userId) {
        V_ReplaceUser repair = repairDao.findRepairByRepairId(repairId);
        if ((repair.getRepairUserId()).equals(userId)){
            return true;
        }else {
            return false;
        }
    }

    public V_ReplaceUser findByUserId(Integer userId) {
        V_ReplaceUser repair = repairDao.findRepairByRepairId(userId);
        return repair;
    }

    public ResultDTO number(HttpServletRequest httpServletRequest) {
        Integer id = JWTInfo.getUserId_int(httpServletRequest);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("noRepair",repairDao.noRepair());
        jsonObject.put("noFinish",repairDao.noFinish(id));
        jsonObject.put("finish",repairDao.finish(id));
        return ResultDTO.successOf("维修数据",jsonObject);
    }
}
