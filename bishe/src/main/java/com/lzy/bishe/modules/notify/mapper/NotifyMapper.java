package com.lzy.bishe.modules.notify.mapper;

import com.lzy.bishe.modules.notify.model.entry.Notify;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lzy
 */
@Mapper
@Repository
public interface NotifyMapper {

    @Insert("insert into notify(notifierId,receiverId,status,data,notifyTime,tieId)" +
            "value (#{notifierId},#{receiverId},#{status},#{data},#{notifyTime},#{tieId})")
    void send(Notify notify);

    @Select("select * from notify where receiverId = #{id}")
    List<Notify> queryByReceiverId(int id);

    @Update("update notify set status = #{code} where id = #{id}")
    void updateOne(Integer id, Integer code);

    @Update("update notify set status = #{code} where receiverId = #{id}")
    void updateAll(Integer id, Integer code);

    @Delete("delete from notify where receiverId = #{id}")
    void delete(Integer id);

    @Select("select * from notify where receiverId = #{id} and status = '0'")
    List<Notify> queryNoSee(int id);

    @Select("select count(*) from notify where receiverId = #{id} and status = '0'")
    Integer newNumber(Integer id);
}
