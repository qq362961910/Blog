package com.jy.service;

import com.jy.entity.LoginLog;

public interface LoginLogService extends BaseService<LoginLog> {
    LoginLog queryByTicket(String ticket);
}
