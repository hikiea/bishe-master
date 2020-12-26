package com.lzy.bishe.modules.notify.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.common.NotifyStatusCode;
import com.lzy.bishe.modules.notify.mapper.NotifyMapper;
import com.lzy.bishe.modules.notify.model.entry.Notify;
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
public class NotifyService {

    @Autowired
    private NotifyMapper notifyMapper;

    public void send(HttpServletRequest httpServletRequest,
                     String data,
                     Integer targetId) {
        Notify notify = new Notify();
        notify.setNotifierId(Integer.parseInt(JWTInfo.getUserId(httpServletRequest)));
        notify.setReceiverId(targetId);
        notify.setData(data);
        notify.setStatus(NotifyStatusCode.NO_READ.getCode());
        notify.setNotifyTime(LocalDateTime.now());
        notifyMapper.send(notify);
    }

    public ResultDTO query(HttpServletRequest httpServletRequest,
                           Integer page,
                           Integer size) {
        int id = Integer.parseInt(JWTInfo.getUserId(httpServletRequest));
        PageHelper.startPage(page,size);
        List<Notify> list = notifyMapper.queryByReceiverId(id);
        PageInfo pageInfo = new PageInfo(list);
        return ResultDTO.successOf("通知获取成功",pageInfo);
    }

    public ResultDTO updateOne(Integer id) {
        notifyMapper.updateOne(id,NotifyStatusCode.READ.getCode());
        return ResultDTO.successOf("通知更新成功");
    }

    public ResultDTO updateAll(HttpServletRequest httpServletRequest) {
        int id = Integer.parseInt(JWTInfo.getUserId(httpServletRequest));
        notifyMapper.updateAll(id,NotifyStatusCode.READ.getCode());
        return ResultDTO.successOf("所有通知更新成功");
    }

    public ResultDTO delete(HttpServletRequest httpServletRequest) {
        int id = Integer.parseInt(JWTInfo.getUserId(httpServletRequest));
        notifyMapper.delete(id);
        return ResultDTO.successOf("删除成功");
    }
}
