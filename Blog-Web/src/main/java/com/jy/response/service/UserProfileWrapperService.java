package com.jy.response.service;

import com.jy.entity.Book;
import com.jy.entity.UserBookLike;
import com.jy.entity.UserProfile;
import com.jy.response.entity.BookWrapper;
import com.jy.response.entity.UserProfileWrapper;
import com.jy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileWrapperService extends ResponseBaseService{

    private static final UserProfileWrapper empty = new UserProfileWrapper();

    @Autowired
    private BookWrapperService bookWrapperService;
    @Autowired
    private BookService bookService;

    public UserProfileWrapper buildUserProfileWrapper(UserProfile userProfile, boolean needLikeBooks) {
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
        if (userProfile.getUserBookLikeList() != null && needLikeBooks) {
            List<BookWrapper> bookWrapperServiceList = new ArrayList<>(userProfile.getUserBookLikeList() .size());
            for (UserBookLike userBookLike: userProfile.getUserBookLikeList()) {
                Book book = bookService.queryById(Book.class, userBookLike.getUserBookEmbedKey().getBookId());
                bookWrapperServiceList.add(bookWrapperService.buildBookWrapper(book));
            }
            userProfileWrapper.setLikeBooks(bookWrapperServiceList);
        }
        return userProfileWrapper;
    }
}
