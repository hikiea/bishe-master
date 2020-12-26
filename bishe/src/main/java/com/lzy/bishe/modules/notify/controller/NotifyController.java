package com.lzy.bishe.modules.notify.controller;


import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/notify")
@CrossOrigin
@Slf4j
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @PostMapping("/send")
    @CrossOrigin
    @ApiOperation(value = "发起通知", notes = "发起通知")
    public ResultDTO doNotify(HttpServletRequest httpServletRequest){
        notifyService.send(httpServletRequest,"测试",123);
        return ResultDTO.successOf("通知发送成功");
    }

    @PostMapping("/query")
    @CrossOrigin
    @ApiOperation(value = "查看自己所有的通知", notes = "查看自己所有的通知")
    public ResultDTO query(HttpServletRequest httpServletRequest,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size){
        ResultDTO query = notifyService.query(httpServletRequest, page, size);
        return ResultDTO.successOf("获取通知成功",query);
    }

    @PostMapping("/updateOne/{id}")
    @CrossOrigin
    @ApiOperation(value = "修改通知状态", notes = "修改通知状态")
    public ResultDTO updateOne(@PathVariable("id") Integer id){
        return notifyService.updateOne(id);
    }

    @PostMapping("/updateAll")
    @CrossOrigin
    @ApiOperation(value = "修改通知状态", notes = "修改通知状态")
    public ResultDTO updateAll(HttpServletRequest httpServletRequest){
        ResultDTO query = notifyService.updateAll(httpServletRequest);
        return ResultDTO.successOf("所有消息已经更新已读",query);
    }

    @PostMapping("/delete")
    @CrossOrigin
    @ApiOperation(value = "删除所有通知", notes = "删除所有通知")
    public ResultDTO delete(HttpServletRequest httpServletRequest){
        return notifyService.delete(httpServletRequest);
    }

}
