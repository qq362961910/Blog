package com.jy.dao.impl;

import com.jy.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class BaseDaoImpl<Entity> implements BaseDao<Entity>{

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Entity entity) {
        if (entity != null) {
            sessionFactory.getCurrentSession().save(entity);
        }
    }

    public void update(Entity entity) {
        if (entity != null) {
            sessionFactory.getCurrentSession().update(entity);
        }
    }

    public void deleteById(Class<Entity> clazz, Serializable id) {
        if (id != null) {
            Entity entity = queryById(clazz, id);
            if (entity != null) {
                sessionFactory.getCurrentSession().delete(entity);
            }
        }
    }

    public Entity queryById(Class<Entity> clazz, Serializable id) {
        return (Entity) sessionFactory.getCurrentSession().get(clazz, id);
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void flushCurrentSession() {
        sessionFactory.getCurrentSession().flush();
    }
}
