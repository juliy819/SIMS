/*
 * Created by JFormDesigner on Fri Jun 03 17:06:42 CST 2022
 */

package com.juliy.view;

import com.juliy.controller.ButtonMonitor;
import com.juliy.controller.WindowMonitor;
import com.juliy.model.bean.Student;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * @author JuLiy
 */
public class StuInfoView extends JFrame {

    /** 无参构造方法，表示是添加新学生 */
    public StuInfoView() {
        initComponents();
        addListener();
        setComponentsName();
        btn_sure.setText("添加");
        btn_sure.setName("stuInfo_add");
        setVisible(true);
    }

    /** 带参构造方法，表示是修改学生信息 */
    public StuInfoView(int flag) {
        initComponents();
        addListener();
        setComponentsName();
        btn_sure.setText("修改");
        btn_sure.setName("stuInfo_change");
        setVisible(true);
    }

    /** 给需要监听的控件添加对应自定义监听器 */
    private void addListener() {
        //监听窗口事件
        addWindowListener(WindowMonitor.windowMonitor);
        //监听点击事件
        btn_sure.addActionListener(ButtonMonitor.btnListener);
        btn_cancel.addActionListener(ButtonMonitor.btnListener);
    }

    /** 给需要监听的控件设置name，以便识别 */
    private void setComponentsName() {
        setName("frame_stuInfo");
        btn_cancel.setName("stuInfo_cancel");
    }

    /** 将要修改的学生信息预先填入输入框内 */
    public void setField(Student student) {
        //用于将Date类型转化为String，格式为年-月-日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        field_number.setText(student.getNumber());
        field_name.setText(student.getName());
        field_age.setText(student.getAge());
        field_sex.setText(student.getSex());
        field_college.setText(student.getCollege());
        field_preMajor.setText(student.getPreMajor());
        field_major.setText(student.getMajor());
        field_class.setText(student.getStuClass());
        field_startTime.setText(sdf.format(student.getStartTime()));
        field_politicalStatus.setText(student.getPoliticalStatus());
        field_hometown.setText(student.getHometown());
        field_nationality.setText(student.getNationality());
        field_id.setText(student.getId());
        field_phone.setText(student.getPhone());
        field_address.setText(student.getAddress());
        field_isSports.setText(student.getIsSports());
        field_selectCourse.setText(student.getSelectCourse());
    }

    /** 清空输入框内容 */
    public void clearField() {
        field_number.setText("");
        field_name.setText("");
        field_age.setText("");
        field_sex.setText("");
        field_college.setText("");
        field_preMajor.setText("");
        field_major.setText("");
        field_class.setText("");
        field_startTime.setText("");
        field_politicalStatus.setText("");
        field_hometown.setText("");
        field_nationality.setText("");
        field_id.setText("");
        field_phone.setText("");
        field_address.setText("");
        field_isSports.setText("");
        field_selectCourse.setText("");
    }

    public String getField_number() {
        return field_number.getText();
    }

    public String getField_name() {
        return field_name.getText();
    }

    public String getField_age() {
        return field_age.getText();
    }

    public String getField_sex() {
        return field_sex.getText();
    }

    public String getField_college() {
        return field_college.getText();
    }

    public String getField_preMajor() {
        return field_preMajor.getText();
    }

    public String getField_major() {
        return field_major.getText();
    }

    public String getField_class() {
        return field_class.getText();
    }

    public String getField_startTime() {
        return field_startTime.getText();
    }

    public String getField_politicalStatus() {
        return field_politicalStatus.getText();
    }

    public String getField_hometown() {
        return field_hometown.getText();
    }

    public String getField_nationality() {
        return field_nationality.getText();
    }

    public String getField_id() {
        return field_id.getText();
    }

    public String getField_phone() {
        return field_phone.getText();
    }

    public String getField_address() {
        return field_address.getText();
    }

    public String getField_isSports() {
        return field_isSports.getText();
    }

