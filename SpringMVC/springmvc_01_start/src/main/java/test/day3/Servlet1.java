package test.day3;
//请求转发
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数（查看办事材料）
        String username=req.getParameter("username");
        System.out.println("在Servlet中查看参数:"+username);

        //2.给材料盖一个章，并传递到Servlet2（柜台2）
        req.setAttribute("key","柜台1的章");

        //3.问路，Servlet2（柜台2）怎么走
        //请求转发必须要以斜杠打头，/ 斜杠地址表示为：http://ip:port/工程名，映射到IDEA代码的web目录中
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("/servlet2");
        requestDispatcher.forward(req,resp);
    }
}
