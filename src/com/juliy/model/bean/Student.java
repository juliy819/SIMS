package com.juliy.model.bean;

import java.util.Date;

/** 学生模型 */
public class Student {

    private String number;          //学号
    private String name;            //姓名
    private String age;             //年龄
    private String sex;             //性别
    private String nationality;     //民族
    private String politicalStatus; //政治面貌
    private String hometown;        //籍贯
    private String id;              //身份证号
    private String address;         //地址
    private String phone;           //电话
    private String college;         //学院
    private String preMajor;        //分流前专业
    private String major;           //专业
    private String stuClass;        //班级
    private Date startTime;         //入学日期
    private String selectCourse;    //是否能选课
    private String isSports;        //是否体育生

    private String score;

    public Student() {

    }

    public Student(String number, String name, String age, String sex,
                   String nationality, String politicalStatus, String hometown,
                   String id, String address, String phone, String college,
                   String preMajor, String major, String stuClass,
                   Date startTime, String selectCourse, String isSports, String score) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.nationality = nationality;
        this.politicalStatus = politicalStatus;
        this.hometown = hometown;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.college = college;
        this.preMajor = preMajor;
        this.major = major;
        this.stuClass = stuClass;
        this.startTime = startTime;
        this.selectCourse = selectCourse;
        this.isSports = isSports;
        this.score = score;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPreMajor() {
        return preMajor;
    }

    public void setPreMajor(String preMajor) {
        this.preMajor = preMajor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getSelectCourse() {
        return selectCourse;
    }

    public void setSelectCourse(String selectCourse) {
        this.selectCourse = selectCourse;
    }

    public String getIsSports() {
        return isSports;
    }

    public void setIsSports(String isSports) {
        this.isSports = isSports;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
