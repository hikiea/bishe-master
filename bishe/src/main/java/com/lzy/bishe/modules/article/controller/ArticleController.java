package com.lzy.bishe.modules.article.controller;


import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.article.model.entry.Article;
import com.lzy.bishe.modules.article.service.ArticleService;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/article")
@CrossOrigin
@Slf4j
@Api(tags = {"ArticleController"}, description = "物品相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查询所有物品", notes = "查询所有物品")
    @GetMapping("/query")
    public ResultDTO query(@RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size,
                           HttpServletRequest httpServletRequest){
        ResultDTO resultDTO = articleService.qeury(page, size,httpServletRequest);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "查询单个物品", notes = "查询单个物品")
    @GetMapping("/queryById/{id}")
    public ResultDTO queryById(@PathVariable("id") Integer id){
        ResultDTO resultDTO = articleService.queryById(id);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "增加物品", notes = "增加物品")
    @PostMapping("/add")
    public ResultDTO add(HttpServletRequest httpServletRequest,
                         @RequestBody Article article){
        ResultDTO resultDTO = articleService.add(article,httpServletRequest);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "删除物品", notes = "删除物品")
    @GetMapping("/delete/{id}")
    public ResultDTO delete(@PathVariable("id") Integer id){
        ResultDTO resultDTO = articleService.delete(id);
        return resultDTO;
    }

    @CrossOrigin @UserLoginToken
    @ApiOperation(value = "更新物品", notes = "更新物品")
    @PostMapping("/update")
    public ResultDTO update(@RequestBody Article article){
        ResultDTO resultDTO = articleService.update(article);
        return resultDTO;
    }
}
