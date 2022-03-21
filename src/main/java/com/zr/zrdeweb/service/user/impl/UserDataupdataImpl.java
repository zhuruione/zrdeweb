package com.zr.zrdeweb.service.user.impl;

import com.zr.zrdeweb.system.dao.UserDataMapper;
import com.zr.zrdeweb.system.dao.UserInfMapper;
import com.zr.zrdeweb.system.model.user.UserData;
import com.zr.zrdeweb.system.model.user.UserInf;
import com.zr.zrdeweb.service.user.UserDataupdata;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataupdataImpl implements UserDataupdata {

    @Autowired
    UserInfMapper userInfMapper;
    @Autowired
    UserDataMapper dataMapper;

    @Override
    public boolean UserRegiset(String username, String password, String user, String areaid, String phone) {
        Subject currtuser= SecurityUtils.getSubject();
        Session session=currtuser.getSession();
        UserInf newuser=new UserInf(username,password,"ordinary",(String) session.getAttribute("email"));
        userInfMapper.insert(newuser);
        newuser =userInfMapper.selectByUsername(username);
        int id=newuser.getId();
        dataMapper.insert(new UserData(id,user,phone,Integer.valueOf(areaid)));
        return true;
    }

    //实现用户注册
}
