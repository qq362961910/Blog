package com.jy.blog.blog.dao;

import com.jy.blog.entity.Mood;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public interface MoodDao extends BaseDao<Mood> {

    class MoodParam extends BaseParam {

        private String username;

        public String getUsername() {
            return username;
        }

        public MoodParam setUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public void addSubRestrictions(Criteria criteria) {
            if (username != null) {
                criteria.add(Restrictions.eq("owner.username", username));
            }
        }

        @Override
        public String toString() {
            return "MoodParam{" +
                "username='" + username + '\'' +
                '}';
        }
    }
}
