package com.juliy.model.bean;

/** 成绩模型 */
public class Score {
    private String courseNumber;    //课程编号
    private String studentNumber;   //学生学号
    private String schoolYear;      //学年
    private String term;            //学期
    private String totalScore;      //总评成绩
    private String retestScore;     //补考成绩
    private String relearnScore;    //重修成绩

    //下面这些不属于成绩表，但为了方便显示成绩信息，还是加上了
    private String courseName;      //课程名
    private String credit;          //学分
    private String type;            //课程类型

    public Score() {

    }

    public Score(String courseNumber, String studentNumber, String schoolYear,
                 String term, String totalScore, String retestScore, String relearnScore,
                 String courseName, String credit, String type) {
        this.courseNumber = courseNumber;
        this.studentNumber = studentNumber;
        this.schoolYear = schoolYear;
        this.term = term;
        this.totalScore = totalScore;
        this.retestScore = retestScore;
        this.relearnScore = relearnScore;

        this.courseName = courseName;
        this.credit = credit;
        this.type = type;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getRetestScore() {
        return retestScore;
    }

    public void setRetestScore(String retestScore) {
        this.retestScore = retestScore;
    }

    public String getRelearnScore() {
        return relearnScore;
    }

    public void setRelearnScore(String relearnScore) {
        this.relearnScore = relearnScore;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
}
