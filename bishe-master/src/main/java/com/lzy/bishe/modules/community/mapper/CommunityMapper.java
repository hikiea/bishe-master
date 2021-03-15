package com.lzy.bishe.modules.community.mapper;

import com.lzy.bishe.modules.community.model.Community;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lzy
 */
@Mapper
@Repository
public interface CommunityMapper {

    @Select("select * from community")
    List<Community> getAllCommunity();

    @Delete("delete from community where id = #{id}")
    void deleteCommunity(Integer id);

    @Insert("insert into community(communityName,communityAddress) " +
            "values (#{communityName},#{communityAddress})")
    void addCommunity(Community community);

    @Select("select * from community where communityName = #{name}")
    Community queryByName(String name);
}
