package com.jy.service;

import com.jy.entity.LeaveMessage;

public interface LeaveMessageService extends BaseService<LeaveMessage> {
    void save(Long fromUserId, Long toUserId, Long leaveMessageId, String content);
}
