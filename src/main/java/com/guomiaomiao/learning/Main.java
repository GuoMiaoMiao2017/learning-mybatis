package com.guomiaomiao.learning;

import com.guomiaomiao.learning.dao.UserMapper;
import com.guomiaomiao.learning.service.IUserService;
import com.guomiaomiao.learning.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 15295 on 2018/5/23.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        mybatisUpdateUser();
    }

//    jdbc方式频繁开启关闭数据库，造成资源浪费，解决：用数据库连接池管理数据库连接
//    sql语句硬编码，如修改，需重新编译。不利于系统维护。解决：将sql语句配置在xml文件中。
//    PreparedStatement中输入的序号及sql参数硬编码。
//    ResultSet遍历时参在硬编码。
    public static void jdbcDemo() throws Exception {
        final String URL = "jdbc:mysql://192.168.1.103:3306/shopping?characterEncoding=utf-8";
        final String USERNAME = "mmall";
        final String PASSWORD = "mmall";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        Statement statement = connection.createStatement();
        //预编译的statement,数据库编译sql语句，编译完后存到数据库端缓存，下次不再编译。
         PreparedStatement statement = connection.prepareStatement("select * from user where name = ?");
        statement.setString(1,"张帅");
//        ResultSet resultSet = statement.executeQuery("select * from user where id = 1");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println("User{" +
                    "id=" + resultSet.getInt("id") +
                    ", name='" + resultSet.getString("name") + '\'' +
                    ", password='" + resultSet.getString("password") + '\'' +
                    ", sex='" + resultSet.getInt("sex") + '\'' +
                    ", isVip='" + resultSet.getInt("is_vip") + '\'' +
                    '}');
        }
    }

    public static void dbcpBasicDataSource() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where id = 2");
        while (resultSet.next()) {
            System.out.println("User{" +
                    "id=" + resultSet.getInt("id") +
                    ", name='" + resultSet.getString("name") + '\'' +
                    ", password='" + resultSet.getString("password") + '\'' +
                    ", sex='" + resultSet.getString("sex") + '\'' +
                    ", isVip='" + resultSet.getString("is_vip") +
                    '}');
        }

        User user = (User)ac.getBean("guomiaomiao");
        System.out.println(user);

        IUserService iUserService = ac.getBean(IUserService.class);
        User u = iUserService.getUser(user);
        System.out.println(u);
        resultSet.close();
        statement.close();
        connection.close();
    }

    public static void mybatisDemo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
//        User user = sqlSession.selectOne("UserMapper.getUser", 1);

//        List<User> list = sqlSession.selectList("UserMapper.selectUserByName", "小");
        List<User> list = sqlSession.selectList("UserMapper.selectUserByName", "%小%");
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //sqlSession.commit();
        sqlSession.close();
    }
    /*
    推荐
    需要用到Mapper.xml文件，采用Mapper自动装配，直接用Mapper中的方法
    mapper代理
     */
    public static void mybatisDemo1() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper  = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUser(1);
        System.out.println(user);
        sqlSession.close();
    }

    public static void mybatisInsertUser() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper  = sqlSession.getMapper(UserMapper.class);
        User user = new User("熊泽华", "123", 1, (byte)1);
        userMapper.insertUser(user);
//        插入删除操作需要写commit方法，这样才能在数据库显示出来。如若不写，并且设置id自增，那么在数据库不显示，
//        但是id还是增加了的。如原来最后一个id是5，我不加commit,让执行两次insert操作，这是在数据库虽然没有增加成功
//        但是此时的id已经是7了，这是加上commit跑一边，发现id是8，中间的6、7就没增加成功
        sqlSession.commit();
        sqlSession.close();
    }

    public static void mybatisDeleteUserById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUserById(7);
        sqlSession.commit();
        sqlSession.close();
    }

    public static void mybatisUpdateUser() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User("郭苗苗", "888", 0, (byte)1);
        user.setId(1);
        userMapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

}
