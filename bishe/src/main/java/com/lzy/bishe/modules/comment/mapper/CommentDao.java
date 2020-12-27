package com.lzy.bishe.modules.comment.mapper;
import com.lzy.bishe.modules.comment.model.entry.Comment;
import com.lzy.bishe.modules.comment.model.entry.SecondComment;
import com.lzy.bishe.modules.comment.model.entry.V_CommentUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    @Insert("Insert into comment (tieId,commentUserId,commentContent,commentTime,commentTypes,commentPicture) values " +
            "(#{tieId},#{commentUserId},#{commentContent},#{commentTime},#{commentTypes},#{commentPicture})")
    void publishComment(Comment comment);

    @Select("Select * from v_comment_user where tieId = #{tieId}")
    List<V_CommentUser> selectTeiComment(Integer tieId);

    @Select("Select * from v_comment_user where commentId = #{commentId}")
    V_CommentUser selectOneComment(Integer commentId);

    @Delete("Delete from comment where commentId = #{commentId} or replyCommentId = #{commentId}")
    void deleteTieComment(Integer commentId);

    @Insert("Insert into comment (tieId,commentUserId,commentContent,commentTime,commentTypes,replyCommentId) values " +
            "(#{tieId},#{commentUserId},#{commentContent},#{commentTime},#{commentTypes},#{replyCommentId})")
    void publishSecondComment(SecondComment secondComment);

    @Select("Select * from v_comment_user where replyCommentId = #{replyCommentId} and commentTypes = #{commentTypes}")
    List<V_CommentUser> doSelectSecondComment(Integer replyCommentId, Integer commentTypes);

    @Update("Update comment set commentLikes = #{commentLikes} where commentId = #{commentId}")
    void updateCommentLikes(Integer commentId,Integer commentLikes);

    @Select("Select * from v_comment_user where commentId = #{replyCommentId}")
    V_CommentUser selectCommentByReplyCommentId(Integer replyCommentId);

}
