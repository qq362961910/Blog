package com.jy.service.impl;

import com.jy.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Service;
import com.jy.service.BaseService;

import javax.annotation.Resource;
import java.io.Serializable;

@Service
public class BaseServiceImpl<Entity> implements BaseService <Entity>{

    @Resource
    private BaseDaoImpl<Entity> baseDao;

    public void save(Entity entity) {
        baseDao.save(entity);
    }

    public void update(Entity entity) {
        baseDao.update(entity);
    }

    public void deleteById(Class<Entity> clazz, Serializable id) {
        baseDao.deleteById(clazz, id);
    }

    public Entity queryById(Class<Entity> clazz, Serializable id) {
        return baseDao.queryById(clazz, id);
    }
}
