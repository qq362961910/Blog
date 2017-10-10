package com.jy.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class BookWrapper extends BaseWrapper {

    /**
     * 书名
     */
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public BookWrapper setName(String name) {
        this.name = name;
        return this;
    }
}
