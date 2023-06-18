package com.juliy.model.bean;

/** 教师模型 */
public class Teacher {

    private String number;          //工号
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
    private String profession;      //专业
    private String job;             //职称

    public Teacher() {

    }

    public Teacher(String number, String name, String age, String sex,
                   String nationality, String politicalStatus, String hometown,
                   String id, String address, String phone, String college,
                   String profession, String job) {
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
        this.profession = profession;
        this.job = job;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
