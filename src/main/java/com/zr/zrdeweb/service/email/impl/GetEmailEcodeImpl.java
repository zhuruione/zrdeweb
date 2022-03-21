package com.zr.zrdeweb.service.email.impl;

import com.zr.zrdeweb.service.email.GetEmailEcode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class GetEmailEcodeImpl implements GetEmailEcode {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    @Async
    public void sendemail(String useremail,String ecode) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject("你的注册验证码");
        mailMessage.setText(ecode);
        mailMessage.setTo(useremail);
        mailMessage.setFrom("862739771@qq.com");
        mailSender.send(mailMessage);
        return;
    }

    @Override
    @Async
    public void sendtome(String email, String s) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject("回调");
        mailMessage.setText(s);
        mailMessage.setTo(email);
        mailMessage.setFrom("862739771@qq.com");
        mailSender.send(mailMessage);
        return;
    }
}
