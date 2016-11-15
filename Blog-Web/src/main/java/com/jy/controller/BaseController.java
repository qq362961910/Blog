package com.jy.controller;


import com.jy.exception.ExceptionCode;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected int pageSizeDefault = 10;
    protected int currentPageDefault = 1;

    public Map<String, Object> success() {
        return result(true, null, null, null);
    }

    public Map<String, Object> success(Object data) {
        return result(true, null, null, data);
    }

    public Map<String, Object> success(String description) {
        return result(true, null, description, null);
    }

    public Map<String, Object> success(String description, Object data) {
        return result(true, null, description, data);
    }

    public Map<String, Object> fail() {
        return result(false, null, null, null);
    }

    public Map<String, Object> fail(String errorCode, Object data) {
        return result(false, errorCode, null, data);
    }

    public Map<String, Object> fail(String errorCode, String description) {
        return result(false, null, description, null);
    }

    public Map<String, Object> fail(String errorCode, String description, Object data) {
        return result(false, null, description, data);
    }

    public Map<String, Object> result(boolean success, String errorCode, String description, Object data) {
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        if (errorCode == null) {
            errorCode = ExceptionCode.SERVER_INTERNAL_EXCEPTION.getValue();
        }
        result.put("code", errorCode);
        result.put("description", description);
        result.put("data", data);
        return result;
    }

}
