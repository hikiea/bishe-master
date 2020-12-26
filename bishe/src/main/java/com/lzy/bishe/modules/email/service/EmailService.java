package com.lzy.bishe.modules.email.service;

import com.lzy.bishe.util.ResultDTO;
import com.sun.istack.internal.NotNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @author Lzy
 */
@Service
public class EmailService {

    @NotNull
    private JavaMailSenderImpl mailSender;

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public ResultDTO sendEmail(String setToEmail, String emailTitle, String emailContent){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(emailTitle);
        message.setText(emailContent);
        message.setTo(setToEmail);
        message.setFrom("594183034@qq.com");
        getMailSender().send(message);
        return ResultDTO.successOf("发送成功",message);
    }

}
