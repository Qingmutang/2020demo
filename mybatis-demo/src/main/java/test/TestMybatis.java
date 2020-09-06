package test;

import dao.UserDao;
import model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author qinglin
 * @create 2020-09-06 09:20
 */
public class TestMybatis {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        User user = new User();
        user.setName("xiaoming");
        user.setPassword("123456");
        userDao.save(user);

        sqlSession.commit();
    }
}
