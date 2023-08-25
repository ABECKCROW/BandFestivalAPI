package com.lesson9.Bandlist;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //インターセプターを追加
        registry.addInterceptor(new CurrentDateInterceptor());
    }
}
