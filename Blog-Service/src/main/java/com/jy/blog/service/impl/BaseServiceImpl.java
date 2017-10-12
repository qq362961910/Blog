package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.service.BaseService;

import java.util.Collections;
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

    @Override
    public Pageable<Entity> queryPageableListByParam(BaseDao.BaseParam param) {
        int count = countByParam(param);
        List<Entity> entityList;
        if (count > 0) {
            entityList = queryListByParam(param);
        }
        else {
            entityList = Collections.emptyList();
        }
        Pageable<Entity> entityPageable = new Pageable<>();
        entityPageable.setCurrentPage(param.getCurrentPage());
        entityPageable.setTotalPage((count + param.getPageSize() - 1) / param.getPageSize());
        entityPageable.setEntityList(entityList);
        return entityPageable;
    }
    public abstract BaseDao<Entity> getBaseDao();

}
