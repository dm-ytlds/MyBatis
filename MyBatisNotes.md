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

​	1.动态代理：使用SqlSession.getMapper(dao接口.class)获取这个dao接口的对象。

​	2.传入参数：从java代码中吧数据传入到mapper文件的sql语句中。

​		parameterType：写在mapper文件中的一个属性。表示dao接口中方法的参数数据类型。parameterType不是强制的，mybatis通过反射机制能够发现接口参数的数据类型，一般都不写。

​	3.简单类型的参数

​		简单类型：mybatis把java的基本数据类型（包括它们的包装类）和String都叫做简单类型。

​		在mapper文件中获取简单类型的一个参数的值，使用 #{任意字符} 。使用 #{}之后，mybatis执行sql是使用的jdbc中的PreparedStatement对象。由mybatis执行相应的sql语句。

​	4.多个参数，使用@param命名参数

```java
接口 public List<Student> selectMutilParam(@Param("myname") String name, @Param("myage") Integer age);
使用 @Param("参数名") String name
mapper文件：
    <select>
    	select * from student where name=#{myname} or age=#{myage}
    </select>
```

​	5.多个参数，使用java对象的属性值，作为参数实际值。（常用）

​		使用对象语法： #{属性名, javaType=类型名称, jdbcType=数据类型} 。

​		javaType：指java中的属性数据类型。

​		jdbcType：在数据库中的数据类型。

​		例如：#{paramName, javaType=java.lang.String, jdbcType=VAECHAR} 。这种方式太繁琐，建议用下面的简化方式：

​	#{属性名} 。因为javaType和jdbcType的值mybatis反射能获取到，不用单独提供。		



---->#和\$的区别：

​	#是使用？做占位符。使用PrepareStatement执行sql，效率高；而\$不使用占位符，是字符串连接的方式，使用Statement对象执行sql，效率低。

​	使用#的地方也可以用\$替换。只是说\$存在sql注入的风险，在确保安全的情况下，两者可以替换使用。 \$一般用来替换列名或者表名。

 6.  resultType

     resultType 结果类型，指sql语句执行完毕后，数据转为java对象，java类型是任意的。

     resultType结果类型的值包括：

     ​	（1）类型的全限定名称；

     ​	（2）类型的别名，例如java.lang.Integer别名是int

     处理方式：简化了jdbc中的代码。

7. 自定义别名

   （1）在mybatis主配置文件中定义，使用<typeAlias>标签定义别名；

   （2）可以在resultType中使用自定义别名。

8. resultMap：结果映射，指定列名和java对象的属性对应关系。

   （1）自定义列值然后赋值给哪个属性；

   （2）当你的列名和属性名不一样时，一定使用resultMap

### 动态SQL

​	可以根据条件获取到不同的sql语句。主要是where部分发生变化。

​	动态sql的实现：使用的是mybatis提供的标签，<if> <where> <foreach>

​	（1）<if>是判断条件的，语法：<if test="判断java对象的属性值"> 部分sql语句 </if>

​	（2）<where>用来包含多个<if>的，当多个if有一个成立的，<where>会自动增加一个where关键字，并去掉 if 中多余的and , or 等。

​	（3）<foreach collection="" item="" open="" close="" separator=""> 部分sql语句 </foreach>

​		collection：表示接口中的方法参数的类型，如果是数组使用array，如果是List集合，使用 list

​		item：自定义，表示数组和集合成员的变量

​		open：循环开始时的字符

​		close：循环结束时的字符

​		separator：集合成员之间的分隔符

​	<SQL片段的使用简介> 

```xml
<!--定义sql片段-->
<sql id="自定义名称1">
    select * from 数据库名
</sql>
<!--使用sql片段-->
<select id = "自定义名称2">
    <include refid="自定义名称1" />
    ...
</select>
```

### 主配置文件

1.DataSource：表示数据源，java体系中，规定实现了javax.sql.DataSource接口的都是数据源。数据源表示Connection对象的。

​	type：指定数据源的类型。

​		1） POOLED：使用连接池，mybatis会创建pooledDataSource类。

​		2）UNPOOLED：不使用连接池，在每次执行sql语句，先创建连接，执行sql，在关闭连接mybatis会创建一个unpooledDataSource，管理Connection对象的使用。

​		3）JNDI：java命名和目录服务（类似Windows注册表）。

2.数据库的属性配置文件

​	使用的意义和目的：把数据库连接信息放到一个单独的文件中。和mybatis配置文件分开。目的是便于修改，保存，处理多个数据库的信息。

​	（1）在resources目录中定义一个属性配置文件。xxx.properties 。在属性配置文件中，定义数据，格式是key=value。key一般使用 ' . ' 做多级目录。

​	（2）在mybatis的主配置文件中，使用<properties>标签指定文件的位置。在需要使用值的地方，使用 “${key}” 来代替直接填入value值。

3.mapper文件，使用package指定路径。

```xml
<mappers>
    <!--使用包名
		name：xml文件所在的包名，这个包中所有的xml文件一次都能加载给mybatis。
		使用package的要求：
		1. mapper文件名称需要和接口名称一样，区分大小写；
		2. mapper文件和dao接口需要在同一目录下。
	-->
    <package name="" />
    ...
</mappers>
```

### 分页 PageHelper

​	Mybatis通用分页插件：PageHelper

​	在pom.xml文件中添加依赖项：

```xml
<dependencies>
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>版本号</version>
    </dependency>
</dependencies>
```

​	在mybatis.xml文件中添加插件标签：

```xml
<configuration>
    <plugins>
        <plugin interceptor="包名" />
    </plugins>
    ...
</configuration>
```

