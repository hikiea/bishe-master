package com.lzy.bishe.modules.tie.model.entry;

import lombok.Data;

@Data
public class Tie {

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



}
