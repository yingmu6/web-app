package com.orange.entity;

/**
 * @author chensy
 * @date 2024/3/25
 */

public class UserInfoDO extends BaseDO<Long> {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
