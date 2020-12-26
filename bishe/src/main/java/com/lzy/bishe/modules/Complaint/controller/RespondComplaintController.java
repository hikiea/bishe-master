package com.lzy.bishe.modules.Complaint.controller;

import com.lzy.bishe.modules.Complaint.model.entry.RespondComplaint;
import com.lzy.bishe.modules.Complaint.service.RespondComplaintService;
import com.lzy.bishe.modules.tie.mapper.TieDao;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class RespondComplaintController {

    @Autowired
    private RespondComplaintService respondComplaintService;

/*
    @Autowired
    private NotificationService notificationService;*/

    @Autowired
    private TieDao tieDao;

    /* 跟进投诉建议 */
    @PutMapping("/complaint/{complaintId}")
    public ResultDTO doRespondComplaintByComplaintId(@PathVariable("complaintId") Integer complaintId,
                                                     @RequestBody RespondComplaint respondComplaint){

/*        Complaint result = respondComplaintService.selectByComplaintId(complaintId);
        notificationService.sendNotification(
                userId,
                result.getUserId(),
                COMMENT_NOTICE,
                username + "已经收到您的投诉建议，请等待联系");*/

        ResultDTO resultDTO = respondComplaintService.respondComplainByComplaintId(respondComplaint,complaintId);
        return resultDTO;
    }

    /* 查询自己跟进过的投诉建议 */
    @GetMapping("/complaint/{respondComplaintUserId}")
    public ResultDTO doSelectMyRespondComplaint(@PathVariable("respondComplaintUserId") Integer respondComplaintUserId,
                                                @RequestParam(name = "page",defaultValue = "1") Integer page,
                                                @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = respondComplaintService.selectMyRespondComplaint(respondComplaintUserId, page, size);
        return resultDTO;
    }

    /* 查询还未跟进的投诉建议 */
    @GetMapping("/not/complaint")
    public ResultDTO doSelectNoRespondComplaint( @RequestParam(name = "page",defaultValue = "1") Integer page,
                                                 @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = respondComplaintService.selectNoRespondComplaint(page,size);
        return resultDTO;
    }
}
