package com.jy.service.impl;

import com.jy.dao.BaseDao;
import com.jy.service.BaseService;

public abstract class BaseServiceImpl<Entity> implements BaseService<Entity> {

    public void save(Entity entity) {
        getBaseDao().save(entity);
    }

    public void update(Entity entity) {
        getBaseDao().update(entity);
    }

    public void deleteById(Long id) {
        getBaseDao().deleteById(id);
    }

    public Entity queryById(Long id) {
        return getBaseDao().queryById(id);
    }

    public abstract BaseDao<Entity> getBaseDao();

}
