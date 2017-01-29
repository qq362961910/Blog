package com.jy.dao;

import com.jy.entity.Mood;

import java.util.List;

public interface MoodDao extends BaseDao<Mood>{

    int countMoodByMoodParam(MoodParam param);

    List<Mood> selectMoodByMoodParam(MoodParam param);

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
        public String toString() {
            return "MoodParam{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }
}
