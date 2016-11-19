package com.jy.controller;

import com.jy.dao.ArticleDao;
import com.jy.entity.Article;
import com.jy.response.entity.ArticleWrapper;
import com.jy.response.service.ArticleWrapperService;
import com.jy.service.ArticleService;
import constants.ArticleType;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文章
 * */
@RequestMapping("article")
@RestController
public class ArticleController extends BaseController{

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleWrapperService articleWrapperService;
    /**
     *
     * 文章列表查詢（json）
     *
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     * */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> articleList(@RequestBody Map<String, Object> param){
        Integer pageSize =  (Integer) param.get(pageSizeKey);
        Integer currentPage = (Integer) param.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        }
        else {
            if (pageSize > 30) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach( article ->articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     *
     * 推荐文章（json）
     *
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     * */
    @RequestMapping(value = "recommendArticle", method = RequestMethod.POST)
    public Map<String, Object> recommendArticle(@RequestBody Map<String, Object> param){
        Integer pageSize =  (Integer) param.get(pageSizeKey);
        Integer currentPage = (Integer) param.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        }
        else {
            if (pageSize > 30) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        serviceParam.setRecommended(true);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     * 最新文章
     * */
    @RequestMapping(value = "latestArticle", method = RequestMethod.GET)
    public Map<String, Object> latestArticle(){
        Integer pageSize =  8;
        Integer currentPage = 1;
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setOrderBy(new Pair<>("create_time", "desc"));
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     * 查看排行文章
     * */
    @RequestMapping(value = "readCountRankArticle", method = RequestMethod.GET)
    public Map<String, Object> readCountRankArticle(){
        Integer pageSize =  5;
        Integer currentPage = 1;
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setOrderBy(new Pair<>("readCount", "desc"));
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     *
     * 个人模板（json）
     *
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     * */
    @RequestMapping(value = "htmlTemplateList", method = RequestMethod.POST)
    public Map<String, Object> htmlTemplateList(@RequestBody Map<String, Object> param){
        Integer pageSize =  6;
        Integer currentPage = 1;
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.HTML_TEMPLATE);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach( article ->articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

}
