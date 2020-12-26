package com.lzy.bishe.modules.Complaint.mapper;


import com.lzy.bishe.modules.Complaint.model.entry.Complaint;
import com.lzy.bishe.modules.Complaint.model.entry.PublishComplaint;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PublishComplaintDao {

    @Insert("Insert into complaint " +
            "(userId,username,complaintContent,complaintTime,communityId,userEmail,userPhone,complaintStatus) values " +
            "(#{userId},#{username},#{complaintContent},#{complaintTime},#{communityId},#{userEmail},#{userPhone},#{complaintStatus})")
    void publishComplaint(PublishComplaint publishComplaint);

    @Select("select * from complaint where userId = #{userId}")
    List<Complaint> selectMyComplaint(Integer userId);

    @Select("select * from complaint where communityId = #{communityId}")
    List<Complaint> selectCommunityComplaint(Integer communityId);

    @Update("update complaint set " +
            "complaintContent = #{complaintContent} " +
            "where " +
            "complaintId = #{complaintId}")
    void updateMyComplaint(PublishComplaint publishComplaint);

    @Delete("delete from complaint where complaintId = #{complaintId}")
    void deleteMyComplaint(Integer complaintId);

}
