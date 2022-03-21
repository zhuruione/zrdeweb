package com.zr.zrdeweb.system.config.shiro;

import com.zr.zrdeweb.system.model.user.UserInf;
import com.zr.zrdeweb.service.user.UserinfService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserinfService userinfService;
    @Autowired
    HttpServletRequest request;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获取当前用户
        Subject user= SecurityUtils.getSubject();
        //获取对象
        UserInf inf=(UserInf)user.getPrincipal();
        //添加身份
        info.addStringPermission(inf.getPerms());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        UserInf inf =userinfService.getUserInfdByUsername(token.getUsername());
        if(inf==null){
            return null;
        }
        request.getSession().setAttribute("userid",inf.getId());
        //第一个变量可以将对象保存在principal中
        return new SimpleAuthenticationInfo(inf,inf.getPassword(),"");
    }
}
