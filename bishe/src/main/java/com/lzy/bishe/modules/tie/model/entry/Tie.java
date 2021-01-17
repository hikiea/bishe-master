package com.lzy.bishe.modules.tie.model.entry;

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
public class Tie {

    @ApiModelProperty(notes = "主键id")
    private Integer tieId;

    @ApiModelProperty(notes = "发贴人 id")
    private Integer userId;

    @ApiModelProperty(notes = "标题")
    private String title;

    @ApiModelProperty(notes = "内容")
    private String content;

    @ApiModelProperty(notes = "发表时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime publishTime;

    @ApiModelProperty(notes = "图片地址")
    private String picture;

    @ApiModelProperty(notes = "浏览次数")
    private Integer browse;

    @ApiModelProperty(notes = "点赞次数")
    private Integer likes;

    @ApiModelProperty(notes = "贴子属性")
    private String tieStatus;


}
