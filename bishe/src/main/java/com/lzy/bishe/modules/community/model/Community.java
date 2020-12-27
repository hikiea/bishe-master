package com.lzy.bishe.modules.community.model;

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
public class Community {

    @ApiModelProperty(notes = "主键id")
    private Integer id;

    @ApiModelProperty(notes = "小区名")
    private String communityName;

    @ApiModelProperty(notes = "小区地址")
    private String communityAddress;

}
