import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmpInfo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        int id= Integer.parseInt(req.getParameter("id"));
        PrintWriter pw= resp.getWriter();
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection=DBConnectionSource.getConnection();
            ps=connection.prepareStatement("delete  from t_emp where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            resp.sendRedirect("list");//重定向到list页面
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
