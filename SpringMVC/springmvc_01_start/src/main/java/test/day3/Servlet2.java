package test.day3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数（查看办事材料）
        String username=req.getParameter("username");
        System.out.println("在Servlet中查看参数:"+username);

        //查看柜台1是否盖章
        Object key=req.getAttribute("key");
        System.out.println("柜台1是否有章-->"+key);

        //处理自己的业务
        System.out.println("Servlet2处理自己业务");
    }
}
