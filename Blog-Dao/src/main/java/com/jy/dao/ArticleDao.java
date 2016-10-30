package com.jy.dao;

import com.jy.entity.Article;

import java.util.List;

public interface ArticleDao extends BaseDao<Article>{

    List<Article> selectArticleByArticleParam(ArticleParam param);

    class ArticleParam extends BaseParam{

        private String title;

        public String getTitle() {
            return title;
        }

        public ArticleParam setTitle(String title) {
            this.title = title;
            return this;
        }
    }
}
