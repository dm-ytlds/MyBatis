package com.dengmin.demi;

import com.dengmin.demi.dao.StudentDao;
import com.dengmin.demi.domain.Student;
import com.dengmin.demi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    /**
     * 使用mybatis的动态代理机制，使用SqlSession.getMapper(dao接口)
     * getMapper能获取dao接口对应的实现类对象。
     */

    @Test
    public void testSelectStudentById() {
        SqlSession session = MybatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student student = dao.selectStudentById(1007);
        System.out.println(student);

        System.out.println("dao使用的代理模式：" + dao.getClass().getName());  // dao使用的代理模式：com.sun.proxy.$Proxy7
        // 记得关闭资源
        session.close();
    }


    @Test
    public void testSelectStudents() {
        SqlSession session = MybatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        System.out.println("dao使用的代理模式：" + dao.getClass().getName());  // dao使用的代理模式：com.sun.proxy.$Proxy7

        // 调用dao的方法，执行数据库的操作
        List<Student> students = dao.selectStudents();
        for(Student student : students) {
            System.out.println(student);
        }
        // 记得关闭资源
        session.close();
    }

    @Test
    public void testInsertStudents() {
        SqlSession session = MybatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student stu = new Student();
        stu.setId(1007);
        stu.setName("jj");
        stu.setEmail("jj@gmail.com");
        stu.setAge(23);
        int nums = dao.insertStudents(stu);
        // 一定记得要提交
        session.commit();
        System.out.println("成功插入" + nums + "条语句。");
        // 记得关闭资源
        session.close();
    }

    @Test
    public void testUpdateStudent() {
        SqlSession session = MybatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        int udNums = dao.updateStudent();
        session.commit();
        System.out.println("更新了" + udNums + "条语句");
        // 记得关闭资源
        session.close();
    }
}
