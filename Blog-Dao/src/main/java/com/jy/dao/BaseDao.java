package com.jy.dao;

import org.hibernate.Session;

public interface BaseDao<Entity> {

    /**
     * save
     * */
    void save(Entity entity);

    /**
     * update
     * */
    void update(Entity entity);

    /**
     * delte by ID
     * */
    void deleteById(Class<Entity> clazz, Long id);

    /**
     * query by ID
     * */
    Entity queryById(Class<Entity> clazz, Long id);

    /**
     * 获取当前Session
     * */
    Session getCurrentSession();

    /**
     * flush session
     * */
    void flushCurrentSession();
}
