package com.jy.response.service;

import com.jy.entity.Article;
import com.jy.response.entity.ArticleWrapper;
import com.jy.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ArticleWrapperService extends ResponseBaseService{

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
        articleWrapper.setOwner(userWrapperService.buildUserWrapper(article.getOwner()));
        articleWrapper.setCoverImage(StringUtils.hasText(article.getCoverImage()) ? article.getCoverImage() : qiNiuHelper.getImg404());
        return articleWrapper;
    }
}
