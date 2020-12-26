package com.lzy.bishe.modules.complaint.controller;

import com.lzy.bishe.modules.complaint.model.entry.RespondComplaint;
import com.lzy.bishe.modules.complaint.service.RespondComplaintService;
import com.lzy.bishe.modules.notify.model.entry.Notify;
import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.modules.tie.mapper.TieDao;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/admin")
public class RespondComplaintController {

    @Autowired
    private RespondComplaintService respondComplaintService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private TieDao tieDao;

    /* 跟进投诉建议 */
    @PostMapping("/complaint/{complaintId}")
    public ResultDTO doRespondComplaintByComplaintId(@PathVariable("complaintId") Integer complaintId,
                                                     @RequestBody RespondComplaint respondComplaint,
                                                     HttpServletRequest httpServletRequest){
        ResultDTO resultDTO = respondComplaintService.respondComplainByComplaintId(respondComplaint,complaintId,httpServletRequest);
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
