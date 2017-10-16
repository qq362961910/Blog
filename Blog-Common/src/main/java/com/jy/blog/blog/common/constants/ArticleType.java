package com.jy.blog.blog.common.constants;

public enum ArticleType {

    COMMON("文章", 1),
    TEMPLATE_SHARE("模板分享", 2),
    KNOWLEDGE("学无止境", 3);


    public static ArticleType getArticleType(int value) {
        for (ArticleType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public ArticleType setName(String name) {
        this.name = name;
        return this;
    }

    public int getValue() {
        return value;
    }

    public ArticleType setValue(int value) {
        this.value = value;
        return this;
    }

    ArticleType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
            "name='" + name + '\'' +
            ", value=" + value +
            '}';
    }
}
