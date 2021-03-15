package com.lzy.bishe.modules.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.community.model.Community;
import com.lzy.bishe.modules.community.service.CommunityService;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/house")
@CrossOrigin
@Slf4j
@Api(tags = {"CommunityController"}, description = "小区相关接口")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/getAllCommunity")
    @CrossOrigin
    @ApiOperation(value = "查询所有小区", notes = "查询所有小区")
    public ResultDTO getAllCommunity(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                     @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = communityService.getAllCommunity(page,size);
        return resultDTO;
    }

    @GetMapping("/deleteCommunityById/{id}")
    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "删除小区", notes = "删除小区")
    public ResultDTO deleteCommunity(@PathVariable("id") Integer id){
        ResultDTO resultDTO = communityService.deleteCommunity(id);
        return resultDTO;
    }

    @PostMapping("/addCommunity")
    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "新增小区", notes = "新增小区")
    public ResultDTO addCommunity(@RequestBody Community community){
        ResultDTO resultDTO = communityService.addCommunity(community);
        return resultDTO;
    }

    @GetMapping("/queryByName")
    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "根据小区名获取小区id", notes = "新增小区")
    public ResultDTO queryByName(@RequestParam String name){
        ResultDTO resultDTO = communityService.queryByName(name);
        return resultDTO;
    }

}
