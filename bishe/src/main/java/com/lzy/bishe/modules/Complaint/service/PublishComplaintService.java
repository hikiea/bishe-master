package com.lzy.bishe.modules.Complaint.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.Complaint.mapper.PublishComplaintDao;
import com.lzy.bishe.modules.Complaint.model.entry.Complaint;
import com.lzy.bishe.modules.Complaint.model.entry.PublishComplaint;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PublishComplaintService {

    @Autowired
    private PublishComplaintDao publishComplaintDao;

    public ResultDTO publishComplaint(PublishComplaint publishComplaint){
        Date now = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
        publishComplaint.setComplaintTime(nowTime);
        publishComplaint.setComplaintStatus("等待管理人员联系");
        publishComplaintDao.publishComplaint(publishComplaint);
        return ResultDTO.successOf("发表投诉建议成功",publishComplaint);

    }

    public ResultDTO selectMyComplaint(Integer respondComplaintUserId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Complaint> complaints = publishComplaintDao.selectMyComplaint(respondComplaintUserId);
        PageInfo pageInfo = new PageInfo(complaints);
        return ResultDTO.successOf("我的投诉建议获取成功",pageInfo);
    }

    public ResultDTO selectCommunityComplaint(Integer communityId,Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Complaint> complaints = publishComplaintDao.selectCommunityComplaint(communityId);
        PageInfo pageInfo = new PageInfo(complaints);
        return ResultDTO.successOf("小区投诉建议获取成功",pageInfo);
    }

    public ResultDTO updateMyComplaint(Integer complaintId, PublishComplaint publishComplaint) {
        publishComplaint.setComplaintId(complaintId);
        publishComplaintDao.updateMyComplaint(publishComplaint);
        return ResultDTO.successOf("投诉建议修改完成",publishComplaint.getComplaintContent());
    }

    public ResultDTO deleteMyComplaint(Integer complaintId) {
        publishComplaintDao.deleteMyComplaint(complaintId);
        return ResultDTO.successOf("删除成功");
    }
}
