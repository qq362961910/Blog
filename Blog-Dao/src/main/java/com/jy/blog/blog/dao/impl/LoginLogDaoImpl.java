package com.jy.blog.blog.dao.impl;

import com.jy.blog.blog.dao.LoginLogDao;
import com.jy.blog.entity.LoginLog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDaoImpl extends BaseDaoImpl<LoginLog> implements LoginLogDao {

    @Override
    public LoginLog queryByTicket(String ticket) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select loginLog from com.jy.blog.entity.LoginLog loginLog where loginLog.ticket = :ticket");
        query.setString("ticket", ticket);
        return (LoginLog) query.uniqueResult();
    }
}
