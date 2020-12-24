package com.lzy.bishe.modules.tie.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.tie.service.TieService;
import com.lzy.bishe.modules.user.service.UserService;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tie")
public class TieController {

    @Autowired
    private TieService tieService;

    @Autowired
    private UserService userService;

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "发帖", notes = "发帖")
    @PostMapping("/ties")
    public ResultDTO doPublish(@RequestBody Tie tie){
        ResultDTO result = tieService.publish(tie);
        return result;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "删帖", notes = "删帖")
    @PostMapping("/delete/{tieId}")
    public ResultDTO deDelete(@PathVariable("tieId") Integer tieId){
        ResultDTO result = tieService.delete(tieId);
        return result;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "改贴", notes = "改贴")
    @PostMapping("/update/{tieId}")
    public ResultDTO doUpdate(@RequestBody Tie tie,
                              @PathVariable("tieId") Integer tieId){
        tie.setTieId(tieId);
        tieService.update(tie);
        return ResultDTO.successOf("修改成功");
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查询所有帖子", notes = "查询所有帖子")
    @GetMapping("/query")
    public ResultDTO deSelectAllTie2(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", defaultValue = "5") Integer size){
        ResultDTO resultDTO = tieService.selectAllTie(page, size);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查看个人所有帖子", notes = "查看个人所有帖子")
    @GetMapping("/queryByUserId/{userId}")
    public ResultDTO doSelectPersonTie(@PathVariable("userId") Integer userId,
                                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                                       @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = tieService.selectPersonTie(userId, page, size);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查询某一个帖子", notes = "查询某一个帖子")
    @GetMapping("/queryById/{tieId}")
    public ResultDTO doSelectOneTie(@PathVariable("tieId") Integer tieId){
        ResultDTO resultDTO = tieService.selectOneTie(tieId);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查询小区所有帖子", notes = "查询小区所有帖子")
    @GetMapping("/community")
    public ResultDTO doSelectCommunityTie(@RequestParam(name = "communityId") Integer communityId,
                                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                                          @RequestParam(name = "size",defaultValue = "5") Integer size){
        ResultDTO resultDTO = tieService.selectCommunityTie(communityId, page, size);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "点赞帖子", notes = "点赞帖子")
    @PostMapping("/like/{tieId}")
    public ResultDTO doLikeTie(@PathVariable("tieId") Integer tieId){
        ResultDTO resultDTO = tieService.likeTie(tieId);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "取消点赞帖子", notes = "取消点赞帖子")
    @PostMapping("/notLike/{tieId}")
    public ResultDTO doNotLikeTie(@PathVariable("tieId") Integer tieId){
        ResultDTO resultDTO = tieService.NotLikeTie(tieId);
        return resultDTO;
    }


}
