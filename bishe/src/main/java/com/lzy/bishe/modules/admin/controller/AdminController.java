package com.lzy.bishe.modules.admin.controller;

import com.lzy.bishe.modules.admin.service.AdminService;
import com.lzy.bishe.modules.base.model.entity.ResultDTO;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lizhongyi
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @CrossOrigin
    @ApiOperation(value = "管理员登录接口", notes = "管理员登录接口")
    public ResultDTO adminLogin(@RequestBody UserLoginDTO userLoginInfo){
        ResultDTO resultDTO = adminService.adminLogin(userLoginInfo);
        return resultDTO;
    }


    @GetMapping("/user")
    @CrossOrigin
    @ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
    public ResultDTO getAllUserInfo(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "size", defaultValue = "5") Integer size){
        ResultDTO allUserInfo = adminService.getAllUserInfo(page,size);
        return allUserInfo;
    }

    @PutMapping("/user")
    @CrossOrigin
    @ApiOperation(value = "更改用户状态", notes = "更改用户状态")
    public ResultDTO updateUserStatus( @RequestParam(name = "id")Integer id,
                                       @RequestParam(name = "status") Integer status){
        ResultDTO allUserInfo = adminService.updateUserStatus(id,status);
        return allUserInfo;
    }

    @DeleteMapping("/user")
    @CrossOrigin
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public ResultDTO deleteUser( @RequestParam(name = "id")Integer id){
        ResultDTO allUserInfo = adminService.deleteUser(id);
        return allUserInfo;
    }

}
