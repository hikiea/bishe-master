package com.lzy.bishe.modules.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.bishe.modules.admin.mapper.AdminMapper;
import com.lzy.bishe.modules.base.model.entity.ResultDTO;
import com.lzy.bishe.modules.user.mapper.UserMapper;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.modules.user.service.CheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author lizhongyi
 */
@Service
@Slf4j
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CheckService checkService;

    public ResultDTO adminLogin(UserLoginDTO userLoginInfo) {
        User user = userMapper.checkEqualUsername(userLoginInfo.getUsername());
        if (user.getPower().equals("admin")){
            return checkService.checkLogin(userLoginInfo);
        }else{
            return ResultDTO.errorOf(403,"权限不足");
        }
    }

    public ResultDTO getAllUserInfo(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<User> allUserInfo = adminMapper.getAllUserInfo();
        PageInfo pageInfo = new PageInfo(allUserInfo);
        return ResultDTO.successOf("获取成功",pageInfo);
    }

    public ResultDTO updateUserStatus(Integer id, Integer status) {
        adminMapper.updateUserStatus(id,status);
        return ResultDTO.successOf("修改状态成功");
    }

    public ResultDTO deleteUser(Integer id) {
        adminMapper.deleteUser(id);
        return ResultDTO.successOf("删除用户成功");
    }
}
