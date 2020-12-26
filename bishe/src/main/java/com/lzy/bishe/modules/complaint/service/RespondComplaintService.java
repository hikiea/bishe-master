package com.lzy.bishe.modules.complaint.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.complaint.mapper.RespondComplaintDao;
import com.lzy.bishe.modules.complaint.model.entry.Complaint;
import com.lzy.bishe.modules.complaint.model.entry.RespondComplaint;
import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RespondComplaintService {

    @Autowired
    private RespondComplaintDao respondComplaintDao;

    @Autowired
    private NotifyService notifyService;

    public ResultDTO respondComplainByComplaintId(RespondComplaint respondComplaint, Integer complaintId, HttpServletRequest httpServletRequest){
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        respondComplaint.setRespondComplaintTime(nowTime);
        String status = "已跟进";
        respondComplaintDao.updateComplaintStatusByComplaintId(status,complaintId);
        respondComplaint.setComplaintId(complaintId);
        respondComplaintDao.respondComplaintByComplaintId(respondComplaint);
        String data = "您的投诉已被跟进，请等待工作人员联系";
        notifyService.send(httpServletRequest,data,respondComplaint.getRespondComplaintUserId());
        return ResultDTO.successOf("回复投诉成功",respondComplaint);
    }

    public ResultDTO selectMyRespondComplaint(Integer respondComplaintUserId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Complaint> complaints = respondComplaintDao.selectMyRespondComplaint(respondComplaintUserId);
        PageInfo pageInfo = new PageInfo(complaints);
        return ResultDTO.successOf("我的投诉建议获取成功",pageInfo);
    }

    public ResultDTO selectNoRespondComplaint(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Complaint> complaints = respondComplaintDao.selectNoRespondComplaint();
        PageInfo pageInfo = new PageInfo(complaints);
        return ResultDTO.successOf("未处理的投诉建议获取成功",pageInfo);
    }


    public Complaint selectByComplaintId(Integer complaintId) {
        Complaint respondComplaint = respondComplaintDao.selectByComplaintId(complaintId);
        return respondComplaint;
    }
}
