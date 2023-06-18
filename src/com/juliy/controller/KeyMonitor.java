package com.juliy.controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 按键监听器类，用于监听输入按键并进行响应
 */
public class KeyMonitor implements KeyListener {
    //定义静态实例以供view层调用
    public static final KeyListener keyListener = new KeyMonitor();


    //按键按下触发事件重写
    @Override
    public void keyPressed(KeyEvent e) {
        //获取触发的文本框
        JTextField field = (JTextField) e.getSource();
        //判断按键类型是否为回车
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //获取文本框名字，并响应对应操作
            if ("login_account".equals(field.getName()) ||
                    "login_password".equals(field.getName())) {
                LoginMethod.loginCheck();
            } else if ("changePwd_oldPassword".equals(field.getName()) ||
                    "changePwd_newPassword".equals(field.getName()) ||
                    "changePwd_confirmPassword".equals(field.getName())) {
                ChangePasswordMethod.changePassword();
            } else if ("stu_address".equals(field.getName())) {
                StudentMethod.updateAddress();
            } else if ("stu_phone".equals(field.getName())) {
                StudentMethod.updatePhone();
            } else if ("tch_address".equals(field.getName())) {
                TeacherMethod.updateAddress();
            } else if ("tch_phone".equals(field.getName())) {
                TeacherMethod.updatePhone();
            }
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
