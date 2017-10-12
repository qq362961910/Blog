package com.jy.blog.response.service;

import com.jy.blog.entity.UserProfile;
import com.jy.blog.entity.User;
import com.jy.blog.response.entity.BaseWrapper;
import com.jy.blog.response.entity.UserWrapper;
import com.jy.blog.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserWrapperService extends PageableWrapperService<User> {

    private static final UserWrapper empty = new UserWrapper();

    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserProfileWrapperService userProfileWrapperService;

    public UserWrapper buildUserWrapper(User user, boolean needProfile) {
        if (user == null) {
            return empty;
        }
        UserWrapper userWrapper = new UserWrapper();
        userWrapper.setUsername(user.getUsername());
        userWrapper.setNickname(user.getNickname());
        userWrapper.setAvatar(StringUtils.isEmpty(user.getAvatar()) ? getQiNiuHelper().getImg404() : user.getAvatar());
        if (needProfile) {
            UserProfile userProfile = userProfileService.findByOwnerId(user.getId());
            userWrapper.setUserProfileWrapper(userProfileWrapperService.buildUserProfileWrapper(userProfile, true, true));
        }
        return userWrapper;
    }

    @Override
    public List<? extends BaseWrapper> buildEntityListWrapper(List<User> users) {
        if (users == null) {
            return null;
        }
        List<UserWrapper> userWrapperList = new ArrayList<>(users.size());
        users.forEach(user -> userWrapperList.add(buildUserWrapper(user,false)));
        return userWrapperList;
    }
}
