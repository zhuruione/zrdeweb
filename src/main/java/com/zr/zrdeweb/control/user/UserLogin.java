package com.zr.zrdeweb.control.user;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserLogin {


    //实现用户登出
    @GetMapping("/logout")
    public String Userlogout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
    //实现用户登录
    @PostMapping("/login")
    @ResponseBody
    public String Userlogin(String password, String username,String rememberMe, Model model){
        Subject currtuser= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        if(new String("true").equals(rememberMe)){
            System.out.println("设置了cookie");
            token.setRememberMe(true);
        }
        try {
            currtuser.login(token);
            return null;
        } catch (UnknownAccountException e) {
            //找不到用户名错误
            return "用户名不存在";
        }catch (IncorrectCredentialsException e) {
            //密码错误
            return "密码错误";
        }
    }

}
