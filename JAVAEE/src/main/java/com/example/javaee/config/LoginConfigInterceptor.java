package com.example.javaee.config;

import com.example.javaee.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//这里是拦截请求配置
@Configuration
public class LoginConfigInterceptor  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor=new LoginInterceptor();
            List<String> list=new ArrayList<>();

            list.add("/user/login");//这里是放行的接口，其他接口都要拦截

            list.add("/user/signup");
            list.add("/swagger-ui.html");

            registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);
    }

}
