package com.jy.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseWrapper {

    @JsonProperty("id")
    private long id;

    public long getId() {
        return id;
    }

    public BaseWrapper setId(long id) {
        this.id = id;
        return this;
    }

}
