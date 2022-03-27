package dao;

import entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    /**
     * 查询所有员工
     */
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<Employee>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnecting();
            ps = connection.prepareStatement("SELECT * from t_emp");
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getInt("age"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return employees;
    }

    /**
     * 根据id删除员工信息
     *
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnecting();
            ps = connection.prepareStatement("delete from t_emp where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
    }

    /**
     * 添加员工
     *
     * @param employee
     */
    public void add(Employee employee) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnecting();
            ps = connection.prepareStatement("insert into t_emp (name,salary,age) values (?,?,?)");
            ps.setString(1, employee.getName());
            ps.setDouble(2, employee.getSalary());
            ps.setInt(3, employee.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
    }

    /**
     * 根据员工id查询员工信息
     *
     * @param id
     * @return
     */
    public Employee findById(int id) {
        Employee employee = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnecting();
            ps = connection.prepareStatement("select * from t_emp where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return employee;
    }

    public void modify(Employee employee) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnecting();
            ps = connection.prepareStatement("update t_emp set name=?,set salary=?," +
                    "set age=? where id=?");
            ps.setString(1,employee.getName());
            ps.setDouble(2,employee.getSalary());
            ps.setInt(3,employee.getAge());
            ps.setInt(4,employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection);
        }
    }

}
