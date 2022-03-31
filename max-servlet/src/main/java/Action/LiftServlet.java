package Action;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LiftServlet extends HttpServlet {
    /**
     * 阶段1，实例化
     */
    public LiftServlet() {
        System.out.println("1.Constructor is running");
    }
    /**
     * 阶段2，初始化
     */
    @Override
    public void init() throws ServletException {
        System.out.println("2.Init is running");
    }

    /**
     * 阶段3，就绪
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        ServletConfig servletConfig=getServletConfig();
        String name=servletConfig.getInitParameter("company");
        String address=servletConfig.getInitParameter("address");
        System.out.println("3.Service is running");
        System.out.println("初始参数为:"+name+" "+address);
    }

    /**
     * 阶段4，销毁
     */
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("4.Destroy is running");
    }
}
