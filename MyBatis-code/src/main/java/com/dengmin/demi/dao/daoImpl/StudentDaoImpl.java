package com.dengmin.demi.dao.daoImpl;

import com.dengmin.demi.dao.StudentDao;
import com.dengmin.demi.domain.Student;
import com.dengmin.demi.utils.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectStudents(){

        // 5. 获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession session = MybatisUtils.getSqlSession();
        // 6. 指定要执行的sql语句标识。 sql映射文件中的namespace + "." + 标签的id值
        String sqlId = "com.dengmin.demi.dao.StudentDao" + "." + "selectStudents";
        // 7. 执行sql语句，通过sqlId找到语句
        List<Student> studentList = session.selectList(sqlId);
        return studentList;
    }
}
