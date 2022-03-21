//package com.zr.zrdeweb.system.config.Interceptor;
//
//import com.zr.zrdeweb.system.Interceptor.GetUserinf;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//public class GetUserInfConfig implements WebMvcConfigurer {
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration=registry.addInterceptor(new GetUserinf());
//        registration.addPathPatterns("/**");
//        registration.excludePathPatterns("/user/**");
//    }
//}
