

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmpInfo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw=resp.getWriter();
        String name=req.getParameter("name");
        double salary= Double.parseDouble(req.getParameter("salary"));
        int age= Integer.parseInt(req.getParameter("age"));
        Connection connection=null;
        PreparedStatement ps=null;
        String sql=null;
        try {
            connection=DBConnectionSource.getConnection();
            connection.setAutoCommit(false);
            sql="INSERT INTO t_emp (name,salary,age)VALUES(?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setString(1,name);
            ps.setDouble(2,salary);
            ps.setInt(3,age);
            ps.executeUpdate();
            connection.commit();
            pw.println("添加成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            pw.println("系统繁忙，请稍后再试");
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


}
