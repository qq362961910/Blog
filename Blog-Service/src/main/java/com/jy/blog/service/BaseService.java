package com.jy.blog.service;

import com.jy.blog.blog.dao.BaseDao;

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

    /**
     * query pageable list
     * */
    Pageable<Entity> queryPageableListByParam(BaseDao.BaseParam param);


    class Pageable<Entity> {
        /**
         * 当前页面
         * */
        private int currentPage;
        /**
         * 总页面数
         * */
        private int totalPage;
        /**
         * entity list
         * */
        private List<Entity> entityList;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<Entity> getEntityList() {
            return entityList;
        }

        public void setEntityList(List<Entity> entityList) {
            this.entityList = entityList;
        }
    }
}
