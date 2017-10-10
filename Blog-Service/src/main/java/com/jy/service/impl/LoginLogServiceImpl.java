package com.jy.service.impl;

import com.jy.dao.BaseDao;
import com.jy.dao.LoginLogDao;
import com.jy.entity.LoginLog;
import com.jy.service.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog> implements LoginLogService {

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
