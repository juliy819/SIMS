package com.juliy.controller;

import com.juliy.App;
import com.juliy.model.bean.Course;
import com.juliy.model.bean.Score;
import com.juliy.model.bean.Student;
import com.juliy.model.dao.impl.CourseDAOImpl;
import com.juliy.model.dao.impl.ScoreDAOImpl;
import com.juliy.model.dao.impl.StudentDAOImpl;
import com.juliy.view.ChangePasswordView;
import com.juliy.view.LoginView;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/** 学生主界面各种操作的具体实现 */
public class StudentMethod {

    //========== 其他 ==========

    /** 第一次打开界面时加载数据 */
    public static void loadInfo() {
        loadWelcomeAndTime();
        showAllCourse();
        showAvailableCourse();
        showDropCourse();
        showAllScore();
        loadCredit();
        loadPersonalInfoPage();
    }

    /** 加载欢迎标签和时间 */
    private static void loadWelcomeAndTime() {
        //在student表中查询登录学生的信息
        List<Student> list = new StudentDAOImpl()
                .queryStudentInfo(LoginView.user.getNumber(), "", "", "", "");
        //设置欢迎语句
        Student student = list.get(0);
        String welcome = student.getMajor() + " " + student.getStuClass() + " " +
                student.getName() + " 同学，您好，欢迎使用本系统";
        App.stuMainView.setLabel_welcome(welcome);
        //获取当前时间并设置
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EEEE");
        String time = "当前时间：" + dateFormat.format(date);
        App.stuMainView.setLabel_time(time);
    }


    /** 注销账号，返回登录界面 */
    public static void logout() {
        int result = JOptionPane.showConfirmDialog(null, "是否要注销账号？", "提示",
                                                   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            App.stuMainView.dispose();
            App.loginView = new LoginView();
        }
    }

    /** 显示修改密码界面 */
    public static void openChangePasswordView() {
        //判断是否打开了新界面，避免同时打开多个界面
        if (App.changePasswordView == null) {
            App.changePasswordView = new ChangePasswordView();
        }
        App.changePasswordView.clearField();
    }

    //========== 选课管理界面 ==========
    //======== 选课信息 ========

    /** 显示所有已选课程信息 */
    public static void showAllCourse() {
        //查询登录学生的课程信息
        List<Course> list = new CourseDAOImpl().queryCourseInfo(LoginView.user.getNumber(), "", "", 0);
        setCourseTableModel(list);
        App.stuMainView.resetCourseCbBox();
    }

    /** 显示符合要求的已选课程信息 */
    public static void showSelectedCourse() {
        //获取下拉框中选择的内容
        String schoolYear = App.stuMainView.getCourse_CbBox_schoolYear();
        String term = App.stuMainView.getCourse_CbBox_term();
        //为便于数据库模糊查询，查询全部需设置为空
        if (schoolYear.equals("全部")) {
            schoolYear = "";
        }
        if (term.equals("全部")) {
            term = "";
        }

        //查询登录学生的课程信息
        List<Course> list = new CourseDAOImpl()
                .queryCourseInfo(LoginView.user.getNumber(), schoolYear, term, 0);
        setCourseTableModel(list);
    }

