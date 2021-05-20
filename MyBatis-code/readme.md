### 第一个MyBatis的例子

**实现步骤：**

    1.新建student表
        写一个student.sql文件，导入数据库中，便于操作。
    2.加入maven的mybatis坐标以及mysql驱动的坐标
    3.创建实体类Student，保存表中的每一行数据
        在main.java.com.dengmin.demi.domain目录下创建一个Student类。
    4.创建持久层的dao接口，定义操作数据库的方法
        在main.java.com.dengmin.demi.dao目录下创建一个StudentDao接口。
    5.创建一个mybatis使用的配置文件：sql映射文件，在其中写sql语句。
        一般来说，一个表对应一个sql映射文件，文件格式为xml。
        （1）写在接口所在的目录中；
        （2）文件名称和接口保持一致。
        在main.java.com.dengmin.demi.dao目录下创建一个StudentDao.xml文件。
    6.创建mybatis的主配置文件。
        一个项目就一个主配置文件。主配置文件提供了数据库的连接信息和sql映射文件的位置信息。
        (注意：此处mapper中用的是target中的sql映射文件，所以在这一步之前，需要先编译整个项目。)
        此处的配置文件在resources资源文件目录下创建的mybatis.xml文件。
    7.创建使用mybatis类，通过mybatis实现数据库的访问。
        通过创建的TestApp文件实现对数据库的操作。

输出日志：

    <!--控制mybatis的全局行为-->
    <settings>
        <!--设置mybatis的输出日志-->
        <setting name="logImpl" value="STDOUT_LOGGING" />
    </settings>

---
其中，

    TestMyBatis01.java 实现写工具类前的代码流程。
    TestMyBatis02.java 用自己写的工具类utils/MyBatisUtils.java来实现sql语句的操作。
    TestMyBatis03.java 实现传统的Dao（创建类，调用方法）来实现sql语句的操作。