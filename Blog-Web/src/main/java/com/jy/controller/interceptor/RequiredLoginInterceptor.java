package com.jy.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jy.common.constants.ServiceErrorCode;
import com.jy.controller.interceptor.anno.RequiredLogin;
import com.jy.controller.util.AppContextUtil;
import com.jy.controller.util.RequestUtil;
import com.jy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequiredLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean validate = true;
        if (handler != null && handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            RequiredLogin requiredLogin = method.getAnnotation(RequiredLogin.class);
            if (requiredLogin != null) {
                String ticket = RequestUtil.getCookie(request, "ticket");
                if (ticket == null) {
                    validate = false;
                } else {
                    User user = AppContextUtil.getUserByTicket(ticket);
                    if (user == null) {
                        validate = false;
                    } else {
                        AppContextUtil.setCurrentUser(user);
                    }
                }
            }
        }
        if (!validate) {
            Map<String, Object> result = new HashMap<>();
            result.put("success", Boolean.FALSE);
            result.put("code", ServiceErrorCode.CHECK_NO_USER_LOGIN.code);
            result.put("msg", ServiceErrorCode.CHECK_NO_USER_LOGIN.msg);
            result.put("data", "");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
        else {
            AppContextUtil.setCurrentRequestTimePoint(new Date());
        }
        return validate;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
