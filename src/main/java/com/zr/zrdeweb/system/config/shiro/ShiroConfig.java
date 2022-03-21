package com.zr.zrdeweb.system.config.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        Map<String,String> fittermap=new LinkedHashMap<>();
        fittermap.put("/phone/**","user");
        fittermap.put("/file/**","user");
        fittermap.put("/videopath/**","anon");
        bean.setFilterChainDefinitionMap(fittermap);
        //设置默认登录页面
        bean.setLoginUrl("/user/login");
        //设置默认权限无法访问页面
        bean.setUnauthorizedUrl("/unauthorized");
        return bean;
    }


   // 记住我
    @Bean(name = "logincookie")
    public CookieRememberMeManager rememberMe(){
        CookieRememberMeManager manager=new CookieRememberMeManager();
        SimpleCookie simpleCookie=new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(24*60*60*14);
        manager.setCookie(simpleCookie);
        return manager;
    }



    //创建realm对象，需要自定义
    @Bean(name = "myrealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }


    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myrealm") UserRealm userRealm,@Qualifier("logincookie") CookieRememberMeManager rememberMeManager){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRememberMeManager(rememberMeManager);
        manager.setRealm(userRealm);
        return manager;
    }

    // ShiroFilterFactoryBean
    //整合shiroDialect  :用来整合shiro和thymeleaf  命名空间：xmlns:shiro="http://www.thymeleaf.org/thymeleaf-extras-shiro"
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }



}
