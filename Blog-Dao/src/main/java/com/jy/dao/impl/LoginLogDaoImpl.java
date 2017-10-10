package com.jy.dao.impl;

import com.jy.dao.LoginLogDao;
import com.jy.entity.LoginLog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDaoImpl extends BaseDaoImpl<LoginLog> implements LoginLogDao {

    @Override
    public LoginLog queryByTicket(String ticket) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select loginLog from com.jy.entity.LoginLog loginLog where loginLog.ticket = :ticket");
        query.setString("ticket", ticket);
        return (LoginLog)query.uniqueResult();
    }
}
