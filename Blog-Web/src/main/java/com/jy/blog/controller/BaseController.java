package com.jy.blog.controller;


import com.jy.blog.blog.common.constants.ServiceErrorCode;

import java.util.HashMap;
import java.util.Map;


public class BaseController {

    protected String pageSizeKey = "pageSize";
    protected String currentPageKey = "currentPageKey";
    protected String totalPageKey = "totalPageKey";
    protected int pageSizeDefault = 10;
    protected int currentPageDefault = 1;
    protected static final String SUCCESS_CODE = "200";

    public Map<String, Object> success() {
        return result(true, SUCCESS_CODE, null, null);
    }

    public Map<String, Object> success(Object data) {
        return result(true, SUCCESS_CODE, null, data);
    }

    public Map<String, Object> success(String description) {
        return result(true, SUCCESS_CODE, description, null);
    }

    public Map<String, Object> success(String description, Object data) {
        return result(true, null, description, data);
    }

    public Map<String, Object> fail() {
        return result(false, ServiceErrorCode.SERVER_INTERNAL_EXCEPTION.code, null, null);
    }

    public Map<String, Object> fail(String errorCode) {
        return result(false, errorCode, null, null);
    }

    public Map<String, Object> fail(ServiceErrorCode errorCode) {
        return result(false, errorCode.code, errorCode.msg, null);
    }

    public Map<String, Object> fail(String errorCode, Object data) {
        return result(false, errorCode, null, data);
    }

    public Map<String, Object> fail(String errorCode, String description) {
        return result(false, errorCode, description, null);
    }

    public Map<String, Object> fail(String errorCode, String description, Object data) {
        return result(false, ServiceErrorCode.SERVER_INTERNAL_EXCEPTION.code, description, data);
    }

    /**
     * 404
     */
    public Map<String, Object> fail404() {
        return result(false, ServiceErrorCode.SOURCE_NOT_FOUND_EXCEPTION.code, null, null);
    }

    public Map<String, Object> result(boolean success, String errorCode, String description, Object data) {
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        if (errorCode == null) {
            errorCode = SUCCESS_CODE;
        }
        result.put("code", errorCode);
        result.put("msg", description);
        result.put("data", data);
        return result;
    }
}
