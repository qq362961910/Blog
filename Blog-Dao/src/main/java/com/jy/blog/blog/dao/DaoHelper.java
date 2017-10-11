package com.jy.blog.blog.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public interface DaoHelper {
    /**
     * 获取当前Session
     */
    Session getCurrentSession();

    /**
     * 设置HQL参数
     */
    Query setHqlParam(Query query, String key, Object value);

    /**
     * flush session
     */
    void flushCurrentSession();
}
