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
import java.security.NoSuchAlgorithmException;
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
     * 密码登录
     * */
    @RequestMapping(value = "login/password", method = {RequestMethod.GET, RequestMethod.POST})
    public Object loginByPassword(@RequestParam Map<String, Object> param, HttpServletResponse response) throws NoSuchAlgorithmException {
        return restLoginByPassword(param, response);
    }

    /**
     * 密码登录
     * */
    @RequestMapping(value = "rest/login/password", method = RequestMethod.POST)
    public Object restLoginByPassword(@RequestBody Map<String, Object> param, HttpServletResponse response) throws NoSuchAlgorithmException {
        String username = (String) param.get("username");
        String password = (String) param.get("password");
        User user = userService.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            return fail(ServiceErrorCode.LOGIN_USERNAME_OR_PASSWORD_ERROR);
        }
        writeTicket(user, response);
        return successResponse(user);
    }

    /**
     * 验证码登录
     */
    @RequestMapping(value = "login/code", method = RequestMethod.POST)
    public Object loginByPhoneCode(@RequestBody Map<String, Object> param, HttpServletResponse response) {
        String code = (String) param.get("code");
        String phoneNumber = (String) param.get("phoneNumber");
        String realCode = AppContextUtil.getAndRemovePhoneLoginCode(phoneNumber);
        if (realCode == null || !realCode.equals(code)) {
            return fail(ServiceErrorCode.LOGIN_CODE_ERROR);
        }
        User user = userService.findUserByPhone(phoneNumber);
        if (user == null) {
            return fail(ServiceErrorCode.USER_NOT_FOUND);
        }
        writeTicket(user, response);
        return successResponse(user);
    }

    /**
     * 注册并登录
     */
    @RequestMapping(value = "phone_reg_and_login", method = RequestMethod.POST)
    public Map<String, Object> phoneRegisterAndLogin(@RequestBody Map<String, Object> param, HttpServletResponse response) throws Exception {
        String code = (String) param.get("code");
        String phoneNumber = (String) param.get("phoneNumber");
        String realCode = AppContextUtil.getAndRemovePhoneLoginCode(phoneNumber);
        if (realCode == null || !realCode.equals(code)) {
            return fail(ServiceErrorCode.LOGIN_CODE_ERROR);
        }
        User user = userService.findUserByPhone(phoneNumber);
        if (user != null) {
            return fail(ServiceErrorCode.USER_ALREADY_EXISTS);
        }
        user = userService.save(phoneNumber);
        writeTicket(user, response);
        return successResponse(user);
    }

    private Map<String, Object> successResponse(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", userWrapperService.buildUserWrapper(user, false));
        return success(data);
    }

    private void writeTicket(User user, HttpServletResponse response) {
        String ticket = AppContextUtil.generateTicket(user);
        Cookie cookie = new Cookie(TICKET_KEY, ticket);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
