package first.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component("connectionUtil")
public class ConnectionUtil {
    private ThreadLocal<Connection> tl=new ThreadLocal<>();
    @Autowired
    private DataSource dataSource;

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
