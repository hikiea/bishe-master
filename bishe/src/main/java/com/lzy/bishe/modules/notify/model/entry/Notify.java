package com.lzy.bishe.modules.notify.model.entry;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime notifyTime;

    @ApiModelProperty(notes = "帖子id")
    private Integer tieId;

}
