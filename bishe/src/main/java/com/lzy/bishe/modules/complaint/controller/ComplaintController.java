package com.lzy.bishe.modules.complaint.controller;


import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.complaint.model.entry.Complaint;
import com.lzy.bishe.modules.complaint.service.PublishComplaintService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Lzy
 */
@Api(tags = {"ComplaintController"}, description = "投诉建议相关接口")
@RestController
@RequestMapping("/api/complaint/")
public class ComplaintController {

    @Autowired
    private PublishComplaintService publishComplaintService;

    @ApiOperation(value = "发表投诉", notes = "发表投诉")
    @PostMapping("/publish")
    @UserLoginToken
    @CrossOrigin
    public ResultDTO doPublishRealNameComplaint(@RequestBody Complaint publishComplaint){
        ResultDTO resultDTO = publishComplaintService.publishComplaint(publishComplaint);
        return resultDTO;
    }

    @ApiOperation(value = "查看我发表的投诉", notes = "查看我发表的投诉")
    @GetMapping("/queryByMe")
    @UserLoginToken @CrossOrigin
    public ResultDTO doSelectComplaint(HttpServletRequest httpServletRequest,
                                       @RequestParam(name = "page",defaultValue = "1") Integer page,
                                       @RequestParam(name = "size",defaultValue = "5") Integer size){
        Integer userId = JWTInfo.getUserId_int(httpServletRequest);
        ResultDTO resultDTO = publishComplaintService.selectMyComplaint(userId, page, size);
        return resultDTO;
    }

    @ApiOperation(value = "查看小区所有投诉", notes = "查看小区所有投诉")
    @GetMapping("/queryByCommunityId")
    @UserLoginToken @CrossOrigin
    public ResultDTO doSelectCommunityComplaint(HttpServletRequest httpServletRequest,
                                                @RequestParam String content,
                                                @RequestParam(name = "page",defaultValue = "1") Integer page,
                                                @RequestParam(name = "size",defaultValue = "5") Integer size){
        String communityId = JWTInfo.getUserCommunityId(httpServletRequest);
        ResultDTO resultDTO = publishComplaintService.selectCommunityComplaint(communityId, page, size,content);
        return resultDTO;
    }

    @ApiOperation(value = "查看小区未完成投诉", notes = "查看小区未完成投诉")
    @GetMapping("/queryByCommunityId_no")
    @UserLoginToken @CrossOrigin
    public ResultDTO doSelectCommunityComplaint_no(HttpServletRequest httpServletRequest,
                                                   @RequestParam String content,
                                                   @RequestParam(name = "page",defaultValue = "1") Integer page,
                                                   @RequestParam(name = "size",defaultValue = "5") Integer size){
        String communityId = JWTInfo.getUserCommunityId(httpServletRequest);
        ResultDTO resultDTO = publishComplaintService.doSelectCommunityComplaint_no(communityId, page, size,content);
        return resultDTO;
    }

    @ApiOperation(value = "修改投诉建议", notes = "修改投诉建议")
    @PostMapping("/update/{complaintId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doUpdateMyComplaint(@PathVariable("complaintId") Integer complaintId,
                                         @RequestBody Complaint publishComplaint){
        ResultDTO resultDTO = publishComplaintService.updateMyComplaint(complaintId, publishComplaint);
        return resultDTO;
    }

    @ApiOperation(value = "删除投诉建议", notes = "删除投诉建议")
    @PostMapping("/delete/{complaintId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO doDeleteMyComplaint(@PathVariable("complaintId") Integer complaintId){
        ResultDTO resultDTO = publishComplaintService.deleteMyComplaint(complaintId);
        return resultDTO;
    }

    @ApiOperation(value = "投诉已跟进", notes = "投诉已跟进")
    @PostMapping("/finish/{complaintId}")
    @UserLoginToken @CrossOrigin
    public ResultDTO finish(@PathVariable("complaintId") Integer complaintId){
        ResultDTO resultDTO = publishComplaintService.finish(complaintId);
        return resultDTO;
    }

    @ApiOperation(value = "已解决，未解决，总数量", notes = "已解决，未解决，总数量")
    @GetMapping("/number")
    @UserLoginToken @CrossOrigin
    public ResultDTO number(HttpServletRequest httpServletRequest){
        ResultDTO resultDTO = publishComplaintService.number(httpServletRequest);
        return resultDTO;
    }

}
