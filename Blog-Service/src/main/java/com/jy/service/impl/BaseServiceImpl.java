package com.jy.service.impl;

import com.jy.dao.BaseDao;
import com.jy.service.BaseService;

import java.util.List;

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

    @Override
    public int countByParam(BaseDao.BaseParam param) {
        return getBaseDao().countByParam(param);
    }

    @Override
    public List<Entity> queryListByParam(BaseDao.BaseParam param) {
        return getBaseDao().queryListByParam(param);
    }

    public abstract BaseDao<Entity> getBaseDao();

}
