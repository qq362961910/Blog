package com.jy.service;

import com.jy.dao.MoodDao;
import com.jy.entity.Mood;

import java.util.List;

public interface MoodService extends BaseService<Mood> {

    int countMoodByMoodParam(MoodDao.MoodParam param);

    List<Mood> findMoodByMoodParam(MoodDao.MoodParam param);
}
