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

    @Insert("insert into notify(notifierId,receiverId,status,data,notifyTime)" +
            "value (#{notifierId},#{receiverId},#{status},#{data},#{notifyTime})")
    void send(Notify notify);

    @Select("select * from notify where receiverId = #{id}")
    List<Notify> queryByReceiverId(int id);

    @Update("update notify set status = #{code} where id = #{id}")
    void updateOne(Integer id, Integer code);

    @Update("update notify set status = #{code} where receiverId = #{id}")
    void updateAll(Integer id, Integer code);

    @Delete("delete from notify where receiverId = #{id}")
    void delete(Integer id);
}
