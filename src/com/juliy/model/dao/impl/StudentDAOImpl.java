package com.juliy.model.dao.impl;

import com.juliy.model.bean.Student;
import com.juliy.model.dao.StudentDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** student表DAO实现类 */
public class StudentDAOImpl extends BaseDAO implements StudentDAO {

    @Override
    public List<Student> queryStudentInfo(String number, String name, String college, String major, String status) {
        //SQL查询语句
        String sql = "select * from student where number like concat('%', ?, '%') and " +
                "name like concat('%', ?, '%') and college like concat('%', ?, '%') and " +
                "major like concat('%', ?, '%') and political_status like concat('%', ?, '%') " +
                "order by number asc";
        List<Student> list = new ArrayList<>();
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, number);
            this.pStatement.setString(2, name);
            this.pStatement.setString(3, college);
            this.pStatement.setString(4, major);
            this.pStatement.setString(5, status);
            this.rs = this.pStatement.executeQuery();
            while (this.rs.next()) {
                //获取详细数据封装进student对象,再添加到list中
                String stuNumber = this.rs.getString("number");
                String stuName = this.rs.getString("name");
                String age = this.rs.getString("age");
                String sex = this.rs.getString("sex");
                String nationality = this.rs.getString("nationality");
                String politicalStatus = this.rs.getString("political_status");
                String hometown = this.rs.getString("hometown");
                String id = this.rs.getString("id");
                String address = this.rs.getString("address");
                String phone = this.rs.getString("phone");
                String stuCollege = this.rs.getString("college");
                String preMajor = this.rs.getString("pre_major");
                String stuMajor = this.rs.getString("major");
                String stuClass = this.rs.getString("class");
                Date startTime = this.rs.getDate("start_time");
                String selectCourse = this.rs.getString("select_course");
                String isSports = this.rs.getString("is_sports");
                Student student = new Student(stuNumber, stuName, age, sex, nationality,
                                              politicalStatus, hometown, id, address,
                                              phone, stuCollege, preMajor, stuMajor, stuClass,
                                              startTime, selectCourse, isSports, null);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<Student> queryStudentScore(String courseName) {
        List<Student> list = new ArrayList<>();
        //SQL查询语句
        String sql = "select a.*, b.total_score from student a, score b, course c " +
                "where b.student_number = a.number and b.course_number = c.number and " +
                "c.name = ? order by a.number asc";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, courseName);
            this.rs = this.pStatement.executeQuery();
            while (rs.next()) {
                //获取详细数据封装进student对象,再添加到list中
                String number = rs.getString("number");
                String name = rs.getString("name");
                String college = rs.getString("college");
                String major = rs.getString("major");
                String stuClass = rs.getString("class");
                String score = rs.getString("total_score");
                Student student = new Student(number, name, null, null, null,
                                              null, null, null, null,
                                              null, college, null, major, stuClass,
                                              null, null, null, score);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public boolean updateAddress(String number, String address, String phone) {
        //SQL查询语句
        String sql;
        //address不为null则更新address，否则更新phone
        if (address != null) {
            sql = "update student set address = ? where number = ? ";
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
            sql = "update student set phone = ? where number = ? ";
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

    @Override
    public boolean deleteStudent(String number) {
        //SQL查询语句
        String sql = "delete from student where number = ?";
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
    public boolean addStudent(Student student) {
        //用于string和date的转化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //SQL查询语句
        String sql = "insert into student value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, student.getNumber());
            this.pStatement.setString(2, student.getName());
            this.pStatement.setString(3, student.getAge());
            this.pStatement.setString(4, student.getSex());
            this.pStatement.setString(5, student.getNationality());
            this.pStatement.setString(6, student.getPoliticalStatus());
            this.pStatement.setString(7, student.getHometown());
            this.pStatement.setString(8, student.getId());
            this.pStatement.setString(9, student.getAddress());
            this.pStatement.setString(10, student.getPhone());
            this.pStatement.setString(11, student.getCollege());
            this.pStatement.setString(12, student.getPreMajor());
            this.pStatement.setString(13, student.getMajor());
            this.pStatement.setString(14, student.getStuClass());
            this.pStatement.setString(15, sdf.format(student.getStartTime()));
            this.pStatement.setString(16, student.getSelectCourse());
            this.pStatement.setString(17, student.getIsSports());
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
    public boolean updateStudent(Student student) {
        //用于string和date的转化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //SQL查询语句
        String sql = "update student set name = ?, age = ?, sex = ?, nationality = ?, " +
                "political_status = ?, hometown = ?, id = ?, address = ?, phone = ?, " +
                "college = ?, pre_major = ?, major = ?, class = ?, start_time = ?, " +
                "select_course = ?, is_sports = ? where number = ?";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, student.getName());
            this.pStatement.setString(2, student.getAge());
            this.pStatement.setString(3, student.getSex());
            this.pStatement.setString(4, student.getNationality());
            this.pStatement.setString(5, student.getPoliticalStatus());
            this.pStatement.setString(6, student.getHometown());
            this.pStatement.setString(7, student.getId());
            this.pStatement.setString(8, student.getAddress());
            this.pStatement.setString(9, student.getPhone());
            this.pStatement.setString(10, student.getCollege());
            this.pStatement.setString(11, student.getPreMajor());
            this.pStatement.setString(12, student.getMajor());
            this.pStatement.setString(13, student.getStuClass());
            this.pStatement.setString(14, sdf.format(student.getStartTime()));
            this.pStatement.setString(15, student.getSelectCourse());
            this.pStatement.setString(16, student.getIsSports());
            this.pStatement.setString(17, student.getNumber());
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
