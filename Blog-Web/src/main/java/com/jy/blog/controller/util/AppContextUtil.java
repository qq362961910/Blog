package com.jy.blog.controller.util;


import com.jy.blog.blog.common.util.RandomUtil;
import com.jy.blog.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppContextUtil {

    private static final ThreadLocal<User> requestThreadUser = new ThreadLocal<>();
    private static final ThreadLocal<Date> requestTimePoint = new ThreadLocal<>();
    private static final Map<String, String> cachedPhoneCode = new HashMap<>();
    private static final Map<String, User> cachedTicket = new HashMap<>();

    /**
     * 设置当前线程User
     */
    public static void setCurrentUser(User user) {
        if (user == null) {
            return;
        }
        requestThreadUser.set(user);
    }

    /**
     * 获取当前线程User
     */
    public static User getCurrentUser() {
        return requestThreadUser.get();
    }

    /**
     * 去除当前线程User
     */
    public static void removeCurrentUser() {
        requestThreadUser.remove();
    }

    /**
     * 设置当前线程系统时间
     */
    public static void setCurrentRequestTimePoint(Date now) {
        requestTimePoint.set(now);
    }

    /**
     * 获取当前线程系统时间
     */
    public static Date getCurrentRequestTimePoint() {
        Date now = requestTimePoint.get();
        return now == null ? new Date() : now;
    }

    /**
     * 去除当前线程系统时间
     */
    public static void removeCurrentRequestTimePoint() {
        requestTimePoint.remove();
    }

    public static void initRequestContext(User user, Date now) {
        setCurrentUser(user);
        setCurrentRequestTimePoint(now);
    }

    public static void clearRequestContext() {
        removeCurrentUser();
        removeCurrentRequestTimePoint();
    }

    /**
     * 生成手机验证码
     */
    public static String generatePhoneLoginCode(String phone) {
        String code = RandomUtil.randomNumberString(phone, (byte) 6);
        cachedPhoneCode.put(phone, code);
        return code;
    }

    /**
     * 生成手机验证码
     */
    public static String getAndRemovePhoneLoginCode(String phone) {
        return cachedPhoneCode.remove(phone);
    }

    /**
     * 生成ticket
     */
    public static String generateTicket(User user) {
        String ticket = RandomUtil.randomNumberString(user.getPhone(), (byte) 32);
        cachedTicket.put(ticket, user);
        return ticket;
    }
    public static void removeTicket(String ticket) {
        if (ticket != null) {
            cachedTicket.remove(ticket);
        }
    }

    /**
     * 通过ticket获取用户
     */
    public static User getUserByTicket(String ticket) {
        return cachedTicket.get(ticket);
    }
}
