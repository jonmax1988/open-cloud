package Action;

import dao.EmployeeDao;
import entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        //获取请求的资源路径
        String url = req.getRequestURI();
        //获取请求资源路径中除应用名以外的部分
        String action = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        if (action.equals("list")) {
            try {
                EmployeeDao employeeDao = new EmployeeDao();
                List<Employee> employees = employeeDao.findAll();
                pw.println("<table border='1' cellpadding='0' cellspacing='0' width='600px'>");
                pw.println("<caption>员工信息列表</caption>");
                pw.println("<tr><td>ID</td><td>姓名</td><td>薪水</td><td>年龄</td></tr>");
                for (Employee employee : employees) {
                    pw.println("<tr>");
                    pw.println("<td>" + employee.getId() + "</td>");
                    pw.println("<td>" + employee.getName() + "</td>");
                    pw.println("<td>" + employee.getSalary() + "</td>");
                    pw.println("<td>" + employee.getAge() + "</td>");
                    pw.println("<td><a href='#'>删除</a>");
                    pw.println("<a href='#'>修改</a></td>");
                    pw.println("</tr>");
                }
                pw.println("</table>");
                pw.println("<a href='addEmp.html'>增加新员工</a>");
            } catch (Exception e) {
                e.printStackTrace();
                pw.println("系统繁忙,请稍后再试");
            }
        }else if(action.equals("add")){
            String name=req.getParameter("name");
            double salary= Double.parseDouble(req.getParameter("salary"));
            int age=Integer.parseInt(req.getParameter("age"));
            Employee employee=new Employee();
            employee.setName(name);
            employee.setSalary(salary);
            employee.setAge(age);
            EmployeeDao employeeDao=new EmployeeDao();
            employeeDao.add(employee);
            resp.sendRedirect("list.do");
        }
    }
}
