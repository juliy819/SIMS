package com.juliy.controller;

import com.juliy.App;
import com.juliy.model.bean.Course;
import com.juliy.model.bean.Student;
import com.juliy.model.bean.Teacher;
import com.juliy.model.dao.impl.*;
import com.juliy.view.CourseInfoView;
import com.juliy.view.LoginView;
import com.juliy.view.StuInfoView;
import com.juliy.view.TchInfoView;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/** 管理员主界面各种操作的具体实现 */
public class ManagerMethod {

    //========== 其他 ==========

    /** 第一次打开界面时加载数据 */
    public static void loadInfo() {
        loadTime();
        showAllStudent();
        showAllTeacher();
        showAllCourse();
    }

    /** 加载时间 */
    private static void loadTime() {
        //获取当前时间并设置
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EEEE");
        String time = "当前时间：" + dateFormat.format(date);
        App.mgrMainView.setLabel_time(time);
    }

    /** 注销账号，返回登录界面 */
    public static void logout() {
        int result = JOptionPane.showConfirmDialog(null, "是否要注销账号？", "提示",
                                                   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            App.mgrMainView.dispose();
            App.loginView = new LoginView();
        }
    }

    //========== 学生管理界面 ==========

    /** 显示所有学生信息 */
    public static void showAllStudent() {
        List<Student> list = new StudentDAOImpl()
                .queryStudentInfo("", "", "", "", "");
        setStuInfoModel(list);

        //用于存放下拉框内容
        Vector<String> collegeData = new Vector<>();
        Vector<String> majorData = new Vector<>();
        Vector<String> statusData = new Vector<>();
        collegeData.add("全部");
        majorData.add("全部");
        statusData.add("全部");
        for (Student student : list) {
            //将存在的学院、专业、政治面貌等信息保存起来，以提供到下拉框中
            if (!collegeData.contains(student.getCollege()))
                collegeData.add(student.getCollege());
            if (!majorData.contains(student.getMajor()))
                majorData.add(student.getMajor());
            if (!statusData.contains(student.getPoliticalStatus()))
                statusData.add(student.getPoliticalStatus());
        }
        //将内容添加到下拉框中
        DefaultComboBoxModel<String> collegeModel = new DefaultComboBoxModel<>(collegeData);
        DefaultComboBoxModel<String> majorModel = new DefaultComboBoxModel<>(majorData);
        DefaultComboBoxModel<String> statusModel = new DefaultComboBoxModel<>(statusData);
        App.mgrMainView.setStuCbBox(collegeModel, majorModel, statusModel);
    }

    /** 显示符合要求的学生信息 */
    public static void searchStudent() {
        //获取输入框、下拉框内容
        String number = App.mgrMainView.getField_stuNumber();
        String name = App.mgrMainView.getField_stuName();
        String college = App.mgrMainView.getCbBox_stuCollege();
        String major = App.mgrMainView.getCbBox_stuMajor();
        String status = App.mgrMainView.getCbBox_stuStatus();

        //若下拉框选择的是全部，需设置为""以便搜索全部
        if ("全部".equals(college)) {
            college = "";
        }
        if ("全部".equals(major)) {
            major = "";
        }
        if ("全部".equals(status)) {
            status = "";
        }

        List<Student> list = new StudentDAOImpl()
                .queryStudentInfo(number, name, college, major, status);
        setStuInfoModel(list);
    }

    /** 设置学生信息表格模型并加载数据 */
    private static void setStuInfoModel(List<Student> list) {
        //用于将Date类型转化为String，格式为年-月-日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将list中的学生信息转存到vector数组中
        Vector<Vector<String>> tableData = new Vector<>();
        for (Student student : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(student.getNumber());
            rowData.add(student.getName());
            rowData.add(student.getAge());
            rowData.add(student.getSex());
            rowData.add(student.getCollege());
            rowData.add(student.getPreMajor());
            rowData.add(student.getMajor());
            rowData.add(student.getStuClass());
            rowData.add(sdf.format(student.getStartTime()));
            rowData.add(student.getPoliticalStatus());
            rowData.add(student.getHometown());
            rowData.add(student.getNationality());
            rowData.add(student.getId());
            rowData.add(student.getAddress());
            rowData.add(student.getPhone());
            rowData.add(student.getIsSports());
            rowData.add(student.getSelectCourse());
            rowData.add("修改");
            rowData.add("删除");
            tableData.add(rowData);
        }

        Vector<String> columnName = new Vector<>();
        columnName.add("学号");
        columnName.add("姓名");
        columnName.add("年龄");
        columnName.add("性别");
        columnName.add("学院");
        columnName.add("分流前专业");
        columnName.add("专业");
        columnName.add("班级");
        columnName.add("入学年份");
        columnName.add("政治面貌");
        columnName.add("祖籍");
        columnName.add("民族");
        columnName.add("身份证号");
        columnName.add("现居地址");
        columnName.add("电话");
        columnName.add("是否体育生");
        columnName.add("是否能选课");
        columnName.add("修改");
        columnName.add("删除");

        //自定义表格模型
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
                //列名为“修改”则显示修改，列名为“删除”则显示删除，否则显示vector内容
                if ("修改".equals(getColumnName(columnIndex))) {
                    return "修改";
                } else if ("删除".equals(getColumnName(columnIndex))) {
                    return "删除";
                } else {
                    return tableData.get(rowIndex).get(columnIndex);
                }
            }

