package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))") //定义切点，即切入位置 在Service下的所有类的所有方法上
    public void pointcut() {

    }

    @Before("pointcut()") //在该切入点之前执行
    public void before() {
        System.out.println("before");
    }

    @After("pointcut()") //在该切入点之后执行,无论是否成功返回值，或者出现异常，都执行
    public void after() {
        System.out.println("after");
    }

    @AfterReturning("pointcut()") //成功返回结果后执行
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    @AfterThrowing("pointcut()") //抛出异常后执行
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    @Around("pointcut()") //即在前又在后执行
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object obj = joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }
}
