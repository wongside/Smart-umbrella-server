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
        String sql = "insert into datatab(NAME,VALUE,TIME,DEVICE) VALUES('"+sensor.getName()
                +"', "+sensor.getValue()+",  '"+sensor.getTime()+"', '"+sensor.getUserName()+"');";
        try {
            ok = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            SystemMessage.setMessage(e.toString());
        }
        return ok == 1;
    }
    public Sensor [] getSensor(String deviceId, String dataName, int count){
        String sql = "SELECT * FROM datatab where NAME='"+ dataName +"' and device = '" + deviceId +
                "' ORDER BY TIME DESC limit " + count;
        Sensor sensor = null;
        Sensor sensors[] = new Sensor[count];
        try {
            ResultSet rst = stmt.executeQuery(sql);
            for(int i = 0; i < count && rst.next(); i++){
                sensor = new Sensor();
                String value = rst.getString("VALUE");
                String time = rst.getString("TIME");
                sensor.setTime(time);
                sensor.setValue(value);
                sensors[i] = sensor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensors;
    }
}
