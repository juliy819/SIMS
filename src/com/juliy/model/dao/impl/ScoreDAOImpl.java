package com.juliy.model.dao.impl;

import com.juliy.model.bean.Score;
import com.juliy.model.dao.ScoreDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** score表DAO实现类 */
public class ScoreDAOImpl extends BaseDAO implements ScoreDAO {
    @Override
    public List<Score> queryScore(String stuNumber, String schoolYear, String term, String type) {
        String sql;
        List<Score> list = new ArrayList<>();

        //schoolYear为空表示查询所有成绩
        if (schoolYear == null) {
            sql = "select a.*, b.* from score a, course b where a.student_number = ? and a.course_number = b.number " +
                    "order by a.school_year desc, a.term desc, b.type";
            try {
                //配置并执行SQL语句
                this.pStatement = this.conn.prepareStatement(sql);
                this.pStatement.setString(1, stuNumber);
                addScore(list);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
        //schoolYear不为空表示查询筛选的成绩
        else {
            //由于要实现部分筛选功能，即部分查询条件可能为空，需使用模糊查询，一般的模糊查询语句为 xx = ‘%?%’,
            //而单引号里的？不会被preparedStatement识别，又需要使用sql的concat函数
            sql = "select a.*, b.* from score a, course b where a.student_number = ? and a.course_number = b.number " +
                    "and a.school_year like concat('%', ?, '%') and a.term like concat('%', ?, '%') " +
                    "and b.type like concat('%', ?, '%') order by a.school_year desc, a.term desc, b.type";
            try {
                this.pStatement = this.conn.prepareStatement(sql);
                this.pStatement.setString(1, stuNumber);
                this.pStatement.setString(2, schoolYear);
                this.pStatement.setString(3, term);
                this.pStatement.setString(4, type);

                addScore(list);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        return list;
    }

    /** 重复部分,用于将结果集的内容转化为score并存入list中 */
    private void addScore(List<Score> list) throws SQLException {
        this.rs = this.pStatement.executeQuery();
        while (rs.next()) {
            //总评成绩为null或空表示该门课还未结课，不加到list里
            if (rs.getString("total_score") != null && !"".equals(rs.getString("total_score"))) {
                String schoolYear = rs.getString("school_year");
                String term = rs.getString("term");
                String courseName = rs.getString("name");
                String type = rs.getString("type");
                String totalScore = rs.getString("total_score");
                String retestScore = rs.getString("retest_score");
                String relearnScore = rs.getString("relearn_score");
                String credit = rs.getString("credit");
                Score score = new Score(null, null, schoolYear, term,
                                        totalScore, retestScore, relearnScore, courseName, credit, type);
                list.add(score);
            }
        }
    }

    @Override
    public boolean addCourseSelection(String stuNumber, String courseNumber) {
        //SQL查询语句
        String sql = "insert into score value(?, ?, ?, ?, null, null, null)";
        try {
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, stuNumber);
            this.pStatement.setString(2, courseNumber);
            this.pStatement.setString(3, thisSchoolYear);
            this.pStatement.setString(4, thisTerm);
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
    public boolean deleteCourseSelection(String stuNumber, String courseNumber) {
        //SQL查询语句
        String sql = "delete from score where student_number = ? and course_number like concat('%', ?, '%')";
        try {
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, stuNumber);
            this.pStatement.setString(2, courseNumber);
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
    public boolean updateScore(String studentNumber, String courseName, String score) {
        //SQL查询语句
        String sql = "update score set total_score = ? where student_number = ? and " +
                "course_number = (select number from course where name = ?)";
        try {
            this.pStatement = this.conn.prepareStatement(sql);
            this.pStatement.setString(1, score);
            this.pStatement.setString(2, studentNumber);
            this.pStatement.setString(3, courseName);
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





