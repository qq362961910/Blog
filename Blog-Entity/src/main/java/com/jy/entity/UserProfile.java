package com.jy.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog_user_profile")
public class UserProfile extends BaseEntity {

    @Column(name = "owner_id", unique = true)
    private long ownerId;

    /**
     * 身份证号码
     */
    @Column(name = "id_card_no", unique = true)
    private String idCardNo;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 职业
     */
    @Column(name = "occupation")
    private String occupation;

    /**
     * 职位
     */
    @Column(name = "position")
    private String position;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 生日
     */
    @Column(name = "birthday", columnDefinition = "timestamp")
    private Date birthday;

    /**
     * 籍贯
     */
    @Column(name = "native_place")
    private String nativePlace;

    /**
     * 现居
     */
    @Column(name = "address")
    private String address;

    /**
     * 自我描述
     */
    @Column(name = "self_introduction", columnDefinition = "TEXT")
    private String selfIntroduction;

    /**
     * 用户喜欢的书籍
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userBookEmbedKey.ownerId")
    private List<UserBookLike> userBookLikeList;

    public long getOwnerId() {
        return ownerId;
    }

    public UserProfile setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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

    public String getOccupation() {
        return occupation;
    }

    public UserProfile setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public UserProfile setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserProfile setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UserProfile setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public UserProfile setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserProfile setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public UserProfile setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
        return this;
    }

    public List<UserBookLike> getUserBookLikeList() {
        return userBookLikeList;
    }

    public UserProfile setUserBookLikeList(List<UserBookLike> userBookLikeList) {
        this.userBookLikeList = userBookLikeList;
        return this;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "ownerId=" + ownerId +
                ", idCardNo='" + idCardNo + '\'' +
                ", companyName='" + companyName + '\'' +
                ", occupation='" + occupation + '\'' +
                ", position='" + position + '\'' +
                ", realName='" + realName + '\'' +
                ", birthday=" + birthday +
                ", nativePlace='" + nativePlace + '\'' +
                ", address='" + address + '\'' +
                ", selfIntroduction='" + selfIntroduction + '\'' +
                ", userBookLikeList=" + userBookLikeList +
                '}';
    }
}
