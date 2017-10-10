package com.jy.controller.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class RequestUtil {

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
