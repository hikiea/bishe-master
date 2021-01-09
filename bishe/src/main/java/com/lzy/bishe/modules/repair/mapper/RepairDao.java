package com.lzy.bishe.modules.repair.mapper;

import com.lzy.bishe.modules.repair.model.entry.Repair;
import com.lzy.bishe.modules.repair.model.entry.V_ReplaceUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RepairDao {

    @Insert("insert into repair " +
            "(repairUserId,repairContent,repairPicture,homeId,repairPhone,repairEmail,repairTime,repairStatus) " +
            "values " +
            "(#{repairUserId},#{repairContent},#{repairPicture},#{homeId},#{repairPhone},#{repairEmail},#{repairTime},#{repairStatus})")
    void publishRepair(Repair repair);

    @Delete("delete from repair where repairId = #{repairId}")
    void deleteRepair(Integer repairId);

    @Update("update repair set repairStatus = #{repairStatus} where repairId = #{repairId}")
    void updateRepairStatus(Integer repairId,String repairStatus);

    @Select("select * from v_replace_user where repairUserId = #{repairUserId}")
    List<V_ReplaceUser> doSelectMyRepair(Integer repairUserId);

    @Select("select * from v_replace_user where okRepairUserId is null and communityId = #{id}")
    List<V_ReplaceUser> doSelectNoRepair(String id);

    @Update("update repair set okRepairUserId = #{okRepairUserId}, okRepairTime = #{okRepairTime},repairStatus = #{repairStatus} where repairId = #{repairId}")
    void doAcceptRepair(Repair repair);

    @Select("select * from v_replace_user where okRepairUserId = #{okRepairUserId}")
    List<V_ReplaceUser> doSelectAcceptRepair(Integer okRepairUserId);

    @Select("select * from v_replace_user where repairUserId = #{userId}")
    V_ReplaceUser findRepairUserId(Integer userId);

    @Select("select * from v_replace_user where repairId = #{repairId}")
    V_ReplaceUser findRepairByRepairId(Integer repairId);

}
