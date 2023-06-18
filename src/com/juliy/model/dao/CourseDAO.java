package com.juliy.model.dao;

import com.juliy.model.bean.Course;

import java.util.List;

/**
 * course表DAO接口
 * 包含涉及course表的各种操作
 */
public interface CourseDAO {
    /**
     * 查询某学生已选课程信息
     * @return List<Score></Score>
     */
    List<Course> queryCourseInfo(String stuNumber, String schoolYear, String term, int flag);

    /**
     * 查询某学生可选的课程
     * @return List<Score></Score>
     */
    List<Course> queryCourseInfo(String stuNumber, String type);

    /**
     * 查询某学生可退选的课程
     * @return List<Score></Score>
     */
    List<Course> queryCourseInfo(String stuNumber, int flag);

    /**
     * 按课程属性查询课程信息
     * @return List<Score></Score>
     */
    List<Course> queryCourseInfo(String number, String college, String type);

    /**
     * 查询某教师教的课
     * @return List<Score></Score>
     */
    List<Course> queryCourseInfo(String teacherNumber, String type, int flag);

    /** 删除课程信息 */
    boolean deleteCourse(String courseNumber, String teacherNumber);

    /** 添加新课程 */
    boolean addCourse(Course course);

    /** 更新对应的课程信息 */
    boolean updateCourse(Course course);
}
