package com.lzy.bishe.modules.repair.controller;


import com.lzy.bishe.modules.repair.model.entry.Repair;
import com.lzy.bishe.modules.repair.service.RepairService;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    /* 用户报修发布 */
    @PostMapping("/repair")
    public ResultDTO doPublishRepair(@RequestBody Repair repair){
        ResultDTO resultDTO = repairService.publishRepair(repair);
        return resultDTO;
    }

    /* 用户报修删除 */
    @DeleteMapping("/repair/{repairId}")
    public ResultDTO doDeleteRepair(@PathVariable("repairId") Integer repairId){
        ResultDTO resultDTO = repairService.deleteRepair(repairId);
        return resultDTO;
    }

    /* 完成维修后用户修改状态 */
    @PutMapping("/repair/ok/{repairId}")
    public ResultDTO repairFinish(@PathVariable("repairId") Integer repairId){
        ResultDTO resultDTO = repairService.repairFinished(repairId);
        return resultDTO;
    }

    /* 查看自己所有的报修记录 */
    @GetMapping("/repair/{repairUserId}")
    public ResultDTO selectMyRepair(@PathVariable("repairUserId") Integer repairUserId,
                                    @RequestParam(name = "page",defaultValue = "1") Integer page,
                                    @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = repairService.doSelectMyRepair(repairUserId, page, size);
        return resultDTO;
    }

}
