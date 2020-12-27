package com.lzy.bishe.modules.complaint.model.entry;

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

    @ApiModelProperty(notes = "投诉时间")
    private LocalDateTime complaintTime;

    @ApiModelProperty(notes = "当前状态")
    private String status;

    @ApiModelProperty(notes = "完成时间")
    private LocalDateTime finshTime;

    @ApiModelProperty(notes = "邮件")
    private Integer userEmail;

    @ApiModelProperty(notes = "用户电话")
    private String userPhone;

    @ApiModelProperty(notes = "小区id")
    private Integer communityId;

    @ApiModelProperty(notes = "昵称")
    private String nickName;


}
