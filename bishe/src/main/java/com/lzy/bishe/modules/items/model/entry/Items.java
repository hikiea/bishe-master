package com.lzy.bishe.modules.items.model.entry;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lzy
 */
@Data
public class Items {

    @ApiModelProperty(notes = "主键id")
    private Integer id;

    @ApiModelProperty(notes = "用户id")
    private Integer userId;

    @ApiModelProperty(notes = "物品名称")
    private String itemName;

    @ApiModelProperty(notes = "物品数量")
    private String itemNum;

    @ApiModelProperty(notes = "物品图片")
    private String itemPicture;

    @ApiModelProperty(notes = "是否已购买 0已购买，1未购买")
    private String isBuy;

    @ApiModelProperty(notes = "价钱")
    private String money;

    @ApiModelProperty(notes = "添加/修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime addTime;

}
