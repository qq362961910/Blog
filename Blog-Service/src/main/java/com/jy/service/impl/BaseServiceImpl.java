package com.jy.service.impl;

import com.jy.dao.impl.BaseDaoImpl;
import com.jy.service.BaseService;
import com.jy.util.AppReflectUtil;

import javax.annotation.Resource;

public abstract class BaseServiceImpl<Entity> implements BaseService<Entity> {

    private Class<Entity> clazz;

    @Resource
    private BaseDaoImpl<Entity> baseDaoImpl;

    public void save(Entity entity) {
        baseDaoImpl.save(entity);
    }

    public void update(Entity entity) {
        baseDaoImpl.update(entity);
    }

    public void deleteById(Long id) {
        baseDaoImpl.deleteById(clazz, id);
    }

    public Entity queryById(Long id) {
        return baseDaoImpl.queryById(clazz, id);
    }

    public BaseServiceImpl() {
        clazz = AppReflectUtil.findTypeParam(this, BaseServiceImpl.class, "Entity");
    }

}
