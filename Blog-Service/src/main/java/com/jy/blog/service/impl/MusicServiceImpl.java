package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.blog.dao.MusicDao;
import com.jy.blog.entity.Music;
import com.jy.blog.service.MusicService;
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
