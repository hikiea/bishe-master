package com.lzy.bishe.modules.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.community.mapper.CommunityMapper;
import com.lzy.bishe.modules.community.model.Community;
import com.lzy.bishe.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lzy
 */
@Service
public class CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    public ResultDTO getAllCommunity(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Community> allCommunity = communityMapper.getAllCommunity();
        PageInfo pageInfo = new PageInfo(allCommunity);
        return ResultDTO.successOf("小区获取成功",pageInfo);
    }

    public ResultDTO deleteCommunity(Integer id) {
        communityMapper.deleteCommunity(id);
        return ResultDTO.successOf("删除小区成功");
    }

    public ResultDTO addCommunity(Community community) {
        communityMapper.addCommunity(community);
        return ResultDTO.successOf("小区添加成功");
    }

    public ResultDTO queryByName(String name) {
        return ResultDTO.successOf("获取成功",communityMapper.queryByName(name));
    }
}
