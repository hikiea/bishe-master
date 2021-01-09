package com.lzy.bishe.modules.complaint.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lzy
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class V_ComplaintUser {

    @ApiModelProperty(notes = "主键id")
    private Integer complaintId;

    @ApiModelProperty(notes = "用户id")
    private Integer userId;

    @ApiModelProperty(notes = "投诉内容")
    private String complaintContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(notes = "投诉时间")
    private LocalDateTime complaintTime;

    @ApiModelProperty(notes = "当前状态")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(notes = "完成时间")
    private LocalDateTime finishTime;

    @ApiModelProperty(notes = "邮件")
    private String userEmail;

    @ApiModelProperty(notes = "用户电话")
    private String userPhone;

    @ApiModelProperty(notes = "小区id")
    private String communityId;

    @ApiModelProperty(notes = "昵称")
    private String nickName;


}
