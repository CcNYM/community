package com.nowcoder.community.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Aspect
public class ServiceLogAspect {

    private static final Logger logger  =  LoggerFactory.getLogger(ServiceLogAspect.class);

    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))") //定义切点，即切入位置 在Service下的所有类的所有方法上
    public void pointcut() {

    }

    @Before("pointcut()") //在该切入点之前执行
    public void before(JoinPoint joinPoint) {
        // 用户某某 在 某时间， 访问了 * com.nowcoder.community.service.*.*(..))
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            //因为consumer调用，是非用户的请求，是特殊的
            return;
        }
        HttpServletRequest request  = attributes.getRequest();

        String ip = request.getRemoteHost();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info(String.format("用户[%s], 在[%s], 访问了[%s] .",ip, now, target));

    }

//    @After("pointcut()") //在该切入点之后执行,无论是否成功返回值，或者出现异常，都执行
//    public void after() {
//        System.out.println("after");
//    }
//
//    @AfterReturning("pointcut()") //成功返回结果后执行
//    public void afterReturning() {
//        System.out.println("afterReturning");
//    }
//
//    @AfterThrowing("pointcut()") //抛出异常后执行
//    public void afterThrowing() {
//        System.out.println("afterThrowing");
//    }
//
//    @Around("pointcut()") //即在前又在后执行
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("around before");
//        Object obj = joinPoint.proceed();
//        System.out.println("around after");
//        return obj;
//    }

}
