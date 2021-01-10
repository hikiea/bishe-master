package com.lzy.bishe.modules.repair.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.modules.repair.mapper.RepairDao;
import com.lzy.bishe.modules.repair.model.entry.Repair;
import com.lzy.bishe.modules.repair.service.OkRepairServer;
import com.lzy.bishe.modules.repair.service.RepairService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/worker")
@Api(tags = {"OkRepairController"}, description = "完成维修相关接口")
public class OkRepairController {

    @Autowired
    private OkRepairServer okRepairServer;

    @UserLoginToken
    @CrossOrigin
    @GetMapping("/not/repair")
    @ApiOperation(value = "查询未修理的报修", notes = "查询未修理的报修")
    public ResultDTO doSelectNoRepair(HttpServletRequest httpServletRequest,
                                      @RequestParam(name = "page",defaultValue = "1") Integer page,
                                      @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = okRepairServer.doSelectNoRepair(page, size,httpServletRequest);
        return resultDTO;
    }

    @ApiOperation(value = "接单去维修", notes = "接单去维修")
    @UserLoginToken @CrossOrigin
    @PostMapping("/repair/{repairId}")
    public ResultDTO acceptRepair(@PathVariable Integer repairId,
                                  HttpServletRequest httpServletRequest){
        Repair repair = new Repair();
        repair.setOkRepairUserId(JWTInfo.getUserId_int(httpServletRequest));
        repair.setRepairStatus("等待维修");
        repair.setOkRepairTime(LocalDateTime.now().plusHours(8));
        repair.setRepairId(repairId);
        ResultDTO resultDTO = okRepairServer.doAcceptRepair(repair, httpServletRequest);
        return resultDTO;
    }

    @ApiOperation(value = "查询已接维护", notes = "查询已接维护")
    @UserLoginToken @CrossOrigin
    @GetMapping("/yes/repair")
    public ResultDTO doSelectAcceptRepair(HttpServletRequest httpServletRequest,
                                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                                          @RequestParam(name = "size",defaultValue = "5") Integer size){
        Integer okRepairUserId = JWTInfo.getUserId_int(httpServletRequest);
        ResultDTO resultDTO = okRepairServer.doSelectAcceptRepair(okRepairUserId, page, size);
        return resultDTO;
    }
}
