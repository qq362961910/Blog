package com.jy.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog_book")
public class Book extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
