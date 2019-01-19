package side;
import java.sql.*;

public class Database {
    private String str = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://47.93.227.16:3306/smart-umbrella?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
    private String user = "root";
    private String password = "123456";
    private Connection conn;
    private Statement stmt;
    private ResultSet rst;

    public Database() {
        try {
            Class.forName(str).newInstance();
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            SystemMessage.setMessage(e.toString());
        }
    }

    public boolean insert(Sensor sensor){
        int ok = 0;
        String sql = "insert into datatab(NAME,VALUE,TIME,USERNAME) VALUES('"+sensor.getName()
                +"', "+sensor.getValue()+",  '"+sensor.getTime()+"', '"+sensor.getUserName()+"');";
        try {
            ok = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            SystemMessage.setMessage(e.toString());
        }
        return ok == 1;
    }
}
