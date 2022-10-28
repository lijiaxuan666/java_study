package jdbc_text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//执行DML与DDL sql语句
public class jdbc1 {
    public static void main(String[] args){
        Connection conn= null;
        Statement stmt=null;
        //如果使用throws抛出异常，因为可能中途存在异常，会导致无法释放资源，造成资源浪费，所以尽量使用try-catch
        try {
            //1.导入jar包
            //2.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");//可以不写，但最好写上，因为只有在5之后才可以不写，不写无法向下兼容
            //3.获取数据库连接对象

            conn = DriverManager.getConnection("jdbc_text.jdbc:mysql://localhost:3306/student","root","root");
            //4.定义sql语句
            String sql="insert into employee values(null,'陈鸡良',18,1)";
            //5.获取执行sql对象 Statement
            stmt=conn.createStatement();
            //6.执行sql
            int i=stmt.executeUpdate(sql);//返回值大于0，则表示成功
            //7.处理结果
            System.out.println(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            //8.释放资源
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
        }
    }
}
