package com.lzy.bishe.modules.items.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.items.mapper.ItemsMapper;
import com.lzy.bishe.modules.items.model.entry.Items;
import com.lzy.bishe.modules.items.model.entry.V_ItemsUser;
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
public class ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    public ResultDTO addItem(Items items, HttpServletRequest httpServletRequest) {
        items.setUserId(JWTInfo.getUserId_int(httpServletRequest));
        items.setAddTime(LocalDateTime.now());
        itemsMapper.addItems(items);
        return ResultDTO.successOf("物品添加成功");
    }

    public ResultDTO deleteItem(Integer id, HttpServletRequest httpServletRequest) {
        itemsMapper.deleteItem(id);
        return ResultDTO.successOf("物品删除成功");
    }

    public ResultDTO queryBuy(HttpServletRequest httpServletRequest, Integer page, Integer size) {
        String address = JWTInfo.getAddress(httpServletRequest);
        String userCommunityId = JWTInfo.getUserCommunityId(httpServletRequest);
        PageHelper.startPage(page,size);
        List<V_ItemsUser> list = itemsMapper.queryBuy(address, userCommunityId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO queryNoBuy(HttpServletRequest httpServletRequest, Integer page, Integer size) {
        String address = JWTInfo.getAddress(httpServletRequest);
        String userCommunityId = JWTInfo.getUserCommunityId(httpServletRequest);
        PageHelper.startPage(page,size);
        List<V_ItemsUser> list = itemsMapper.queryNoBuy(address, userCommunityId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO queryByName(HttpServletRequest httpServletRequest, String itemName) {
        String address = JWTInfo.getAddress(httpServletRequest);
        List<V_ItemsUser> list = itemsMapper.queryByName(address, JWTInfo.getUserCommunityId(httpServletRequest), itemName);
        return ResultDTO.successOf("获取成功",list);
    }

    public ResultDTO updateItem(Integer id) {
        itemsMapper.updateItem(id);
        return ResultDTO.successOf("修改成功");
    }
}
