package side;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {
    private SimpleDateFormat sdf;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String message = SystemMessage.getMessage();
        if(!message.equals("")){
            out.println(sdf.format(new Date()) + message);
        }
    }
    public void init() throws ServletException {
        super.init();
        StringBuilder sb = new StringBuilder();
        sb.append("yyyy年MM月dd日 HH:mm:ss :");
        sdf = new SimpleDateFormat(sb.toString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
