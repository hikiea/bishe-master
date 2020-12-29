package com.lzy.bishe.modules.admin.mapper;


import com.lzy.bishe.modules.user.model.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author lizhongyi
 */
@Mapper
@Repository
public interface AdminMapper {

    @Select("select * from user")
    List<User> getAllUserInfo();

    @Update("update user set publishStatus = #{status} where id = #{id}")
    void updateUserStatus(Integer id, Integer status);

    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    @Select("select * from user where username = #{username}")
    List<User> getAllUserInfoByUsername(String username);
}
