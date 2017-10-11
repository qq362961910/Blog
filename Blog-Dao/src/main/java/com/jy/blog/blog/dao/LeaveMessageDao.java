package com.jy.blog.blog.dao;

import com.jy.blog.entity.LeaveMessage;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public interface LeaveMessageDao extends BaseDao<LeaveMessage> {

    class LeaveMessageParam extends BaseParam {

        private Long ownerId;

        public Long getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
        }

        @Override
        public void addSubRestrictions(Criteria criteria) {
            if (ownerId != null) {
                criteria.add(Restrictions.eq("toUserId", ownerId));
            }
        }
    }
}
