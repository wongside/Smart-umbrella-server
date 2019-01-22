package test;

import java.net.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author Side
 */
public class SendTestData {
    public static void main(String[] args) {
        boolean flag = true;
        String sensor[] = {"UV", "Heating", "tempture", "dryness", "PM2.5", "AirQuality"};
        Scanner scanner = new Scanner(System.in);
        try {
            byte[] buffer = new byte[1024];
            //InetAddress address = InetAddress.getByName("webside.wang");
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramPacket dataPack = new DatagramPacket(buffer, buffer.length, address, 12345);
            DatagramSocket postman = new DatagramSocket();
            while (flag) {
                StringBuilder mess = new StringBuilder("{");
                for (int i = 0; i < 6; i++) {
                    mess.append(sensor[i]).append(":").append(getRandomData(sensor[i]));
                    if (i != 5) {
                        mess.append(",");
                    }
                }
                mess.append("}#");
                buffer = mess.toString().getBytes("ISO-8859-1");
                int len = mess.length() - 1;
                int sum = 0;
                for (int i = 0; i < len; i++) {
                    sum += buffer[i];
                    sum %= 128;
                }
                buffer[len] = (byte) sum;
                dataPack.setData(buffer);
                postman.send(dataPack);
//                System.out.print("是否继续发送模拟数据包:");
//                flag = scanner.nextInt() == 1;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static String  getRandomData(String s) {
        double d;
        int value;
        DecimalFormat df;
        switch (s) {
            case "UV":
                d = Math.random()*2;
                df = new DecimalFormat("0.000");
                return df.format(d);
            case "Heating":
                value = (int) (Math.random()*500);
                return Integer.toString(value);
            case"tempture":
                d = Math.random()*100 - 20;
                df = new DecimalFormat("#.0");
                return df.format(d);
            case "dryness":
                value = (int) (Math.random()*100);
                return Integer.toString(value);
            case "PM2.5":
                value = (int) (Math.random()*4);
                return Integer.toString(value+65);
            case "AirQuality":
                value = (int) (Math.random()*4);
                return Integer.toString(value+65);
        }
        return null;
    }
}
