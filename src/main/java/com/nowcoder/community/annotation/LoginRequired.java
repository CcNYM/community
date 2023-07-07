package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;

@Target(ElementType.METHOD)//可以在方法上描述
@Retention(RetentionPolicy.RUNTIME)//运行时生效
public @interface LoginRequired {
}
