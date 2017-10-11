package com.jy.blog.service;

import com.jy.blog.blog.dao.MoodDao;
import com.jy.blog.entity.Mood;

import java.util.List;

public interface MoodService extends BaseService<Mood> {

    int countMoodByMoodParam(MoodDao.MoodParam param);

    List<Mood> findMoodByMoodParam(MoodDao.MoodParam param);
}
