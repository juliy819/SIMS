package com.juliy.model.bean;

/** 登录用户模型 */
public enum User {
    //三个枚举常量表示对应登录用户类型
    MANAGER,
    TEACHER,
    STUDENT;

    //存储账号，对应学号或工号
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
