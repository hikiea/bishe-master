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

    @Insert("insert into tie " +
            "(userId,title,content,publishTime,picture,tieStatus) " +
            "values " +
            "(#{userId},#{title},#{content},#{publishTime},#{picture},#{tieStatus})")
    void publish(Tie tie);

    @Delete("delete from tie where tieId = #{tieId}")
    void deleteTie(Integer tieId);

    @Update("update tie set " +
            "title = #{title}," +
            "content = #{content}," +
            "label = #{label} " +
            "where " +
            "tieId = #{tieId}")
    void updateTie(Tie ite);

    @Select("select * from v_tie_user")
    List<V_TieUser> selectAllTie();

    @Select("select * from v_tie_user where userId = #{userId}")
    List<V_TieUser> selectPersonTie(Integer userId);

    @Select("select * from v_tie_user where id = #{tieId}")
    V_TieUser selectOneTie(Integer tieId);

    @Update("update tie set browse = #{browse} where tieId = #{tieId}")
    void rememberBrowse(Integer browse,Integer tieId);

    @Select("select * from v_tie_user where communityId = #{communityId} and tieStatus = #{status} order by publishTime desc ")
    List<V_TieUser> selectCommunityTie(String communityId, String status);

    @Update("update tie set likes = #{likes} where tieId = #{tieId}")
    void likeTie(Integer likes,Integer tieId);

}
