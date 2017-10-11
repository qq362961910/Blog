package com.jy.blog.service;

import com.jy.blog.entity.LeaveMessage;

public interface LeaveMessageService extends BaseService<LeaveMessage> {
    void save(Long fromUserId, Long toUserId, Long leaveMessageId, String content);
}
