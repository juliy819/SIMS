package com.juliy.model.dao.impl;

import com.juliy.model.bean.Teacher;
import com.juliy.model.dao.TeacherDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Teacher表DAO实现类 */
public class TeacherDAOImpl extends BaseDAO implements TeacherDAO {

    @Override
    public List<Teacher> queryTeacherInfo(String number, String name, String college, String job, String status) {
        //SQL查询语句
        String sql = "select * from teacher where number like concat('%', ?, '%') and " +
                "name like concat('%', ?, '%') and college like concat('%', ?, '%') and " +
                "job like concat(?, '%') and political_status like concat('%', ?, '%') " +
                "order by number asc";
        List<Teacher> list = new ArrayList<>();
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, number);
            this.pStatement.setString(2, name);
            this.pStatement.setString(3, college);
            this.pStatement.setString(4, job);
            this.pStatement.setString(5, status);
            this.rs = this.pStatement.executeQuery();
            while (this.rs.next()) {
                //获取详细数据封装进teacher对象,再添加到list中
                String tchNumber = this.rs.getString("number");
                String tchName = this.rs.getString("name");
                String age = this.rs.getString("age");
                String sex = this.rs.getString("sex");
                String nationality = this.rs.getString("nationality");
                String politicalStatus = this.rs.getString("political_status");
                String hometown = this.rs.getString("hometown");
                String id = this.rs.getString("id");
                String address = this.rs.getString("address");
                String phone = this.rs.getString("phone");
                String tchCollege = this.rs.getString("college");
                String profession = this.rs.getString("profession");
                String tchJob = this.rs.getString("job");
                Teacher teacher = new Teacher(tchNumber, tchName, age, sex, nationality,
                                              politicalStatus, hometown, id, address,
                                              phone, tchCollege, profession, tchJob);
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public boolean deleteTeacher(String number) {
        //SQL查询语句
        String sql = "delete from teacher a where number = ?";
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

    @Override
    public boolean addTeacher(Teacher teacher) {
        //SQL查询语句
        String sql = "insert into teacher value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, teacher.getNumber());
            this.pStatement.setString(2, teacher.getName());
            this.pStatement.setString(3, teacher.getAge());
            this.pStatement.setString(4, teacher.getSex());
            this.pStatement.setString(5, teacher.getNationality());
            this.pStatement.setString(6, teacher.getPoliticalStatus());
            this.pStatement.setString(7, teacher.getHometown());
            this.pStatement.setString(8, teacher.getId());
            this.pStatement.setString(9, teacher.getAddress());
            this.pStatement.setString(10, teacher.getPhone());
            this.pStatement.setString(11, teacher.getCollege());
            this.pStatement.setString(12, teacher.getProfession());
            this.pStatement.setString(13, teacher.getJob());
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
    public boolean updateTeacher(Teacher teacher) {
        //SQL查询语句
        String sql = "update teacher set name = ?, age = ?, sex = ?, nationality = ?, " +
                "political_status = ?, hometown = ?, id = ?, address = ?, phone = ?, " +
                "college = ?, profession = ?, job = ? where number = ?";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, teacher.getName());
            this.pStatement.setString(2, teacher.getAge());
            this.pStatement.setString(3, teacher.getSex());
            this.pStatement.setString(4, teacher.getNationality());
            this.pStatement.setString(5, teacher.getPoliticalStatus());
            this.pStatement.setString(6, teacher.getHometown());
            this.pStatement.setString(7, teacher.getId());
            this.pStatement.setString(8, teacher.getAddress());
            this.pStatement.setString(9, teacher.getPhone());
            this.pStatement.setString(10, teacher.getCollege());
            this.pStatement.setString(11, teacher.getProfession());
            this.pStatement.setString(12, teacher.getJob());
            this.pStatement.setString(13, teacher.getNumber());
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
    public String queryTeacherNumber(String name) {
        //SQL查询语句
        String sql = "select number from teacher where name = ?";
        try {
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, name);
            this.rs = this.pStatement.executeQuery();
            if (this.rs.next()) {
                return this.rs.getString("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return "";
    }

    @Override
    public boolean updateAddress(String number, String address, String phone) {
        //SQL查询语句
        String sql;
        //address不为null则更新address，否则更新phone
        if (address != null) {
            sql = "update teacher set address = ? where number = ? ";
            try {
                //配置并执行SQL语句
                this.pStatement = this.conn.prepareStatement(sql);
                this.pStatement.setString(1, address);
                this.pStatement.setString(2, number);
                int result = this.pStatement.executeUpdate();
                return result != 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        } else {
            sql = "update teacher set phone = ? where number = ? ";
            try {
                //配置并执行SQL语句
                this.pStatement = this.conn.prepareStatement(sql);
                this.pStatement.setString(1, String.valueOf(phone));
                this.pStatement.setString(2, number);
                int result = this.pStatement.executeUpdate();
                return result != 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
        return false;
    }
}
