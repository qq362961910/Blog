package com.jy.blog.response.service;

import com.jy.blog.entity.LeaveMessage;
import com.jy.blog.response.entity.BaseWrapper;
import com.jy.blog.response.entity.LeaveMessageWrapper;
import com.jy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveMessageWrapperService extends PageableWrapperService<LeaveMessage> {

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
        leaveMessageWrapper.setCreateTime(leaveMessage.getCreateTime());
        return leaveMessageWrapper;
    }

    @Override
    public List<? extends BaseWrapper> buildEntityListWrapper(List<LeaveMessage> leaveMessages) {
        if (leaveMessages == null) {
            return null;
        }
        List<LeaveMessageWrapper> leaveMessageWrapperList = new ArrayList<>(leaveMessages.size());
        leaveMessages.forEach(leaveMessage -> leaveMessageWrapperList.add(buildLeaveMessageWrapper(leaveMessage)));
        return leaveMessageWrapperList;
    }
}
