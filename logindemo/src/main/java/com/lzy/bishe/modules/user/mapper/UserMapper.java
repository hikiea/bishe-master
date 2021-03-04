package com.lzy.bishe.modules.user.mapper;
import com.lzy.bishe.modules.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * @author lizhongyi
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user")
    ArrayList<User> getUserList();

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @Update("update user set date = #{now},status = #{status} where id = #{id}")
    void updateTime(Integer id,LocalDateTime now,String status);
}
