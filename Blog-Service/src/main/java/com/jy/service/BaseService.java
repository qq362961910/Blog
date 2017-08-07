package com.jy.service;

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
}
