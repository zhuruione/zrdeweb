package com.zr.zrdeweb.control.test;

import com.zr.zrdeweb.system.dao.UserInfMapper;
import com.zr.zrdeweb.system.model.user.UserData;
import com.zr.zrdeweb.system.model.user.UserInf;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class test{

    @Autowired
    UserInfMapper mapper;
    @Autowired
    HttpServletRequest request;
    @GetMapping("/test")
    @ResponseBody
    public UserInf test(){
        return mapper.selectByUsername("zr");
    }
    @GetMapping("/testshiro")
    @ResponseBody
    public UserInf testshiro(){
        return (UserInf) SecurityUtils.getSubject().getPrincipal();
    }
}