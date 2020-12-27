package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lzy
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getNum(){
        List<User> users = userMapper.get();
        return users;
    }

    public User getNum1(){
        User userById = userMapper.getUserById();
        return userById;
    }

    public User queryById(Integer id) {
        User user = userMapper.queryById(id);
        return user ;
    }
}
