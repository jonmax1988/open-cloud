package dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static BasicDataSource dataSource = null;

    public DBUtil() {

    }

    public static void init() {
        Properties properties = new Properties();
        try {
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("加载配置文件失败~");
        }
        try {
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String initialSize = properties.getProperty("dataSource.initialSize");
            String minIdle = properties.getProperty("dataSource.minIdle");
            String maxIdle = properties.getProperty("dataSource.maxIdle");
            String maxTotal = properties.getProperty("dataSource.maxTotal");
            String maxWait = properties.getProperty("dataSource.maxWait");

            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);

            if (initialSize != null) {
                dataSource.setInitialSize(Integer.parseInt(initialSize));
            }
            if (minIdle != null) {
                dataSource.setMinIdle(Integer.parseInt(minIdle));
            }
            if (maxIdle != null) {
                dataSource.setMaxIdle(Integer.parseInt(maxIdle));
            }
            if (maxWait != null) {
                dataSource.setMaxWaitMillis(Long.parseLong(maxWait));
            }
            if (maxTotal != null) ;
            {
                if (!maxTotal.trim().equals(0)) {
                    dataSource.setMaxTotal(Integer.parseInt(maxTotal));
                }
            }

        } catch (Exception e) {
            System.out.println("创建连接池失败，请检查配置！！！");
        }
    }

    public static synchronized Connection getConnecting() throws SQLException {
        if (dataSource == null) {
            init();
        }
        Connection connection = null;
        if (dataSource != null) {
            connection = dataSource.getConnection();
        }
        return connection;

    }
    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testPrint() throws SQLException {
        System.out.println(getConnecting());
    }

}
