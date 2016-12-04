package com.jy.controller;

import com.jy.dao.ArticleDao;
import com.jy.entity.Article;
import com.jy.exception.ExceptionCode;
import com.jy.response.entity.ArticleWrapper;
import com.jy.response.service.ArticleWrapperService;
import com.jy.service.ArticleService;
import constants.ArticleType;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 文章
 */
@RequestMapping("article")
@RestController
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleWrapperService articleWrapperService;

    /**
     * 文章详情
     * */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Map<String, Object> articleDetail(@PathVariable("id") Long articleId) {
        Article article = articleService.queryById(Article.class, articleId);
        if (article == null) {
            return fail404();
        }
        ArticleWrapper articleWrapper = articleWrapperService.buildArticleWrapper(article);
        Map<String, Object> data = new HashMap<>();
        data.put("article", articleWrapper);
        return success(data);
    }

    /**
     * 推荐文章（json）
     * <p>
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     */
    @RequestMapping(value = "recommendArticle", method = RequestMethod.POST)
    public Map<String, Object> recommendArticle(@RequestBody Map<String, Object> param) {
        String username = (String)param.get("username");
        Integer pageSize = (Integer) param.get(pageSizeKey);
        Integer currentPage = (Integer) param.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        } else {
            if (pageSize > 30) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setUsername(username);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        serviceParam.setRecommended(true);
        List<Article> articles = articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     * 最新文章
     * <p>
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     */
    @RequestMapping(value = "latestArticle", method = RequestMethod.POST)
    public Map<String, Object> latestArticle(@RequestBody Map<String, Object> param) {
        String username = (String)param.get("username");
        Integer pageSize = (Integer) param.get(pageSizeKey);
        Integer currentPage = (Integer) param.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        } else {
            if (pageSize > 30) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setUsername(username);
        serviceParam.setOrderBy(new Pair<>("article.createTime", "desc"));
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles = articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     * 查看排行文章
     * <p>
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     */
    @RequestMapping(value = "readCountRankArticle", method = RequestMethod.POST)
    public Map<String, Object> readCountRankArticle(@RequestBody Map<String, Object> param) {
        String username = (String)param.get("username");
        Integer pageSize = (Integer) param.get(pageSizeKey);
        Integer currentPage = (Integer) param.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        } else {
            if (pageSize > 30) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setUsername(username);
        serviceParam.setOrderBy(new Pair<>("readCount", "desc"));
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles = articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     * 个人模板（json）
     * <p>
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     */
    @RequestMapping(value = "htmlTemplateList", method = RequestMethod.POST)
    public Map<String, Object> htmlTemplateList(@RequestBody Map<String, Object> param) {
        String username = (String)param.get("username");
        Integer pageSize = 6;
        Integer currentPage = 1;
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.HTML_TEMPLATE);
        serviceParam.setUsername(username);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles = articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     * 用户文章查詢（json）
     * <p>
     * optional params:
     * username string optional 用户名
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> articleList(@RequestBody Map<String, Object> param) {
        String username = (String)param.get("username");
        Integer pageSize = (Integer) param.get(pageSizeKey);
        Integer currentPage = (Integer) param.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        } else {
            if (pageSize > 30) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.COMMON);
        serviceParam.setUsername(username);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        serviceParam.setOrderBy(new Pair<String, String>("article.createTime","desc"));
        int count = articleService.countArticleByArticleParam(serviceParam);
        List<Article> articles;
        if (count == 0) {
            articles = Collections.emptyList();
        }
        else {
            articles = articleService.findArticleByArticleParam(serviceParam);
        }
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        Map<String,Object> result = new HashMap<>();
        result.put("size", count);
        result.put("articles", articleWrappers);
        result.put(totalPageKey, (count + pageSize -1)/pageSize);
        result.put(pageSizeKey, pageSize);
        result.put(currentPageKey, currentPage);
        return success(result);
    }

}
