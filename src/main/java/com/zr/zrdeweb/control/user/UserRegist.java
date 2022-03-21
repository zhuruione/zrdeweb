package com.zr.zrdeweb.control.user;


import com.zr.zrdeweb.system.model.user.UserInf;
import com.zr.zrdeweb.service.email.GetEmailEcode;
import com.zr.zrdeweb.service.user.UserDataupdata;
import com.zr.zrdeweb.service.user.UserinfService;
import com.zr.zrdeweb.system.utils.GetRedomeEcode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserRegist {
    //用户数据修改类
    @Autowired
    UserDataupdata dataupdata;
    //获取随机6位验证码类
    @Autowired
    GetRedomeEcode redomeEcode;

    @Autowired
    UserinfService userinfService;
    @Autowired
    GetEmailEcode emailEcode;

    @Autowired
    HttpServletRequest request;

    //查看邮箱是否可用
    @ResponseBody
    @PostMapping("/testemail")
    public String testemail(String email) {
        String emailcheck = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern pattern = Pattern.compile(emailcheck);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return "邮箱格式不正确";
        } else {
            UserInf inf = userinfService.getUserInfByEmail(email);
            System.out.println(email);
            if (inf == null) {
                String ecode = redomeEcode.ecodes();
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
                session.setAttribute("ecode", ecode);
                session.setAttribute("email", email);
                request.getSession().setAttribute("shirosession",session);
                emailEcode.sendemail(email, ecode);
                return "true";
            } else {
                return "该邮箱已被注册！";
            }
        }
    }

    //验证验证码
    @ResponseBody
    @PostMapping("/user/checkemailecode")
    public String checkemailecode(String ecode, String email) {
        Subject currtuser = SecurityUtils.getSubject();
        Session session = currtuser.getSession();
        String e = (String) session.getAttribute("email");
        if (e == null) {
            return "请获取验证码！";
        }
        if (e.equals(email)) {
            if (session.getAttribute("ecode").equals(ecode)) {
                session.setAttribute("ecodecheck", true);
                return "true";
            }
            return "验证码不正确";
        }
        return "邮箱不正确";
    }

    //查看用户名是否被注册
    @ResponseBody
    @GetMapping("/checkusername")
    public String checkusername(String username) {
        String checkusername = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(checkusername);
        Matcher matcher = pattern.matcher(username);
        if (username == "") {
            return "用户名不能为空";
        }
        if (!matcher.matches()) {
            return "用户名必须是3~15个数字字母的组合";
        }
        UserInf inf = userinfService.getUserInfdByUsername(username);
        if (inf == null) {
            Session s=(Session) request.getSession().getAttribute("shirosession");
            s.setAttribute("usernamecheck",true);
            return "true";
        } else {
            return "用户名已被注册";
        }
    }

    //验证登录信息
    @ResponseBody
    @PostMapping("/checkinformation")
    public String checkpassword(String password, String username, String user, String areaid, String phone) {
        Session s=(Session) request.getSession().getAttribute("shirosession");
        if (!(boolean) (s.getAttribute("usernamecheck"))) {
            return "用户名不合法！";
        }
        String passwordcheck = "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$";
        Pattern pattern = Pattern.compile(passwordcheck);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return "密码格式不合法！";
        }
        if(areaid==null){
            return "地址为必填项";
        }
        if(user==""){
            return "昵称为必填项";
        }
        passwordcheck = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$";
        pattern = Pattern.compile(passwordcheck);
        matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            return "电话不合法！";
        }
        if (dataupdata.UserRegiset(username, password,user,areaid,phone))
            return "true";
        else
            return "注册失败，请检查信息";

    }
}
