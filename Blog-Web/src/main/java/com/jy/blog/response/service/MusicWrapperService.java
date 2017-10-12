package com.jy.blog.response.service;

import com.jy.blog.entity.Music;
import com.jy.blog.response.entity.BaseWrapper;
import com.jy.blog.response.entity.MusicWrapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicWrapperService extends PageableWrapperService<Music>{

    private static final MusicWrapper empty = new MusicWrapper();

    public MusicWrapper buildMusicWrapper(Music music) {
        if (music == null) {
            return empty;
        }
        MusicWrapper musicWrapper = new MusicWrapper();
        musicWrapper.setId(music.getId());
        musicWrapper.setName(music.getName());
        return musicWrapper;
    }

    @Override
    public List<? extends BaseWrapper> buildEntityListWrapper(List<Music> musicList) {
        if (musicList == null) {
            return null;
        }
        List<MusicWrapper> musicWrapperList = new ArrayList<>(musicList.size());
        musicList.forEach(music -> musicWrapperList.add(buildMusicWrapper(music)));
        return musicWrapperList;
    }
}
