package com.lzy.bishe.modules.items.mapper;

import com.lzy.bishe.modules.items.model.entry.Items;
import com.lzy.bishe.modules.items.model.entry.V_ItemsUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lzy
 */
@Mapper
@Repository
public interface ItemsMapper {

    @Insert("insert into items(userId,itemName,itemNum,itemPicture,isBuy,money,addTime) " +
            "values (#{userId},#{itemName},#{itemNum},#{itemPicture},#{isBuy},#{money},#{addTime})")
    void addItems(Items items);

    @Update("update items set isBuy = '0' where id = #{id}")
    void updateItem(Integer items);

    @Delete("delete from items where id = #{id}")
    void deleteItem(Integer id);

    @Select("select * from v_items_user where address = #{address} and communityId = #{communityId} and isBuy = '0'")
    List<V_ItemsUser> queryBuy(String address, String communityId);

    @Select("select * from v_items_user where address = #{address} and communityId = #{communityId} and isBuy = '1'")
    List<V_ItemsUser> queryNoBuy(String address, String communityId);

    @Select("select * from v_items_user " +
            "where address = #{address} " +
            "and communityId = #{communityId} " +
            "and itemName like '%${itemName}%'")
    List<V_ItemsUser> queryByName(String address, String communityId, String itemName);
}