    public String getField_selectCourse() {
        return field_selectCourse.getText();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        field_number = new JTextField();
        field_age = new JTextField();
        field_college = new JTextField();
        field_major = new JTextField();
        field_startTime = new JTextField();
        field_hometown = new JTextField();
        field_name = new JTextField();
        field_sex = new JTextField();
        field_preMajor = new JTextField();
        field_class = new JTextField();
        field_selectCourse = new JTextField();
        field_nationality = new JTextField();
        field_isSports = new JTextField();
        field_phone = new JTextField();
        field_id = new JTextField();
        btn_sure = new JButton();
        btn_cancel = new JButton();
        label17 = new JLabel();
        field_politicalStatus = new JTextField();
        field_address = new JTextField();

        //======== this ========
        setTitle("\u5b66\u751f\u4fe1\u606f");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u5b66\u53f7");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("\u59d3\u540d");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u5e74\u9f84");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label4 ----
        label4.setText("\u6027\u522b");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label5 ----
        label5.setText("\u5b66\u9662");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label6 ----
        label6.setText("\u5206\u6d41\u524d\u4e13\u4e1a");
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label7 ----
        label7.setText("\u4e13\u4e1a");
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label8 ----
        label8.setText("\u73ed\u7ea7");
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label9 ----
        label9.setText("\u5165\u5b66\u5e74\u4efd");
        label9.setHorizontalAlignment(SwingConstants.CENTER);
        label9.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label10 ----
        label10.setText("\u653f\u6cbb\u9762\u8c8c");
        label10.setHorizontalAlignment(SwingConstants.CENTER);
        label10.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label11 ----
        label11.setText("\u7956\u7c4d");
        label11.setHorizontalAlignment(SwingConstants.CENTER);
        label11.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label12 ----
        label12.setText("\u6c11\u65cf");
        label12.setHorizontalAlignment(SwingConstants.CENTER);
        label12.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label13 ----
        label13.setText("\u8eab\u4efd\u8bc1\u53f7");
        label13.setHorizontalAlignment(SwingConstants.CENTER);
        label13.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label14 ----
        label14.setText("\u73b0\u5c45\u5730\u5740");
        label14.setHorizontalAlignment(SwingConstants.CENTER);
        label14.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label15 ----
        label15.setText("\u7535\u8bdd\u53f7\u7801");
        label15.setHorizontalAlignment(SwingConstants.CENTER);
        label15.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label16 ----
        label16.setText("\u662f\u5426\u4f53\u80b2\u751f");
        label16.setHorizontalAlignment(SwingConstants.CENTER);
        label16.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- btn_sure ----
        btn_sure.setText("\u786e\u5b9a");

        //---- btn_cancel ----
        btn_cancel.setText("\u53d6\u6d88");

        //---- label17 ----
        label17.setText("\u662f\u5426\u80fd\u9009\u8bfe");
        label17.setHorizontalAlignment(SwingConstants.CENTER);
        label17.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label14, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(field_address, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 70, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label13, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(field_id))
                                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(field_number, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(field_age, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(field_major, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(label9, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(field_startTime, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(label11, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(field_hometown, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(field_college, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label16, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_isSports, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_politicalStatus, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label17, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_selectCourse, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label15, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_phone, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label12, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_nationality, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_class, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_preMajor, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_sex, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(field_name, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(145, 145, 145)
                                    .addComponent(btn_cancel)
                                    .addGap(58, 58, 58)
                                    .addComponent(btn_sure)))
                            .addContainerGap(70, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(field_number, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(field_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_age, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_sex, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(field_college, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(field_preMajor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_major, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_class, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_startTime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_politicalStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_hometown, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_nationality, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_id, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label15, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_phone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label16, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_isSports, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label17, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_selectCourse, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label14, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_address, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_sure)
                        .addComponent(btn_cancel))
                    .addGap(27, 27, 27))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JTextField field_number;
    private JTextField field_age;
    private JTextField field_college;
    private JTextField field_major;
    private JTextField field_startTime;
    private JTextField field_hometown;
    private JTextField field_name;
    private JTextField field_sex;
    private JTextField field_preMajor;
    private JTextField field_class;
    private JTextField field_selectCourse;
    private JTextField field_nationality;
    private JTextField field_isSports;
    private JTextField field_phone;
    private JTextField field_id;
    private JButton btn_sure;
    private JButton btn_cancel;
    private JLabel label17;
    private JTextField field_politicalStatus;
    private JTextField field_address;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
