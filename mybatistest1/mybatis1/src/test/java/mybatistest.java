//多表查询
import first.dao.IEmployeeDao;
import first.domain.Employee;
import first.domain.EmployeeUser;
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
    private InputStream in;
    private IEmployeeDao userDao;
    private SqlSession session;

    @Before//执行之前调用
    public void init() throws IOException {
        //1.读取配置文件，生产字节流
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //3.使用工厂生产SqlSession对象
        //openSession提供了autoCommit方法，传入true值是自动提交
        //不传默认为false，需要手动commit
        session=factory.openSession();
        //4.使用SqlSession创建Dao接口代理对象
        userDao=session.getMapper(IEmployeeDao.class);
    }

    @After//执行之后调用
    public void destory(){
        //释放资源
        session.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testfindAll(){
        //第一种方法在主表中封装一个从表的对象
        //再定义封装employee和department的resultMap-----常用
        List<Employee> users=userDao.findAll();
        for(Employee user:users){
            System.out.println(user);
            System.out.println(user.getDepartment());
        }
    }

    @Test
    public void testfindSome(){
        //第二种方法，创建一个子类来实现-----不常用
        //同时查询employee的名字和department的地址
        List<EmployeeUser> users=userDao.findSome();
        for(EmployeeUser user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testFirstLevelCache(){
        //一级缓存，他发生了一次查询，第二次是从缓存中查询，没有经过数据库
        //该缓存储存在SqlSession的一块区域中，
        //如果在第一个执行之后，关闭原本的，再重新开启一个SqlSession，则会返回false
        //或者调用sqlSession.clearCache();方法清空缓存
        List<Employee> users=userDao.findAll();
        for(Employee user:users){
            System.out.println(user);
        }
        System.out.println("=================");
        List<Employee> users2=userDao.findAll();
        for(Employee user:users2){
            System.out.println(user);
        }
        System.out.println("=================");
        System.out.println(users==users2);//true
    }
}
