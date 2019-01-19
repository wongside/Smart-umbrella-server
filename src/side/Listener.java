package side;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

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
