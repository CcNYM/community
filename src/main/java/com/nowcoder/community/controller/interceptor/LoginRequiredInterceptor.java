package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.util.HostHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果拦截到的是一个方法,静态资源不用管
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            //尝试获取LoginRequired注解
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);

            //如果获取到注解，即该方法是需要登录后才能够访问的，但是holdHolder中并未保存user，
            if(loginRequired != null && hostHolder.getUser() == null){

                //因为不是controller，所以要通过response进行重定向
                response.sendRedirect(request.getContextPath()+"/login");

                //拒绝后续的请求
                return false;
            }
        }
        //同意后续的请求
        return true;
    }
}
