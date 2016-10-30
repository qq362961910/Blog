package com.jy.response.service;

import com.jy.entity.User;
import com.jy.response.entity.UserWrapper;
import org.springframework.stereotype.Component;

@Component
public class UserWrapperService {

    private static final UserWrapper empty = new UserWrapper();

    public UserWrapper buildUserWrapper(User user) {
        if (user == null) {
            return empty;
        }
        UserWrapper userWrapper = new UserWrapper();
        userWrapper.setUsername(user.getUsername());
        return userWrapper;
    }
}
