package com.lzy.bishe.modules.user.service;

import com.lzy.bishe.modules.user.mapper.UserMapper;
import com.lzy.bishe.modules.user.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * @author lizhongyi
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ArrayList<User> getUserList(){
        ArrayList<User> userList = userMapper.getUserList();
        return userList;
    }

    public String login(String username, String password, HttpServletRequest request) {
        User userInfo= userMapper.login(username, password);
        if (userInfo == null){
            return "loginError";
        }else{
            User s = userMapper.login(username, password);
            userMapper.updateTime(userInfo.getId(),LocalDateTime.now(),"已登录");
            request.getSession().setAttribute("user", s);
            request.getSession().setAttribute("loginTime", s.getDate().toString().replace("T"," "));
            System.out.println(s.getDate());
            return "welcome";
        }
    }
}
