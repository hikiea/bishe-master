package com.lzy.bishe.modules.Complaint.model.entry;

import lombok.Data;

@Data
public class Complaint {

    private Integer complaintId;
    private Integer userId;
    private String username;
    private String complaintContent;
    private Integer communityId;
    private String complaintTime;
    private String userEmail;
    private String userPhone;

    private Integer respondComplaintUserId;
    private String respondComplaintUsername;
    private String respondComplaintContent;
    private String respondComplaintTime;
    private String respondComplaintAuthority;
    private String complaintStatus;

}
