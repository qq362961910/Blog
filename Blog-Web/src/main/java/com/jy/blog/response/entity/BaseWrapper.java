package com.jy.blog.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class BaseWrapper {

    @JsonProperty("id")
    private long id;

    @JsonProperty("structureType")
    private String structureType;

    public long getId() {
        return id;
    }

    public BaseWrapper setId(long id) {
        this.id = id;
        return this;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }
}
