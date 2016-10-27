package com.jy.service.impl;

import com.jy.entity.UserProfile;
import com.jy.service.UserProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile> implements UserProfileService {

}
