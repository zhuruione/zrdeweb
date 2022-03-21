package com.zr.zrdeweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;


@EnableTransactionManagement
@EnableAsync
@MapperScan(basePackages = "com/zr/zrdeweb/system/dao")
@SpringBootApplication
public class ZrdewebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZrdewebApplication.class, args);
    }

    //设置请求地址中可以存在特殊字符
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory () {
        // 修改内置的 tomcat 容器配置
        TomcatServletWebServerFactory tomcatServlet = new TomcatServletWebServerFactory();
        tomcatServlet.addConnectorCustomizers(
                (TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}")
        );
        return tomcatServlet;
    }
    //修改临时文件目录
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("G:/data/temp");
        return factory.createMultipartConfig();

    }

}
