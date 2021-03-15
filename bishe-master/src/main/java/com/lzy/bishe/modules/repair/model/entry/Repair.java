package com.lzy.bishe.modules.repair.model.entry;

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
public class Repair {

    @ApiModelProperty(notes = "主键id")
    public Integer repairId;

    @ApiModelProperty(notes = "用户id")
    public Integer repairUserId;

    @ApiModelProperty(notes = "报修内容")
    public String repairContent;

    @ApiModelProperty(notes = "图片")
    public String repairPicture;

    @ApiModelProperty(notes = "报修时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public LocalDateTime repairTime;

    @ApiModelProperty(notes = "维修人id")
    public Integer okRepairUserId;

    @ApiModelProperty(notes = "维修时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public LocalDateTime okRepairTime;

    @ApiModelProperty(notes = "当前状态")
    public String repairStatus;

}
