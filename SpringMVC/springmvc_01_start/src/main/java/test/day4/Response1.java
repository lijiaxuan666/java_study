package test.day4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //重定向一般是原网址搬迁到了新网址，用户依然访问原网址，就会进行重定向，找到新的网址
        System.out.println("来到了Response1");
        //设置响应状态码302，表示重定向（已搬迁）
        //resp.setStatus(302);
        //resp.setHeader("Location","http://localhost:8080/springmvc_01_start/response2");

        //常用方法
        resp.sendRedirect("http://localhost:8080/springmvc_01_start/response2");
    }
}
