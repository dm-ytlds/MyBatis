package com.dengmin.demi.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 创建MyBatis工具类
 */
public class MybatisUtils {
    private static SqlSessionFactory factory = null;
    // 静态代码块，创建对象，自动执行
    static {
        InputStream in = null;
        try {
            // 1. 定义mybatis主配置文件的名称，从类（class）路径的根目录开始
            String config = "mybatis.xml";
            // 2. 读取config的表示文件
            in = Resources.getResourceAsStream(config);
            // 3. 创建SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            // 4. 创建SqlSessionFactory对象
            factory = builder.build(in);
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
    public static SqlSession getSqlSession(){
        SqlSession session = null;
        if(factory != null){
            session =  factory.openSession();
        }
        return session;
    }
}
