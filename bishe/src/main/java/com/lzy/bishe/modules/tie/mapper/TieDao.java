package com.lzy.bishe.modules.tie.mapper;


import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.user.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Lzy
 */
@Mapper
@Repository
public interface TieDao {

    /*发帖*/
    @Insert("insert into tie " +
            "(userId,username,communityId,title,content,label,publishTime,picture,tieTypes,tieStatus) " +
            "values " +
            "(#{userId},#{username},#{communityId},#{title},#{content},#{label},#{publishTime},#{picture},#{tieTypes},#{tieStatus})")
    void publish(Tie tie);

    /*删帖*/
    @Delete("delete from tie where tieId = #{tieId}")
    void deleteTie(Integer tieId);

    /*修改贴*/
    @Update("update tie set " +
            "title = #{title}," +
            "content = #{content}," +
            "label = #{label} " +
            "where " +
            "tieId = #{tieId}")
    void updateTie(Tie ite);

    /*查询所有帖子*/
    @Select("select * from tie")
    List<Tie> selectAllTie();

    /*查询个人的所有帖子*/
    @Select("select * from tie where userId = #{userId}")
    List<Tie> selectPersonTie(Integer userId);

    /* 查询某一个帖子 */
    @Select("select * from tie where tieId = #{tieId}")
    Tie selectOneTie(Integer tieId);

    /* 查询某一个帖子时，增加浏览数 */
    @Update("update tie set browse = #{browse} where tieId = #{tieId}")
    void rememberBrowse(Integer browse,Integer tieId);

    /* 查询某一个小区的帖子 */
    @Select("select * from tie where communityId = #{communityId}")
    List<Tie> selectCommunityTie(Integer communityId);

    /* 点赞帖子 */
    @Update("update tie set likes = #{likes} where tieId = #{tieId}")
    void likeTie(Integer likes,Integer tieId);

    @Select("select * from user where name = #{username}")
    User selectByUsername(String username);

}
