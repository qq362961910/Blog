package com.jy.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog_login_log")
public class LoginLog extends BaseEntity{

    /**
     * 用户id
     * */
    private Long userId;

    /**
     * ticket
     * */
    private String ticket;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
