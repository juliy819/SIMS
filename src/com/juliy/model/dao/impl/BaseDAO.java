package com.juliy.model.dao.impl;

import com.juliy.model.dao.DAO;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 基本DAO类，实现数据库的连接与关闭操作
 * 其余DAO类均继承本类
 */
public class BaseDAO implements DAO {
    final String thisSchoolYear = "2021-2022";
    final String thisTerm = "2";

    final LoadProperties lp = LoadProperties.initial();
    /** 连接对象 */
    final Connection conn;
    /** 查询语句对象 */
    PreparedStatement pStatement = null;
    /** 结果集对象 */
    ResultSet rs = null;

    //在创建对象时直接进行数据库连接
    public BaseDAO() {
        conn = getConnection();
    }

    @Override
    public Connection getConnection() {
        try {
            //加载SQL Server JDBC驱动程序
            Class.forName(lp.className);
            //建立数据库连接
            return DriverManager.getConnection(lp.url, lp.user, lp.password);
        } catch (Exception e) {
            System.err.println("数据库连接失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            System.err.println("关闭rs对象失败");
            e.printStackTrace();
        }
        try {
            if (pStatement != null)
                pStatement.close();
        } catch (SQLException e) {
            System.err.println("关闭stmt对象失败");
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.err.println("关闭conn对象失败");
            e.printStackTrace();
        }
    }
}

/** 加载数据库配置文件类 */
class LoadProperties {
    public String className;
    public String url;
    public String user;
    public String password;

    private static LoadProperties lp;

    private LoadProperties() {
        //打开content目录下的配置文件
        InputStream is = getClass().getResourceAsStream("/resources/property/database.properties");
        Properties props = new Properties();
        try {
            //读取并设置对应内容
            props.load(is);
            this.className = props.getProperty("className");
            this.url = props.getProperty("url");
            this.user = props.getProperty("user");
            this.password = props.getProperty("password");
            System.out.println(this.user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 初始化，并避免重复读取内容 */
    public static LoadProperties initial() {
        //若未读取过，则lp为null，进行读取操作；若已读取过，则lp不为null，直接返回
        if (lp == null)
            lp = new LoadProperties();
        return lp;
    }
}
