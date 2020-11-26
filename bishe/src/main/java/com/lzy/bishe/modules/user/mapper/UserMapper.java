package com.lzy.bishe.modules.user.mapper;

import com.lzy.bishe.modules.user.model.dto.responseDTO.UserInfoResponseDTO;
import com.lzy.bishe.modules.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    User findUserById(String id);

    @Select("select * from user")
    List<UserInfoResponseDTO> findUser();

}
