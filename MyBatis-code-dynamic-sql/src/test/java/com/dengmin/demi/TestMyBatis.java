package com.dengmin.demi;

import com.dengmin.demi.dao.StudentDao;
import com.dengmin.demi.domain.Student;
import com.dengmin.demi.objects.QueryParam;
import com.dengmin.demi.utils.MybatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    /**
     * 使用mybatis的动态代理机制，使用SqlSession.getMapper(dao接口)
     * getMapper能获取dao接口对应的实现类对象。
     */

    // 测试动态sql语句之<if>标签
    @Test
    public void restSelectDynamicIf() {
        SqlSession session = MybatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student stu = new Student();
        stu.setName("zs");
        stu.setAge(10);
        List<Student> students = dao.selectDynamicIf(stu);
        for(Student student : students) {
            System.out.println("<if> : " + student);
        }
    }


    // 测试动态sql语句之<where>标签
    @Test
    public void restSelectDynamicWhere() {
        SqlSession session = MybatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student stu = new Student();
        stu.setName("zs");
        stu.setAge(10);
        List<Student> students = dao.selectDynamicWhere(stu);
        for(Student student : students) {
            System.out.println("<where> : " + student);
        }
    }
}
