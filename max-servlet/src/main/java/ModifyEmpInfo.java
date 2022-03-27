import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyEmpInfo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw= resp.getWriter();

        int id= Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        double salary= Double.parseDouble(req.getParameter("salary"));
        int age= Integer.parseInt(req.getParameter("age"));

        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection=DBConnectionSource.getConnection();
            ps=connection.prepareStatement("update t_emp set name=?,salary=?,age=? where id=?");
            ps.setString(1,name);
            ps.setDouble(2,salary);
            ps.setInt(3,age);
            ps.setInt(4,id);
            ps.executeUpdate();
            resp.sendRedirect("list");
        } catch (SQLException e) {
                e.printStackTrace();
                pw.println("系统出现问题，请稍后再试。<br><p><a href='list.do'>员工信息列表</a></p>");
        }finally {
            try {
                 if(connection!=null){
                     connection.close();
                 }
                 if(ps!=null){
                     ps.close();
                 }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
