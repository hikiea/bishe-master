package com.lzy.springbootjwtcaptcha.modules.checkCode.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzy.springbootjwtcaptcha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lzy.springbootjwtcaptcha.annotation.PassToken;
import com.lzy.springbootjwtcaptcha.modules.checkCode.model.entity.CheckCode;
import com.lzy.springbootjwtcaptcha.modules.checkCode.service.CheckCodeService;
import lombok.extern.slf4j.Slf4j;


/**
 * 获取验证码图片
 * @author lizhongyi
 */
@Controller
@RequestMapping("api")
@Slf4j
@CrossOrigin
public class CheckCodeController {

    @Autowired
    private CheckCodeService checkCodeService;

    @Autowired
    private RedisUtil redisUtil;

    @PassToken
    @GetMapping("/checkCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置长宽
            CheckCode checkCode = checkCodeService.generate(80, 28);
            String code = checkCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute("code", code);
            redisUtil.set(code,code);
            System.out.println("获取验证码图片接口中的值:" + request.getSession().getAttribute("code"));
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(checkCode.getImgBytes());
            response.getOutputStream().flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
