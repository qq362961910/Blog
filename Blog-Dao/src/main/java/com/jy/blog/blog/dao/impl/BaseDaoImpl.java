package com.jy.blog.blog.dao.impl;

import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.blog.dao.DaoHelper;
import com.jy.blog.blog.common.util.AppReflectUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
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

    @Override
    public int countByParam(BaseParam param) {
        Criteria criteria = param.buildCriteriaRestrictions(getCurrentSession(), clazz);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).intValue();
    }

    @Override
    public List<Entity> queryListByParam(BaseParam param) {
        Criteria criteria = param.buildCriteria(getCurrentSession(), clazz);
        return criteria.list();
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

    @Override
    public Class<Entity> getEntityClass() {
        return clazz;
    }

    public BaseDaoImpl() {
        clazz = AppReflectUtil.findTypeParam(this, BaseDaoImpl.class, "Entity");
    }
}
