package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.ArticleDao;
import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.entity.Article;
import com.jy.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public int countArticleByArticleParam(ArticleDao.ArticleParam param) {
        return articleDao.countByParam(param);
    }

    @Override
    public List<Article> findArticleByArticleParam(ArticleDao.ArticleParam param) {
        return articleDao.queryListByParam(param);
    }

    @Override
    public BaseDao<Article> getBaseDao() {
        return articleDao;
    }
}
