package com.jy.service.impl;

import com.jy.dao.ArticleDao;
import com.jy.dao.BaseDao;
import com.jy.entity.Article;
import com.jy.service.ArticleService;
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
        return articleDao.countArticleByArticleParam(param);
    }

    @Override
    public List<Article> findArticleByArticleParam(ArticleDao.ArticleParam param) {
        return articleDao.selectArticleByArticleParam(param);
    }

    @Override
    public BaseDao<Article> getBaseDao() {
        return articleDao;
    }
}
