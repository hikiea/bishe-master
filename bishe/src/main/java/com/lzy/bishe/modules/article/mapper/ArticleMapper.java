package com.lzy.bishe.modules.article.mapper;

import com.lzy.bishe.modules.article.model.entry.Article;
import com.lzy.bishe.modules.article.model.entry.V_ArticleUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lzy
 */
@Mapper
@Repository
public interface ArticleMapper {

    @Select("select * from v_article_user where communityId = #{communityId} and address = #{address}")
    List<V_ArticleUser> qeury(String communityId, String address);

    @Insert("insert into article (number,name,picture,userId,time) values (#{number},#{name},#{picture},#{userId},#{time})")
    void add(Article article);

    @Delete("delete from article where id = #{id}")
    void delete(Integer id);

    @Update("update article set name = #{name},number = #{number},picture=#{picture} where id = #{id}")
    void update(Article article);

    @Select("select * from article where id = #{id}")
    Article queryById(Integer id);
}
