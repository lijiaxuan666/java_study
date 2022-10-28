package Service;

import Utils.utils;
import org.springframework.jdbc.core.JdbcTemplate;


public class Buy_Ticket {
    public void buy(int id){
        JdbcTemplate template=new JdbcTemplate(utils.getDataSource());
        String sql="select * from movies where id = ?";
        template.update(sql,id);
    }
    public void buy(String name){

    }
}
