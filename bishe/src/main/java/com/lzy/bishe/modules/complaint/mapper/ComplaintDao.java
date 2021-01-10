package com.lzy.bishe.modules.complaint.mapper;


import com.lzy.bishe.modules.complaint.model.entry.Complaint;
import com.lzy.bishe.modules.complaint.model.entry.V_ComplaintUser;
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
            "(userId,complaintContent,complaintTime,status) values " +
            "(#{userId},#{complaintContent},#{complaintTime},#{status})")
    void publishComplaint(Complaint publishComplaint);

    @Select("select * from v_complaint_user where userId = #{userId}")
    List<V_ComplaintUser> selectMyComplaint(Integer userId);

    @Select("select * from v_complaint_user where communityId = #{communityId}")
    List<V_ComplaintUser> selectCommunityComplaint(String communityId);

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

    @Select("select * from v_complaint_user where communityId = #{communityId} and status <> '已跟进'")
    List<V_ComplaintUser> doSelectCommunityComplaint_no(String communityId);

    @Select("select * from v_complaint_user where communityId = #{communityId} and complaintContent like '%${content}%' ")
    List<V_ComplaintUser> queryByContent(String communityId, String content);

    @Select("select * from v_complaint_user where communityId = #{communityId} and complaintContent like '%${content}%' and status <> '已跟进'")
    List<V_ComplaintUser> queryByContent_no(String communityId, String content);

    @Select("SELECT COUNT(*) FROM v_complaint_user where communityId =  #{id} ")
    Integer all(String id);

    @Select("SELECT COUNT(*) FROM v_complaint_user where communityId =  #{id} and status = '已跟进' ")
    Integer okAll(String id);

    @Select("SELECT COUNT(*) FROM v_complaint_user where communityId =  #{id} and status <> '已跟进' ")
    Integer noAll(String id);
}
