/*
 * Created by JFormDesigner on Sat Jun 04 15:25:46 CST 2022
 */

package com.juliy.view;

import com.juliy.controller.ButtonMonitor;
import com.juliy.controller.WindowMonitor;
import com.juliy.model.bean.Teacher;

import javax.swing.*;
import java.awt.*;

/**
 * @author JuLiy
 */
public class TchInfoView extends JFrame {

    /** 无参构造方法，表示是添加新教师 */
    public TchInfoView() {
        initComponents();
        addListener();
        setComponentsName();
        btn_sure.setText("添加");
        btn_sure.setName("tchInfo_add");
        setVisible(true);
    }

    /** 带参构造方法，表示是修改教师信息 */
    public TchInfoView(int flag) {
        initComponents();
        addListener();
        setComponentsName();
        btn_sure.setText("修改");
        btn_sure.setName("tchInfo_change");
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
        setName("frame_tchInfo");
        btn_cancel.setName("tchInfo_cancel");
    }

    /** 将要修改的教师信息预先填入输入框内 */
    public void setField(Teacher teacher) {
        field_number.setText(teacher.getNumber());
        field_name.setText(teacher.getName());
        field_age.setText(teacher.getAge());
        field_sex.setText(teacher.getSex());
        field_college.setText(teacher.getCollege());
        field_profession.setText(teacher.getProfession());
        field_job.setText(teacher.getJob());
        field_politicalStatus.setText(teacher.getPoliticalStatus());
        field_hometown.setText(teacher.getHometown());
        field_nationality.setText(teacher.getNationality());
        field_id.setText(teacher.getId());
        field_phone.setText(teacher.getPhone());
        field_address.setText(teacher.getAddress());
    }

    /** 清空输入框内容 */
    public void clearField() {
        field_number.setText("");
        field_name.setText("");
        field_age.setText("");
        field_sex.setText("");
        field_college.setText("");
        field_profession.setText("");
        field_job.setText("");
        field_politicalStatus.setText("");
        field_hometown.setText("");
        field_nationality.setText("");
        field_id.setText("");
        field_phone.setText("");
        field_address.setText("");
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

    public String getField_profession() {
        return field_profession.getText();
    }

    public String getField_job() {
        return field_job.getText();
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        label7 = new JLabel();
        label11 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        field_address = new JTextField();
        field_id = new JTextField();
        field_hometown = new JTextField();
        field_job = new JTextField();
        field_college = new JTextField();
        field_age = new JTextField();
        field_number = new JTextField();
        label2 = new JLabel();
        field_name = new JTextField();
        field_sex = new JTextField();
        label4 = new JLabel();
        field_profession = new JTextField();
        label10 = new JLabel();
        field_politicalStatus = new JTextField();
        field_nationality = new JTextField();
        label12 = new JLabel();
        label15 = new JLabel();
        field_phone = new JTextField();
        btn_cancel = new JButton();
        btn_sure = new JButton();
        label16 = new JLabel();

        //======== this ========
        setTitle("\u6559\u5e08\u4fe1\u606f");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u5de5\u53f7");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u5e74\u9f84");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label5 ----
        label5.setText("\u5b66\u9662");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label7 ----
        label7.setText("\u804c\u79f0");
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label11 ----
        label11.setText("\u7956\u7c4d");
        label11.setHorizontalAlignment(SwingConstants.CENTER);
        label11.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label13 ----
        label13.setText("\u8eab\u4efd\u8bc1\u53f7");
        label13.setHorizontalAlignment(SwingConstants.CENTER);
        label13.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label14 ----
        label14.setText("\u73b0\u5c45\u5730\u5740");
        label14.setHorizontalAlignment(SwingConstants.CENTER);
        label14.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("\u59d3\u540d");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label4 ----
        label4.setText("\u6027\u522b");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label10 ----
        label10.setText("\u653f\u6cbb\u9762\u8c8c");
        label10.setHorizontalAlignment(SwingConstants.CENTER);
        label10.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label12 ----
        label12.setText("\u6c11\u65cf");
        label12.setHorizontalAlignment(SwingConstants.CENTER);
        label12.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label15 ----
        label15.setText("\u7535\u8bdd\u53f7\u7801");
        label15.setHorizontalAlignment(SwingConstants.CENTER);
        label15.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- btn_cancel ----
        btn_cancel.setText("\u53d6\u6d88");

        //---- btn_sure ----
        btn_sure.setText("\u786e\u5b9a");

        //---- label16 ----
        label16.setText("\u4e13\u4e1a");
        label16.setHorizontalAlignment(SwingConstants.CENTER);
        label16.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addComponent(btn_cancel)
                            .addGap(59, 59, 59)
                            .addComponent(btn_sure))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
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
                                    .addComponent(field_age, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(field_sex, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(field_college, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label16, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_profession, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(field_job, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(label11, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(field_hometown, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(contentPaneLayout.createParallelGroup()
                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label10, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(field_politicalStatus, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label12, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(field_nationality, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label13, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(field_id, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(label15, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(field_phone, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label14, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(field_address, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(58, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_number, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_age, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_sex, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(field_college, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(label5))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(field_profession, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label16, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_politicalStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_job, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_hometown, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_nationality, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_id, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label15, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_phone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label14, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(field_address, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_sure)
                        .addComponent(btn_cancel))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label3;
    private JLabel label5;
    private JLabel label7;
    private JLabel label11;
    private JLabel label13;
    private JLabel label14;
    private JTextField field_address;
    private JTextField field_id;
    private JTextField field_hometown;
    private JTextField field_job;
    private JTextField field_college;
    private JTextField field_age;
    private JTextField field_number;
    private JLabel label2;
    private JTextField field_name;
    private JTextField field_sex;
    private JLabel label4;
    private JTextField field_profession;
    private JLabel label10;
    private JTextField field_politicalStatus;
    private JTextField field_nationality;
    private JLabel label12;
    private JLabel label15;
    private JTextField field_phone;
    private JButton btn_cancel;
    private JButton btn_sure;
    private JLabel label16;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
