package com.jy.service;

import com.jy.dao.ArticleDao;
import com.jy.entity.Article;

import java.util.List;

public interface ArticleService extends BaseService<Article> {

    int countArticleByArticleParam(ArticleDao.ArticleParam param);

    List<Article> findArticleByArticleParam(ArticleDao.ArticleParam param);
}
