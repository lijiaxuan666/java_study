package utils;

import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionUtil {
    private ThreadLocal<Connection> tl=new ThreadLocal<>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection creatConnection(){
        try{
            Connection conn=tl.get();
            if(conn==null){
                conn=dataSource.getConnection();
                tl.set(conn);
            }
            return conn;
        }catch (Exception e){
            throw new RuntimeException("获取连接时失败");
        }
    }

    public void removeConnection(){
        tl.remove();
    }
}
