package com.jy.blog.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class PageableWrapper {

    @JsonProperty("currentPage")
    private int currentPage;

    @JsonProperty("totalPage")
    private int totalPage;

    @JsonProperty("objectList")
    private List<? extends Object> objectList = new ArrayList<>();

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<? extends Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<? extends Object> objectList) {
        this.objectList = objectList;
    }
}
