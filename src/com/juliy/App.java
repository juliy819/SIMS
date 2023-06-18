package com.juliy;

import com.formdev.flatlaf.FlatLightLaf;
import com.juliy.view.*;

import javax.swing.*;

/**
 * @author juliy
 */
public class App {
    //创建各窗口对象
    public static LoginView loginView;
    public static StudentMainView stuMainView;
    public static TeacherMainView tchMainView;
    public static ManagerMainView mgrMainView;
    public static ChangePasswordView changePasswordView;
    public static StuInfoView stuInfoView;
    public static TchInfoView tchInfoView;
    public static CourseInfoView courseInfoView;

    public static void main(String[] args) {
        initializeFlatLaf();
        loginView = new LoginView();
    }

    /**
     * 初始化swing美化包 需在main方法开头引用  用户名
     */
    private static void initializeFlatLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            //四种风格：FlatDarkLaf()、FlatDarculaLaf()、FlatLightLaf()、FlatIntelliJLaf()

        } catch (Exception ex) {
            java.lang.System.err.println("Failed to initialize FlatLaF");
        }
    }
}
