package com.jy.blog.response.service;

import com.jy.blog.blog.common.util.TimeUtil;
import com.jy.blog.entity.Article;
import com.jy.blog.response.entity.ArticleWrapper;
import com.jy.blog.response.entity.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleWrapperService extends PageableWrapperService<Article> {

    private static final ArticleWrapper empty = new ArticleWrapper();

    @Autowired
    private UserWrapperService userWrapperService;

    public ArticleWrapper buildArticleWrapper(Article article) {
        if (article == null) {
            return empty;
        }
        ArticleWrapper articleWrapper = new ArticleWrapper();
        articleWrapper.setId(article.getId());
        articleWrapper.setTitle(article.getTitle());
        articleWrapper.setSummary(article.getSummary());
        articleWrapper.setContent(article.getContent());
        articleWrapper.setReadCount(article.getReadCount());
        articleWrapper.setLikeCount(article.getLikeCount());
        articleWrapper.setCreateTime(TimeUtil.formatYYYYMMMDDHHMMSS(article.getCreateTime()));
        articleWrapper.setKeyworks(article.getKeyworks());
        articleWrapper.setOwner(userWrapperService.buildUserWrapper(article.getOwner(), false));
        articleWrapper.setCoverImage(StringUtils.hasText(article.getCoverImage()) ? article.getCoverImage() : getQiNiuHelper().getImg404());
        return articleWrapper;
    }

    @Override
    public List<? extends BaseWrapper> buildEntityListWrapper(List<Article> articles) {
        if (articles == null) {
            return null;
        }
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(buildArticleWrapper(article)));
        return articleWrappers;
    }
}
