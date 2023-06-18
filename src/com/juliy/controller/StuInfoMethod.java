package com.juliy.controller;

import com.juliy.App;
import com.juliy.model.bean.Student;
import com.juliy.model.dao.impl.AccountDAOImpl;
import com.juliy.model.dao.impl.StudentDAOImpl;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 学生信息界面各种操作的具体实现 */
public class StuInfoMethod {
    /** 关闭页面 */
    public static void cancel() {
        App.stuInfoView.dispose();
    }

    /** 添加新学生 */
    public static void addNewStudent() throws ParseException {
        Student student = checkInput();
        if (student != null) {
            if (new StudentDAOImpl().addStudent(student) && new AccountDAOImpl().addUser(student.getNumber(), "student")) {
                JOptionPane.showMessageDialog(null, "学生信息添加成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.stuInfoView.dispose();
                ManagerMethod.showAllStudent();
            } else {
                JOptionPane.showMessageDialog(null, "对应学号学生已存在，添加失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 修改学生信息 */
    public static void changeStudentInfo() throws ParseException {
        Student student = checkInput();
        if (student != null) {
            if (new StudentDAOImpl().updateStudent(student)) {
                JOptionPane.showMessageDialog(null, "学生信息修改成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.stuInfoView.dispose();
                ManagerMethod.showAllStudent();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，学生信息修改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 检查输入是否合法 */
    private static Student checkInput() throws ParseException {
        //用于将Date类型转化为String，格式为年-月-日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取输入框内容
        String number = App.stuInfoView.getField_number();
        String name = App.stuInfoView.getField_name();
        String age = App.stuInfoView.getField_age();
        String sex = App.stuInfoView.getField_sex();
        String college = App.stuInfoView.getField_college();
        String preMajor = App.stuInfoView.getField_preMajor();
        String major = App.stuInfoView.getField_major();
        String stuClass = App.stuInfoView.getField_class();
        String politicalStatus = App.stuInfoView.getField_politicalStatus();
        String startTime = App.stuInfoView.getField_startTime();
        String hometown = App.stuInfoView.getField_hometown();
        String nationality = App.stuInfoView.getField_nationality();
        String id = App.stuInfoView.getField_id();
        String phone = App.stuInfoView.getField_phone();
        String address = App.stuInfoView.getField_address();
        String isSports = App.stuInfoView.getField_isSports();
        String selectCourse = App.stuInfoView.getField_selectCourse();

        //判断输入是否合法
        if ("".equals(number) || "".equals(name) || "".equals(age) || "".equals(sex) || "".equals(college) ||
                "".equals(preMajor) || "".equals(major) || "".equals(stuClass) || "".equals(politicalStatus) ||
                "".equals(startTime) || "".equals(hometown) || "".equals(nationality) || "".equals(id) ||
                "".equals(isSports) || "".equals(selectCourse)) {
            JOptionPane.showMessageDialog(null, "除地址和电话外，其余输入框不能为空！请检查",
                                          "警告", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            if (number.length() > 12) {
                JOptionPane.showMessageDialog(null, "学号过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (name.length() > 10) {
                JOptionPane.showMessageDialog(null, "名字过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (age.length() > 3) {
                JOptionPane.showMessageDialog(null, "年龄过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (sex.length() > 2) {
                JOptionPane.showMessageDialog(null, "性别过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (college.length() > 40) {
                JOptionPane.showMessageDialog(null, "学院过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (preMajor.length() > 40) {
                JOptionPane.showMessageDialog(null, "分流前专业过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (major.length() > 40) {
                JOptionPane.showMessageDialog(null, "专业过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            //对日期进行正则匹配，格式为 yyyy-mm-dd
            Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
            Matcher matcher = pattern.matcher(startTime);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "入学时间格式不正确，应为年-月-日，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (stuClass.length() > 15) {
                JOptionPane.showMessageDialog(null, "班级过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (!"中共党员".equals(politicalStatus) && !"中共预备党员".equals(politicalStatus) &&
                    !"共青团员".equals(politicalStatus) && !"民革党员".equals(politicalStatus) &&
                    !"民盟盟员".equals(politicalStatus) && !"民建会员".equals(politicalStatus) &&
                    !"民进会员".equals(politicalStatus) && !"农工党党员".equals(politicalStatus) &&
                    !"致公党党员".equals(politicalStatus) && !"九三学社社员".equals(politicalStatus) &&
                    !"台盟盟员".equals(politicalStatus) && !"无党派人士".equals(politicalStatus) &&
                    !"普通居民".equals(politicalStatus)) {
                JOptionPane.showMessageDialog(null, "政治面貌有误，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (hometown.length() > 50) {
                JOptionPane.showMessageDialog(null, "祖籍过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (nationality.length() > 10) {
                JOptionPane.showMessageDialog(null, "民族过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (id.length() > 18) {
                JOptionPane.showMessageDialog(null, "身份证号过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (phone.length() > 11) {
                JOptionPane.showMessageDialog(null, "电话号码过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (address.length() > 100) {
                JOptionPane.showMessageDialog(null, "现居地址过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (!"是".equals(isSports) && !"否".equals(isSports)) {
                JOptionPane.showMessageDialog(null, "是否体育生栏输入有误，只能为‘是’或‘否’",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (!"是".equals(selectCourse) && !"否".equals(selectCourse)) {
                JOptionPane.showMessageDialog(null, "是否能选课栏输入有误，只能为‘是’或‘否’",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            //若通过错误检测，则返回输入的学生信息
            return new Student(number, name, age, sex, nationality,
                               politicalStatus, hometown, id, address,
                               phone, college, preMajor, major, stuClass,
                               sdf.parse(startTime), selectCourse, isSports, null);
        }
    }


}


