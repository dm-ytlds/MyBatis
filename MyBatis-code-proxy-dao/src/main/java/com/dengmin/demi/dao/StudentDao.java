package com.dengmin.demi.dao;

import com.dengmin.demi.domain.Student;
import com.dengmin.demi.objects.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    // 查询student表中的单行数据
    public Student selectStudentById(Integer id);

    // 查询student表中的所有数据
    public List<Student> selectStudents();

    // 给student表插入数据
    public int insertStudents(Student student);

    // 更新student表中的数据
    public int updateStudent();

    // 多个参数，使用@Param的方式传参
    List<Student> selectParams(@Param("myname") String name, @Param("myage") Integer age);

    // 多个参数，使用对象属性值的方式传参

    List<Student> selectQueryParams(QueryParam param);
}
