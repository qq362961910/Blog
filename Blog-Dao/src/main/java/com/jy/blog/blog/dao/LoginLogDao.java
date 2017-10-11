package com.jy.blog.blog.dao;

import com.jy.blog.entity.LoginLog;

public interface LoginLogDao extends BaseDao<LoginLog> {

    LoginLog queryByTicket(String ticket);
}
