package com.dengmin.demi;

import com.dengmin.demi.domain.Student;
import com.dengmin.demi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 运用创建的MyBatis工具类实现
 */
public class TestMyBatis02 {
    @Test
    public void testMyBatis02(){

        // 获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession session = MybatisUtils.getSqlSession();
        // 指定要执行的sql语句标识。 sql映射文件中的namespace + "." + 标签的id值
        String sqlId = "com.dengmin.demi.dao.StudentDao" + "." + "insertStudents";
        // 执行sql语句，通过sqlId找到语句
        Student student = new Student();
        student.setId(1005);
        student.setName("ll");
        student.setEmail("ll@qq.com");
        student.setAge(28);
        int insertNums = session.insert(sqlId, student);
        // 手动提交事务
        session.commit();
        // 8. 输出结果
        System.out.println("成功插入" + insertNums + "条语句。");
        // 9. 释放资源
        session.close();
    }
}
