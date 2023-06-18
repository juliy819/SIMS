package com.juliy.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/** 按钮监听器类，用于监听按钮并进行响应 */
public class ButtonMonitor implements ActionListener {
    //定义静态实例以供view层调用
    public static final ButtonMonitor btnListener = new ButtonMonitor();

    //按钮触发事件重写
    @Override
    public void actionPerformed(ActionEvent e) {
        //获取触发的按钮
        JButton btn = (JButton) e.getSource();
        //获取定义好的按钮名称，并调用相应service方法
        //========= 登陆界面 =========

        if ("login_login".equals(btn.getName())) {
            LoginMethod.loginCheck();
        }
        //========== 修改密码界面 ==========

        else if ("changePwd_changePassword".equals(btn.getName())) {
            ChangePasswordMethod.changePassword();
        }

        //========== 学生信息界面 ==========

        else if ("stuInfo_cancel".equals(btn.getName())) {
            StuInfoMethod.cancel();
        } else if ("stuInfo_add".equals(btn.getName())) {
            try {
                StuInfoMethod.addNewStudent();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } else if ("stuInfo_change".equals(btn.getName())) {
            try {
                StuInfoMethod.changeStudentInfo();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        //========== 教师信息界面 ==========

        else if ("tchInfo_cancel".equals(btn.getName())) {
            TchInfoMethod.cancel();
        } else if ("tchInfo_add".equals(btn.getName())) {
            TchInfoMethod.addNewTeacher();
        } else if ("tchInfo_change".equals(btn.getName())) {
            TchInfoMethod.changeTeacherInfo();
        }

        //========== 课程信息界面 ==========

        else if ("courseInfo_cancel".equals(btn.getName())) {
            CourseInfoMethod.cancel();
        } else if ("courseInfo_add".equals(btn.getName())) {
            CourseInfoMethod.addNewCourse();
        } else if ("courseInfo_change".equals(btn.getName())) {
            CourseInfoMethod.changeCourseInfo();
        }

        //========== 学生主界面 ==========

        else if ("stu_logout".equals(btn.getName())) {
            StudentMethod.logout();
        } else if ("stu_changePassword".equals(btn.getName())) {
            StudentMethod.openChangePasswordView();
        } else if ("stu_queryScore".equals(btn.getName())) {
            StudentMethod.showSelectedScore();
        } else if ("stu_queryAllScore".equals(btn.getName())) {
            StudentMethod.showAllScore();
        } else if ("stu_queryCourse".equals(btn.getName())) {
            StudentMethod.showSelectedCourse();
        } else if ("stu_queryAllCourse".equals(btn.getName())) {
            StudentMethod.showAllCourse();
        } else if ("stu_courseFilter".equals(btn.getName())) {
            StudentMethod.showAvailableCourse();
        } else if ("stu_selectCourse".equals(btn.getName())) {
            StudentMethod.selectCourse();
        } else if ("stu_dropCourse".equals(btn.getName())) {
            StudentMethod.dropCourse();
        } else if ("stu_editAddress".equals(btn.getName())) {
            StudentMethod.editAddress();
        } else if ("stu_confirmAddress".equals(btn.getName())) {
            StudentMethod.updateAddress();
        } else if ("stu_editPhone".equals(btn.getName())) {
            StudentMethod.editPhone();
        } else if ("stu_confirmPhone".equals(btn.getName())) {
            StudentMethod.updatePhone();
        }

        //========== 教师主界面 ==========

        else if ("tch_logout".equals(btn.getName())) {
            TeacherMethod.logout();
        } else if ("tch_changePassword".equals(btn.getName())) {
            TeacherMethod.openChangePasswordView();
        } else if ("tch_editAddress".equals(btn.getName())) {
            TeacherMethod.editAddress();
        } else if ("tch_confirmAddress".equals(btn.getName())) {
            TeacherMethod.updateAddress();
        } else if ("tch_editPhone".equals(btn.getName())) {
            TeacherMethod.editPhone();
        } else if ("tch_confirmPhone".equals(btn.getName())) {
            TeacherMethod.updatePhone();
        } else if ("tch_search".equals(btn.getName())) {
            TeacherMethod.searchCourse();
        } else if ("tch_showAll".equals(btn.getName())) {
            TeacherMethod.showAllCourse();
        } else if ("tch_choose".equals(btn.getName())) {
            TeacherMethod.showStuScore();
        } else if ("tch_editScore".equals(btn.getName())) {
            TeacherMethod.editScore();
        }

        //========== 管理员主界面 ==========
        else if ("mgr_logout".equals(btn.getName())) {
            ManagerMethod.logout();
        } else if ("mgr_stuShowAll".equals(btn.getName())) {
            ManagerMethod.showAllStudent();
        } else if ("mgr_stuSearch".equals(btn.getName())) {
            ManagerMethod.searchStudent();
        } else if ("mgr_stuChange".equals(btn.getName())) {
            ManagerMethod.changeStudentInfo();
        } else if ("mgr_stuDelete".equals(btn.getName())) {
            ManagerMethod.deleteStudent();
        } else if ("mgr_stuAdd".equals(btn.getName())) {
            ManagerMethod.addNewStudent();
        } else if ("mgr_tchShowAll".equals(btn.getName())) {
            ManagerMethod.showAllTeacher();
        } else if ("mgr_tchSearch".equals(btn.getName())) {
            ManagerMethod.searchTeacher();
        } else if ("mgr_tchChange".equals(btn.getName())) {
            ManagerMethod.changeTeacherInfo();
        } else if ("mgr_tchDelete".equals(btn.getName())) {
            ManagerMethod.deleteTeacher();
        } else if ("mgr_tchAdd".equals(btn.getName())) {
            ManagerMethod.addNewTeacher();
        } else if ("mgr_courseShowAll".equals(btn.getName())) {
            ManagerMethod.showAllCourse();
        } else if ("mgr_courseSearch".equals(btn.getName())) {
            ManagerMethod.searchCourse();
        } else if ("mgr_courseChange".equals(btn.getName())) {
            ManagerMethod.changeCourseInfo();
        } else if ("mgr_courseDelete".equals(btn.getName())) {
            ManagerMethod.deleteCourse();
        } else if ("mgr_courseAdd".equals(btn.getName())) {
            ManagerMethod.addNewCourse();
        }
    }
}
