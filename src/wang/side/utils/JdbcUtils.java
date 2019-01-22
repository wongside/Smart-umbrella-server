package wang.side.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;


public class JdbcUtils {

    private static DataSource dataSource;
    private static JdbcUtils instance = new JdbcUtils();

    static {
        dataSource = new ComboPooledDataSource();
    }

    private JdbcUtils() {
    }

    public static JdbcUtils getInstance() {
        return instance;
    }


    Connection getConnection() {
        Connection conn = null;
        if (dataSource != null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            SystemMessage.setMessage(e.toString());
        }
    }
}
