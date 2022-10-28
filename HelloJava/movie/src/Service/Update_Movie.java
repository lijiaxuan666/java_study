package Service;

import Utils.utils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Update_Movie {
    JdbcTemplate template=new JdbcTemplate(utils.getDataSource());
    @Test
    public void Add(){
        String sql="insert into movies values (null,?,?,?,?,?)";
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入电影名称：");
        String name=sc.next();
        System.out.println("请输入日期(格式2021-06-18-15：00：00)：");
        String show_time=sc.next();
        System.out.println("请输入电影时长(单位分钟)：");
        int duration=sc.nextInt();
        System.out.println("请输入电影票价：");
        double price=sc.nextDouble();
        System.out.println("请输入电影类型：");
        String type=sc.next();
        template.update(sql,name,show_time,duration,price,type);
        System.out.println("添加成功!");
    }
    @Test
    public void Delete(int id){
        String sql="delete from movies where id = ?";
        template.update(sql,id);
        System.out.println("删除!");
    }
    @Test
    public void Delete(String name){
        String sql="delete from movies where id = ?";
        template.update(sql,name);
        System.out.println("删除!");
    }
}
