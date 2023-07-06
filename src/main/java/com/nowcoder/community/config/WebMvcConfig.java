package com.nowcoder.community.config;


import com.nowcoder.community.controller.interceptor.AlphaInterceptor;
import com.nowcoder.community.controller.interceptor.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

//@EnableWebMvc //禁用boot默认配置
//本配置类会影响到静态资源的加载
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //静态资源加载相关
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //保留以前的默认配置
        //自定义配置
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/a/","classpath:/b/")
                .setCacheControl(CacheControl.maxAge(5000, TimeUnit.SECONDS));
    }


    //拦截器加载
    @Autowired
    private AlphaInterceptor alphaInterceptor;

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(alphaInterceptor)
//                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg")//不拦截的资源，即拦截器中的方法不执行
//                .addPathPatterns("/register","/login");//要拦截的controller，即拦截器中的方法执行

        //如果不写addPathPatterns，那么除了excludePathPartterns以外，所有的controller都会被拦截
        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");//不拦截的资源，即拦截器中的方法不执行

    }
}

