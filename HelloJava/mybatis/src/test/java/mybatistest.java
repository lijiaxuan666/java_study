import first.dao.IUserDao;
import first.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class mybatistest {
    private InputStream in;
    private IUserDao userDao;
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
        userDao=session.getMapper(IUserDao.class);
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
    /*
        Windows：
        如果将User对象里的变量名改变如id改为userId(加上user改变了变量名)，name改为Name(只改变大小写)
        增删改就会报错，因为parameterType已经改变
        但是查询操作可以执行
        但是观察发现，只有name被封装了，可以查询出来值，但其他都为null值
        原因是MySQL是不区分大小写的，仍然可以查询到(注意不是Java不区分而是MySQL)
        但其他加上了user，变量名已经变化，所以无法被封装
        Linux：
        严格区分大小写，包括MySQL也严格区分大小写
        所以所有的都无法查询

        解决方法：
        1.起别名，如select id as UserId,name as Name.....   from stu;
        起别名效率最高，因为是在sql层面上进行改变
        2.采用配置方法，由mybatis提供，需要单独配置
        <!--配置 查询结果的列名和实体类的属性名的对应关系
            这个id是唯一标识，可以随便写  type是实体类类型-->
        <resultMap id="userMap" type="first.domain.User">
            <!--主键字段对应-->
            <id property="userId" colum="id"></id>
            <!--非主键字段对应
                此处是对应Java，所有严格区分大小写-->
            <result property="userName" column="name"></result>
            <result property="userAge" column="age"></result>
            ........以此类推
        </resultMap>

       然后将resultType改为resultMap，如下
       <select id="findAll22" resultMap="userMap">
            select * from stu;
       </select>
       然后就会用resultMap的映射进行封装
       执行效率显然没有起别名高，但是开发效率变快了，只需要修改每个的resulType为resultMap
    */
    @Test
    public void testfind(){
        //5.使用代理对象执行方法
        List<User> users=userDao.findAll22();
        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testsave(){
        User user =new User();
        user.setId(null);
        user.setName("张鑫");
        user.setAge(20);
        user.setScore(100.2);
        user.setBirthday(new Date());
        user.setInsert_time(new Date());

        System.out.println(user);//执行前可以发现id为null
        userDao.saveUser(user);
        System.out.println(user);//执行后发现id已经被赋值，是新插入的id值

        session.commit();
    }

    @Test   //这个表没有创建自增类型id，所以无法使用此方法
    public void updateUser(){
        User user =new User();
        user.setId(5);
        user.setName("张鑫");
        user.setAge(20);
        user.setScore(100.5);
        user.setBirthday(new Date());
        user.setInsert_time(new Date());

        userDao.updateUser(user);
        //需要提交事务，不然虽然运行成功，但是不会保存
        session.commit();
    }

    @Test
    public void testDeleteUser(){

        userDao.deleteUser(7);
        //需要提交事务，不然虽然运行成功，但是不会保存
        session.commit();
    }

    @Test
    public void testFindOne(){

        User user=userDao.findById(2);
        System.out.println(user);
        //需要提交事务，不然虽然运行成功，但是不会保存
        session.commit();
    }

    @Test
    public void testFindName(){//模糊查询

        List<User> users=userDao.findByName("%李%");
        for(User user:users){
            System.out.println(user);
        }
        session.commit();
    }

    @Test
    public void testFindTotal(){

        int count=userDao.findTotal();
        System.out.println("人数："+count);
        session.commit();
    }

    @Test
    public void testFindVo(){//测试使用QueryVo作为查询条件
        QueryVo vo=new QueryVo();
        User user =new User();
        user.setName("%李%");
        vo.setUser(user);
        List<User> users=userDao.findByVo(vo);
        for(User u:users){
            System.out.println(u);
        }
        session.commit();
    }

    @Test
    public void findByCondition(){
        User u =new User();
        u.setName("李佳璇");

        List<User> users=userDao.findByCondition(u);
        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void findInIds(){
        QueryVo vo=new QueryVo();
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        vo.setIds(list);

        List<User> users=userDao.findInIds(vo);
        for(User user:users){
            System.out.println(user);
        }
    }

}
