package utils;

import java.sql.SQLException;

public class TransactionManager {
    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }
    public void begin(){
        try {
            connectionUtil.creatConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void commit(){
        try {
            connectionUtil.creatConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void rollback(){
        try {
            connectionUtil.creatConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void realse(){
        try {
            connectionUtil.creatConnection().close();
            connectionUtil.removeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
