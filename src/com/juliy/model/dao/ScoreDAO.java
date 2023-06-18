package com.juliy.model.dao;

import com.juliy.model.bean.Score;

import java.util.List;

/**
 * score表DAO接口
 * 包含涉及score表的各种操作
 */
public interface ScoreDAO {
    /**
     * 查询某学生的成绩信息
     * @return List<Score></Score>
     */
    List<Score> queryScore(String stuNumber, String schoolYear, String term, String type);

    /** 添加选课信息 */
    boolean addCourseSelection(String stuNumber, String courseNumber);

    /** 删除选课信息 */
    boolean deleteCourseSelection(String stuNumber, String courseNumber);

    /** 修改学生成绩 */
    boolean updateScore(String studentNumber, String courseName, String score);
}
