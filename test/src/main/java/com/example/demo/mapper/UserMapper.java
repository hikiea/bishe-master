package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * @author Lzy
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user")
    List<User> get();

    @Select("select * from user where id=1")
    User getUserById();

    @Select("select * from user where id=#{id}")
    User queryById(Integer id);

}
