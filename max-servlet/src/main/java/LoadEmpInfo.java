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

public class LoadEmpInfo extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        ResultSet rs=null;
        int id= Integer.parseInt(req.getParameter("id"));
        PrintWriter pw= resp.getWriter();
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection=DBConnectionSource.getConnection();
            ps=connection.prepareStatement("select * from t_emp where id=?");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            pw.println("<html><head></head><body style='font-size:10px'>");
            while (rs.next()){
                String name=rs.getString("name");
                double salary=rs.getDouble("salary");
                int age=rs.getInt("age");
                pw.println("<form action='modify' method='post'>");
                pw.println("编号:"+id+"<br>");
                pw.println("<input type='hidden' name='id' value='"+id+"'><br>");
                pw.println("姓名:<input name='name' value='"+name+"'><br>");
                pw.println("薪水:<input name='salary' value='"+salary+"'><br>");
                pw.println("年龄:<input name='age' value='"+age+"'><br>");
                pw.println("<input type='submit' value='修改'>");
                pw.println("</form>");
            }
                pw.println("</body></html>");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(rs!=null){
                    rs.close();
                }
            }catch (Exception e){
                System.out.println("关闭异常");
            }
        }

    }
}
