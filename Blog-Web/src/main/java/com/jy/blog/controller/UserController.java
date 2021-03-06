package com.jy.blog.controller;

import com.jy.blog.entity.User;
import com.jy.blog.response.entity.UserWrapper;
import com.jy.blog.response.service.UserWrapperService;
import com.jy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 */
@RequestMapping("user/{username}")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserWrapperService userWrapperService;


    /**
     * 用户首页
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public ModelAndView userIndex(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("index", param);
    }

    /**
     * 用户关于
     */
    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView userAbout(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("about", param);
    }

    /**
     * 用户文章
     */
    @RequestMapping(value = {"articles"}, method = RequestMethod.GET)
    public ModelAndView userArticles(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("articles", param);
    }

    /**
     * 闲言碎语
     * mood list
     */
    @RequestMapping(value = {"mood_list"}, method = RequestMethod.GET)
    public ModelAndView userMoodList(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("mood-list", param);
    }

    /**
     * 分享
     * share
     */
    @RequestMapping(value = {"template_share"}, method = RequestMethod.GET)
    public ModelAndView userShare(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("template-share", param);
    }

    /**
     * 学无止境
     * knowledge
     */
    @RequestMapping(value = {"knowledge"}, method = RequestMethod.GET)
    public ModelAndView userKnowledge(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("knowledge", param);
    }

    /**
     * 留言
     * book
     */
    @RequestMapping(value = {"book"}, method = RequestMethod.GET)
    public ModelAndView userBook(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("book", param);
    }

    /**
     * 文章详情
     * book
     */
    @RequestMapping(value = {"article/{id}"}, method = RequestMethod.GET)
    public ModelAndView userArticle(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ModelAndView("404");
        }
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return new ModelAndView("article-detail", param);
    }


    /**
     * 用户信息
     */
    @RequestMapping(value = "user_info", method = RequestMethod.GET)
    public Map<String, Object> getUserInfo(@PathVariable("username") String username) throws Exception {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            user = new User();
        }
        UserWrapper userWrapper = userWrapperService.buildUserWrapper(user, true);
        Map<String, Object> data = new HashMap<>();
        data.put("userInfo", userWrapper);
        return success(data);
    }


}
