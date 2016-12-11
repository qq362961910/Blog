package com.jy.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileWrapper extends BaseWrapper{

    /**
     * 姓名
     * */
    @JsonProperty("realName")
    private String realName;

    /**
     * 生日
     * */
    @JsonProperty("birthday")
    private String birthday;

    /**
     * 籍贯
     * */
    @JsonProperty("nativePlace")
    private String nativePlace;

    /**
     * 现居地址
     * */
    @JsonProperty("address")
    private String address;

    /**
     * 职业
     * */
    @JsonProperty("occupation")
    private String occupation;

    /**
     * 喜欢的书籍
     * */
    @JsonProperty("likeBooks")
    private List<BookWrapper> likeBooks;

    /**
     * 个人介绍
     * */
    @JsonProperty("selfIntroduction")
    private String selfIntroduction;

    public String getRealName() {
        return realName;
    }

    public UserProfileWrapper setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public UserProfileWrapper setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public UserProfileWrapper setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserProfileWrapper setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public UserProfileWrapper setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public List<BookWrapper> getLikeBooks() {
        return likeBooks;
    }

    public UserProfileWrapper setLikeBooks(List<BookWrapper> likeBooks) {
        this.likeBooks = likeBooks;
        return this;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public UserProfileWrapper setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
        return this;
    }
}
