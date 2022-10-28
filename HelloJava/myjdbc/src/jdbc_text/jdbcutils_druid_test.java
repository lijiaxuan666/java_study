package jdbc_text;

import util.JDBCutils_Druid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcutils_druid_test {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn= JDBCutils_Druid.getConnection();
            //用？防止sql注入
            String sql="insert into employee values(null,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,"刘喜");
            pstmt.setInt(2,19);
            pstmt.setInt(3,2);

            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            //释放资源
            JDBCutils_Druid.close(pstmt,conn);
        }
    }
}
