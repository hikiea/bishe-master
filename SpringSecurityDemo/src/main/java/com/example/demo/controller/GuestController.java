package com.example.demo.controller;

import com.example.demo.component.RegisterImageUtils;
import com.example.demo.dto.ResultDTO;
import com.example.demo.model.User;
import com.example.demo.myenum.roleEnum.RoleCode;
import com.example.demo.service.UserService;
import com.example.demo.untils.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.example.demo.exception.CustomErrorCode.CONT_REGISTER_THIS_ROLE;
import static com.example.demo.exception.CustomErrorCode.FILE_IS_NULL;
import static com.example.demo.myenum.roleEnum.RoleCode.REGISTERED_USER;
import static com.example.demo.myenum.roleEnum.RoleCode.WORKER;

@RestController
public class GuestController {

    @Value("${win_registerImageDir}")
    private String win_registerImageDir;

    @Value("${linux_registerImageDir}")
    private String linux_registerImageDir;

    @Autowired
    private UserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/guest/validateCodeImg", method = RequestMethod.GET)
    public void downloadWCImage(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
//   https://blog.csdn.net/u010648555/article/details/52261050
    }

    @PostMapping("/guest/register")
    public ResultDTO<String> userRegister(@RequestBody User user){
        String password = user.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodePassword);//加密密码
        String role=user.getRole();
        if(!role.equals(REGISTERED_USER.getRole())&&role.equals(WORKER.getRole())){
            return ResultDTO.errorOf(CONT_REGISTER_THIS_ROLE);
        }
        userService.userRegister(user);
        return ResultDTO.okOf("注册申请已提交");
    }

    @Autowired
    private RegisterImageUtils registerImageUtils;

    @PostMapping("/guest/registerImageUpload")
    public ResultDTO<String> imageUpload(@RequestParam("img")MultipartFile multipartFile){
        String dir= registerImageUtils.getRegisterImageDir();
        if(multipartFile.isEmpty()){
            ResultDTO.errorOf(FILE_IS_NULL);
        }

        String fileName=multipartFile.getOriginalFilename();

        File dest=new File(dir+fileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        try {
            multipartFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultDTO.okOf(fileName);
    }

}
