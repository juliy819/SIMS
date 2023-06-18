package com.juliy.controller;

import com.juliy.App;
import com.juliy.model.bean.Course;
import com.juliy.model.dao.impl.CourseDAOImpl;
import com.juliy.model.dao.impl.TeacherDAOImpl;

import javax.swing.*;

/** 课程信息界面各种操作的具体实现 */
public class CourseInfoMethod {

    /** 关闭页面 */
    public static void cancel() {
        App.courseInfoView.dispose();
    }

    /** 添加新课程 */
    public static void addNewCourse() {
        Course course = checkInput();
        if (course != null) {
            //依据输入的教师姓名查询对应工号，并封入course内
            String teacherNumber = new TeacherDAOImpl().queryTeacherNumber(course.getTeacherName());
            if ("".equals(teacherNumber)) {
                JOptionPane.showMessageDialog(null, "出现未知错误，课程信息修改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            course.setTeacherNumber(teacherNumber);
            if (new CourseDAOImpl().addCourse(course)) {
                JOptionPane.showMessageDialog(null, "课程信息添加成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.courseInfoView.dispose();
                ManagerMethod.showAllCourse();
            } else {
                JOptionPane.showMessageDialog(null, "对应编号课程已存在，添加失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 修改课程信息 */
    public static void changeCourseInfo() {
        Course course = checkInput();
        if (course != null) {
            //依据输入的教师姓名查询对应工号，并封入course内
            String teacherNumber = new TeacherDAOImpl().queryTeacherNumber(course.getTeacherName());
            if ("".equals(teacherNumber)) {
                JOptionPane.showMessageDialog(null, "出现未知错误，课程信息修改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            course.setTeacherNumber(teacherNumber);
            if (new CourseDAOImpl().updateCourse(course)) {
                JOptionPane.showMessageDialog(null, "课程信息修改成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.courseInfoView.dispose();
                ManagerMethod.showAllCourse();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，课程信息修改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static Course checkInput() {
        //获取输入框内容
        String number = App.courseInfoView.getField_number();
        String name = App.courseInfoView.getField_name();
        String teacher = App.courseInfoView.getField_teacher();
        String college = App.courseInfoView.getField_college();
        String profession = App.courseInfoView.getField_profession();
        String type = App.courseInfoView.getField_type();
        String credit = App.courseInfoView.getField_credit();
        String period = App.courseInfoView.getField_period();

        //判断输入是否合法
        if ("".equals(number) || "".equals(name) || "".equals(teacher) || "".equals(college) ||
                "".equals(profession) || "".equals(type) || "".equals(credit) || "".equals(period)) {
            JOptionPane.showMessageDialog(null, "输入框不能为空！请检查",
                                          "警告", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            if (number.length() > 4) {
                JOptionPane.showMessageDialog(null, "课程编号过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (name.length() > 30) {
                JOptionPane.showMessageDialog(null, "课程名称过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (teacher.length() > 10) {
                JOptionPane.showMessageDialog(null, "任课教师过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (college.length() > 40) {
                JOptionPane.showMessageDialog(null, "开课院系过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (profession.length() > 40) {
                JOptionPane.showMessageDialog(null, "开课专业过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (!"必修".equals(type) && !"选修".equals(type) && !"通识".equals(type)) {
                JOptionPane.showMessageDialog(null, "课程性质有误，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (credit.length() > 2) {
                JOptionPane.showMessageDialog(null, "学分过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (period.length() > 3) {
                JOptionPane.showMessageDialog(null, "学时过长，请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            //若通过错误检测，则返回输入的课程信息
            return new Course(number, name, null, teacher,
                              period, credit, type, profession, college);
        }
    }

}
