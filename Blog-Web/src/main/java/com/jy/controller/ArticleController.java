package com.jy.controller;

import com.jy.dao.ArticleDao;
import com.jy.entity.Article;
import com.jy.response.entity.ArticleWrapper;
import com.jy.response.service.ArticleWrapperService;
import com.jy.service.ArticleService;
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
        Integer pageSize =  (Integer) param.get("pageSize");
        Integer currentPage = (Integer) param.get("currentPage");
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
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach( article -> {
            articleWrappers.add(articleWrapperService.buildArticleWrapper(article));
        });
        return success(articleWrappers);
    }
}
