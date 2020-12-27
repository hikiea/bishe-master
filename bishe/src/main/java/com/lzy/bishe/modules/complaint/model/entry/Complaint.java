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
public class Complaint {

    @ApiModelProperty(notes = "主键id")
    private Integer complaintId;

    @ApiModelProperty(notes = "用户id")
    private Integer userId;

    @ApiModelProperty(notes = "投诉内容")
    private String complaintContent;

    @ApiModelProperty(notes = "投诉时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime complaintTime;

    @ApiModelProperty(notes = "当前状态")
    private String status;

    @ApiModelProperty(notes = "完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime finishTime;

}
