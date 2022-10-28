package test.day1;
//继承HttpServlet类-----常用
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//一般在项目实际开发中，都是使用继承HttpServlet类的方式去实现Servelt
public class Servlet01 extends HttpServlet {
    /*
    @Override
    public void init(ServletConfig config) throws ServletException{
        System.out.println("重写了init方法");

        //重新init方法一定要调用父类方法，因为父类代码中有获取config并保存的操作
        //当我们重写时，父类不被调用，所以config是空值，为了避免这种情况需要调用父类init方法
        super.init(config);

    }
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet01的doGet方法");

        //属于单个Servlet的
        ServletConfig servletConfig=getServletConfig();
        System.out.println("Servlet01的别名是:"+servletConfig.getServletName());
        System.out.println("初始化参数url的值是:"+servletConfig.getInitParameter("url"));

        //属于整个web工程的，所以Servlet都可以获取到
        //ServletContext是在web工程部署时候创建，在web工程停止的时候销毁
        ServletContext context=getServletConfig().getServletContext();
        System.out.println("context-param参数的值为"+context.getInitParameter("username"));

        //获取当前工程路径 ，格式： /工程路径
        System.out.println("当前工程路径"+context.getContextPath());
        //获取工程部署后在服务器硬盘上的绝对路径
        System.out.println("工程部署路径"+context.getRealPath("/"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet01的doPost方法");
    }
}
