package User;

import Utils.utils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class login {
    JdbcTemplate template=new JdbcTemplate(utils.getDataSource());
    Scanner sc=new Scanner(System.in);
    //注册
    @Test
    public void login_in(){
        String sql="insert into user values(?,?,?)";
        System.out.println("请输入注册账号：");
        String username=sc.next();
        String password;
        while(true){
            System.out.println("请输入密码：(区分大小写)");
            password=sc.next();
            System.out.println("请确认密码：（区分大小写）");
            String password1=sc.next();
            if(!password.equals(password1)){
                System.out.println("两次输入密码不一致，请重新输入：");
            }else{
                break;
            }
        }
        System.out.println("输入权限：（0为普通用户，1为管理员）");
        int power=sc.nextInt();
        template.update(sql,username,password,power);
        return;
    }
    //登录
    @Test
    public int login_on(){
        String sql="select count(username) from user where username = ? and userpassword = ?";

        int power1 = 0;
        String username1=null,password1 = null;
        while(true){
            System.out.println("请输入账号：");
            String username=sc.next();
            System.out.println("请输入密码：");
            String password=sc.next();

            Long total =template.queryForObject(sql,Long.class,username,password);

            if (total!=1) {
                System.out.println("账号或者密码错误，请重新输入！");
            }else{
                String sql2="select userpower from user where username = ? and userpassword = ?";
                power1=template.update(sql,username,password);
                System.out.println("登录成功！");
                break;
            }
        }
        //返回权限管理员or普通用户
        return power1;
    }
}
