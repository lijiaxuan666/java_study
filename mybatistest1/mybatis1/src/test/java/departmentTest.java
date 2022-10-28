
import first.dao.IDepartmentDao;
import first.domain.Department;
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

public class departmentTest {
    private InputStream in;
    private IDepartmentDao depDao;
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
        depDao=session.getMapper(IDepartmentDao.class);
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
        List<Department> deps=depDao.findAll();
        for(Department dep:deps){
            System.out.println(dep);
            System.out.println(dep.getList());
        }
    }


}

