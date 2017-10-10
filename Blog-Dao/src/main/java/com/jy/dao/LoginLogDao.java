package com.jy.dao;

import com.jy.entity.LoginLog;

public interface LoginLogDao extends BaseDao<LoginLog> {

    LoginLog queryByTicket(String ticket);
}
