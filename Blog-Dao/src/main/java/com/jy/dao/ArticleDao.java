package com.jy.dao;

import com.jy.entity.Article;
import constants.ArticleType;

import java.util.List;

public interface ArticleDao extends BaseDao<Article> {

    List<Article> selectArticleByArticleParam(ArticleParam param);

    class ArticleParam extends BaseParam {

        private String title;

        private Boolean recommended;

        private ArticleType articleType;

        private String username;

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

        public ArticleType getArticleType() {
            return articleType;
        }

        public ArticleParam setArticleType(ArticleType articleType) {
            this.articleType = articleType;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public ArticleParam setUsername(String username) {
            this.username = username;
            return this;
        }
    }
}
