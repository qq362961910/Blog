package com.jy.blog.response.service;

import com.jy.blog.entity.*;
import com.jy.blog.response.entity.*;
import com.jy.blog.service.BookService;
import com.jy.blog.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileWrapperService extends PageableWrapperService<UserProfile> {

    private static final UserProfileWrapper empty = new UserProfileWrapper();

    @Autowired
    private BookWrapperService bookWrapperService;
    @Autowired
    private MusicWrapperService musicWrapperService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MusicService musicService;

    public UserProfileWrapper buildUserProfileWrapper(UserProfile userProfile, boolean needLikeBooks, boolean needLikeMusic) {
        if (userProfile == null) {
            return empty;
        }
        UserProfileWrapper userProfileWrapper = new UserProfileWrapper();
        userProfileWrapper.setId(userProfile.getId());
        userProfileWrapper.setRealName(userProfile.getRealName());
        userProfileWrapper.setOccupation(userProfile.getOccupation());
        userProfileWrapper.setAddress(userProfile.getAddress());
        userProfileWrapper.setNativePlace(userProfile.getNativePlace());
        userProfileWrapper.setSelfIntroduction(userProfile.getSelfIntroduction());
        if (userProfile.getBirthday() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userProfileWrapper.setBirthday(sdf.format(userProfile.getBirthday()));
        }
        if (needLikeBooks && userProfile.getUserBookLikeList() != null) {
            List<BookWrapper> bookWrapperServiceList = new ArrayList<>(userProfile.getUserBookLikeList().size());
            for (UserBookLike userBookLike : userProfile.getUserBookLikeList()) {
                Book book = bookService.queryById(userBookLike.getUserBookEmbedKey().getBookId());
                bookWrapperServiceList.add(bookWrapperService.buildBookWrapper(book));
            }
            userProfileWrapper.setLikeBooks(bookWrapperServiceList);
        }
        if (needLikeMusic && userProfile.getUserMusicLikeList() != null) {
            List<MusicWrapper> musicWrapperList = new ArrayList<>(userProfile.getUserMusicLikeList().size());
            for (UserMusicLike userMusicLike : userProfile.getUserMusicLikeList()) {
                Music music = musicService.queryById(userMusicLike.getUserMusicEmbedKey().getMusicId());
                musicWrapperList.add(musicWrapperService.buildMusicWrapper(music));
            }
            userProfileWrapper.setLikeMusics(musicWrapperList);
        }

        return userProfileWrapper;
    }

    @Override
    public List<? extends BaseWrapper> buildEntityListWrapper(List<UserProfile> userProfiles) {
        if (userProfiles == null) {
            return null;
        }
        List<UserProfileWrapper> userProfileWrapperList = new ArrayList<>(userProfiles.size());
        userProfiles.forEach(userProfile -> userProfileWrapperList.add(buildUserProfileWrapper(userProfile, false, false)));
        return userProfileWrapperList;
    }
}
