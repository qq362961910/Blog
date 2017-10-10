package com.jy.service;

import com.jy.dao.BaseDao;

import java.util.List;

public interface BaseService<Entity> {

    /**
     * save
     */
    void save(Entity entity);

    /**
     * update
     */
    void update(Entity entity);

    /**
     * delte by ID
     */
    void deleteById(Long id);

    /**
     * query by ID
     */
    Entity queryById(Long id);

    /**
     * count
     */
    int countByParam(BaseDao.BaseParam param);

    /**
     * query list by param
     */
    List<Entity> queryListByParam(BaseDao.BaseParam param);
}
