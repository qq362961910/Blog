package com.jy.dao.impl;

import com.jy.dao.BaseDao;
import com.jy.dao.DaoHelper;
import com.jy.util.AppReflectUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDaoImpl<Entity> implements BaseDao<Entity> {

    private Class<Entity> clazz;

    @Autowired
    private DaoHelper daoHelper;

    public void save(Entity entity) {
        if (entity != null) {
            daoHelper.getCurrentSession().save(entity);
        }
    }

    public void update(Entity entity) {
        if (entity != null) {
            daoHelper.getCurrentSession().update(entity);
        }
    }

    public void deleteById(Long id) {
        if (id != null) {
            Entity entity = queryById(id);
            if (entity != null) {
                daoHelper.getCurrentSession().delete(entity);
            }
        }
    }

    public Entity queryById(Long id) {
        return daoHelper.getCurrentSession().get(clazz, id);
    }

    public Session getCurrentSession() {
        return daoHelper.getCurrentSession();
    }

    public void flushCurrentSession() {
        daoHelper.flushCurrentSession();
    }

    @Override
    public Query setHqlParam(Query query, String key, Object value) {
        return daoHelper.setHqlParam(query, key, value);
    }

    public BaseDaoImpl() {
        clazz = AppReflectUtil.findTypeParam(this, BaseDaoImpl.class, "Entity");
    }
}
