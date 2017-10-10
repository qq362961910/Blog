package com.jy.controller;

import com.jy.common.constants.ServiceErrorCode;
import com.jy.controller.util.AppContextUtil;
import com.jy.entity.User;
import com.jy.response.service.UserWrapperService;
import com.jy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController extends BaseController {

    private static final String TICKET_KEY = "ticket";

    @Autowired
    private UserService userService;
    @Autowired
    private UserWrapperService userWrapperService;

    /**
     * 验证码登录
     * */
    @RequestMapping(value = "login/code", method = RequestMethod.POST)
    public Object loginByPhoneCode(@RequestBody Map<String, Object> param, HttpServletResponse response) {
        String code = (String)param.get("code");
        String phoneNumber = (String)param.get("phoneNumber");
        String realCode = AppContextUtil.getAndRemovePhoneLoginCode(phoneNumber);
        if (realCode == null || !realCode.equals(code)) {
            return fail(ServiceErrorCode.LOGIN_CODE_ERROR);
        }
        User user = userService.findUserByPhone(phoneNumber);
        if (user == null) {
            return fail(ServiceErrorCode.USER_NOT_FOUND);
        }
        String ticket = AppContextUtil.generateTicket(user);
        Cookie cookie = new Cookie(TICKET_KEY, ticket);
        response.addCookie(cookie);
        return success();
    }

    /**
     * 注册并登录
     * */
    @RequestMapping(value = "phone_reg_and_login", method = RequestMethod.POST)
    public Map<String, Object> phoneRegisterAndLogin(@RequestBody Map<String, Object> param, HttpServletResponse response) throws Exception{
        String code = (String)param.get("code");
        String phoneNumber = (String)param.get("phoneNumber");
        String realCode = AppContextUtil.getAndRemovePhoneLoginCode(phoneNumber);
        if (realCode == null || !realCode.equals(code)) {
            return fail(ServiceErrorCode.LOGIN_CODE_ERROR);
        }
        User user = userService.findUserByPhone(phoneNumber);
        if (user != null) {
            return fail(ServiceErrorCode.USER_ALREADY_EXISTS);
        }
        user = userService.save(phoneNumber);
        String ticket = AppContextUtil.generateTicket(user);
        Cookie cookie = new Cookie(TICKET_KEY, ticket);
        response.addCookie(cookie);
        Map<String, Object> data = new HashMap<>();
        data.put("user", userWrapperService.buildUserWrapper(user, false));
        return success(data);
    }
}
