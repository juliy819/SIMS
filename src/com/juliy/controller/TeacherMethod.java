package com.juliy.controller;

import com.juliy.App;
import com.juliy.model.bean.Course;
import com.juliy.model.bean.Student;
import com.juliy.model.bean.Teacher;
import com.juliy.model.dao.impl.CourseDAOImpl;
import com.juliy.model.dao.impl.ScoreDAOImpl;
import com.juliy.model.dao.impl.StudentDAOImpl;
import com.juliy.model.dao.impl.TeacherDAOImpl;
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
public class TeacherMethod {

    //========== 其他 ==========

    /** 第一次打开界面时加载数据 */
    public static void loadInfo() {
        loadWelcomeAndTime();
        loadPersonalInfoPage();
        showAllCourse();
        showStuScore();
    }

    /** 加载欢迎标签和时间 */
    private static void loadWelcomeAndTime() {
        //在teacher表中查询登录教师的信息
        List<Teacher> list = new TeacherDAOImpl()
                .queryTeacherInfo(LoginView.user.getNumber(), "", "", "", "");
        //设置欢迎语句
        Teacher teacher = list.get(0);
        String welcome = teacher.getCollege() + " " + teacher.getProfession() + " " +
                teacher.getName() + "" + teacher.getJob() + " ，您好，欢迎使用本系统";
        App.tchMainView.setLabel_welcome(welcome);
        //获取当前时间并设置
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EEEE");
        String time = "当前时间：" + dateFormat.format(date);
        App.tchMainView.setLabel_time(time);
    }


