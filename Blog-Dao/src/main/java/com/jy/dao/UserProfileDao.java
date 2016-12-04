package com.jy.dao;

import com.jy.entity.UserProfile;

public interface UserProfileDao extends BaseDao<UserProfile> {

    UserProfile selectByUsername(String username);

    UserProfile selectByOwnerId(Long ownerId);

}
