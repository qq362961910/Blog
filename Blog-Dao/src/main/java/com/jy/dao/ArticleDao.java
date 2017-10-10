package com.jy.dao;

import com.jy.entity.Article;
import constants.ArticleType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public interface ArticleDao extends BaseDao<Article> {

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

        @Override
        public void addSubRestrictions(Criteria criteria) {
            //Username
            if (username != null) {
                criteria.add(Restrictions.eq("owner.username", username));
            }
            //Title
            if (title != null) {
                criteria.add(Restrictions.like("title", "%" + title + "%"));
            }
            //recommend
            if (recommended != null) {
                criteria.add(Restrictions.eq("recommended", recommended));
            }
            //type
            if (articleType != null) {
                criteria.add(Restrictions.eq("type", articleType.getValue()));
            }
        }

        @Override
        public String toString() {
            return "ArticleParam{" +
                "title='" + title + '\'' +
                ", recommended=" + recommended +
                ", articleType=" + articleType +
                ", username='" + username + '\'' +
                '}';
        }
    }
}