    /** 重复内容过多，提取出的方法，用于设置已选课程表格模型并加载数据 */
    private static void setCourseTableModel(List<Course> list) {
        //将list中的成绩信息转存到vector数组中
        Vector<Vector<String>> tableData = new Vector<>();
        for (Course course : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(course.getProfession());
            rowData.add(course.getNumber());
            rowData.add(course.getName());
            rowData.add(course.getType());
            rowData.add(course.getTeacherName());
            rowData.add(course.getCredit());
            tableData.add(rowData);
        }
        Vector<String> columnName = new Vector<>();
        columnName.add("专业名称");
        columnName.add("课程代码");
        columnName.add("课程名");
        columnName.add("课程性质");
        columnName.add("上课教师");
        columnName.add("学分");
        DefaultTableModel model = new DefaultTableModel(tableData, columnName) {
            //让表格内容不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        App.stuMainView.setTable_courseInfo(model);
    }

    //======== 课程选修 ========

    /** 显示符合要求、可供选择的课程 */
    public static void showAvailableCourse() {
        //获取下拉框中选择的内容
        String type = App.stuMainView.getCourse_CbBox_type();
        //为便于数据库模糊查询，查询全部信息需设置为空
        if (type.equals("全部")) {
            type = "";
        }
        List<Course> list = new CourseDAOImpl().queryCourseInfo(LoginView.user.getNumber(), type);

        Vector<Vector<String>> tableData = new Vector<>();
        for (Course course : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(course.getNumber());
            rowData.add(course.getName());
            rowData.add(course.getType());
            rowData.add(course.getCredit());
            rowData.add(course.getProfession());
            rowData.add(course.getCollege());
            rowData.add(course.getTeacherName());
            tableData.add(rowData);
        }
        Vector<String> columnName = new Vector<>();
        columnName.add("课程编号");
        columnName.add("课程名称");
        columnName.add("课程性质");
        columnName.add("学分");
        columnName.add("专业名称");
        columnName.add("开课院系");
        columnName.add("上课教师");
        columnName.add("选择");

        class tableModel extends AbstractTableModel {
            //设置行数
            @Override
            public int getRowCount() {
                return tableData.size();
            }

            //设置列数
            @Override
            public int getColumnCount() {
                return columnName.size();
            }

            //显示表格内容
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                //列名为“选择”则该列显示选择，否则显示vector内容
                if ("选择".equals(getColumnName(columnIndex))) {
                    return "选择";
                } else {
                    return tableData.get(rowIndex).get(columnIndex);
                }
            }

            //显示列名
            @Override
            public String getColumnName(int columnIndex) {
                return columnName.get(columnIndex);
            }

            //设置单元格是否可编辑 第7列为按钮，需要设置为编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equals("选择");
            }
        }

