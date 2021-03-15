package com.lzy.bishe.modules.complaint.service;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.complaint.mapper.ComplaintDao;
import com.lzy.bishe.modules.complaint.model.entry.Complaint;
import com.lzy.bishe.modules.complaint.model.entry.V_ComplaintUser;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PublishComplaintService {

    @Autowired
    private ComplaintDao complaintDao;

    public ResultDTO publishComplaint(String content, HttpServletRequest httpServletRequest){
        Integer userId = JWTInfo.getUserId_int(httpServletRequest);
        Complaint complaint = new Complaint();
        complaint.setUserId(userId);
        complaint.setComplaintContent(content);
        complaint.setComplaintTime(LocalDateTime.now());
        complaint.setStatus("等待管理人员联系");
        complaintDao.publishComplaint(complaint);
        return ResultDTO.successOf("发表投诉建议成功",complaint);
    }

    public ResultDTO selectMyComplaint(Integer respondComplaintUserId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<V_ComplaintUser> complaints = complaintDao.selectMyComplaint(respondComplaintUserId);
        PageInfo pageInfo = new PageInfo(complaints);
        return ResultDTO.successOf("我的投诉建议获取成功",pageInfo);
    }

    public ResultDTO deleteMyComplaint(Integer complaintId) {
        complaintDao.deleteMyComplaint(complaintId);
        return ResultDTO.successOf("删除成功");
    }

    public ResultDTO finish(Integer complaintId) {
        Complaint complaint = new Complaint();
        complaint.setComplaintId(complaintId);
        complaint.setStatus("已跟进");
        complaint.setFinishTime(LocalDateTime.now());
        complaintDao.finish(complaint);
        return ResultDTO.successOf("修改成功");
    }

    public ResultDTO doSelectCommunityComplaint_no(String communityId, Integer page, Integer size, String content) {
        if (content.isEmpty()){
            PageHelper.startPage(page,size);
            List<V_ComplaintUser> complaints = complaintDao.doSelectCommunityComplaint_no(communityId);
            PageInfo pageInfo = new PageInfo(complaints);
            return ResultDTO.successOf("小区投诉建议获取成功",pageInfo);
        }else{
            PageHelper.startPage(page,size);
            List<V_ComplaintUser> complaints = complaintDao.queryByContent_no(communityId,content);
            PageInfo pageInfo = new PageInfo(complaints);
            return ResultDTO.successOf("小区投诉建议获取成功",pageInfo);
        }
    }

    public ResultDTO selectCommunityComplaint(String communityId, Integer page, Integer size, String content) {
        if (content.isEmpty()){
            PageHelper.startPage(page,size);
            List<V_ComplaintUser> complaints = complaintDao.selectCommunityComplaint(communityId);
            PageInfo pageInfo = new PageInfo(complaints);
            return ResultDTO.successOf("小区投诉建议获取成功",pageInfo);
        }else{
            PageHelper.startPage(page,size);
            List<V_ComplaintUser> complaints = complaintDao.queryByContent(communityId,content);
            PageInfo pageInfo = new PageInfo(complaints);
            return ResultDTO.successOf("小区投诉建议获取成功",pageInfo);
        }

    }

    public ResultDTO number(HttpServletRequest httpServletRequest) {
        String id = JWTInfo.getUserCommunityId(httpServletRequest);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("all",complaintDao.all(id));
        jsonObject.put("okAll",complaintDao.okAll(id));
        jsonObject.put("noAll",complaintDao.noAll(id));
        return ResultDTO.successOf("投诉数据",jsonObject);
    }
}
