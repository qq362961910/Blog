package com.jy.blog.controller;

import com.jy.blog.blog.dao.MoodDao;
import com.jy.blog.entity.Mood;
import com.jy.blog.service.BaseService;
import com.jy.blog.service.MoodService;
import com.jy.blog.response.service.MoodWrapperService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/mood")
public class MoodController extends BaseController {

    @Autowired
    private MoodService moodService;
    @Autowired
    private MoodWrapperService moodWrapperService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> moodList(@RequestBody Map<String, Object> param) {
        String username = (String) param.get("username");
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
        MoodDao.MoodParam serviceParam = new MoodDao.MoodParam();
        serviceParam.setUsername(username);
        serviceParam.setPageSize(pageSize);
        serviceParam.setCurrentPage(currentPage);
        serviceParam.getOrderList().add(Order.desc("createTime"));
        BaseService.Pageable<Mood> moodPageable = moodService.queryPageableListByParam(serviceParam);
        return success(createPageableMap(moodWrapperService.buildPageableWrapper(moodPageable)));
    }

}
