package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/guest/get")
    public String getUserContext(){
        return "这里不需要权限";
    }

    @GetMapping("/user/get")
    public String getUserContext2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();//当前登录的用户名
        return "当前用户名：" + username + "，当前使用了user权限";
    }

    @GetMapping("/admin/get")
    public String getUserContext3(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();//当前登录的用户名
        return "当前用户名：" + username + "，当前使用了admin权限";
    }

    @GetMapping("/root/get")
    public String getUserContext4(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();//当前登录的用户名
        return "当前用户名：" + username + "，当前使用了root权限";
    }

}
