package com.jy.controller.interceptor.anno;

import com.jy.common.constants.ServiceErrorCode;

public @interface RequiredLogin {
    ServiceErrorCode errorCode() default ServiceErrorCode.CHECK_NO_USER_LOGIN;
}
