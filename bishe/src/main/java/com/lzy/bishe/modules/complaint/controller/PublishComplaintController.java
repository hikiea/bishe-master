package com.lzy.bishe.modules.complaint.controller;


import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.complaint.model.entry.PublishComplaint;
import com.lzy.bishe.modules.complaint.service.PublishComplaintService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/complaint/")
public class PublishComplaintController {

    @Autowired
    private PublishComplaintService publishComplaintService;

    /* 发表投诉 */
    @PostMapping("/publish")
    @UserLoginToken
    @CrossOrigin
    public ResultDTO doPublishRealNameComplaint(@RequestBody PublishComplaint publishComplaint){
        ResultDTO resultDTO = publishComplaintService.publishComplaint(publishComplaint);
        return resultDTO;
    }

    /* 查看我发表的投诉 */
    @GetMapping("/queryByMe")
    @UserLoginToken @CrossOrigin
    public ResultDTO doSelectComplaint(HttpServletRequest httpServletRequest,
                                       @RequestParam(name = "page",defaultValue = "1") Integer page,
                                       @RequestParam(name = "size",defaultValue = "5") Integer size
                                       ){
        Integer userId = JWTInfo.getUserIdINT(httpServletRequest);
        ResultDTO resultDTO = publishComplaintService.selectMyComplaint(userId, page, size);
        return resultDTO;
    }

    /* 查看小区所有投诉 */
    @GetMapping("/queryByCommunityId/{communityId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doSelectCommunityComplaint(@PathVariable("communityId") Integer communityId,
                                                @RequestParam(name = "page",defaultValue = "1") Integer page,
                                                @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = publishComplaintService.selectCommunityComplaint(communityId, page, size);
        return resultDTO;
    }

    /* 修改投诉建议 */
    @PostMapping("/update/{complaintId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doUpdateMyComplaint(@PathVariable("complaintId") Integer complaintId,
                                         @RequestBody PublishComplaint publishComplaint){
        ResultDTO resultDTO = publishComplaintService.updateMyComplaint(complaintId, publishComplaint);
        return resultDTO;
    }

    /* 删除投诉建议 */
    @PostMapping("/delete/{complaintId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doDeleteMyComplaint(@PathVariable("complaintId") Integer complaintId){
        ResultDTO resultDTO = publishComplaintService.deleteMyComplaint(complaintId);
        return resultDTO;
    }
}
