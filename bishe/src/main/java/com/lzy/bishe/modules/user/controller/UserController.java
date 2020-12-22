package com.lzy.bishe.modules.user.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.base.model.entity.ResultDTO;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.bishe.modules.user.model.dto.responseDTO.UpdateUserInfoDTO;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.modules.user.service.CheckService;
import com.lzy.bishe.modules.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhongyi
 *
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckService checkService;

    @PostMapping("/login")
    @CrossOrigin
    @ApiOperation(value = "用户登录接口", notes = "登录")
    public ResultDTO login(@RequestBody UserLoginDTO userLoginInfo){
        ResultDTO resultDTO = checkService.checkLogin(userLoginInfo);
        return resultDTO;
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册接口",notes = "注册接口")
    public ResultDTO registerUser(@RequestBody User user){
        ResultDTO userMessage = userService.registerUser(user);
        return userMessage;
    }
    
    @UserLoginToken
    @GetMapping("/user")
    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    public ResultDTO getUserMessage(HttpServletRequest httpServletRequest){
        ResultDTO userMessage = userService.getUserMessage(httpServletRequest);
        return userMessage;
    }

    @UserLoginToken
    @PutMapping("/user")
    @ApiOperation(value = "修改用户信息",notes = "修改用户信息")
    public ResultDTO updateUserMessage(@RequestBody UpdateUserInfoDTO userInfo){
        ResultDTO userMessage = userService.updateUserMessage(userInfo);
        return userMessage;
    }

    @UserLoginToken
    @GetMapping("/logout")
    @ApiOperation(value = "用户登出",notes = "获取用户信息")
    public ResultDTO logout(HttpServletRequest httpServletRequest){
        ResultDTO logout = userService.logout(httpServletRequest);
        return logout;
    }

}
