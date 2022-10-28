
import first.dao.IUserDao;
import first.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mybatistest {
    InputStream is= null;
    IUserDao userDao=null;
    SqlSession session=null;
    @Before
    public void init() throws IOException {
        is=Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
        session=factory.openSession();
        userDao=session.getMapper(IUserDao.class);
    }

    @After
    public void destory(){
        //出现过问题，没有提交事务导致删除方法没有报错但是没有删除成功
        session.commit();
        session.close();
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll(){
        List<User> user=userDao.findAll();
        for(User users:user){
            System.out.println(users);
        }

    }
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(8);
    }

    @Test
    public void testFindOne(){
        User user=userDao.findById(5);
        System.out.println(user);
    }

    @Test
    public void testFindName(){
        List<User> users=userDao.findByName("%李%");
        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindName2(){
        List<User> users=userDao.findByName2("李");
        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int total=userDao.findTotal();
        System.out.println(total);
    }
}
