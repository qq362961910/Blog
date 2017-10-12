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
         * 分页大小
         * */
        private int pageSize;
        /**
         * 总页面数
         * */
        private int totalPage;
        /**
         * 总数量
         * */
        private int totalSize;
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

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public List<Entity> getEntityList() {
            return entityList;
        }

        public void setEntityList(List<Entity> entityList) {
            this.entityList = entityList;
        }
    }
}
