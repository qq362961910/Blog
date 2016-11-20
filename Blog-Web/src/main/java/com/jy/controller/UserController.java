package com.jy.controller;

import com.jy.dao.ArticleDao;
import com.jy.entity.Article;
import com.jy.entity.User;
import com.jy.response.entity.ArticleWrapper;
import com.jy.response.entity.UserWrapper;
import com.jy.response.service.ArticleWrapperService;
import com.jy.response.service.UserWrapperService;
import com.jy.service.ArticleService;
import com.jy.service.UserService;
import constants.ArticleType;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * */
@RequestMapping("user/{username}")
@RestController
public class UserController extends BaseController{

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleWrapperService articleWrapperService;
    @Autowired
    private UserWrapperService userWrapperService;

    /**
     * 用户首页
     * */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public ModelAndView userIndex(@PathVariable("username") String username){

        User user = userService.findUserByUsername(username);
        if (user == null){
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("index", param);
    }
    /**
     * 用户关于
     * */
    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView userAbout(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null){
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("about", param);
    }

    /**
     * 用户文章
     * */
    @RequestMapping(value = {"articles"}, method = RequestMethod.GET)
    public ModelAndView userArticle(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null){
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("articles", param);
    }

    /**
     * 闲言碎语
     * mood list
     * */
    @RequestMapping(value = {"moodlist"}, method = RequestMethod.GET)
    public ModelAndView userMoodList(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null){
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("moodlist", param);
    }

    /**
     * 分享
     * share
     * */
    @RequestMapping(value = {"share"}, method = RequestMethod.GET)
    public ModelAndView userShare(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null){
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("share", param);
    }
    /**
     * 学无止境
     * knowledge
     * */
    @RequestMapping(value = {"knowledge"}, method = RequestMethod.GET)
    public ModelAndView userKnowledge(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null){
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("knowledge", param);
    }
    /**
     * 留言
     * book
     * */
    @RequestMapping(value = {"book"}, method = RequestMethod.GET)
    public ModelAndView userBook(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null){
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("book", param);
    }


    /**
     * 用户信息
     * */
    @RequestMapping(value = "userinfo", method = RequestMethod.GET)
    public Map<String, Object> getUserInfo(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            user = new User();
        }
        UserWrapper userWrapper = userWrapperService.buildUserWrapper(user);
        return success(userWrapper);
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
    public Map<String, Object> recommendArticle(@PathVariable("username") String username, @RequestBody Map<String, Object> param){
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
        serviceParam.setUsername(username);
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
     *
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     * */
    @RequestMapping(value = "latestArticle", method = RequestMethod.POST)
    public Map<String, Object> latestArticle(@PathVariable("username") String username,@RequestBody Map<String, Object> param){
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
        serviceParam.setUsername(username);
        serviceParam.setOrderBy(new Pair<>("article.createTime", "desc"));
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach(article -> articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

    /**
     * 查看排行文章
     *
     * optional params:
     * pageSize int optional default: 10 description: 分頁大小
     * currentPage int optional default: 1 description: 當前頁面
     * */
    @RequestMapping(value = "readCountRankArticle", method = RequestMethod.POST)
    public Map<String, Object> readCountRankArticle(@PathVariable("username") String username,@RequestBody Map<String, Object> param){
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
        serviceParam.setUsername(username);
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
    public Map<String, Object> htmlTemplateList(@PathVariable("username") String username,@RequestBody Map<String, Object> param){
        Integer pageSize =  6;
        Integer currentPage = 1;
        ArticleDao.ArticleParam serviceParam = new ArticleDao.ArticleParam();
        serviceParam.setArticleType(ArticleType.HTML_TEMPLATE);
        serviceParam.setUsername(username);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        List<Article> articles =  articleService.findArticleByArticleParam(serviceParam);
        List<ArticleWrapper> articleWrappers = new ArrayList<>(articles.size());
        articles.forEach( article ->articleWrappers.add(articleWrapperService.buildArticleWrapper(article)));
        return success(articleWrappers);
    }

}
