package com.jy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog_user_profile")
public class UserProfile extends BaseEntity{

    @Column(name = "owner_id", unique = true)
    private int userId;

    /**
     * 身份证号码
     * */
    @Column(name = "id_card_no", unique = true)
    private String idCardNo;

    /**
     * 公司名称
     * */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 职位
     * */
    @Column(name = "position")
    private String position;

    public int getUserId() {
        return userId;
    }

    public UserProfile setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public UserProfile setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public UserProfile setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public UserProfile setPosition(String position) {
        this.position = position;
        return this;
    }
}
