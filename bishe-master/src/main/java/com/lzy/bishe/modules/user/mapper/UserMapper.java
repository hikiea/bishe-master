package com.lzy.bishe.modules.user.mapper;

import com.lzy.bishe.modules.user.model.dto.responseDTO.UpdateUserInfoDTO;
import com.lzy.bishe.modules.user.model.entity.User;
import org.apache.ibatis.annotations.Insert;
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
public interface UserMapper {

    @Select("SELECT * FROM user where username=#{username}")
    User findByUsernameToToken(String username);

    @Select("SELECT * FROM user where id=#{id}")
    User findUserById(Integer id);

    @Insert("insert into user(username,password,power,nickname,tel,headUrl,communityId,address,createTime,sex,publishStatus,email) " +
            "values(#{username},#{password},#{power},#{nickname},#{tel},#{headUrl},#{communityId},#{address},#{createTime},#{sex},#{publishStatus},#{email})")
    void registerUser(User user);

    @Select("select * from user where id = #{id}")
    User getUserMessage(String id);

    @Update("update user set password = #{password},nickname = #{nickname},tel = #{tel},headUrl = #{headUrl},communityId = #{communityId},address = #{address},power = #{power} where id = #{id}")
    void updateUserMessage(UpdateUserInfoDTO userInfo);

    @Select("select * from user where username = #{username}")
    User checkEqualUsername(String username);

    @Update("update user set headUrl = #{headUrl} where id = #{id}")
    void changeHead(Integer id, String headUrl);
}
