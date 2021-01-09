package com.lzy.bishe.modules.tie.mapper;


import com.lzy.bishe.modules.tie.model.entry.Tie;
import com.lzy.bishe.modules.tie.model.entry.V_TieUser;
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
            "(userId,title,content,label,publishTime,picture,tieTypes,tieStatus) " +
            "values " +
            "(#{userId},#{title},#{content},#{label},#{publishTime},#{picture},#{tieTypes},#{tieStatus})")
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
    @Select("select * from v_tie_user")
    List<V_TieUser> selectAllTie();

    /*查询个人的所有帖子*/
    @Select("select * from v_tie_user where userId = #{userId}")
    List<V_TieUser> selectPersonTie(Integer userId);

    /* 查询某一个帖子 */
    @Select("select * from v_tie_user where tieId = #{tieId}")
    V_TieUser selectOneTie(Integer tieId);

    /* 查询某一个帖子时，增加浏览数 */
    @Update("update tie set browse = #{browse} where tieId = #{tieId}")
    void rememberBrowse(Integer browse,Integer tieId);

    /* 查询某一个小区的帖子 */
    @Select("select * from v_tie_user where communityId = #{communityId}")
    List<V_TieUser> selectCommunityTie(String communityId);

    /* 点赞帖子 */
    @Update("update tie set likes = #{likes} where tieId = #{tieId}")
    void likeTie(Integer likes,Integer tieId);

}