            //显示列名
            @Override
            public String getColumnName(int columnIndex) {
                return columnName.get(columnIndex);
            }

            //设置单元格是否可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equals("修改") || getColumnName(column).equals("删除");
            }
        }

        App.mgrMainView.setTable_stuInfo(new tableModel());
    }

    /** 修改选中的学生信息 */
    public static void changeStudentInfo() {
        //获取选中的学生学号
        String number = App.mgrMainView.getSelectedStuNumber();
        //查询对应学生信息
        List<Student> list = new StudentDAOImpl()
                .queryStudentInfo(number, "", "", "", "");
        Student student = list.get(0);

        //判断是否打开了新界面，避免同时打开多个界面
        if (App.stuInfoView == null) {
            App.stuInfoView = new StuInfoView(1);
        }
        App.stuInfoView.setField(student);
    }

    /** 删除选中的学生信息 */
    public static void deleteStudent() {
        //获取选中的学生学号
        String number = App.mgrMainView.getSelectedStuNumber();
        //弹出提示框避免误操作
        int result = JOptionPane.showConfirmDialog(null, "确定要删除学号为 " + number + " 的学生吗？",
                                                   "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //同时从account表、student表、score表中删除对应数据
            if (new StudentDAOImpl().deleteStudent(number) && new AccountDAOImpl().deleteUser(number) &&
                    new ScoreDAOImpl().deleteCourseSelection(number, "")) {
                JOptionPane.showMessageDialog(null, "删除成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                //页面更新
                showAllStudent();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，删除学生失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 添加新学生 */
    public static void addNewStudent() {
        //判断是否打开了新界面，避免同时打开多个界面
        if (App.stuInfoView == null) {
            App.stuInfoView = new StuInfoView();
        }
        App.stuInfoView.clearField();
    }

    //========== 教师管理界面 ==========

    /** 显示所有教师信息 */
    public static void showAllTeacher() {
        List<Teacher> list = new TeacherDAOImpl()
                .queryTeacherInfo("", "", "", "", "");
        setTchInfoModel(list);

        //用于存放下拉框内容
        Vector<String> collegeData = new Vector<>();
        Vector<String> jobData = new Vector<>();
        Vector<String> statusData = new Vector<>();
        collegeData.add("全部");
        jobData.add("全部");
        statusData.add("全部");
        for (Teacher teacher : list) {
            //将存在的学院、专业、政治面貌等信息保存起来，以提供到下拉框中
            if (!collegeData.contains(teacher.getCollege()))
                collegeData.add(teacher.getCollege());
            if (!jobData.contains(teacher.getJob()))
                jobData.add(teacher.getJob());
            if (!statusData.contains(teacher.getPoliticalStatus()))
                statusData.add(teacher.getPoliticalStatus());
        }
        //将内容添加到下拉框中
        DefaultComboBoxModel<String> collegeModel = new DefaultComboBoxModel<>(collegeData);
        DefaultComboBoxModel<String> jobModel = new DefaultComboBoxModel<>(jobData);
        DefaultComboBoxModel<String> statusModel = new DefaultComboBoxModel<>(statusData);
        App.mgrMainView.setTchCbBox(collegeModel, jobModel, statusModel);
    }

    /** 显示符合要求的教师信息 */
    public static void searchTeacher() {
        //获取输入框、下拉框内容
        String number = App.mgrMainView.getField_tchNumber();
        String name = App.mgrMainView.getField_tchName();
        String college = App.mgrMainView.getCbBox_tchCollege();
        String job = App.mgrMainView.getCbBox_tchJob();
        String status = App.mgrMainView.getCbBox_tchStatus();

        //若下拉框选择的是全部，需设置为""以便搜索全部
        if ("全部".equals(college)) {
            college = "";
        }
        if ("全部".equals(job)) {
            job = "";
        }
        if ("全部".equals(status)) {
            status = "";
        }

        List<Teacher> list = new TeacherDAOImpl()
                .queryTeacherInfo(number, name, college, job, status);
        setTchInfoModel(list);
    }

    /** 设置教师信息表格模型并加载数据 */
    private static void setTchInfoModel(List<Teacher> list) {
        //将list中的学生信息转存到vector数组中
        Vector<Vector<String>> tableData = new Vector<>();
        for (Teacher teacher : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(teacher.getNumber());
            rowData.add(teacher.getName());
            rowData.add(teacher.getAge());
            rowData.add(teacher.getSex());
            rowData.add(teacher.getCollege());
            rowData.add(teacher.getProfession());
            rowData.add(teacher.getJob());
            rowData.add(teacher.getPoliticalStatus());
            rowData.add(teacher.getHometown());
            rowData.add(teacher.getNationality());
            rowData.add(teacher.getId());
            rowData.add(teacher.getAddress());
            rowData.add(teacher.getPhone());
            rowData.add("修改");
            rowData.add("删除");
            tableData.add(rowData);
        }

        Vector<String> columnName = new Vector<>();
        columnName.add("工号");
        columnName.add("姓名");
        columnName.add("年龄");
        columnName.add("性别");
        columnName.add("学院");
        columnName.add("专业");
        columnName.add("职称");
        columnName.add("政治面貌");
        columnName.add("祖籍");
        columnName.add("民族");
        columnName.add("身份证号");
        columnName.add("现居地址");
        columnName.add("电话");
        columnName.add("修改");
        columnName.add("删除");

        //自定义表格模型
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
                //列名为“修改”则显示修改，列名为“删除”则显示删除，否则显示vector内容
                if ("修改".equals(getColumnName(columnIndex))) {
                    return "修改";
                } else if ("删除".equals(getColumnName(columnIndex))) {
                    return "删除";
                } else {
                    return tableData.get(rowIndex).get(columnIndex);
                }
            }

            //显示列名
            @Override
            public String getColumnName(int columnIndex) {
                return columnName.get(columnIndex);
            }

            //设置单元格是否可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equals("修改") || getColumnName(column).equals("删除");
            }
        }

        App.mgrMainView.setTable_tchInfo(new tableModel());
    }

    /** 修改选中的教师信息 */
    public static void changeTeacherInfo() {
        //获取选中的教师工号
        String number = App.mgrMainView.getSelectedTchNumber();
        //查询对应教师信息
        List<Teacher> list = new TeacherDAOImpl()
                .queryTeacherInfo(number, "", "", "", "");
        Teacher teacher = list.get(0);

        //判断是否打开了新界面，避免同时打开多个界面
        if (App.tchInfoView == null)
            App.tchInfoView = new TchInfoView(1);
        App.tchInfoView.setField(teacher);
    }

    /** 删除选中的教师信息 */
    public static void deleteTeacher() {
        //获取选中的教师工号
        String number = App.mgrMainView.getSelectedTchNumber();
        //弹出提示框避免误操作
        int result = JOptionPane.showConfirmDialog(null, "确定要删除工号为 " + number + " 的教师吗？",
                                                   "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //同时从account表、teacher表、course表中删除对应数据
            if (new TeacherDAOImpl().deleteTeacher(number) && new AccountDAOImpl().deleteUser(number)) {
                //不将课程信息的删除作为判断条件，未使用连接查询，若一名教师没有授课，则返回值为false
                new CourseDAOImpl().deleteCourse("", number);
                JOptionPane.showMessageDialog(null, "删除成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                //页面更新
                showAllTeacher();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，删除教师失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 添加新教师 */
    public static void addNewTeacher() {
        //判断是否打开了新界面，避免同时打开多个界面
        if (App.tchInfoView == null)
            App.tchInfoView = new TchInfoView();
        App.tchInfoView.clearField();
    }

    //========== 课程管理界面 ==========

    /** 显示所有课程信息 */
    public static void showAllCourse() {
        List<Course> list = new CourseDAOImpl()
                .queryCourseInfo("", "", "");
        setCourseInfoModel(list);

        //用于存放下拉框内容
        Vector<String> collegeData = new Vector<>();
        Vector<String> typeData = new Vector<>();
        collegeData.add("全部");
        typeData.add("全部");
        for (Course course : list) {
            //将存在的学院、类型等信息保存起来，以提供到下拉框中
            if (!collegeData.contains(course.getCollege()))
                collegeData.add(course.getCollege());
            if (!typeData.contains(course.getType()))
                typeData.add(course.getType());
        }
        //将内容添加到下拉框中
        DefaultComboBoxModel<String> collegeModel = new DefaultComboBoxModel<>(collegeData);
        DefaultComboBoxModel<String> typeModel = new DefaultComboBoxModel<>(typeData);
        App.mgrMainView.setCourseCbBox(collegeModel, typeModel);
    }

    /** 显示符合要求的课程信息 */
    public static void searchCourse() {
        //获取输入框、下拉框内容
        String college = App.mgrMainView.getCbBox_courseCollege();
        String type = App.mgrMainView.getCbBox_courseType();

        //若下拉框选择的是全部，需设置为""以便搜索全部
        if ("全部".equals(college)) {
            college = "";
        }
        if ("全部".equals(type)) {
            type = "";
        }

        List<Course> list = new CourseDAOImpl()
                .queryCourseInfo("", college, type);
        setCourseInfoModel(list);
    }

    /** 设置课程信息表格模型并加载数据 */
    private static void setCourseInfoModel(List<Course> list) {
        //将list中的学生信息转存到vector数组中
        Vector<Vector<String>> tableData = new Vector<>();
        for (Course course : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(course.getNumber());
            rowData.add(course.getName());
            rowData.add(course.getTeacherName());
            rowData.add(course.getCollege());
            rowData.add(course.getProfession());
            rowData.add(course.getType());
            rowData.add(course.getCredit());
            rowData.add(course.getPeriod());
            rowData.add("修改");
            rowData.add("删除");
            tableData.add(rowData);
        }

        Vector<String> columnName = new Vector<>();
        columnName.add("编号");
        columnName.add("课程名称");
        columnName.add("任课教师");
        columnName.add("开课院系");
        columnName.add("开课专业");
        columnName.add("课程性质");
        columnName.add("学分");
        columnName.add("学时");
        columnName.add("修改");
        columnName.add("删除");

        //自定义表格模型
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
                //列名为“修改”则显示修改，列名为“删除”则显示删除，否则显示vector内容
                if ("修改".equals(getColumnName(columnIndex))) {
                    return "修改";
                } else if ("删除".equals(getColumnName(columnIndex))) {
                    return "删除";
                } else {
                    return tableData.get(rowIndex).get(columnIndex);
                }
            }

            //显示列名
            @Override
            public String getColumnName(int columnIndex) {
                return columnName.get(columnIndex);
            }

            //设置单元格是否可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equals("修改") || getColumnName(column).equals("删除");
            }
        }

        App.mgrMainView.setTable_courseInfo(new tableModel());
    }

    /** 修改选中的课程信息 */
    public static void changeCourseInfo() {
        //获取选中的课程编号
        String number = App.mgrMainView.getSelectedCourseNumber();
        //查询对应教师信息
        List<Course> list = new CourseDAOImpl().queryCourseInfo(number, "", "");
        Course course = list.get(0);

        //判断是否打开了新界面，避免同时打开多个界面
        if (App.courseInfoView == null)
            App.courseInfoView = new CourseInfoView(1);
        App.courseInfoView.setField(course);
    }

    /** 删除选中的课程信息 */
    public static void deleteCourse() {
        //获取选中的课程编号
        String number = App.mgrMainView.getSelectedCourseNumber();
        //弹出提示框避免误操作
        int result = JOptionPane.showConfirmDialog(null, "确定要删除编号为 " + number + " 的课程吗？",
                                                   "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //从course表中删除对应数据
            if (new CourseDAOImpl().deleteCourse(number, "")) {
                JOptionPane.showMessageDialog(null, "删除成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                //页面更新
                showAllCourse();
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，删除教师失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 添加新课程 */
    public static void addNewCourse() {
        //判断是否打开了新界面，避免同时打开多个界面
        if (App.courseInfoView == null)
            App.courseInfoView = new CourseInfoView();
        App.courseInfoView.clearField();
    }

}