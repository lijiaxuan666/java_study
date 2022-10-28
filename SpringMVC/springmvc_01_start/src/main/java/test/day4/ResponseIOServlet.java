package test.day4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println(resp.getCharacterEncoding());//默认ISO-8859-1

        //第一种
        //如果只使用这个，会出现别的乱码。因为它只修改了服务器的字符集，浏览器的字符集依然是默认的GBK
        //resp.setCharacterEncoding("UTF-8");
        //加上通过响应头，设置浏览器也使用UTF-8字符集
        //resp.setHeader("Content-Type","text/html;charset=UTF-8");

        //第二种
        //它会同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头
        //此方法要在获取流之前调用
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer=resp.getWriter();
        writer.write("答复的内容");
        writer.write("response's content!!!");
    }
}
