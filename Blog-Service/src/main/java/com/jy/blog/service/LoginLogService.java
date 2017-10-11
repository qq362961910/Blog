package com.jy.blog.service;

import com.jy.blog.entity.LoginLog;

public interface LoginLogService extends BaseService<LoginLog> {
    LoginLog queryByTicket(String ticket);
}
