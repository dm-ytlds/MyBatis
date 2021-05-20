package com.dengmin.demi;

import com.dengmin.demi.dao.StudentDao;
import com.dengmin.demi.dao.daoImpl.StudentDaoImpl;
import com.dengmin.demi.domain.Student;
import org.junit.Test;

import java.util.List;

public class TestMyBatis03 {
    @Test
    public void testMyBatis03() {
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.selectStudents();
        for(Student student : students) {
            System.out.println(student);
        }
    }
}
