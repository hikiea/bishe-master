package com.lzy.bishe.modules.notify.model.entry;

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
public class Notify {

    @ApiModelProperty(notes = "主键id")
    private Integer id;

    @ApiModelProperty(notes = "发布人id")
    private Integer notifierId;

    @ApiModelProperty(notes = "接收人id")
    private Integer receiverId;

    @ApiModelProperty(notes = "通知状态")
    private Integer status;

    @ApiModelProperty(notes = "通知内容")
    private String data;

    @ApiModelProperty(notes = "通知时间")
    private LocalDateTime notifyTime;

}
