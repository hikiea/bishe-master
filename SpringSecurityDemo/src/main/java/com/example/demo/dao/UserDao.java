package com.example.demo.dao;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where username = #{username}")
    User findUserByUserName(String username);

}
