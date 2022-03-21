//package com.zr.zrdeweb.system.Interceptor;
//
//import org.apache.shiro.SecurityUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class GetUserinf implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        request.getSession().setAttribute("user", SecurityUtils.getSubject().getPrincipal());
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//}
