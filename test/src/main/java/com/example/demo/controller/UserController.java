package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lzy
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User> test(){
        return userService.getNum();
    }

    @GetMapping("/get2")
    public Object test1(){
        User num1 = userService.getNum1();
        return num1.getUser();
    }

    @GetMapping("/set/{id}")
    public Integer testPost(@PathVariable("id") Integer id){
        return id;
    }

    @GetMapping("/getInfo/{id}")
    public User testPost1(@PathVariable("id") Integer id){
        User user = userService.queryById(id);
        return user;
    }







}
