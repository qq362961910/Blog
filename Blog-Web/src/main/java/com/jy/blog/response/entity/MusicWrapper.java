package com.jy.blog.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class MusicWrapper extends BaseWrapper {

    /**
     * 音乐名
     */
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public MusicWrapper setName(String name) {
        this.name = name;
        return this;
    }
}
