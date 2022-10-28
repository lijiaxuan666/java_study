package jdbc_text;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import util.JDBCutils_Druid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class jdbctemplate {
    //Junit单元测试，可以让方法独立执行
    JdbcTemplate template=new JdbcTemplate(JDBCutils_Druid.getDataSource());
    @Test
    public void test1(){ //修改数据
        String sql="update employee set age=19 where id =1";
        int count=template.update(sql);
        System.out.println(count);
    }

    @Test
    public void test2(){ //插入数据
        String sql="insert into employee values(null,?,?,?)";
        int count=template.update(sql,"刘帷萱",19,1);
        System.out.println(count);
    }

    @Test
    public void test3(){ //插入数据
        String sql="delete from employee where id = ?";
        int count=template.update(sql,9);
        System.out.println(count);
    }

    @Test
    public void test4(){  //查询数据,将其封装为Map集合，这个方法查询结果集长度只能为1，即只能查询一行数据
        String sql="select * from employee where id = ?";
        Map<String,Object> map=template.queryForMap(sql,1);
        System.out.println(map);
    }

    @Test
    public void test5(){  //查询数据,将其封装为List集合，可以查询多个记录
        String sql="select * from employee";
        List<Map<String,Object>> list=template.queryForList(sql);
        //每条记录用Map封装，再将Map用List封装，最后用循环输出每条Map集合

        //迭代器遍历
        Iterator<Map<String,Object>> it=list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        /*
        //增强for循环遍历
        for(Map<String,Object> map:list){
            System.out.println(map);
        }*/
    }

    @Test
    public void test6(){  //查询数据,将其封装为Employee对象的List集合，可以查询多个记录(自己写一个匿名内部类)
        String sql="select * from stu";
        List<Student> list=template.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
                Student stu=new Student();
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt(2));
                stu.setScore(rs.getDouble(3));
                stu.setBirthday(rs.getString("birthday"));
                stu.setInsert_time(rs.getString("insert_time"));
                return stu;
            }
        });

        for(Student student:list){
            System.out.println(student);
        }
    }

    @Test
    public void test7(){  //查询数据,将其封装为Employee对象的List集合，可以查询多个记录(使用提供的实现类)
        String sql="select * from stu";
        //查询过程中可能会出现某种数据类型不能为空的异常，
        //是因为基本类型的值默认为0，不能为null，此时可以将该类中的数据类型替换为对应的包装类，即可避免出现此问题
        List<Student> list=template.query(sql, new BeanPropertyRowMapper<Student>(Student.class));

        for(Student student:list){
            System.out.println(student);
        }
    }

    @Test
    public void test8(){  //聚合函数
        String sql="select count(id) from employee";
        Long total =template.queryForObject(sql,Long.class);
        System.out.println(total);
    }
}
