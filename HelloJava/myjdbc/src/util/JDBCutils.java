package util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCutils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //文件的读取，使用静态代码块，只需读取一次就可以拿到这些值
    static{
        //读取资源文件
        try {
            //1.创建Properties集合类
            Properties pro=new Properties();

            //2.加载文件
            //获取src路径下的文件方式->ClassLoader 类加载器
            ClassLoader classLoader= JDBCutils.class.getClassLoader();
            URL res=classLoader.getResource("jdbc.properties");
            //这里之前写的是src.jdbc.properties，导致其在src下找src这个包，然后找不到报错
            String path=res.getPath();
            pro.load(new FileReader(path));

            //pro.load(new FileReader("D:\\ljx\\Hello Java\\myjdbc\\src\\jdbc.properties"));//绝对路径

            url= pro.getProperty("url");
            user= pro.getProperty("user");
            password= pro.getProperty("password");
            driver= pro.getProperty("driver");

           Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn= DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static void close(Statement stmt, Connection conn){
        close(null,stmt,conn);
    }

    public static void close(ResultSet rs,Statement stmt, Connection conn){
        if (conn != null) {
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
}
