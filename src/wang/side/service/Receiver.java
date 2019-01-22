package wang.side.service;

import wang.side.bean.Sensor;
import wang.side.utils.Database;
import wang.side.utils.SystemMessage;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Timestamp;
import java.util.Date;

public class Receiver implements Runnable {
    private boolean  isRun;
    public Receiver(){
        isRun = true;
    }
    @Override
    public void run() {
            DatagramPacket pack = null;
            DatagramSocket postman = null;
            byte data[] = new byte[8192];
            try {
                pack = new DatagramPacket(data, data.length);
                postman = new DatagramSocket(12345);
            } catch (Exception e) {
                SystemMessage.setMessage(e.toString());
            }
            while (true) {
                if (postman == null) break;
                else {
                    try {
                        postman.receive(pack);
                        String message = new String(pack.getData(), 0, pack.getLength());
                        SystemMessage.setMessage(message);
                        if(checkSum(message)){
                            Database database = new Database();
                            message = message.substring(1, message.length() - 2);
                            String sensorData [] = message.split(",");
                            String userName = "test", time = "";
                            boolean ok = false;
                            for(String s : sensorData){
                                String tmp [] = s.split(":");
                                if(tmp[0].equals(userName)){
                                    userName = tmp[1];
                                }
                                if(tmp[0].equals(time)){
                                    time = tmp[1];
                                }
                                Sensor sensor = new Sensor();
                                sensor.setName(tmp[0]);
                                sensor.setValue(tmp[1]);
                                sensor.setUserName(userName);
                                if(time.equals("")){
                                    sensor.setTime(new Timestamp(new Date().getTime()).toString());
                                }
                                ok = database.insert(sensor);
                            }
                            if(ok){
                                SystemMessage.setMessage("data insert is success!");
                            }else{
                                SystemMessage.setMessage("data insert is failed!");
                            }
                            database.releaseSource();
                        }
                    } catch (Exception e) {
                        SystemMessage.setMessage(e.toString());
                    }
                }
            }
    }
    private boolean checkSum(String message){

        byte buffer [] = new byte[0];
        try {
            buffer = message.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            SystemMessage.setMessage(e.toString());
        }
        int sum = 0;
        int len = message.length() - 1;
        for(int i = 0; i < len; i++){
            sum += buffer[i];
            sum %= 128;
        }
        if(sum == buffer[len]){
            SystemMessage.setMessage("check sum is right!");
            return true;
        }
        SystemMessage.setMessage("check sum is wrong! " + sum + " != " + buffer[len]);
        return false;
    }
}
