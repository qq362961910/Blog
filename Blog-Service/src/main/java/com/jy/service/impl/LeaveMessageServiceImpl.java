package com.jy.service.impl;

import com.jy.dao.BaseDao;
import com.jy.dao.LeaveMessageDao;
import com.jy.entity.LeaveMessage;
import com.jy.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class LeaveMessageServiceImpl extends BaseServiceImpl<LeaveMessage> implements LeaveMessageService {

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Override
    public void save(Long fromUserId, Long toUserId, Long leaveMessageId, String content) {
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setFromUserId(fromUserId);
        leaveMessage.setToUserId(toUserId);
        leaveMessage.setContent(content);
        leaveMessage.setRepliedLeaveMessageId(leaveMessageId);
        save(leaveMessage);
    }

    @Override
    public BaseDao<LeaveMessage> getBaseDao() {
        return leaveMessageDao;
    }
}
