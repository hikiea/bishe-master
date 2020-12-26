package com.lzy.bishe.modules.Complaint.mapper;

import com.lzy.bishe.modules.Complaint.model.entry.Complaint;
import com.lzy.bishe.modules.Complaint.model.entry.RespondComplaint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RespondComplaintDao {

    @Update("update complaint set" +
            " respondComplaintUserId = #{respondComplaintUserId}," +
            "respondComplaintUsername = #{respondComplaintUsername}," +
            "respondComplaintContent = #{respondComplaintContent}," +
            "respondComplaintTime = #{respondComplaintTime}," +
            "respondComplaintAuthority = #{respondComplaintAuthority} " +
            "where " +
            "complaintId = #{complaintId}")
    void respondComplaintByComplaintId(RespondComplaint respondComplaint);

    @Update("update complaint set complaintStatus = #{complaintStatus} where complaintId = #{complaintId}")
    void updateComplaintStatusByComplaintId(String complaintStatus,Integer complaintId);

    @Select("select * from complaint where respondComplaintUserId = #{respondComplaintUserId}")
    List<Complaint> selectMyRespondComplaint(Integer respondComplaintUserId);

    @Select("select * from complaint where respondComplaintUserId is null")
    List<Complaint> selectNoRespondComplaint();

    @Select("select * from complaint where complaintId = #{complaintId}")
    Complaint selectByComplaintId(Integer complaintId);
}
