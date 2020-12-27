package com.lzy.bishe.modules.items.service;

import com.lzy.bishe.modules.items.mapper.ItemsMapper;
import com.lzy.bishe.modules.items.model.entry.Items;
import com.lzy.bishe.modules.items.model.entry.V_ItemsUser;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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

    public ResultDTO updateItem(Items items, HttpServletRequest httpServletRequest) {
        items.setUserId(JWTInfo.getUserId_int(httpServletRequest));
        items.setAddTime(LocalDateTime.now());
        itemsMapper.updateItem(items);
        return ResultDTO.successOf("物品修改成功");
    }

    public ResultDTO deleteItem(Integer id, HttpServletRequest httpServletRequest) {
        itemsMapper.deleteItem(id);
        return ResultDTO.successOf("物品删除成功");
    }

    public ResultDTO queryBuy(HttpServletRequest httpServletRequest) {
        String address = JWTInfo.getAddress(httpServletRequest);
        Integer userCommunityId = JWTInfo.getUserCommunityId(httpServletRequest);
        List<V_ItemsUser> list = itemsMapper.queryBuy(address, userCommunityId);
        return ResultDTO.successOf("获取成功",list);
    }

    public ResultDTO queryNoBuy(HttpServletRequest httpServletRequest) {
        String address = JWTInfo.getAddress(httpServletRequest);
        Integer userCommunityId = JWTInfo.getUserCommunityId(httpServletRequest);
        List<V_ItemsUser> list = itemsMapper.queryNoBuy(address, userCommunityId);
        return ResultDTO.successOf("获取成功",list);
    }

    public ResultDTO queryByName(HttpServletRequest httpServletRequest, String itemName) {
        String address = JWTInfo.getAddress(httpServletRequest);
        Integer userCommunityId = JWTInfo.getUserCommunityId(httpServletRequest);
        List<V_ItemsUser> list = itemsMapper.queryByName(address, userCommunityId, itemName);
        return ResultDTO.successOf("获取成功",list);
    }
}
