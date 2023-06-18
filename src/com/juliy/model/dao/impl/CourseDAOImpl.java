package com.juliy.model.dao.impl;

import com.juliy.model.bean.Course;
import com.juliy.model.dao.CourseDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** score表DAO实现类 */
public class CourseDAOImpl extends BaseDAO implements CourseDAO {

    @Override
    public List<Course> queryCourseInfo(String stuNumber, String schoolYear, String term, int flag) {
        List<Course> list = new ArrayList<>();
        //SQL查询语句
        String sql = "select a.*, b.*, c.name as teacher_name from score a, course b, teacher c where a.student_number = ? and " +
                "a.course_number = b.number and b.teacher_number = c.number and a.school_year like concat('%', ?, '%') " +
                "and a.term like concat('%', ?, '%') order by a.school_year desc, a.term desc, b.type";
        try {
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, stuNumber);
            this.pStatement.setString(2, schoolYear);
            this.pStatement.setString(3, term);
            addList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<Course> queryCourseInfo(String stuNumber, String type) {
        List<Course> list = new ArrayList<>();
        //SQL查询语句
        String sql = "select a.*, c.name as teacher_name from (course a left join score b on a.number = b.course_number " +
                "and b.student_number = ?) left join teacher c on a.teacher_number = c.number " +
                "where b.course_number is null and a.type != '必修' and a.type like concat('%', ?, '%') " +
                "and (a.profession = '面向全校' or a.profession = (select major from student where number = ?))";
        try {
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, stuNumber);
            this.pStatement.setString(2, type);
            this.pStatement.setString(3, stuNumber);
            addList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<Course> queryCourseInfo(String stuNumber, int flag) {
        List<Course> list = new ArrayList<>();
        //可退选课程规则为：本学期的选修/通识课，且未结算成绩
        String sql = "select a.*, b.*, c.name as teacher_name from score a, course b, teacher c where a.student_number = ? " +
                "and a.course_number = b.number and b.teacher_number = c.number and a.total_score is null " +
                "and a.school_year = ? and a.term = ? and b.type != '必修'";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, stuNumber);
            this.pStatement.setString(2, thisSchoolYear);
            this.pStatement.setString(3, thisTerm);
            addList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<Course> queryCourseInfo(String number, String college, String type) {
        List<Course> list = new ArrayList<>();
        //SQL查询语句
        String sql = "select a.*, b.name as teacher_name from course a, teacher b " +
                "where a.teacher_number = b.number and a.number like concat('%', ?, '%') and " +
                "a.college like concat('%', ?, '%') and a.type like concat('%', ?, '%') " +
                "order by a.number asc";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, number);
            this.pStatement.setString(2, college);
            this.pStatement.setString(3, type);
            addList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<Course> queryCourseInfo(String teacherNumber, String type, int flag) {
        List<Course> list = new ArrayList<>();
        //SQL查询语句
        String sql = "select a.*, b.name as teacher_name from course a, teacher b " +
                "where a.teacher_number = b.number and b.number = ?  and " +
                "a.type like concat('%', ?, '%') order by a.number asc";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, teacherNumber);
            this.pStatement.setString(2, type);
            addList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    /** 重复部分,用于将结果集的内容转化为course信息并存入list中 */
    private void addList(List<Course> list) throws SQLException {
        this.rs = this.pStatement.executeQuery();
        while (rs.next()) {
            String courseNumber = rs.getString("number");
            String teacherName = rs.getString("teacher_name");
            String courseName = rs.getString("name");
            String type = rs.getString("type");
            String period = rs.getString("period");
            String credit = rs.getString("credit");
            String profession = rs.getString("profession");
            String college = rs.getString("college");
            Course course = new Course(courseNumber, courseName, null, teacherName,
                                       period, credit, type, profession, college);
            list.add(course);
        }
    }

    @Override
    public boolean deleteCourse(String courseNumber, String teacherNumber) {
        //SQL查询语句
        String sql = "delete from course where number like concat('%', ?, '%') and teacher_number like concat('%', ?, '%')";
        try {
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, courseNumber);
            this.pStatement.setString(2, teacherNumber);
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
    public boolean addCourse(Course course) {
        //SQL查询语句
        String sql = "insert into course value (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, course.getNumber());
            this.pStatement.setString(2, course.getTeacherNumber());
            this.pStatement.setString(3, course.getName());
            this.pStatement.setString(4, course.getPeriod());
            this.pStatement.setString(5, course.getCredit());
            this.pStatement.setString(6, course.getType());
            this.pStatement.setString(7, course.getProfession());
            this.pStatement.setString(8, course.getCollege());
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
    public boolean updateCourse(Course course) {
        //SQL查询语句
        String sql = "update course set teacher_number = ?, name = ?, period = ?, " +
                "credit = ?, type = ?, profession = ?, college = ? where number = ?";
        try {
            //配置并执行SQL语句
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, course.getTeacherNumber());
            this.pStatement.setString(2, course.getName());
            this.pStatement.setString(3, course.getPeriod());
            this.pStatement.setString(4, course.getCredit());
            this.pStatement.setString(5, course.getType());
            this.pStatement.setString(6, course.getProfession());
            this.pStatement.setString(7, course.getCollege());
            this.pStatement.setString(8, course.getNumber());
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
