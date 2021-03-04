package com.lzy.bishe.modules.checkcode.controller;

import com.lzy.bishe.annotation.PassToken;
import com.lzy.bishe.modules.checkcode.model.entity.CheckCode;
import com.lzy.bishe.modules.checkcode.service.CheckCodeService;
import com.lzy.bishe.redis.RedisCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 获取验证码图片
 * @author lizhongyi
 */
@Controller
@RequestMapping("api")
@Slf4j
@CrossOrigin
@Api(tags = {"CheckCodeController"}, description = "验证码相关接口")
public class CheckCodeController {

    @Autowired
    private CheckCodeService checkCodeService;

    @Autowired
    private RedisCodeUtil redisCodeUtil;

    @PassToken
    @GetMapping("/checkCode")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置长宽
            CheckCode checkCode = checkCodeService.generate(80, 28);
            String code = checkCode.getCode();
            redisCodeUtil.set(code,code);
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
