package jdbc_text;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//定义一个方法，查询stu表数据将其封装为对象，然后装载集合，并返回
//1.定义Student类
//2.实现方法 public List<util.jdbc_text.Student> findALL(){}
public class jdbc3 {
    public List<Student> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> list = null;
        //如果使用throws抛出异常，因为可能中途存在异常，会导致无法释放资源，造成资源浪费，所以尽量使用try-catch
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
            //3.定义sql语句
            String sql = "select * from stu";
            //4.获取执行sql对象 Statement
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);//返回一个结果集
            //6.遍历结果集，封装对象，装载集合

            list = new ArrayList<>();
            Student stu = null;
            while (rs.next()) {
                //获取数据
                int id=rs.getInt(1);
                String name = rs.getString("name");
                int age = rs.getInt(3);
                double score = rs.getDouble(4);
                String birthday = rs.getString("birthday");
                String insert_time = rs.getString("insert_time");
                //创建对象并赋值
                stu = new Student();
                stu.setId(id);
                stu.setName(name);
                stu.setAge(age);
                stu.setScore(score);
                stu.setBirthday(birthday);
                stu.setInsert_time(insert_time);

                list.add(stu);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //7.释放资源
            //为避免空指针异常
            if (stmt != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Student> list = new jdbc3().findAll();
        System.out.println(list);
    }
}
