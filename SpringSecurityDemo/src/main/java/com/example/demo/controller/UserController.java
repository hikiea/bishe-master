package com.example.demo.controller;

import com.example.demo.dto.ResultDTO;
import com.example.demo.exception.CustomErrorCode;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @GetMapping("/user/userInfo")
    public ResultDTO<User> getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();//当前登录的用户名
        Integer userId = userService.getUserId(username);
        User userInfo = userService.getUserInfo(userId);
        userInfo.setPassword("");
        return ResultDTO.okOf("用户信息",userInfo);
    }

    @PutMapping("/user/userInfo")
    public ResultDTO<String> UpdateUserInfo(@RequestBody User newUserInfo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();//当前登录的用户名
        Integer userId = userService.getUserId(username);
        User oldUserInfo = userService.getUserInfo(userId);

        //username userId communityId都不能变更！
        newUserInfo.setName(username);
        newUserInfo.setId(userId);
        newUserInfo.setCommunityId(oldUserInfo.getCommunityId());

        boolean matches = false;
        try {
            matches = bCryptPasswordEncoder.matches(newUserInfo.getPassword(),oldUserInfo.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!matches) {
            return ResultDTO.errorOf(CustomErrorCode.PASSWORD_WRONG);
        }

        String password=newUserInfo.getPassword();
        newUserInfo.setPassword(bCryptPasswordEncoder.encode(password));
        userService.updateUserInfo(newUserInfo);
        return ResultDTO.okOf();
    }
}
