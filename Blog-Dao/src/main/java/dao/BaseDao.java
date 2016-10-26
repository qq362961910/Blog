package dao;

import org.hibernate.Session;

import java.io.Serializable;

public interface BaseDao<Entity> {

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

    /**
     * 获取当前Session
     * */
    Session getCurrentSession();

    /**
     * flush session
     * */
    void flushCurrentSession();
}
