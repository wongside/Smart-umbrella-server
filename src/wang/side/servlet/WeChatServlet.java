package wang.side.servlet;

import com.google.gson.Gson;
import wang.side.utils.Database;
import wang.side.utils.SystemMessage;
import wang.side.bean.Sensor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Side
 */
@WebServlet("/weChat/*")
public class WeChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        switch (content) {
            case "data":
                getData(request, response);
                break;
            case "user":
                getUser(request, response);
                break;
            default:
                SystemMessage.setMessage("请求参数错误！");
                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    void test(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI()
                    + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            out.println(url);
        } catch (IOException e) {
           SystemMessage.setMessage(e.toString());
        }
    }

    private void getData(HttpServletRequest request, HttpServletResponse response) {
        String deviceId = request.getParameter("deviceId");
        String dataName = request.getParameter("dataName");
        int count = Integer.parseInt(request.getParameter("count"));
        Database database = new Database();
        Sensor sensor[] = database.getSensor(deviceId, dataName, count);
        Gson gson = new Gson();
        String s = gson.toJson(sensor);
        try {
            PrintWriter out = response.getWriter();
            out.print(s);
        } catch (IOException e) {
            SystemMessage.setMessage(e.toString());
        }
        database.releaseSource();

    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) {

    }
}
