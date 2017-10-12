package com.jy.blog.response.service;

import com.jy.blog.blog.common.util.TimeUtil;
import com.jy.blog.entity.Mood;
import com.jy.blog.response.entity.BaseWrapper;
import com.jy.blog.response.entity.MoodWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoodWrapperService extends PageableWrapperService<Mood> {

    private static final MoodWrapper empty = new MoodWrapper();
    @Autowired
    private UserWrapperService userWrapperService;

    public MoodWrapper buildMoodWrapper(Mood mood) {
        if (mood == null) {
            return empty;
        }
        MoodWrapper moodWrapper = new MoodWrapper();
        moodWrapper.setCoverImage(mood.getCoverImage());
        moodWrapper.setContent(mood.getContent());
        moodWrapper.setCreateTime(TimeUtil.formatYYYYMMMDD(mood.getCreateTime()));
        moodWrapper.setOwner(userWrapperService.buildUserWrapper(mood.getOwner(), false));
        return moodWrapper;
    }

    @Override
    public List<? extends BaseWrapper> buildEntityListWrapper(List<Mood> moods) {
        if (moods == null) {
            return null;
        }
        List<MoodWrapper> moodWrapperList = new ArrayList<>(moods.size());
        moods.forEach(mood -> moodWrapperList.add(buildMoodWrapper(mood)));
        return moodWrapperList;
    }
}
