package jdbc_text;

import java.sql.*;

//执行DQL sql语句
public class jdbc2 {
    public static void main(String[] args){
        Connection conn= null;
        Statement stmt=null;
        ResultSet rs=null;
        //如果使用throws抛出异常，因为可能中途存在异常，会导致无法释放资源，造成资源浪费，所以尽量使用try-catch
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接对象
            conn = DriverManager.getConnection("jdbc_text.jdbc:mysql://localhost:3306/student","root","root");
            //3.定义sql语句
            String sql="select * from employee";
            //4.获取执行sql对象 Statement
            stmt=conn.createStatement();
            //5.执行sql
            rs=stmt.executeQuery(sql);//返回一个结果集
            while(rs.next()!=false){
                //rs.next();让光标往下移动一行，因为此时指针游标指向表头
                int id=rs.getInt(1);//编号从1开始，不是索引
                String name=rs.getString("name");
                int age=rs.getInt(3);
                int dep=rs.getInt(4);
                System.out.println(id+"---"+name+"---"+age+"---"+dep);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            //6.释放资源
            //为避免空指针异常
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}