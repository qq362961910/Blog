package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.blog.dao.LeaveMessageDao;
import com.jy.blog.entity.LeaveMessage;
import com.jy.blog.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        leaveMessage.setLikeCount(0);
        leaveMessage.setRepliedLeaveMessageId(leaveMessageId);
        leaveMessage.setCreateTime(new Date());
        save(leaveMessage);
    }

    @Override
    public BaseDao<LeaveMessage> getBaseDao() {
        return leaveMessageDao;
    }
}
