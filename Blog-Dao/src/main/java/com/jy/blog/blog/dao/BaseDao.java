package com.jy.blog.blog.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseDao<Entity> {

    /**
     * save
     */
    void save(Entity entity);

    /**
     * update
     */
    void update(Entity entity);

    /**
     * delete by ID
     */
    void deleteById(Long id);

    /**
     * query by ID
     */
    Entity queryById(Long id);

    /**
     * count
     */
    int countByParam(BaseParam baseParam);

    /**
     * query list
     */
    List<Entity> queryListByParam(BaseParam baseParam);

    /**
     * 获取当前Session
     */
    Session getCurrentSession();

    /**
     * 获取实体类型
     */
    Class<Entity> getEntityClass();

    /**
     * 设置HQL参数
     */
    Query setHqlParam(Query query, String key, Object value);

    /**
     * flush session
     */
    void flushCurrentSession();

    abstract class BaseParam {

        /**
         * id
         */
        private Long id;

        /**
         * deleted
         */
        private Boolean deleted;

        /**
         * 限制条件
         */
        private Map<String, Object> restrictions = new HashMap<>();

        /**
         * 排序
         */
        private List<Order> orderList = new ArrayList<>();

        private int pageSize = 10;

        private int currentPage = 1;

        public Long getId() {
            return id;
        }

        public BaseParam setId(Long id) {
            this.id = id;
            return this;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        public Map<String, Object> getRestrictions() {
            return restrictions;
        }

        public void setRestrictions(Map<String, Object> restrictions) {
            this.restrictions = restrictions;
        }

        public List<Order> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<Order> orderList) {
            this.orderList = orderList;
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

        public Criteria buildCriteria(Session session, Class clazz) {
            Criteria criteria = buildCriteriaRestrictions(session, clazz);
            if (currentPage > 1) {
                int skip = (currentPage - 1) * pageSize;
                criteria.setFirstResult(skip + 1);
            }
            if (pageSize > 0) {
                criteria.setMaxResults(pageSize);
            }
            if (orderList != null && !orderList.isEmpty()) {
                for (Order order : orderList) {
                    criteria.addOrder(order);
                }
            }
            return criteria;
        }

        public Criteria buildCriteriaRestrictions(Session session, Class clazz) {
            Criteria criteria = session.createCriteria(clazz);
            if (id != null) {
                criteria.add(Restrictions.eq("id", id));
            }
            if (deleted != null) {
                criteria.add(Restrictions.eq("deleted", deleted));
            }
            if (restrictions.size() > 0) {
                for (Map.Entry<String, Object> entry : restrictions.entrySet()) {
                    criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                }
            }
            addSubRestrictions(criteria);
            return criteria;
        }

        public abstract void addSubRestrictions(Criteria criteria);

    }
}
