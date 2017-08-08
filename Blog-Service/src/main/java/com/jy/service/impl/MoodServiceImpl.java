package com.jy.service.impl;

import com.jy.dao.BaseDao;
import com.jy.dao.MoodDao;
import com.jy.entity.Mood;
import com.jy.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodServiceImpl extends BaseServiceImpl<Mood> implements MoodService {

    @Autowired
    private MoodDao moodDao;

    @Override
    public int countMoodByMoodParam(MoodDao.MoodParam param) {
        return moodDao.countMoodByMoodParam(param);
    }

    @Override
    public List<Mood> findMoodByMoodParam(MoodDao.MoodParam param) {
        return moodDao.selectMoodByMoodParam(param);
    }

    @Override
    public BaseDao<Mood> getBaseDao() {
        return moodDao;
    }
}
