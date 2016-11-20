package com.jy.dao.impl;

import com.jy.dao.ArticleDao;
import com.jy.entity.Article;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> selectArticleByArticleParam(ArticleParam param) {

        StringBuilder hqlBuilder = new StringBuilder("select article from Article article where article.deleted = :deleted");
        Map<String, Object> sqlParam = new HashMap<>();
        sqlParam.put("deleted", false);
        if (param != null) {
            //ID
            if (param.getId() != null) {
                hqlBuilder.append(" and article.id = :id");
                sqlParam.put("id", param.getId());
            }
            //Username
            if (param.getUsername() != null) {
                hqlBuilder.append(" and article.owner.username = :username");
                sqlParam.put("username", param.getUsername());
            }
            //Title
            if (StringUtils.hasText(param.getTitle())) {
                hqlBuilder.append(" and article.title like :title");
                sqlParam.put("title", "%" + param.getTitle() + "%");
            }
            //recommend
            if (param.getRecommended() != null) {
                hqlBuilder.append(" and article.recommended = :recommended");
                sqlParam.put("recommended", param.getRecommended());
            }
            //type
            if (param.getArticleType() != null) {
                hqlBuilder.append(" and article.type = :type");
                sqlParam.put("type", param.getArticleType().getValue());
            }
            if (param.getOrderBy() != null && param.getOrderBy().size() > 0) {
                hqlBuilder.append(" order by ");
                param.getOrderBy().forEach(column -> {
                    hqlBuilder.append(column.getKey());
                    hqlBuilder.append(" ");
                    hqlBuilder.append(column.getValue());
                    hqlBuilder.append(",");
                });
                hqlBuilder.deleteCharAt(hqlBuilder.length() - 1);
            }
        }
        Query query = getCurrentSession().createQuery(hqlBuilder.toString());
        for (Map.Entry<String, Object> entry : sqlParam.entrySet()) {
            setHqlParam(query, entry.getKey(), entry.getValue());
        }

        if (param != null) {
            query.setFirstResult((param.getCurrentPage() - 1) * param.getPageSize());
            query.setMaxResults(param.getPageSize());
        }
        return query.list();
    }
}
