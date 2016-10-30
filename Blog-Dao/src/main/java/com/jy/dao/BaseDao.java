package com.jy.dao;

import javafx.util.Pair;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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
    void deleteById(Class<Entity> clazz, Long id);

    /**
     * query by ID
     * */
    Entity queryById(Class<Entity> clazz, Long id);

    /**
     * 获取当前Session
     * */
    Session getCurrentSession();

    /**
     * 设置HQL参数
     * */
    Query setHqlParam(Query query, String key, Object value);

    /**
     * flush session
     * */
    void flushCurrentSession();

    class BaseParam {

        /**
         * 正排序
         * */
        public static final String ASC = "ASC";
        /**
         * 到排序
         * */
        public static final String DESC = "DESC";

        /**
         * id
         * */
        private Long id;

        private int pageSize = 10;

        private int currentPage = 1;

        private List<Pair<String, String>> orderBy;

        private String diection = ASC;

        public Long getId() {
            return id;
        }

        public BaseParam setId(Long id) {
            this.id = id;
            return this;
        }

        public int getPageSize() {
            return pageSize;
        }

        public BaseParam setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public BaseParam setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public List<Pair<String, String>> getOrderBy() {
            return orderBy;
        }

        public BaseParam setOrderBy(List<Pair<String, String>> orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public String getDiection() {
            return diection;
        }

        public BaseParam setDiection(String diection) {
            this.diection = diection;
            return this;
        }
    }
}
