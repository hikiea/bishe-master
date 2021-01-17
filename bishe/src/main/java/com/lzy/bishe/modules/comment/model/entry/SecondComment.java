package com.lzy.bishe.modules.comment.model.entry;

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
public class SecondComment {

    @ApiModelProperty(notes = "主键id")
    public Integer commentId;

    @ApiModelProperty(notes = "贴子id")
    public Integer tieId;

    @ApiModelProperty(notes = "用户id")
    public Integer commentUserId;

    @ApiModelProperty(notes = "评论内容")
    public String commentContent;

    @ApiModelProperty(notes = "时间")
    public LocalDateTime commentTime;

    @ApiModelProperty(notes = "评论类型")
    public Integer commentTypes;

    @ApiModelProperty(notes = "目标评论id")
    public Integer replyCommentId;

}
