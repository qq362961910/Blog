package com.jy.blog.service;

import com.jy.blog.blog.dao.ArticleDao;
import com.jy.blog.entity.Article;

import java.util.List;

public interface ArticleService extends BaseService<Article> {

    int countArticleByArticleParam(ArticleDao.ArticleParam param);

    List<Article> findArticleByArticleParam(ArticleDao.ArticleParam param);
}
