package com.lzy.bishe.modules.repair.mapper;

import com.lzy.bishe.modules.repair.model.entry.Repair;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RepairDao {

    @Insert("insert into repair " +
            "(repairUserId,repairUsername,communityId,repairContent,repairPicture,homeId,repairPhone,repairEmail,repairTime,repairStatus) " +
            "values " +
            "(#{repairUserId},#{repairUsername},#{communityId},#{repairContent},#{repairPicture},#{homeId},#{repairPhone},#{repairEmail},#{repairTime},#{repairStatus})")
    void publishRepair(Repair repair);

    @Delete("delete from repair where repairId = #{repairId}")
    void deleteRepair(Integer repairId);

    @Update("update repair set repairStatus = #{repairStatus} where repairId = #{repairId}")
    void updateRepairStatus(Integer repairId,String repairStatus);

    @Select("select * from repair where repairUserId = #{repairUserId}")
    List<Repair> doSelectMyRepair(Integer repairUserId);

    @Select("select * from repair where okRepairUserId is null")
    List<Repair> doSelectNoRepair();

    @Update("update repair set" +
            " okRepairUserId = #{okRepairUserId}," +
            "okRepairUsername = #{okRepairUsername}," +
            "okRepairTime = #{okRepairTime}," +
            "repairStatus = #{repairStatus} " +
            "where " +
            "repairId = #{repairId}")
    void doAcceptRepair(Repair repair);

    @Select("select * from repair where okRepairUserId = #{okRepairUserId}")
    List<Repair> doSelectAcceptRepair(Integer okRepairUserId);

    @Select("select * from repair where repairUserId = #{userId}")
    Repair findRepairUserId(Integer userId);

    @Select("select * from repair where repairId = #{repairId}")
    Repair findRepairByRepairId(Integer repairId);

}
