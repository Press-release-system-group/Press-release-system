package com.example.javaee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                //扫描接口所在的包
                .apis(RequestHandlerSelectors.basePackage("com.example.javaee.controller"))
                .build()
                .apiInfo(new ApiInfoBuilder()
                .title("我的接口文档")
                .description("这里是描述信息，关于接口")
                .version("5.55hhh版本")
                .contact(new Contact("yueze","sdsdsd","这里是邮箱"))
                .license("证书")
                .licenseUrl("证书url")
                .build());
    }
}
