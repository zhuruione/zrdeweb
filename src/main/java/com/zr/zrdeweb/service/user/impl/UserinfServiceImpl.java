package com.zr.zrdeweb.service.user.impl;

import com.zr.zrdeweb.system.dao.UserInfMapper;
import com.zr.zrdeweb.system.model.user.UserInf;
import com.zr.zrdeweb.service.user.UserinfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class UserinfServiceImpl implements UserinfService {

    @Autowired
    UserInfMapper userinfMapper;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public UserInf getUserInfdByUsername(String name) {
        UserInf user=userinfMapper.selectByUsername(name);
        return user;
    }

    @Override
    public UserInf getUserInfByEmail(String email) {
        UserInf user=userinfMapper.selectByEmail(email);
        return user;
    }
}
