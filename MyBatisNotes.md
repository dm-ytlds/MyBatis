## MyBatis框架

### 框架概述

1.三层架构：

​	界面层：接收用户的请求参数，显示处理结果。

​	业务逻辑层：接收到界面层传递的数据，计算逻辑，调用数据库，获取数据；

​	数据访问层：访问数据库，执行对数据的查询，修改，删除等操作。

2.三层对应的包：

​	界面层：controller包（servlet类）；

​	业务逻辑层：service包（xxxService类）；

​	数据访问层：dao包（xxxDao类）。

3.三层中类的交互：

​	用户使用界面层---->业务逻辑层---->数据访问层（持久层）---->数据库（mysql）

4.三层对应的处理框架：

​	界面层：servlet和springmvc框架

​	业务逻辑层：service类和spring框架

​	数据访问层：dao类和mybatis框架

框架（framework）可以看成是一个模板，是一种可重用的设计。框架是一个软件。

### MyBatis框架

​	1.MyBatis是MyBatis SQL Mapper Framework for Java（SQL映射框架）

​	（1）sql mapper：SQL映射

​			可以数据库表中的一行数据 因射程 一个java对象。操作这个java对象就相当于操作数据库表中的数据。

​	（2）DAOs：Data Access Objects（数据访问对象）

​			对数据库进行增删改查。

​	2.MyBatis提供了哪些功能：

​		（1）提供了创建Connection，Statement，ResultSet的能力，不需要开发人员创建这些对象；

​		（2）提供了执行sql语句的能力；	

​		（3）提供了循环sql，把sql的结果转为java对象，List集合的能力。

​		（4）提供了关闭资源的能力。

​	3.开发人员要做的是：提供sql语句。执行流程：

​		开发人员提供的sql语句---->MyBatis处理sql语句---->开发人员得到List集合或者java对象。

### MyBatis的一个实例

```text
实现步骤：
    1.新建student表
    2.加入maven的mybatis坐标以及mysql驱动的坐标
    3.创建实体类Student，保存表中的没一行数据
    4.创建持久层的dao接口，定义操作数据库的方法
    5.创建一个mybatis使用的配置文件：sql映射文件，在其中写sql语句。
        一般来说，一个表对应一个sql映射文件，文件格式为xml。
        （1）写在接口所在的目录中；
        （2）文件名称和接口保持一致。
    6.创建mybatis的主配置文件。
        一个项目就一个主配置文件。主配置文件提供了数据库的连接信息和sql映射文件的位置信息。
        (注意：此处mapper中用的是target中的sql映射文件，所以在这一步之前，需要先编译整个项目。)
    7.创建使用mybatis类，通过mybatis实现数据库的访问。
```

​	其中主要类的介绍：

​		1) Resources： mybatis中的一个类，负责读取主配置文件。

```java
InputStream in = Reasources.getReasourceAsStream("主配置文件(.xml文件)");
```

​		2) SqlSessionFactoryBuilder：创建SqlSessionFactory对象。

```java
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
SqlSessionFactory factory = builder.build(in);
```

​		3) SqlSessionFactory：重量级对象，程序创建一个对象耗时比较长，使用资源比较多。在整个项目中，有一个就够用了。

​			SqlSessionFactory：是一个接口，接口实现类：DefaultSqlSessionFactory；

​			SqlSessionFactory的作用：获取SqlSession对象。

```java
SqlSession session = factory.openSession();
```

​		openSession()方法说明：

​			（1）openSession()：无参数的，获取的是非自动提交事务的sqlSession对象；

​			（2）openSession(boolean)：true，自动提交事务；false，非自动提交事务。

​		4) SqlSession接口：定义了操作数据的方法。例如selectOne(), selectList(), insert(), update(), delete(), commit(), rollback(), ... 。SqlSession接口的实现类是DefaultSqlSession。

​			使用要求：SqlSession对象不是线程安全的，需要在方法内部使用，在执行sql语句之前，使用openSession()获取SqlSession对象，在执行完sql语句后，需要手动关闭，执行.close()方法，这样能保证使用的是线程安全的。

<font color="#FF0000">学会使用配置模板的方式来写mapper和config文件。</font>

MyBatis的动态代理：MyBatis根据dao的方法调用，获取执行sql语句的信息。MyBatis根据dao的接口，创建出一个dao接口的实现类，并创建这个类的对象。完成SqlSession调用方法，访问数据库。

### MyBatis的动态代理

​	动态代理：使用SqlSession.getMapper(dao接口.class)获取这个dao接口的对象。

​	传入参数：从java代码中吧数据传入到mapper文件的sql语句中。
