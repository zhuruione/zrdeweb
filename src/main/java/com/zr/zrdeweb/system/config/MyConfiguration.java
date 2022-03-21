package com.zr.zrdeweb.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MyConfiguration implements WebMvcConfigurer {
    @Value("${zr.videopath}")
    String videopath;
    @Value("${zr.rootpath}")
    String rootpath;

    /**
     * 虚拟路径配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/videopath/**").addResourceLocations(videopath);
        registry.addResourceHandler("/rootpath/**").addResourceLocations(rootpath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}