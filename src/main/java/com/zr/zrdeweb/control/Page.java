package com.zr.zrdeweb.control;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Page {
    @Autowired
    HttpServletRequest request;
    //首页
    @GetMapping({"/","/index"})
    public String INDEXPAGE(){
        request.getSession().setAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "index";
    }
    //登录页
    @GetMapping("/user/login")
    public String LOGINPAGE(){
        return "user/login";
    }
    //注册页
    @GetMapping("/user/regist")
    public String REGISTPAGE(){
        return "user/regist";
    }
    @GetMapping("user/haschecked/registinf")
    public String CHECKEDREGISTPAGE(){
        return "user/haschecked/registinf";
    }
    @GetMapping("/home")
    public String HOME(){
        request.getSession().setAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "phone/zhangdan/home";
    }
    @GetMapping("/vediotest")
    public String vediotest(){
        return "video/videotest";
    }
    @GetMapping("/page")
    public String page(String name){
        return name;
    }
    @GetMapping("/homework")
    public String homework(){return "homework/homework";}
}
