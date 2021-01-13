package com.lzy.bishe.modules.article.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.article.mapper.ArticleMapper;
import com.lzy.bishe.modules.article.model.entry.Article;
import com.lzy.bishe.modules.article.model.entry.V_ArticleUser;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lzy
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public ResultDTO qeury(Integer page, Integer size, HttpServletRequest httpServletRequest) {
        String communityId = JWTInfo.getUserCommunityId(httpServletRequest);
        String address = JWTInfo.getAddress(httpServletRequest);
        PageHelper.startPage(page,size);
        List<V_ArticleUser> qeury = articleMapper.qeury(communityId, address);
        PageInfo pageInfo = new PageInfo(qeury);
        return ResultDTO.successOf("查询成功",pageInfo);
    }

    public ResultDTO add(Article article, HttpServletRequest httpServletRequest) {
        article.setUserId(JWTInfo.getUserId_int(httpServletRequest));
        article.setTime(LocalDateTime.now());
        articleMapper.add(article);
        return ResultDTO.successOf("新增成功");
    }

    public ResultDTO delete(Integer id) {
        articleMapper.delete(id);
        return ResultDTO.successOf("删除成功");
    }

    public ResultDTO update(Article article) {
        articleMapper.update(article);
        return ResultDTO.successOf("更新成功");
    }

    public ResultDTO queryById(Integer id) {
        Article article = articleMapper.queryById(id);
        return ResultDTO.successOf("查询成功",article);
    }

}
