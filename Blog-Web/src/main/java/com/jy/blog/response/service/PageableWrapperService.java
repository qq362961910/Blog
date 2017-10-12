package com.jy.blog.response.service;

import com.jy.blog.helper.QiNiuHelper;
import com.jy.blog.response.entity.BaseWrapper;
import com.jy.blog.response.entity.PageableWrapper;
import com.jy.blog.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class PageableWrapperService<Entity> {

    @Autowired
    private QiNiuHelper qiNiuHelper;

    public PageableWrapper buildPageableWrapper(BaseService.Pageable<Entity> entityPageable) {
        if (entityPageable == null) {
            return null;
        }
        PageableWrapper pageableWrapper = new PageableWrapper();
        pageableWrapper.setCurrentPage(entityPageable.getCurrentPage());
        pageableWrapper.setPageSize(entityPageable.getPageSize());
        pageableWrapper.setTotalPage(entityPageable.getTotalPage());
        pageableWrapper.setTotalSize(entityPageable.getTotalSize());
        pageableWrapper.setObjectList(buildEntityListWrapper(entityPageable.getEntityList()));
        return pageableWrapper;
    }

    public abstract List<? extends BaseWrapper> buildEntityListWrapper(List<Entity> entityList);

    public QiNiuHelper getQiNiuHelper() {
        return qiNiuHelper;
    }
}