    /** 注销账号，返回登录界面 */
    public static void logout() {
        int result = JOptionPane.showConfirmDialog(null, "是否要注销账号？", "提示",
                                                   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            App.tchMainView.dispose();
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

    //========== 我教的课界面 ==========

    /** 显示所有教的课程 */
    public static void showAllCourse() {
        //查询对应工号的教师的课程信息
        List<Course> list = new CourseDAOImpl().queryCourseInfo(LoginView.user.getNumber(), "", 1);
        setCourseModel(list);

        //用于存放下拉框内容
        Vector<String> typeData = new Vector<>();
        Vector<String> courseData = new Vector<>();
        typeData.add("全部");
        for (Course course : list) {
            //将存在的类型和课程名保存起来，以提供到下拉框中
            if (!typeData.contains(course.getType()))
                typeData.add(course.getType());
            if (!courseData.contains(course.getName()))
                courseData.add(course.getName());
        }
        //将内容添加到下拉框中
        DefaultComboBoxModel<String> typeModel = new DefaultComboBoxModel<>(typeData);
        DefaultComboBoxModel<String> courseModel = new DefaultComboBoxModel<>(courseData);
        App.tchMainView.setCbBox_type(typeModel);
        App.tchMainView.setCbBox_course(courseModel);
    }

    /** 显示对应类型的课程 */
    public static void searchCourse() {
        //获取选择的课程性质
        String type = App.tchMainView.getCbBox_type();
        //若要筛选全部，则需设为空，以便sql搜索
        if ("全部".equals(type)) {
            type = "";
        }
        //查询对应的课程信息
        List<Course> list = new CourseDAOImpl().queryCourseInfo(LoginView.user.getNumber(), type, 1);
        setCourseModel(list);
    }

    /** 设置课程信息表格模型并加载数据 */
    private static void setCourseModel(List<Course> list) {
        //将list中的学生信息转存到vector数组中
        Vector<Vector<String>> tableData = new Vector<>();
        for (Course course : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(course.getNumber());
            rowData.add(course.getName());
            rowData.add(course.getCollege());
            rowData.add(course.getProfession());
            rowData.add(course.getType());
            rowData.add(course.getCredit());
            rowData.add(course.getPeriod());
            tableData.add(rowData);
        }

        Vector<String> columnName = new Vector<>();
        columnName.add("课程编号");
        columnName.add("课程名称");
        columnName.add("开课院系");
        columnName.add("开课专业");
        columnName.add("课程性质");
        columnName.add("学分");
        columnName.add("学时");
        //设置表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName) {
            //让表格内容不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        App.tchMainView.setTable_course(model);
    }

    //========== 学生成绩界面 ==========

    /** 显示对应课程的学生成绩表 */
    public static void showStuScore() {
        //获取选中的课程名
        String courseName = App.tchMainView.getCbBox_course();
        List<Student> list = new StudentDAOImpl().queryStudentScore(courseName);
        setScoreModel(list);
    }

    /** 设置课程信息表格模型并加载数据 */
    private static void setScoreModel(List<Student> list) {
        //将list中的学生信息转存到vector数组中
        Vector<Vector<String>> tableData = new Vector<>();
        for (Student student : list) {
            Vector<String> rowData = new Vector<>();
            rowData.add(student.getNumber());
            rowData.add(student.getName());
            rowData.add(student.getCollege());
            rowData.add(student.getMajor());
            rowData.add(student.getStuClass());
            if (student.getScore() == null)
                rowData.add("");
            else
                rowData.add(student.getScore());
            rowData.add("编辑");
            tableData.add(rowData);
        }

        Vector<String> columnName = new Vector<>();
        columnName.add("学号");
        columnName.add("姓名");
        columnName.add("学院");
        columnName.add("专业");
        columnName.add("班级");
        columnName.add("成绩");
        columnName.add("编辑");

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
                if ("编辑".equals(getColumnName(columnIndex))) {
                    return "编辑";
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
                return getColumnName(column).equals("编辑");
            }
        }
        App.tchMainView.setTable_score(new tableModel());
    }

    /** 编辑选中学生的成绩 */
    public static void editScore() {
        //获取要编辑的学生学号和成绩
        String[] stuInfo = App.tchMainView.getSelectedStuInfo();
        String studentNumber = stuInfo[0];
        String courseName = App.tchMainView.getCbBox_course();
        //获取输入的成绩
        String score = JOptionPane.showInputDialog(null, "请设置成绩：", stuInfo[1]);
        if (score == null)
            return;
        else if (Integer.parseInt(score) < 0 || Integer.parseInt(score) > 100) {
            JOptionPane.showMessageDialog(null, "成绩范围为0-100！",
                                          "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (new ScoreDAOImpl().updateScore(studentNumber, courseName, score)) {
            JOptionPane.showMessageDialog(null, "成绩修改成功！",
                                          "提示", JOptionPane.INFORMATION_MESSAGE);
            showStuScore();
        } else {
            JOptionPane.showMessageDialog(null, "出现未知错误，成绩修改失败！",
                                          "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    //========== 个人信息界面 ==========

    /** 加载教师个人信息页面 */
    public static void loadPersonalInfoPage() {
        //从登录界面获取工号
        String number = LoginView.user.getNumber();
        //在teacher表中查询该工号对应的教师信息
        List<Teacher> list = new TeacherDAOImpl()
                .queryTeacherInfo(number, "", "", "", "");
        Teacher teacher = list.get(0);
        //显示个人信息
        App.tchMainView.setLabel_name(teacher.getName());
        App.tchMainView.setLabel_number(teacher.getNumber());
        App.tchMainView.setLabel_sex(teacher.getSex());
        App.tchMainView.setLabel_age(teacher.getAge());
        App.tchMainView.setLabel_nationality(teacher.getNationality());
        App.tchMainView.setLabel_politicalStatus(teacher.getPoliticalStatus());
        App.tchMainView.setLabel_hometown(teacher.getHometown());
        App.tchMainView.setLabel_id(teacher.getId());
        App.tchMainView.setLabel_address(teacher.getAddress());
        App.tchMainView.setLabel_phone(teacher.getPhone());
        App.tchMainView.setLabel_college(teacher.getCollege());
        App.tchMainView.setLabel_profession(teacher.getProfession());
        App.tchMainView.setLabel_job(teacher.getJob());
    }

    private static String preAddress;

    /** 编辑个人信息页面中的地址栏 */
    public static void editAddress() {
        //存储之前的地址
        preAddress = App.tchMainView.getLabel_address();
        App.tchMainView.changeAddressCard();
        App.tchMainView.changeBtn_editAddress();
    }

    /** 更新地址信息 */
    public static void updateAddress() {
        String address = App.tchMainView.getField_address();
        //判断用户输入是否符合要求
        //输入为空
        if (address.equals("")) {
            int result = JOptionPane.showConfirmDialog(null, "地址不能为空", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.tchMainView.changeAddressCard();
                App.tchMainView.changeBtn_editAddress();
            }
        }
        //新旧地址相同
        else if (address.equals(preAddress)) {
            int result = JOptionPane.showConfirmDialog(null, "新地址与原地址相同", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.tchMainView.changeAddressCard();
                App.tchMainView.changeBtn_editAddress();
            }
        }
        //输入地址过长
        else if (address.length() > 50) {
            int result = JOptionPane.showConfirmDialog(null, "地址过长！", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.tchMainView.changeAddressCard();
                App.tchMainView.changeBtn_editAddress();
            }
        }
        //进行数据更新
        else {
            //判断是否更新成功
            if (new TeacherDAOImpl().updateAddress(LoginView.user.getNumber(), address, null)) {
                JOptionPane.showMessageDialog(null, "地址更改成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.tchMainView.setLabel_address(address);
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，地址更改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
            App.tchMainView.changeAddressCard();
            App.tchMainView.changeBtn_editAddress();
        }
    }

    private static String prePhone;

    /** 编辑个人信息页面中的电话栏 */
    public static void editPhone() {
        //存储之前的地址
        prePhone = App.tchMainView.getLabel_phone();
        App.tchMainView.changePhoneCard();
        App.tchMainView.changeBtn_editPhone();
    }

    /** 更新电话信息 */
    public static void updatePhone() {
        String phone = App.tchMainView.getField_phone();
        //判断用户输入是否符合要求
        //输入为空
        if (phone.equals("")) {
            int result = JOptionPane.showConfirmDialog(null, "号码不能为空", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.tchMainView.changePhoneCard();
                App.tchMainView.changeBtn_editPhone();
            }
        }
        //新旧地址相同
        else if (phone.equals(prePhone)) {
            int result = JOptionPane.showConfirmDialog(null, "新号码与原号码相同", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.tchMainView.changePhoneCard();
                App.tchMainView.changeBtn_editPhone();
            }
        }
        //输入地址过长
        else if (phone.length() > 11) {
            int result = JOptionPane.showConfirmDialog(null, "号码过长！", "错误",
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION) {
                App.tchMainView.changePhoneCard();
                App.tchMainView.changeBtn_editPhone();
            }
        }
        //进行数据更新
        else {
            //判断是否更新成功
            if (new TeacherDAOImpl().updateAddress(LoginView.user.getNumber(), null, phone)) {
                JOptionPane.showMessageDialog(null, "号码更改成功！",
                                              "提示", JOptionPane.INFORMATION_MESSAGE);
                App.tchMainView.setLabel_phone(phone);
            } else {
                JOptionPane.showMessageDialog(null, "出现未知错误，号码更改失败！",
                                              "提示", JOptionPane.ERROR_MESSAGE);
            }
            App.tchMainView.changePhoneCard();
            App.tchMainView.changeBtn_editPhone();
        }
    }

}
