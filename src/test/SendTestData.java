package test;

import java.net.*;
import java.util.*;

/**
 * @author Side
 */
public class SendTestData {
    public static void main(String[] args) {
        boolean flag = true;
        String sensor[] = {"temperature", "PM2.5", "dryness", "humidness"};
        Scanner scanner = new Scanner(System.in);
        try {
            byte[] buffer = new byte[1024];
            InetAddress address = InetAddress.getByName("webside.wang");
//            InetAddress address=InetAddress.getByName("127.0.0.1");
            DatagramPacket dataPack = new DatagramPacket(buffer, buffer.length, address, 12345);
            DatagramSocket postman = new DatagramSocket();
            while (flag) {
                String mess = "{";
                int n = (int) (Math.random() * 20);
                for (int i = 0; i < n; i++) {
                    int t1 = (int) (Math.random() * 4);
                    int t2 = (int) (Math.random() * 100);
                    mess += sensor[t1] + ":" + t2;
                    if (i != n - 1) {
                        mess += ",";
                    }
                }
                mess += "}#";
                buffer = mess.getBytes("ISO-8859-1");
                int len = mess.length() - 1;
                int sum = 0;
                for (int i = 0; i < len; i++) {
                    sum += buffer[i];
                    sum %= 128;
                }
                buffer[len] = (byte) sum;
                dataPack.setData(buffer);
                postman.send(dataPack);
                System.out.print("是否继续发送模拟数据包:");
                flag = scanner.nextInt() == 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
