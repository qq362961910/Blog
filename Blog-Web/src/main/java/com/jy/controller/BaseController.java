package com.jy.controller;


import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected int pageSizeDefault = 10;
    protected int currentPageDefault = 1;

    public Map<String, Object> success() {
        return result(true, null, null);
    }

    public Map<String, Object> success(Object data) {
        return result(true, null, data);
    }

    public Map<String, Object> success(String description) {
        return result(true, description, null);
    }

    public Map<String, Object> success(String description, Object data) {
        return result(true, description, data);
    }

    public Map<String, Object> fail() {
        return result(false, null, null);
    }

    public Map<String, Object> fail( Object data) {
        return result(false, null, data);
    }

    public Map<String, Object> fail(String description) {
        return result(false, description, null);
    }

    public Map<String, Object> fail(String description, Object data) {
        return result(false, description, data);
    }

    public Map<String, Object> result(boolean success, String description, Object data) {
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("success", Boolean.TRUE);
        }
        else {
            result.put("success", Boolean.FALSE);
        }
        result.put("description", description);
        result.put("data",data);
        return result;
    }

}
