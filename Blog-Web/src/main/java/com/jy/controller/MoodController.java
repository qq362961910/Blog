package com.jy.controller;

import com.jy.dao.MoodDao;
import com.jy.entity.Mood;
import com.jy.response.entity.MoodWrapper;
import com.jy.response.service.MoodWrapperService;
import com.jy.service.MoodService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/mood")
public class MoodController  extends BaseController {

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
        serviceParam.setOrderBy(new Pair<String, String>("mood.createTime", "desc"));
        int count = moodService.countMoodByMoodParam(serviceParam);
        List<Mood> moods;
        if (count == 0) {
            moods = Collections.emptyList();
        } else {
            moods = moodService.findMoodByMoodParam(serviceParam);
        }
        List<MoodWrapper> moodWrapperList = new ArrayList<>(moods.size());
        moods.forEach(mood -> moodWrapperList.add(moodWrapperService.buildMoodWrapper(mood)));
        Map<String, Object> result = new HashMap<>();
        result.put("size", count);
        result.put("articles", moodWrapperList);
        result.put(totalPageKey, (count + pageSize - 1) / pageSize);
        result.put(pageSizeKey, pageSize);
        result.put(currentPageKey, currentPage);
        return success(result);
    }

}
