package side;

public class Sensor {
    private String name;
    private String value;
    private String time;
    private String userName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
