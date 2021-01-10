package com.lzy.bishe.modules.tie.model.entry;

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
public class Tie {

    @ApiModelProperty(notes = "主键id")
    private Integer tieId;

    @ApiModelProperty(notes = "发帖人 id")
    private Integer userId;

    @ApiModelProperty(notes = "标题")
    private String title;

    @ApiModelProperty(notes = "内容")
    private String content;

    @ApiModelProperty(notes = "发表时间")
    private String publishTime;

    @ApiModelProperty(notes = "图片地址")
    private String picture;

    @ApiModelProperty(notes = "浏览次数")
    private Integer browse;

    @ApiModelProperty(notes = "帖子类型")
    private Integer tieTypes;

    @ApiModelProperty(notes = "点赞次数")
    private Integer likes;

    @ApiModelProperty(notes = "帖子属性")
    private String tieStatus;


}
