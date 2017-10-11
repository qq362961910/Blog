package com.jy.blog.blog.dao;

import com.jy.blog.entity.UserProfile;

public interface UserProfileDao extends BaseDao<UserProfile> {

    UserProfile selectByUsername(String username);

    UserProfile selectByOwnerId(Long ownerId);

}
