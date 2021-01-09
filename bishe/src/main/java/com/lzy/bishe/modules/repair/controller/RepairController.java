package com.lzy.bishe.modules.repair.controller;


import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.repair.model.entry.Repair;
import com.lzy.bishe.modules.repair.service.RepairService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/repair")
@CrossOrigin
@Slf4j
@Api(tags = {"RepairController"}, description = "报修相关接口")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @UserLoginToken @CrossOrigin
    @PostMapping("/repair")
    @ApiOperation(value = "用户报修发布", notes = "用户报修发布")
    public ResultDTO doPublishRepair(@RequestBody Repair repair){
        ResultDTO resultDTO = repairService.publishRepair(repair);
        return resultDTO;
    }

    @UserLoginToken @CrossOrigin
    @GetMapping("/repair/{repairId}")
    @ApiOperation(value = "用户报修删除", notes = "用户报修删除")
    public ResultDTO doDeleteRepair(@PathVariable("repairId") Integer repairId){
        ResultDTO resultDTO = repairService.deleteRepair(repairId);
        return resultDTO;
    }

    @UserLoginToken @CrossOrigin
    @PostMapping("/repair/ok/{repairId}")
    @ApiOperation(value = "完成维修后用户修改状态", notes = "完成维修后用户修改状态")
    public ResultDTO repairFinish(@PathVariable("repairId") Integer repairId){
        ResultDTO resultDTO = repairService.repairFinished(repairId);
        return resultDTO;
    }

    @UserLoginToken @CrossOrigin
    @ApiOperation(value = "查看自己所有的报修记录", notes = "查看自己所有的报修记录")
    @GetMapping("/repair")
    public ResultDTO selectMyRepair(HttpServletRequest httpServletRequest,
                                    @RequestParam(name = "page",defaultValue = "1") Integer page,
                                    @RequestParam(name = "size",defaultValue = "5") Integer size){
        Integer repairUserId = JWTInfo.getUserId_int(httpServletRequest);
        ResultDTO resultDTO = repairService.doSelectMyRepair(repairUserId, page, size);
        return resultDTO;
    }

    @ApiOperation(value = "维修数据", notes = "维修数据")
    @GetMapping("/number")
    @UserLoginToken @CrossOrigin
    public ResultDTO number(HttpServletRequest httpServletRequest){
        ResultDTO resultDTO = repairService.number(httpServletRequest);
        return resultDTO;
    }

}
