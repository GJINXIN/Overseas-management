import com.alibaba.fastjson.JSONObject;
import com.salary.FunctionsOfMysql;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "adSearchtzbyxiaoxiServlet")
public class adSearchtzbyxiaoxiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String str = request.getParameter("tid");//获取搜索框输入的内容
        ServletContext application = getServletContext();
        ArrayList info = (ArrayList) application.getAttribute("info");
        FunctionsOfMysql func = new FunctionsOfMysql();
        ArrayList info1 = new ArrayList();
        try {
            info1 = func.adSerchtiezibyxiaoxi(str, info.get(6).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int j = 0;
        Map<String, Object> m2 = new LinkedHashMap<String, Object>();
        m2.put("tid", info1.get(j++));
        m2.put("id", info1.get(j++));
        m2.put("title", info1.get(j++));
        m2.put("content", info1.get(j++));
        m2.put("img", info1.get(j++));
        m2.put("time", info1.get(j++));
        m2.put("school", info1.get(j++));
        m2.put("type", info1.get(j++));
        m2.put("shenhe", info1.get(j++));
        JSONObject json = new JSONObject(m2);
        response.setContentType("text/JavaScript; charset=GB2312");
        response.getWriter().print(json.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
