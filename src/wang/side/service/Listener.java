package wang.side.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class Listener implements ServletContextListener{
    Thread readData ;
    public void contextInitialized(ServletContextEvent sce) {
        Receiver receiver = new Receiver();
        readData = new Thread(receiver);
        readData.start();           //负责接收信息的线程
    }

    public void contextDestroyed(ServletContextEvent sce) {
        readData.stop();
    }
}
