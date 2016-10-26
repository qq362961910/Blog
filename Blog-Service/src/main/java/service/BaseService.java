package service;

import java.io.Serializable;

public interface BaseService<Entity> {

    /**
     * save
     * */
    void save(Entity entity);

    /**
     * update
     * */
    void update(Entity entity);

    /**
     * delte by ID
     * */
    void deleteById(Class<Entity> clazz, Serializable id);

    /**
     * query by ID
     * */
    Entity queryById(Class<Entity> clazz, Serializable id);
}
