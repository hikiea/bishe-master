package com.lzy.bishe.modules.complaint.mapper;


import com.lzy.bishe.modules.complaint.model.entry.Complaint;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lzy
 */
@Mapper
@Repository
public interface ComplaintDao {

    @Insert("Insert into complaint " +
            "(userId,complaintContent,complaintTime,userEmail,userPhone,status) values " +
            "(#{userId},#{complaintContent},#{complaintTime},#{userEmail},#{userPhone},#{status})")
    void publishComplaint(Complaint publishComplaint);

    @Select("select * from complaint where userId = #{userId}")
    List<Complaint> selectMyComplaint(Integer userId);

    @Select("select * from complaint where communityId = #{communityId}")
    List<Complaint> selectCommunityComplaint(Integer communityId);

    @Update("update complaint set " +
            "complaintContent = #{complaintContent} " +
            "where " +
            "complaintId = #{complaintId}")
    void updateMyComplaint(Complaint publishComplaint);

    @Delete("delete from complaint where complaintId = #{complaintId}")
    void deleteMyComplaint(Integer complaintId);

    @Update("update complaint set " +
            "status = #{status} ," +
            "finishTime = #{finishTime} " +
            "where " +
            "complaintId = #{complaintId}")
    void finish(Complaint complaint);

}
