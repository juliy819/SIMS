package com.juliy.controller;

import com.juliy.App;
import com.juliy.model.bean.Teacher;
import com.juliy.model.dao.impl.AccountDAOImpl;
import com.juliy.model.dao.impl.TeacherDAOImpl;

import javax.swing.*;

/** 教师信息界面各种操作的具体实现 */
public class TchInfoMethod {

    /** 关闭页面 */
    public static void cancel() {
        App.tchInfoView.dispose();
    }

    /** 添加新教师 */
    public static void addNewTeacher() {
        Teacher teacher = checkInput();
        if (teacher != null) {
            if (new TeacherDAOImpl().addTeacher(teacher) && new AccountDAOImpl().addUser(teacher.getNumber(), "teacher")) {
                JOptionPane.showMessageDialog(null, "教师信息添加成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.tchInfoView.dispose();
                ManagerMethod.showAllTeacher();
            } else {
                JOptionPane.showMessageDialog(null, "对应工号教师已存在，添加失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 修改教师信息 */
    public static void changeTeacherInfo() {
        Teacher teacher = checkInput();
        if (teacher != null) {
            if (new TeacherDAOImpl().updateTeacher(teacher)) {
                JOptionPane.showMessageDialog(null, "教师信息修改成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.tchInfoView.dispose();
                ManagerMethod.showAllTeacher();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，教师信息修改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static Teacher checkInput() {
        //获取输入框内容
        String number = App.tchInfoView.getField_number();
        String name = App.tchInfoView.getField_name();
        String age = App.tchInfoView.getField_age();
        String sex = App.tchInfoView.getField_sex();
        String college = App.tchInfoView.getField_college();
        String profession = App.tchInfoView.getField_profession();
        String job = App.tchInfoView.getField_job();
        String politicalStatus = App.tchInfoView.getField_politicalStatus();
        String hometown = App.tchInfoView.getField_hometown();
        String nationality = App.tchInfoView.getField_nationality();
        String id = App.tchInfoView.getField_id();
        String phone = App.tchInfoView.getField_phone();
        String address = App.tchInfoView.getField_address();

        //判断输入是否合法
        if ("".equals(number) || "".equals(name) || "".equals(age) || "".equals(sex) || "".equals(college)
                || "".equals(profession) || "".equals(job) || "".equals(politicalStatus) || "".equals(hometown)
                || "".equals(nationality) || "".equals(id)) {
            JOptionPane.showMessageDialog(null, "除地址和电话外，其余输入框不能为空！请检查",
                                          "警告", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            if (number.length() > 8) {
                JOptionPane.showMessageDialog(null, "工号过长，请重新输入",
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
            if (profession.length() > 40) {
                JOptionPane.showMessageDialog(null, "专业过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (!"教授".equals(job) && !"副教授".equals(job) && !"讲师".equals(job) && !"助教".equals(job)) {
                JOptionPane.showMessageDialog(null, "职称有误，请重新输入",
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
            //若通过错误检测，则返回输入的学生信息
            return new Teacher(number, name, age, sex, nationality,
                               politicalStatus, hometown, id, address,
                               phone, college, profession, job);
        }
    }
}
