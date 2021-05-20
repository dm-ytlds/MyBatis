package com.dengmin.demi;

import com.dengmin.demi.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis01 {
    @Test
    public void testMyBatis01() {
        // 初始化输入流
        InputStream in = null;
        try {
            // 1. 定义mybatis主配置文件的名称，从类（class）路径的根目录开始
            String config = "mybatis.xml";
            // 2. 读取config的表示文件
            in = Resources.getResourceAsStream(config);
            // 3. 创建SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            // 4. 创建SqlSessionFactory对象
            SqlSessionFactory factory = builder.build(in);
            // 5. 获取SqlSession对象，从SqlSessionFactory中获取SqlSession
            SqlSession session = factory.openSession();
            // 6. 指定要执行的sql语句标识。 sql映射文件中的namespace + "." + 标签的id值
            String sqlId = "com.dengmin.demi.dao.StudentDao" + "." + "selectStudents";
            // 7. 执行sql语句，通过sqlId找到语句
            List<Student> studentList = session.selectList(sqlId);
            // 8. 输出结果
            for(Student student : studentList) {
                System.out.println(student);
            }
            // 9. 释放资源
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
