package com.jy.response.service;

import com.jy.entity.LeaveMessage;
import com.jy.response.entity.LeaveMessageWrapper;
import com.jy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveMessageWrapperService extends ResponseBaseService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserWrapperService userWrapperService;

    public LeaveMessageWrapper buildLeaveMessageWrapper(LeaveMessage leaveMessage) {
        if (leaveMessage == null) {
            return null;
        }
        LeaveMessageWrapper leaveMessageWrapper = new LeaveMessageWrapper();
        leaveMessageWrapper.setFromUser(userWrapperService.buildUserWrapper(userService.queryById(leaveMessage.getFromUserId()), false));
        leaveMessageWrapper.setToUser(userWrapperService.buildUserWrapper(userService.queryById(leaveMessage.getToUserId()), false));
        leaveMessageWrapper.setContent(leaveMessage.getContent());
        leaveMessageWrapper.setLikeCount(leaveMessage.getLikeCount());
        return leaveMessageWrapper;
    }
}
