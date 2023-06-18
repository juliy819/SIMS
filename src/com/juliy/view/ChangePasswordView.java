/*
 * Created by JFormDesigner on Sun May 22 19:47:23 CST 2022
 */

package com.juliy.view;

import com.juliy.controller.ButtonMonitor;
import com.juliy.controller.KeyMonitor;
import com.juliy.controller.WindowMonitor;

import javax.swing.*;
import java.awt.*;

/**
 * @author JuLiy
 */
public class ChangePasswordView extends JFrame {
    public ChangePasswordView() {
        initComponents();
        addActionListener();
        setComponentsName();
        setVisible(true);
    }

    /** 给需要监听的控件添加对应自定义监听器 */
    private void addActionListener() {
        //监听窗口事件
        addWindowListener(WindowMonitor.windowMonitor);
        //监听点击事件
        btn_cfmChangePwd.addActionListener(ButtonMonitor.btnListener);
        //监听回车
        field_oldPassword.addKeyListener(KeyMonitor.keyListener);
        field_newPassword.addKeyListener(KeyMonitor.keyListener);
        field_confirmPassword.addKeyListener(KeyMonitor.keyListener);
    }

    /** 给需要监听的控件设置name，以便识别 */
    private void setComponentsName() {
        setName("frame_changePwd");
        btn_cfmChangePwd.setName("changePwd_changePassword");
        field_oldPassword.setName("changePwd_oldPassword");
        field_newPassword.setName("changePwd_newPassword");
        field_confirmPassword.setName("changePwd_confirmPassword");
    }

    /** 获取输入的旧密码 */
    public String getOldPassword() {
        return field_oldPassword.getText();
    }

    /** 获取输入的新密码 */
    public String getNewPassword() {
        return new String(field_newPassword.getPassword());
    }

    /** 获取输入的确认密码 */
    public String getConfirmPassword() {
        return new String(field_confirmPassword.getPassword());
    }

    /** 清空输入框 */
    //用于重复打开页面时
    public void clearField() {
        field_oldPassword.setText("");
        field_newPassword.setText("");
        field_confirmPassword.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        field_oldPassword = new JTextField();
        field_newPassword = new JPasswordField();
        field_confirmPassword = new JPasswordField();
        btn_cfmChangePwd = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("\u4fee\u6539\u5bc6\u7801");
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u8f93\u5165\u65e7\u5bc6\u7801\uff1a");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("\u8f93\u5165\u65b0\u5bc6\u7801\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u786e\u8ba4\u65b0\u5bc6\u7801\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- btn_cfmChangePwd ----
        btn_cfmChangePwd.setText("\u786e\u8ba4\u4fee\u6539");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(field_oldPassword, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(field_newPassword, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label3)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(field_confirmPassword, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(btn_cfmChangePwd)))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(field_oldPassword, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(field_newPassword, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(field_confirmPassword, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btn_cfmChangePwd)
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField field_oldPassword;
    private JPasswordField field_newPassword;
    private JPasswordField field_confirmPassword;
    private JButton btn_cfmChangePwd;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
