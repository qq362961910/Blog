package com.jy.response.service;

import com.jy.entity.User;
import com.jy.helper.QiNiuHelper;
import com.jy.response.entity.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserWrapperService extends ResponseBaseService {

    private static final UserWrapper empty = new UserWrapper();
    @Autowired
    private QiNiuHelper qiNiuHelper;

    public UserWrapper buildUserWrapper(User user) {
        if (user == null) {
            return empty;
        }
        UserWrapper userWrapper = new UserWrapper();
        userWrapper.setUsername(user.getUsername());
        userWrapper.setAvatar(StringUtils.isEmpty(user.getAvatar()) ? qiNiuHelper.getImg404() : user.getAvatar());
        return userWrapper;
    }
}
