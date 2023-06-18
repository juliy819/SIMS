package com.juliy.model.dao;

import com.juliy.model.bean.Student;

import java.util.List;

/**
 * student表DAO接口
 * 包含涉及student表的各种操作
 */
public interface StudentDAO {

    /**
     * 查询student表中的学生信息
     * @return List<Student></Student>
     */
    List<Student> queryStudentInfo(String number, String name, String college, String major, String status);

    /**
     * 查询某课程对应的学生信息和成绩
     * @return List<Student></Student>
     */
    List<Student> queryStudentScore(String courseName);

    /** 更改对应地址、电话信息 */
    boolean updateAddress(String number, String address, String phone);

    /** 删除对应学号的学生信息 */
    boolean deleteStudent(String number);

    /** 添加对应的学生信息 */
    boolean addStudent(Student student);

    /** 更新对应的学生信息 */
    boolean updateStudent(Student student);
}
