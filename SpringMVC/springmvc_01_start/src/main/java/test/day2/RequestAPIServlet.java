package test.day2;
//请求的API
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的资源路径
        System.out.println("URI==="+req.getRequestURI());
        //2.获取请求的统一资源定位符
        System.out.println("URL==="+req.getRequestURL());
        /* 3.获取客户端的ip地址
         * 在IDEA中，使用localhost访问时，得到的ip地址是127.0.0.1
         * 在IDEA中，使用127.0.0.1访问时，得到的ip地址是127.0.0.1
         * 在IDEA中，使用真实ip访问时，得到的ip地址是真实ip
         * */
        System.out.println("URI==="+req.getRemoteHost());
        //4.获取请求头
        System.out.println("URI==="+req.getHeader("User-Agent"));
        //5.获取请求的方式GET或POST
        System.out.println("URI==="+req.getMethod());
    }
}
