package com.lzy.bishe.modules.repair.controller;

import com.lzy.bishe.modules.notify.service.NotifyService;
import com.lzy.bishe.modules.repair.model.entry.Repair;
import com.lzy.bishe.modules.repair.service.OkRepairServer;
import com.lzy.bishe.modules.repair.service.RepairService;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/api/worker")
public class OkRepairController {

    @Autowired
    private OkRepairServer okRepairServer;

    @Autowired
    private RepairService repairService;

    @Autowired
    private NotifyService notifyService;

    /* 查询未修理的报修 */
    @GetMapping("/not/repair")
    public ResultDTO doSelectNoRepair(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                      @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = okRepairServer.doSelectNoRepair(page, size);
        return resultDTO;
    }

    /* 接单，去维修 */
    @PostMapping("/repair/{repairId}")
    public ResultDTO acceptRepair(@PathVariable("repairId") Integer repairId,
                                  @RequestBody Repair repair,
                                  HttpServletRequest httpServletRequest){
        /*获取当前时间*/
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        repair.setRepairStatus("等待师傅上门维修");
        repair.setOkRepairTime(nowTime);
        repair.setRepairId(repairId);
        ResultDTO resultDTO = okRepairServer.doAcceptRepair(repair, httpServletRequest);
        return resultDTO;
    }

    /* 查询已接维护 */
    @GetMapping("/yes/repair/{okRepairUserId}")
    public ResultDTO doSelectAcceptRepair(@PathVariable("okRepairUserId") Integer okRepairUserId,
                                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                                          @RequestParam(name = "size",defaultValue = "5") Integer size){

        ResultDTO resultDTO = okRepairServer.doSelectAcceptRepair(okRepairUserId, page, size);
        return resultDTO;
    }
}
