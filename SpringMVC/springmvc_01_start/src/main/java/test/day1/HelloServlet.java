package test.day1;
//实现Servlet接口----不咋使用
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 1、执行 Servlet 构造器方法
 * 2、执行 init 初始化方法
 * 第一、二步，是在第一次访问，的时候创建 Servlet 程序会调用。
 *
 * 3、执行 service 方法
 * 第三步，每次访问都会调用。
 *
 * 4、执行 destroy 销毁方法
 * 第四步，在 web工程停止的时候才会调用。
 */
public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("1、执行 Servlet 构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2、执行 init 初始化方法");

        //1、可以获取 Servlet 程序的别名 servlet-name 的值
        System.out.println("HelloServlet的别名是:"+servletConfig.getServletName());
        //2、获取初始化参数 init-param
        System.out.println("初始化参数name的值是:"+servletConfig.getInitParameter("name"));

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     *service方法是专门用来处理请求和响应的
     * 也就是说，只要执行HelloServlet程序，它就会访问这个方法
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3、执行 service 方法 ==>Hello Servlet 被访问了");

        //ServletRequest是没有getmethod方法的，需要将其转成子类HttpServletRequest
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        String method=httpServletRequest.getMethod();

        //如果Get或者Post要执行的语句太多，可以写个方法来执行其中语句，使service中代码更简洁
        if("GET".equals(method)){
            doGet();
        }else if("POST".equals(method)){
            doPost();
        }
    }
    //做get的请求操作
    public void doGet(){
        System.out.println("get请求");
    }
    //做post的请求操作
    public void doPost(){
        System.out.println("post请求");
    }
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4、执行 destroy 销毁方法");
    }
}
