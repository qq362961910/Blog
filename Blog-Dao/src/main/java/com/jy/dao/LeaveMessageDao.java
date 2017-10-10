package com.jy.dao;

import com.jy.entity.LeaveMessage;
import org.hibernate.Criteria;

public interface LeaveMessageDao extends BaseDao<LeaveMessage> {

    class LeaveMessageParam extends BaseParam {
        @Override
        public void addSubRestrictions(Criteria criteria) {

        }
    }
}
