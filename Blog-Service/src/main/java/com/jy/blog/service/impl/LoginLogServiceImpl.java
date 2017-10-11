package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.blog.dao.LoginLogDao;
import com.jy.blog.entity.LoginLog;
import com.jy.blog.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public LoginLog queryByTicket(String ticket) {
        return loginLogDao.queryByTicket(ticket);
    }

    @Override
    public BaseDao<LoginLog> getBaseDao() {
        return loginLogDao;
    }
}
