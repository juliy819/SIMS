package com.juliy.model.dao;

import com.juliy.model.bean.Teacher;

import java.util.List;

/**
 * teacher表DAO接口
 * 包含涉及teacher表的各种操作
 */
public interface TeacherDAO {

    /**
     * 查询teacher表中的教师信息
     * @return List<Teacher></Teacher>
     */
    List<Teacher> queryTeacherInfo(String number, String name, String college, String job, String status);

    /** 删除对应工号的教师信息 */
    boolean deleteTeacher(String number);

    /** 添加对应的教师信息 */
    boolean addTeacher(Teacher teacher);

    /** 更新对应的教师信息 */
    boolean updateTeacher(Teacher teacher);

    /** 获取对应名字老师的编号 */
    String queryTeacherNumber(String name);

    /** 更改对应地址、电话信息 */
    boolean updateAddress(String number, String address, String phone);
}
