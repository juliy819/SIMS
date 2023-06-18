package com.juliy.controller;

import com.juliy.App;
import com.juliy.model.bean.User;
import com.juliy.model.dao.impl.AccountDAOImpl;
import com.juliy.view.LoginView;

import javax.swing.*;

/** 修改密码界面各种操作的具体实现 */
public class ChangePasswordMethod {

    public static void changePassword() {
        //从输入框读取内容
        String oldPassword = App.changePasswordView.getOldPassword();
        String newPassword = App.changePasswordView.getNewPassword();
        String confirmPassword = App.changePasswordView.getConfirmPassword();
        //判断输入是否符合要求
        //任意输入框为空
        if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "输入不能为空！",
                                          "警告", JOptionPane.ERROR_MESSAGE);
        }
        //新密码过长
        else if (newPassword.length() > 20) {
            JOptionPane.showMessageDialog(null, "新密码过长！请控制在20个字符内",
                                          "警告", JOptionPane.ERROR_MESSAGE);
        } else {
            User user = new AccountDAOImpl().queryAccount(LoginView.user.getNumber(), oldPassword);
            //user不为null表示旧密码输入正确，找到了对应账号
            if (user != null) {
                //判断两次新密码是否相同
                if (newPassword.equals(confirmPassword)) {
                    //密码修改成功
                    if (new AccountDAOImpl().updatePassword(LoginView.user.getNumber(), newPassword)) {
                        JOptionPane.showMessageDialog(null, "密码修改成功！",
                                                      "消息", JOptionPane.INFORMATION_MESSAGE);
                        App.changePasswordView.dispose();
                    }
                    //密码修改失败
                    else {
                        JOptionPane.showMessageDialog(null, "出现未知错误，密码修改失败！\n请联系管理员",
                                                      "警告", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致！\n请检查",
                                                  "警告", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "旧密码错误！请重新输入",
                                              "警告", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
