package com.jy.blog.controller.interceptor.anno;

import com.jy.blog.blog.common.constants.ServiceErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredLogin {
    ServiceErrorCode errorCode() default ServiceErrorCode.CHECK_NO_USER_LOGIN;
}
