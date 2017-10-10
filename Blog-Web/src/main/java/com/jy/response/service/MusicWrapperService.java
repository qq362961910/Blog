package com.jy.response.service;

import com.jy.entity.Music;
import com.jy.response.entity.MusicWrapper;
import org.springframework.stereotype.Component;

@Component
public class MusicWrapperService {

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
}
