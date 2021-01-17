package com.lzy.bishe.modules.tie.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.tie.service.TieService;
import com.lzy.bishe.modules.user.service.UserService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/tie")
@Api(tags = {"TieController"}, description = "贴子相关接口")
public class TieController {

    @Autowired
    private TieService tieService;

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "发贴", notes = "发贴")
    @PostMapping("/do")
    public ResultDTO doPublish(@RequestBody Tie tie,
                               HttpServletRequest httpServletRequest
                               ){
        ResultDTO result = tieService.publish(tie,httpServletRequest);
        return result;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "删贴", notes = "删贴")
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
    @ApiOperation(value = "查看个人所有贴子", notes = "查看个人所有贴子")
    @GetMapping("/queryByUserId")
    public ResultDTO doSelectPersonTie(HttpServletRequest httpServletRequest,
                                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                                       @RequestParam(name = "size",defaultValue = "5") Integer size){
        Integer userIdINT = JWTInfo.getUserId_int(httpServletRequest);
        ResultDTO resultDTO = tieService.selectPersonTie(userIdINT, page, size);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查询某一个贴子", notes = "查询某一个贴子")
    @GetMapping("/queryById/{tieId}")
    public ResultDTO doSelectOneTie(@PathVariable("tieId") Integer tieId){
        ResultDTO resultDTO = tieService.selectOneTie(tieId);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查询小区所有贴子", notes = "查询小区所有贴子")
    @GetMapping("/community")
    public ResultDTO doSelectCommunityTie(HttpServletRequest httpServletRequest,
                                          @RequestParam(name = "status") String status,
                                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                                          @RequestParam(name = "size",defaultValue = "5") Integer size){
        String communityId = JWTInfo.getUserCommunityId(httpServletRequest);
        ResultDTO resultDTO = tieService.selectCommunityTie(communityId, page, size,status);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "点赞贴子", notes = "点赞贴子")
    @PostMapping("/like/{tieId}")
    public ResultDTO doLikeTie(@PathVariable("tieId") Integer tieId){
        ResultDTO resultDTO = tieService.likeTie(tieId);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "取消点赞贴子", notes = "取消点赞贴子")
    @PostMapping("/notLike/{tieId}")
    public ResultDTO doNotLikeTie(@PathVariable("tieId") Integer tieId){
        ResultDTO resultDTO = tieService.NotLikeTie(tieId);
        return resultDTO;
    }

    @CrossOrigin
    @GetMapping("/getWeather/{city}")
    public Object test(@PathVariable(name = "city") String city){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.seniverse.com/v3/weather/now.json?key=SiuwJAueYhN7oaicw&location=" + city +"&language=zh-Hans&unit=c";
        System.out.println(url);
        String trans = restTemplate.getForObject(url,String.class);
        return trans;
    }

    @CrossOrigin
    @GetMapping("/getGiteeHTML")
    public Object getGiteeHTML(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gitee.com/oauth/authorize?client_id=d5c2460b0d184927c832ab2d71e7d24ce5b656c352ea28308b66e3f5a6503b75&redirect_uri=http://localhost:8893/callBack&response_type=code";
        System.out.println(url);
        String trans = restTemplate.getForObject(url,String.class);
        return trans;
    }

}
