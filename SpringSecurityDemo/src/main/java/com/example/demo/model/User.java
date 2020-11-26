package com.example.demo.model;

import lombok.Data;

@Data
public class User {
    //用户表
    Integer id;             //用户id
    String username;            //用户名
    String password;        //用户密码
    String sex;             //用户性别
    String tel;             //用户电话
    String email;           //用户邮件
    String headUrl;         //用户头像链接 当注册时是房产证/租赁合同的url
    String communityId;     //用户所属小区编号
    String address;         //用户详细住址
    String roles;            //用户权限
}
