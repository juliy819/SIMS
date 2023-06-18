package com.juliy.model.bean;

/** 课程模型 */
public class Course {
    private String number;          //课程编号
    private String teacherNumber;   //教师编号
    private String name;            //课程名
    private String period;          //学时
    private String credit;          //学分
    private String type;            //课程类型
    private String profession;      //专业
    private String college;         //开课院系

    //下面这些不属于课程表，但为了方便，还是加上了
    private String teacherName;     //教师姓名

    public Course() {}

    public Course(String number, String name, String teacherNumber, String teacherName,
                  String period, String credit, String type, String profession, String college) {
        this.number = number;
        this.name = name;
        this.teacherNumber = teacherNumber;
        this.teacherName = teacherName;
        this.period = period;
        this.credit = credit;
        this.type = type;
        this.profession = profession;
        this.college = college;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
