package com.jy.controller.interceptor;

import com.jy.controller.interceptor.anno.RequiredLogin;
import com.jy.controller.util.AppContextUtil;
import com.jy.controller.util.RequestUtil;
import com.jy.entity.LoginLog;
import com.jy.entity.User;
import com.jy.service.LoginLogService;
import com.jy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

public class RequiredLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private UserService userService;

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
                    LoginLog loginLog = loginLogService.queryByTicket(ticket);
                    if (loginLog == null) {
                        validate = false;
                    } else {
                        User user = userService.queryById(loginLog.getUserId());
                        if (user == null) {
                            validate = false;
                        } else {
                            AppContextUtil.setCurrentUser(user);
                        }
                    }
                }
            }
        }
        AppContextUtil.setCurrentRequestTimePoint(new Date());
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