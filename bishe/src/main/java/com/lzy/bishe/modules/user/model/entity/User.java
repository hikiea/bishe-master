package com.lzy.bishe.modules.user.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * @author lizhongyi
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {

    @ApiModelProperty(notes = "主键id")
    private Integer id;

    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "权限")
    private String power;

    @ApiModelProperty(notes = "昵称")
    private String nickname;

    @ApiModelProperty(notes = "电话")
    private String tel;

    @ApiModelProperty(notes = "头像")
    private String headUrl;

    @ApiModelProperty(notes = "小区id")
    private Integer communityId;

    @ApiModelProperty(notes = "门牌号")
    private String address;

    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(notes = "性别")
    private String sex;

    @ApiModelProperty(notes = "状态")
    private Integer status;

    @ApiModelProperty(notes = "EMail")
    private String email;
}
