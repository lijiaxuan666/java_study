package jdbc_text;

import util.JDBCutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//jdbc4的优化版
public class jdbc4{
    public List<Student> getlist(){
        Connection conn=null;
        Statement stmt=null;
        Student stu=null;
        List<Student> list=null;
        ResultSet rs=null;
        try {
            conn= JDBCutils.getConnection();
            String sql="select * from stu";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            list=new ArrayList<>();
            while (rs.next()) {
                int id=rs.getInt(1);
                String name = rs.getString("name");
                //名字在第二列，之前没写id，导致其读取第一列内容，从而导致int与String类型不匹配报错
                int age = rs.getInt(3);
                double score = rs.getDouble(4);
                String birthday = rs.getString("birthday");
                String insert_time = rs.getString("insert_time");

                //此处把获得的数据封装为对象，再加入ArrayList之中，这样就算连接关闭了，也可以获取查询结果
                //因为结果集是有局限的，在连接关闭时，结果集也会被关闭，之前获取到的结果就无法查询了
                stu = new Student();
                stu.setId(id);
                stu.setName(name);
                stu.setAge(age);
                stu.setScore(score);
                stu.setBirthday(birthday);
                stu.setInsert_time(insert_time);

                list.add(stu);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            JDBCutils.close(rs,stmt,conn);
        }

        return list;
    }

    public static void main(String[] args) {
        List<Student> list= new jdbc4().getlist();
        System.out.println(list);
    }
}
