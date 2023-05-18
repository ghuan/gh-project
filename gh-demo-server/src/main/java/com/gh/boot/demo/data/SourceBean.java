package com.gh.boot.demo.data;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/5/11
 */
public class SourceBean {

    private Integer age;

    private String title;

    private String source;

    private Boolean eat;

    public SourceBean(Integer i, String name, Boolean eat, String source) {
        this.age = i;
        this.title = name;
        this.eat = eat;
        this.source = source;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getEat() {
        return eat;
    }

    public void setEat(Boolean eat) {
        this.eat = eat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getAge() {
        return age;
    }

}