import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryEmpList extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        PrintWriter pw=resp.getWriter();
        try {
            con=DBConnectionSource.getConnection();
            ps=con.prepareStatement("select * from t_emp");
            rs=ps.executeQuery();
            pw.println("<HTML>");
            pw.println("<HEAD><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"></HEAD>");
            pw.println("<BODY>");
            pw.println("<table border='1' cellpadding='0' cellspacing='0' width='600px'>");
            pw.println("<caption>员工信息表</caption>");
            pw.println("<tr><td>ID</td><td>姓名</td><td>薪水</td><td>年龄</td><td>操作</td></tr>");
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                double salary=rs.getDouble("salary");
                int age=rs.getInt("age");
                pw.println("<tr>");
                pw.println("<td>"+id+"</td>");
                pw.println("<td>"+name+"</td>");
                pw.println("<td>"+salary+"</td>");
                pw.println("<td>"+age+"</td>");
                pw.println("<td> <a href='delete?id="+id+"'"+"onclick=\"return confirm('是否确定删除"+name+"');\">删除</a>");
                pw.println("<a href='load?id="+id+"'>修改</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("<a href='addEmp.html'>增加员工信息</a>");
            pw.println("</BODY>");
            pw.println("</HTML>");
            pw.flush();
            pw.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(con!=null){
                    con.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(rs!=null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