        App.stuMainView.setTable_courseSelect(new tableModel());
    }

    /** 选修课程 */
    public static void selectCourse() {
        //获取按钮所在行的课程编号和名称
        String[] courseInfo = App.stuMainView.getSelectCourseName();
        //弹出提示框避免误操作
        int result = JOptionPane.showConfirmDialog(null, "确定要选修 " + courseInfo[1] + " 课程吗？",
                                                   "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (new ScoreDAOImpl().addCourseSelection(LoginView.user.getNumber(), courseInfo[0])) {
                JOptionPane.showMessageDialog(null, "选修课程成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                //页面更新
                showAvailableCourse();
                showAllCourse();
                showDropCourse();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，选修课程失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    //======== 课程退选 ========

    /** 显示可供退选的课程 */
    public static void showDropCourse() {
        List<Course> list = new CourseDAOImpl().queryCourseInfo(LoginView.user.getNumber(), 0);

        Vector<Vector<String>> tableData = new Vector<>();
        for (Course course : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(course.getNumber());
            rowData.add(course.getName());
            rowData.add(course.getType());
            rowData.add(course.getCredit());
            rowData.add(course.getProfession());
            rowData.add(course.getCollege());
            rowData.add(course.getTeacherName());
            tableData.add(rowData);
        }
        Vector<String> columnName = new Vector<>();
        columnName.add("课程编号");
        columnName.add("课程名称");
        columnName.add("课程性质");
        columnName.add("学分");
        columnName.add("专业名称");
        columnName.add("开课院系");
        columnName.add("上课教师");
        columnName.add("退选");

        class tableModel extends AbstractTableModel {
            //设置行数
            @Override
            public int getRowCount() {
                return tableData.size();
            }

            //设置列数
            @Override
            public int getColumnCount() {
                return columnName.size();
            }

            //显示表格内容
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if ("退选".equals(getColumnName(columnIndex))) {
                    return "退选";
                } else {
                    return tableData.get(rowIndex).get(columnIndex);
                }
            }

            //显示列名
            @Override
            public String getColumnName(int columnIndex) {
                return columnName.get(columnIndex);
            }

            //设置单元格是否可编辑 第7列为按钮，需要设置为编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equals("退选");
            }
        }

        App.stuMainView.setTable_courseDrop(new tableModel());
    }

    /** 退选课程 */
    public static void dropCourse() {
        //获取按钮所在行的课程编号和名称
        String[] courseInfo = App.stuMainView.getDropCourseName();
        //弹出提示框避免误操作
        int result = JOptionPane.showConfirmDialog(null, "确定要退选 " + courseInfo[1] + " 课程吗？",
                                                   "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (new ScoreDAOImpl().deleteCourseSelection(LoginView.user.getNumber(), courseInfo[0])) {
                JOptionPane.showMessageDialog(null, "退选课程成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                //页面更新
                showAvailableCourse();
                showAllCourse();
                showDropCourse();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，退选课程失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    //========== 成绩查询界面 ==========
    //======== 普通课程成绩 ========

    /** 显示所有课程成绩 */
    public static void showAllScore() {
        //查询登录学生的成绩信息
        List<Score> list = new ScoreDAOImpl()
                .queryScore(LoginView.user.getNumber(), null, null, null);
        setScoreTableModel(list);
        App.stuMainView.resetScoreCbBox();
    }

    /** 显示符合要求的课程成绩 */
    public static void showSelectedScore() {
        //获取下拉框中选择的内容
        String schoolYear = App.stuMainView.getScore_CbBox_schoolYear();
        String term = App.stuMainView.getScore_CbBox_term();
        String type = App.stuMainView.getScore_CbBox_type();
        //为便于数据库模糊查询，查询全部需设置为空
        if (schoolYear.equals("全部")) {
            schoolYear = "";
        }
        if (term.equals("全部")) {
            term = "";
        }
        if (type.equals("全部")) {
            type = "";
        }

        //查询登录学生的成绩信息
        List<Score> list = new ScoreDAOImpl()
                .queryScore(LoginView.user.getNumber(), schoolYear, term, type);
        setScoreTableModel(list);
    }

    /** 设置成绩表格模型并加载数据 */
    private static void setScoreTableModel(List<Score> list) {
        //将list中的成绩信息转存到vector数组中
        Vector<Vector<String>> tableData = new Vector<>();
        for (Score score : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(score.getSchoolYear());
            rowData.add(score.getTerm());
            rowData.add(score.getCourseName());
            rowData.add(score.getType());
            rowData.add(score.getTotalScore());
            rowData.add(score.getRetestScore());
            rowData.add(score.getRelearnScore());
            rowData.add(score.getCredit());
            tableData.add(rowData);
        }
        Vector<String> columnName = new Vector<>();
        columnName.add("学年");
        columnName.add("学期");
        columnName.add("课程名");
        columnName.add("课程性质");
        columnName.add("总评成绩");
        columnName.add("补考成绩");
        columnName.add("重修成绩");
        columnName.add("学分");
        DefaultTableModel model = new DefaultTableModel(tableData, columnName) {
            //让表格内容不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        App.stuMainView.setTable_score(model);
    }


    //======== 学业完成情况 ========

    public static void loadCredit() {
        //查询登录学生的成绩信息
        List<Score> list = new ScoreDAOImpl()
                .queryScore(LoginView.user.getNumber(), null, null, null);
        //用于计算已获学分
        int requiredCourse = 0, electiveCourse = 0, generalCourse = 0, totalCredit;
        //将list中的成绩信息转存到vector数组中
        for (Score score : list) {
            switch (score.getType()) {
                //lambda表达式，ide自动替换的
                case "必修" -> requiredCourse += Integer.parseInt(score.getCredit());
                case "选修" -> electiveCourse += Integer.parseInt(score.getCredit());
                case "通识" -> generalCourse += Integer.parseInt(score.getCredit());
            }
        }
        totalCredit = requiredCourse + electiveCourse + generalCourse;
        App.stuMainView.setLabel_credit(String.valueOf(totalCredit));

        String[][] tableData = {{"必修", String.valueOf(requiredCourse)},
                {"选修", String.valueOf(electiveCourse)},
                {"通识", String.valueOf(generalCourse)}};

        String[] columnName = {"课程性质", "已获学分"};
        DefaultTableModel model = new DefaultTableModel(tableData, columnName) {
            //让表格内容不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        App.stuMainView.setTable_credit(model);
    }

    //========== 个人信息界面 ==========

    /** 加载学生个人信息页面 */
    public static void loadPersonalInfoPage() {
        //从登录界面获取学号
        String number = LoginView.user.getNumber();
        System.out.println(number);
        //在student表中查询该学号对应的学生信息
        List<Student> list = new StudentDAOImpl()
                .queryStudentInfo(number, "", "", "", "");
        Student student = list.get(0);
        //用于将Date类型转化为String，格式为年-月-日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //显示个人信息
        App.stuMainView.setLabel_name(student.getName());
        App.stuMainView.setLabel_number(student.getNumber());
        App.stuMainView.setLabel_sex(student.getSex());
        App.stuMainView.setLabel_age(student.getAge());
        App.stuMainView.setLabel_nationality(student.getNationality());
        App.stuMainView.setLabel_politicalStatus(student.getPoliticalStatus());
        App.stuMainView.setLabel_hometown(student.getHometown());
        App.stuMainView.setLabel_id(student.getId());
        App.stuMainView.setLabel_address(student.getAddress());
        App.stuMainView.setLabel_phone(student.getPhone());
        App.stuMainView.setLabel_college(student.getCollege());
        App.stuMainView.setLabel_preMajor(student.getPreMajor());
        App.stuMainView.setLabel_major(student.getMajor());
        App.stuMainView.setLabel_class(student.getStuClass());
        App.stuMainView.setLabel_startTime(sdf.format(student.getStartTime()));
        App.stuMainView.setLabel_isSports(student.getIsSports());
    }

    private static String preAddress;

    /** 编辑个人信息页面中的地址栏 */
    public static void editAddress() {
        //存储之前的地址
        preAddress = App.stuMainView.getLabel_address();
        App.stuMainView.changeAddressCard();
        App.stuMainView.changeBtn_editAddress();
    }

    /** 更新地址信息 */
    public static void updateAddress() {
        String address = App.stuMainView.getField_address();
        //判断用户输入是否符合要求
        //输入为空
        if (address.equals("")) {
            int result = JOptionPane.showConfirmDialog(null, "地址不能为空", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.stuMainView.changeAddressCard();
                App.stuMainView.changeBtn_editAddress();
            }
        }
        //新旧地址相同
        else if (address.equals(preAddress)) {
            int result = JOptionPane.showConfirmDialog(null, "新地址与原地址相同", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.stuMainView.changeAddressCard();
                App.stuMainView.changeBtn_editAddress();
            }
        }
        //输入地址过长
        else if (address.length() > 50) {
            int result = JOptionPane.showConfirmDialog(null, "地址过长！", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.stuMainView.changeAddressCard();
                App.stuMainView.changeBtn_editAddress();
            }
        }
        //进行数据更新
        else {
            //判断是否更新成功
            if (new StudentDAOImpl().updateAddress(LoginView.user.getNumber(), address, null)) {
                JOptionPane.showMessageDialog(null, "地址更改成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.stuMainView.setLabel_address(address);
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，地址更改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
            App.stuMainView.changeAddressCard();
            App.stuMainView.changeBtn_editAddress();
        }
    }

    private static String prePhone;

    /** 编辑个人信息页面中的电话栏 */
    public static void editPhone() {
        //存储之前的地址
        prePhone = App.stuMainView.getLabel_phone();
        App.stuMainView.changePhoneCard();
        App.stuMainView.changeBtn_editPhone();
    }

    /** 更新电话信息 */
    public static void updatePhone() {
        String phone = App.stuMainView.getField_phone();
        //判断用户输入是否符合要求
        //输入为空
        if (phone.equals("")) {
            int result = JOptionPane.showConfirmDialog(null, "号码不能为空", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.stuMainView.changePhoneCard();
                App.stuMainView.changeBtn_editPhone();
            }
        }
        //新旧地址相同
        else if (phone.equals(prePhone)) {
            int result = JOptionPane.showConfirmDialog(null, "新号码与原号码相同", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.stuMainView.changePhoneCard();
                App.stuMainView.changeBtn_editPhone();
            }
        }
        //输入地址过长
        else if (phone.length() > 11) {
            int result = JOptionPane.showConfirmDialog(null, "号码过长！", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.stuMainView.changePhoneCard();
                App.stuMainView.changeBtn_editPhone();
            }
        }
        //进行数据更新
        else {
            //判断是否更新成功
            if (new StudentDAOImpl().updateAddress(LoginView.user.getNumber(), null, phone)) {
                JOptionPane.showMessageDialog(null, "号码更改成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.stuMainView.setLabel_phone(phone);
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，号码更改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
            App.stuMainView.changePhoneCard();
            App.stuMainView.changeBtn_editPhone();
        }
    }
}