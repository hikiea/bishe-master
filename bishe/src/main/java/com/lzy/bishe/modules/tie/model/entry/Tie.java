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
    private Integer id;

    /*帖子表*/
    private Integer tieId;   // 帖子id
    private Integer userId;  // 发帖人 id
    private String username;  //发帖人姓名
    private Integer communityId;  //发帖人小区Id
    private String title;  //标题
    private String content;  //内容
    private String label;  //标签
    private String publishTime;  //发表时间
    private String picture;  //图片地址
    private Integer browse;  //浏览次数
    private Integer tieTypes;  //帖子类型
    private Integer likes;  //点赞次数

    @ApiModelProperty(notes = "帖子属性")
    private String tieStatus;



}
