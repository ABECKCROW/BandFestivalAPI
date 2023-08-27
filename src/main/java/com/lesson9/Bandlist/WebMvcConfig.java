package com.lesson9.Bandlist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    CurrentDateInterceptor currentDateInterceptor() {
        return new CurrentDateInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //インターセプターを追加
        registry.addInterceptor(new CurrentDateInterceptor());
    }
}
