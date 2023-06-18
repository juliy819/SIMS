/*
 * Created by JFormDesigner on Sat Jun 04 20:41:40 CST 2022
 */

package com.juliy.view;

import com.juliy.controller.ButtonMonitor;
import com.juliy.controller.WindowMonitor;
import com.juliy.model.bean.Course;

import javax.swing.*;
import java.awt.*;

/**
 * @author JuLiy
 */
public class CourseInfoView extends JFrame {
    /** 无参构造方法，表示是添加新课程 */
    public CourseInfoView() {
        initComponents();
        addListener();
        setComponentsName();
        btn_sure.setText("添加");
        btn_sure.setName("courseInfo_add");
        setVisible(true);
    }

    /** 带参构造方法，表示是修改课程信息 */
    public CourseInfoView(int flag) {
        initComponents();
        addListener();
        setComponentsName();
        btn_sure.setText("修改");
        btn_sure.setName("courseInfo_change");
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
        setName("frame_courseInfo");
        btn_cancel.setName("courseInfo_cancel");
    }

    /** 将要修改的课程信息预先填入输入框内 */
    public void setField(Course course) {
        field_number.setText(course.getNumber());
        field_name.setText(course.getName());
        field_teacher.setText(course.getTeacherName());
        field_college.setText(course.getCollege());
        field_profession.setText(course.getProfession());
        field_type.setText(course.getType());
        field_credit.setText(course.getCredit());
        field_period.setText(course.getPeriod());
    }

    /** 清空输入框内容 */
    public void clearField() {
        field_number.setText("");
        field_name.setText("");
        field_teacher.setText("");
        field_college.setText("");
        field_profession.setText("");
        field_type.setText("");
        field_credit.setText("");
        field_period.setText("");
    }

    public String getField_number() {
        return field_number.getText();
    }

    public String getField_name() {
        return field_name.getText();
    }

    public String getField_teacher() {
        return field_teacher.getText();
    }

    public String getField_college() {
        return field_college.getText();
    }

    public String getField_profession() {
        return field_profession.getText();
    }

    public String getField_type() {
        return field_type.getText();
    }

    public String getField_credit() {
        return field_credit.getText();
    }

    public String getField_period() {
        return field_period.getText();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label16 = new JLabel();
        label7 = new JLabel();
        label10 = new JLabel();
        field_number = new JTextField();
        field_teacher = new JTextField();
        field_profession = new JTextField();
        field_credit = new JTextField();
        field_period = new JTextField();
        field_type = new JTextField();
        field_college = new JTextField();
        field_name = new JTextField();
        btn_sure = new JButton();
        btn_cancel = new JButton();

        //======== this ========
        setTitle("\u8bfe\u7a0b\u4fe1\u606f");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u8bfe\u7a0b\u7f16\u53f7");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("\u8bfe\u7a0b\u540d\u79f0");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u4efb\u8bfe\u6559\u5e08");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label4 ----
        label4.setText("\u5f00\u8bfe\u9662\u7cfb");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label5 ----
        label5.setText("\u5f00\u8bfe\u4e13\u4e1a");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label16 ----
        label16.setText("\u8bfe\u7a0b\u6027\u8d28");
        label16.setHorizontalAlignment(SwingConstants.CENTER);
        label16.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label7 ----
        label7.setText("\u5b66\u5206");
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label10 ----
        label10.setText("\u5b66\u65f6");
        label10.setHorizontalAlignment(SwingConstants.CENTER);
        label10.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- btn_sure ----
        btn_sure.setText("\u786e\u5b9a");
        btn_sure.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- btn_cancel ----
        btn_cancel.setText("\u53d6\u6d88");
        btn_cancel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                          .addGroup(contentPaneLayout.createParallelGroup()
                                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                                              .addGap(39, 39, 39)
                                                                              .addGroup(contentPaneLayout.createParallelGroup()
                                                                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                                                                                  .addComponent(label1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_number, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(18, 18, 18)
                                                                                                                  .addComponent(label2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_name, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                                                                                  .addComponent(label3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_teacher, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(18, 18, 18)
                                                                                                                  .addComponent(label4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_college, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                                                                                  .addComponent(label5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_profession, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(18, 18, 18)
                                                                                                                  .addComponent(label16, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_type, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                                                                                  .addComponent(label7, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_credit, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(18, 18, 18)
                                                                                                                  .addComponent(label10, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                                  .addGap(6, 6, 6)
                                                                                                                  .addComponent(field_period, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
                                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                                              .addGap(201, 201, 201)
                                                                              .addComponent(btn_cancel)
                                                                              .addGap(18, 18, 18)
                                                                              .addComponent(btn_sure)))
                                          .addContainerGap(52, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                          .addGap(38, 38, 38)
                                          .addGroup(contentPaneLayout.createParallelGroup()
                                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(field_number, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(field_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                          .addGap(12, 12, 12)
                                          .addGroup(contentPaneLayout.createParallelGroup()
                                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(field_teacher, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(field_college, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                          .addGap(12, 12, 12)
                                          .addGroup(contentPaneLayout.createParallelGroup()
                                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                                              .addGap(1, 1, 1)
                                                                              .addComponent(label5))
                                                            .addComponent(field_profession, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label16, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(field_type, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                          .addGap(12, 12, 12)
                                          .addGroup(contentPaneLayout.createParallelGroup()
                                                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(field_credit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(field_period, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                          .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(btn_sure)
                                                            .addComponent(btn_cancel))
                                          .addGap(19, 19, 19))
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
    private JLabel label16;
    private JLabel label7;
    private JLabel label10;
    private JTextField field_number;
    private JTextField field_teacher;
    private JTextField field_profession;
    private JTextField field_credit;
    private JTextField field_period;
    private JTextField field_type;
    private JTextField field_college;
    private JTextField field_name;
    private JButton btn_sure;
    private JButton btn_cancel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
