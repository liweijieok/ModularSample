package com.github.io.liweijie.lib1.bean;

/**
 * 作者：黎伟杰-子然 on 2017/4/17.
 * 邮箱：liweijie@linghit.com
 * description：
 * update by:
 * update day:
 */
public class Books {
    private String name;
    private String author;

    public Books() {
    }

    public Books(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
