package com.lzy.bishe.modules.user.model.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author lizhongyi
 */
@Data
@NoArgsConstructor
@ApiModel(value = "UpdateUserInfoDTO",description = "用户修改请求对象")
public class UpdateUserInfoDTO {

    @ApiModelProperty(notes = "主键id")
    private Integer id;

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "昵称")
    private String nickname;

    @ApiModelProperty(notes = "电话")
    private String tel;

    @ApiModelProperty(notes = "头像")
    private String headUrl;

    @ApiModelProperty(notes = "小区id")
    private String communityId;

    @ApiModelProperty(notes = "门牌号")
    private String address;

}
