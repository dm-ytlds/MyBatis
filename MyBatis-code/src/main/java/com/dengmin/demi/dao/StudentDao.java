package com.dengmin.demi.dao;

import com.dengmin.demi.domain.Student;

import java.util.List;

public interface StudentDao {

    // 查询student表中的所有数据
    public List<Student> selectStudents();

    // public int insertStudents(Student student);
}
