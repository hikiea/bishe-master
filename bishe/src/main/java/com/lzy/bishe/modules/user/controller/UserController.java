package com.lzy.bishe.modules.user.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.base.model.entity.ResultDTO;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.bishe.modules.user.service.CheckService;
import com.lzy.bishe.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhongyi
 */
@RestController
@RequestMapping("api")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckService checkService;

    @PostMapping("/login")
    @CrossOrigin
    public ResultDTO login(@RequestBody UserLoginDTO userLoginInfo){
        ResultDTO resultDTO = checkService.checkLogin(userLoginInfo);
        return resultDTO;
    }
    
    @UserLoginToken
    @GetMapping("/user")
    public ResultDTO getUserMessage(HttpServletRequest httpServletRequest){
        ResultDTO userMessage = userService.getUserMessage(httpServletRequest);
        return userMessage;
    }

    @UserLoginToken
    @GetMapping("/user/all")
    public ResultDTO getAllUserByAdmin(HttpServletRequest httpServletRequest){
        ResultDTO allUserByAdmin = userService.getAllUserByAdmin(httpServletRequest);
        return allUserByAdmin;
    }

    @UserLoginToken
    @GetMapping("/logout")
    public ResultDTO logout(HttpServletRequest httpServletRequest){
        ResultDTO logout = userService.logout(httpServletRequest);
        return logout;
    }

}
