package com.jy.dao;

import com.jy.entity.Article;

import java.util.List;

public interface ArticleDao extends BaseDao<Article>{

    List<Article> selectArticleByArticleParam(ArticleParam param);

    class ArticleParam extends BaseParam{

        private String title;

        private Boolean recommended;

        public Boolean getRecommended() {
            return recommended;
        }

        public ArticleParam setRecommended(Boolean recommended) {
            this.recommended = recommended;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public ArticleParam setTitle(String title) {
            this.title = title;
            return this;
        }
    }
}
