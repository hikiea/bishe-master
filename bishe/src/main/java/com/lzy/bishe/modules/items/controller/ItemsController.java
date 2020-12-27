package com.lzy.bishe.modules.items.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.email.model.entry.Email;
import com.lzy.bishe.modules.email.service.EmailService;
import com.lzy.bishe.modules.items.model.entry.Items;
import com.lzy.bishe.modules.items.service.ItemsService;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Lzy
 */
@RequestMapping("/api/items")
@RestController
@Api(tags = {"ItemsController"}, description = "物品管理接口")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @ApiOperation(value = "增加物品", notes = "增加物品")
    @PostMapping("/addItem")
    @UserLoginToken @CrossOrigin
    public ResultDTO addItem(@RequestBody Items items,
                             HttpServletRequest httpServletRequest){
        return itemsService.addItem(items,httpServletRequest);
    }

    @ApiOperation(value = "修改物品", notes = "修改物品")
    @PostMapping("/updateItem")
    @UserLoginToken @CrossOrigin
    public ResultDTO updateItem(@RequestBody Items items,
                             HttpServletRequest httpServletRequest){
        return itemsService.updateItem(items,httpServletRequest);
    }

    @ApiOperation(value = "删除物品", notes = "删除物品")
    @PostMapping("/deleteItem/{id}")
    @UserLoginToken @CrossOrigin
    public ResultDTO deleteItem(@PathVariable("id") Integer id,
                                HttpServletRequest httpServletRequest){
        return itemsService.deleteItem(id,httpServletRequest);
    }

    @ApiOperation(value = "查看家里已经购买的物品", notes = "查看家里已经购买的物品")
    @GetMapping("/queryBuy")
    @UserLoginToken @CrossOrigin
    public ResultDTO queryBuy(HttpServletRequest httpServletRequest){
        return itemsService.queryBuy(httpServletRequest);
    }

    @ApiOperation(value = "查看未购买的物品", notes = "查看家里已经购买的物品")
    @GetMapping("/queryNoBuy")
    @UserLoginToken @CrossOrigin
    public ResultDTO queryNoBuy(HttpServletRequest httpServletRequest){
        return itemsService.queryNoBuy(httpServletRequest);
    }

    @ApiOperation(value = "通过名字模糊查询", notes = "通过名字模糊查询")
    @GetMapping("/queryByName")
    @UserLoginToken @CrossOrigin
    public ResultDTO queryByName(HttpServletRequest httpServletRequest,
                                 @RequestParam(name = "itemName") String itemName){
        return itemsService.queryByName(httpServletRequest,itemName);
    }


}
