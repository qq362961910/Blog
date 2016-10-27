package com.jy.service.impl;

import com.jy.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Service;
import com.jy.service.BaseService;

import javax.annotation.Resource;
import java.io.Serializable;

@Service
public class BaseServiceImpl<Entity> implements BaseService <Entity>{

    @Resource
    private BaseDaoImpl<Entity> baseDaoImpl;

    public void save(Entity entity) {
        baseDaoImpl.save(entity);
    }

    public void update(Entity entity) {
        baseDaoImpl.update(entity);
    }

    public void deleteById(Class<Entity> clazz, Serializable id) {
        baseDaoImpl.deleteById(clazz, id);
    }

    public Entity queryById(Class<Entity> clazz, Serializable id) {
        return baseDaoImpl.queryById(clazz, id);
    }
}
