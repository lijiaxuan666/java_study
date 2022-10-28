package test.day2;
//获取参数
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----------doGet---------");

        System.out.println("用户名："+req.getParameter("username"));
        System.out.println("密码："+req.getParameter("password"));
        System.out.println("确认密码："+req.getParameter("acpassword"));

        //获取单个参数值时使用
        System.out.println("性别："+req.getParameter("sex"));
        //获取多个参数值时使用
        String[] hobby=req.getParameterValues("hobby");
        System.out.println("爱好："+ Arrays.asList(hobby));

        System.out.println("国籍："+req.getParameter("country"));
        System.out.println("自我评价："+req.getParameter("text"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用Post请求时，英文字符不会发生变化，但输入中文字符时就会出现乱码,
        //所以需要设定其字符集,但它要放在请求参数之前使用，不然依然是乱码
        req.setCharacterEncoding("UTF-8");
        System.out.println("----------doPost---------");
        System.out.println("用户名："+req.getParameter("username"));
        System.out.println("密码："+req.getParameter("password"));
    }
}
