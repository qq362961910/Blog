package com.jy.service.impl;

import com.jy.dao.BaseDao;
import com.jy.dao.MusicDao;
import com.jy.entity.Music;
import com.jy.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl extends BaseServiceImpl<Music> implements MusicService {

    @Autowired
    private MusicDao musicDao;

    @Override
    public BaseDao<Music> getBaseDao() {
        return musicDao;
    }
}
