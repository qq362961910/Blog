package com.jy.response.service;

import com.jy.entity.User;
import com.jy.entity.UserProfile;
import com.jy.response.entity.UserWrapper;
import com.jy.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserWrapperService extends ResponseBaseService {

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
        userWrapper.setAvatar(StringUtils.isEmpty(user.getAvatar()) ? qiNiuHelper.getImg404() : user.getAvatar());
        if (needProfile) {
            UserProfile userProfile = userProfileService.findByOwnerId(user.getId());
            userWrapper.setUserProfileWrapper(userProfileWrapperService.buildUserProfileWrapper(userProfile, true));
        }
        return userWrapper;
    }
}
