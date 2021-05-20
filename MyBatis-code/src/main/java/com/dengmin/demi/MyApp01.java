package com.dengmin.demi;

import com.dengmin.demi.dao.StudentDao;
import com.dengmin.demi.dao.daoImpl.StudentDaoImpl;
import com.dengmin.demi.domain.Student;

import java.util.List;

/**
 * 用传统的Dao实现。将Dao实现类单独实现出来，然后通过new 对象的方式调用得到sql操作结果。
 *
 */
public class MyApp01 {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.selectStudents();
        for(Student student : students) {
            System.out.println(student);
        }
    }
}
