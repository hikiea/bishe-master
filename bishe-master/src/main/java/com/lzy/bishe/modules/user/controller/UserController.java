package com.lzy.bishe.modules.user.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.util.ResultDTO;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.bishe.modules.user.model.dto.responseDTO.UpdateUserInfoDTO;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhongyi
 *
 */
@Api(tags = {"UserController"}, description = "用户相关接口")
@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    public ResultDTO login(@RequestBody UserLoginDTO userLoginInfo){
        ResultDTO resultDTO = userService.checkLogin(userLoginInfo);
        return resultDTO;
    }

    @PostMapping("/register")
    @CrossOrigin
    @ApiOperation(value = "用户注册接口",notes = "用户注册接口")
    public ResultDTO registerUser(@RequestBody User user){
        ResultDTO userMessage = userService.registerUser(user);
        return userMessage;
    }
    
    @UserLoginToken @CrossOrigin
    @GetMapping("/info")
    @ApiOperation(value = "获取自己的信息",notes = "获取自己的信息")
    public ResultDTO getUserMessage(HttpServletRequest httpServletRequest){
        ResultDTO userMessage = userService.getUserMessage(httpServletRequest);
        return userMessage;
    }

    @UserLoginToken @CrossOrigin
    @GetMapping("/oneInfo/{id}")
    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    public ResultDTO getOneUser(@PathVariable String id){
        ResultDTO userMessage = userService.getOneUser(id);
        return userMessage;
    }

    @UserLoginToken @CrossOrigin
    @PostMapping("/update")
    @ApiOperation(value = "修改用户信息",notes = "修改用户信息")
    public ResultDTO updateUserMessage(@RequestBody UpdateUserInfoDTO userInfo){
        ResultDTO userMessage = userService.updateUserMessage(userInfo);
        return userMessage;
    }

    @UserLoginToken @CrossOrigin
    @PostMapping("/changeHead/{headUrl}")
    @ApiOperation(value = "用户更换头像",notes = "用户更换头像")
    public ResultDTO changeHead(HttpServletRequest httpServletRequest,
                            @PathVariable String headUrl){
        ResultDTO result = userService.changeHead(httpServletRequest,headUrl);
        return result;
    }

    @UserLoginToken @CrossOrigin
    @GetMapping("/logout")
    @ApiOperation(value = "用户登出",notes = "用户登出")
    public ResultDTO logout(HttpServletRequest httpServletRequest){
        ResultDTO logout = userService.logout(httpServletRequest);
        return logout;
    }

}
