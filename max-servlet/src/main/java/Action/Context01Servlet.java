package Action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Context01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw=resp.getWriter();
        ServletContext servletContext=getServletContext();
        Object count=servletContext.getAttribute("count");
        if(count==null){
            servletContext.setAttribute("count",servletContext.getInitParameter("count"));
            //servletContext.setAttribute("count",1);
        }else {
            servletContext.setAttribute("count",Integer.parseInt(count.toString())+1);
        }
        pw.println("浏览器总量为："+servletContext.getAttribute("count"));
    }
}
