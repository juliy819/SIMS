package com.juliy.model.dao.impl;

import com.juliy.model.bean.User;
import com.juliy.model.dao.AccountDAO;

import java.sql.SQLException;

/** account表DAO实现类 */
public class AccountDAOImpl extends BaseDAO implements AccountDAO {

    @Override
    public User queryAccount(String account, String password) {
        //SQL查询语句
        String sql = "select * from account where account = ? and password = ?";
        User user = null;
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, account);
            this.pStatement.setString(2, password);
            this.rs = this.pStatement.executeQuery();
            //如果对应账号存在，则rs.next()方法的值为true
            if (this.rs.next()) {
                //依据account表中对应账号的类型创建相应User对象
                if (this.rs.getString("type").equals("manager")) {
                    user = User.MANAGER;
                } else if (this.rs.getString("type").equals("teacher")) {
                    user = User.TEACHER;
                } else if (this.rs.getString("type").equals("student")) {
                    user = User.STUDENT;
                }
                assert user != null;
                user.setNumber(rs.getString("account"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return user;
    }

    @Override
    public boolean updatePassword(String account, String newPassword) {
        //SQL查询语句
        String sql = "update account set password = ? where account = ?";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, newPassword);
            this.pStatement.setString(2, account);
            int result = this.pStatement.executeUpdate();
            return result != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean addUser(String number, String type) {
        //SQL查询语句
        String sql = "insert into account value(?, ?, ?)";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, number);
            this.pStatement.setString(2, number);
            this.pStatement.setString(3, type);
            int result = this.pStatement.executeUpdate();
            return result != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean deleteUser(String number) {
        //SQL查询语句
        String sql = "delete from account where account = ?";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, number);
            int result = this.pStatement.executeUpdate();
            return result != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }
}

