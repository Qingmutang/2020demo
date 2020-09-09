package com.test;

import com.dao.UserDao;
import com.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author qinglin
 * @create 2020-09-06 12:53
 */
public class SpringMybatisTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");

        UserDao userDao = (UserDao) context.getBean("userDao");


        //后面执行完成，就直接提交了，而原来的mybatis却需要自己提交
        // 主要是：spirng和mybatis整合时，引入了外部的连接池，默认设置了提交为自动提交
        // 但是后面会手动控制事务，通过spring的事务控制来解决这个问题
        User user = new User();
        user.setName("xiaowang");
        user.setPassword("123456888");
        userDao.save(user);

        context.getBean(TransactionTemplate.class);


    }
}
