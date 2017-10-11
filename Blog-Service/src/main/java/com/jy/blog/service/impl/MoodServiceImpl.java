package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.blog.dao.MoodDao;
import com.jy.blog.entity.Mood;
import com.jy.blog.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodServiceImpl extends BaseServiceImpl<Mood> implements MoodService {

    @Autowired
    private MoodDao moodDao;

    @Override
    public int countMoodByMoodParam(MoodDao.MoodParam param) {
        return moodDao.countByParam(param);
    }

    @Override
    public List<Mood> findMoodByMoodParam(MoodDao.MoodParam param) {
        return moodDao.queryListByParam(param);
    }

    @Override
    public BaseDao<Mood> getBaseDao() {
        return moodDao;
    }
}
