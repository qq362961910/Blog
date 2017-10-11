package com.jy.blog.blog.common.constants;

/**
 * service error code
 * check_xxxx: 状态检查异常
 * login_xxxx: 登录异常
 * user_xxxx: 用户异常
 */
public enum ServiceErrorCode {

    /********************************************** check ********************************************************************/
    /**
     * 用户未登录
     */
    CHECK_NO_USER_LOGIN("check_0001", "用户未登录"),

    /********************************************** login ********************************************************************/

    /**
     * 用户名不正确
     */
    LOGIN_USERNAME_ERROR("login_0001", "用户名不正确"),

    /**
     * 密码不正确
     */
    LOGIN_PASSWORD_ERROR("login_0002", "密码不正确"),

    /**
     * 用户名或密码不正确
     */
    LOGIN_USERNAME_OR_PASSWORD_ERROR("login_0003", "用户名或密码不正确"),


    /**
     * 验证码不正确
     */
    LOGIN_CODE_ERROR("login_0003", "验证码不正确"),

    /********************************************** user ********************************************************************/

    /**
     * 用户不存在
     */
    USER_NOT_FOUND("user_0001", "用户不存在"),

    /**
     * 用户不存在
     */
    USER_ALREADY_EXISTS("user_0002", "用户已存在"),

    /********************************************** param ********************************************************************/

    /**
     * 参数缺失
     */
    PARAM_MISSING("param_missing", "参数缺失"),

    /**
     * 404
     */
    SOURCE_NOT_FOUND_EXCEPTION("404", "404"),

    /**
     * 服务器内部异常
     */
    SERVER_INTERNAL_EXCEPTION("500", "500");


    public String code;

    public String msg;

    ServiceErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
