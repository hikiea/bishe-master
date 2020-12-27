package com.lzy.bishe.modules.repair.model.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @ApiModelProperty(notes = "房间id")
    public String homeId;

    @ApiModelProperty(notes = "电话")
    public String repairPhone;

    @ApiModelProperty(notes = "邮件")
    public String repairEmail;

    @ApiModelProperty(notes = "报修时间")
    public String repairTime;

    @ApiModelProperty(notes = "维修人id")
    public Integer okRepairUserId;

    @ApiModelProperty(notes = "维修时间")
    public String okRepairTime;

    @ApiModelProperty(notes = "当前状态")
    public String repairStatus;

}
