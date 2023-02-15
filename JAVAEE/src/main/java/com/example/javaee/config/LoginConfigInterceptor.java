package com.example.javaee.config;

import com.example.javaee.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//这里是拦截请求配置
@Configuration
public class LoginConfigInterceptor  implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        HandlerInterceptor interceptor=new LoginInterceptor();
//            List<String> list=new ArrayList<>();
//
//            list.add("/user/login");
//
//            list.add("/user/signup");
//            list.add("/swagger-ui.html");
//
//            registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);
//    }
}
