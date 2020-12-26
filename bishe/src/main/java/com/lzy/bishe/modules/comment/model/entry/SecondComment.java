package com.lzy.bishe.modules.comment.model.entry;

import lombok.Data;

@Data
public class SecondComment {

    public Integer commentId;
    public Integer tieId;
    public String commentUsername;
    public Integer commentUserId;
    public String commentContent;
    public String commentTime;
    public Integer commentTypes;
    public Integer replyCommentId;

}
