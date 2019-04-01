package com.ping.eureka.client.productservice.domain;

/**
 * Created by Administrator on 2018/12/11.
 * @author: wenhuaping
 */
public class UserVo {
    /**
     * 用户id
     */
    private int id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户年龄
     */
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
