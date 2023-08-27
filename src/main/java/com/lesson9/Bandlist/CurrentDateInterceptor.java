package com.lesson9.Bandlist;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;

public class CurrentDateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) throws Exception {
        //リクエストごとに今日の日付をセット
        ZonedDateTime currentDate = ZonedDateTime.now();
        request.setAttribute("currentDate", currentDate);

        System.out.println("Intercepted request. Current date: " + currentDate);

        return true;
    }

    @Override
    public void postHandle(@Nonnull HttpServletRequest request,
                           @Nonnull HttpServletResponse response,
                           @Nonnull Object handler, ModelAndView modelAndView) throws Exception {
        //何も処理しない
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request,
                                @Nonnull HttpServletResponse response,
                                @Nonnull Object handler, Exception ex) throws Exception {
        //何も処理しない
    }
}
