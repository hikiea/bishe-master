package com.lzy.bishe.modules.email.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.email.model.entry.Email;
import com.lzy.bishe.modules.email.service.EmailService;
import com.lzy.bishe.util.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Lzy
 */
@RestController
@Api(tags = {"EmailController"}, description = "邮件相关接口")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /*@ApiOperation(value = "发送邮件接口", notes = "发送邮件接口")
    @PostMapping("/email")
    @UserLoginToken @CrossOrigin
    public ResultDTO deSendEmail(@RequestBody Email email){
        ResultDTO resultDTO = emailService.sendEmail(email.getSetToEmail(), email.getEmailTitle(), email.getEmailContent());
        return resultDTO;
    }*/

}
