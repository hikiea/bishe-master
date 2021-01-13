package com.lzy.bishe.modules.article.model.entry;

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
public class Article {

    @ApiModelProperty(notes = "主键id")
    public Integer id;

    @ApiModelProperty(notes = "用户id")
    public Integer userId;

    @ApiModelProperty(notes = "物品名称")
    public String name;

    @ApiModelProperty(notes = "数量")
    public String number;

    @ApiModelProperty(notes = "图片")
    public String picture;

    @ApiModelProperty(notes = "时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime time;

}
