package com.jy.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog_music")
public class Music extends BaseEntity {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public Music setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Music{" +
            "name='" + name + '\'' +
            '}';
    }
}
