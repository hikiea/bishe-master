package com.lzy.bishe.modules.Complaint.model.entry;

import lombok.Data;

@Data
public class RespondComplaint extends Complaint {

    private Integer complaintId;
    private Integer respondComplaintUserId;
    private String respondComplaintUsername;
    private String respondComplaintContent;
    private String respondComplaintTime;
    private String respondComplaintAuthority;
    private String complaintStatus;

}
