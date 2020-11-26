package com.example.demo.service;

import com.example.demo.dao.AuthstrDao;
import com.example.demo.dao.NotificationDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.myenum.noticeEnum.NoticeCode;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private AuthstrDao authstrDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NotificationDao notificationDao;

    public void userRegister(User user){
        authstrDao.insertUser(user);
    }

    public String getAuthstrEmail(Integer authstrId){
        return authstrDao.findUserByUserId(authstrId).getEmail();
    }

    /*
    管理员对申请进行处理
     */
    public void adminAuthstr(Integer authstrId,boolean result,String message) {
        User authstr = authstrDao.findUserByUserId(authstrId);
        authstrDao.deleteUser(authstrId);
        //不管有没有通过，都会通过邮件告知
        //如果通过
        if (result) {
            authstr.setHeadUrl("");//之前url是头像的url
            userDao.insertUser(authstr);
            User user = userDao.findUserByUserName(authstr.getName());
            Notification notification = new Notification();
            notification.setNotifierId(0);//系统通知id为0
            notification.setReceiverId(user.getId());
            notification.setType(NoticeCode.SYSTEM_NOTICE.getCode());
            notification.setGmt_create(System.currentTimeMillis());
            notification.setData(message);
            notificationDao.insertNotice(notification);
        }
    }

    public List<User> getAuthstrs(){
        List<User> allUser = authstrDao.findAllUser();
        return allUser;
    }

    public Integer getUserId(String userName){
        return userDao.findUserByUserName(userName).getId();
    }

    public User getUserInfo(Integer id){
        User userByUserId = userDao.findUserByUserId(id);
        return userByUserId;
    }

    public void updateUserInfo(User user){
        userDao.updateUser(user);
    }
}
