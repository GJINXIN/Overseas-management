import javax.servlet.http.HttpServlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.salary.FunctionsOfMysql;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.AllPermission;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "adGetXiaoxiServlet")
public class adGetXiaoxiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        ServletContext application=getServletContext();
        String str=request.getParameter("usid");
        System.out.println(str);
        ArrayList StaffChart= new ArrayList();
        FunctionsOfMysql func=new FunctionsOfMysql();
        try {
            StaffChart=func.adgetxiaoxi((String) str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        application.setAttribute("staffchart",StaffChart);
        System.out.println(StaffChart);
        List list = new ArrayList();
        int n=StaffChart.size()/5;
        int j=0;
        for(int i=0;i<n;i++) {
            Map<String, Object> m2 = new LinkedHashMap<String, Object>();
            m2.put("tid", StaffChart.get(j++));
            m2.put("uid",StaffChart.get(j++));
            m2.put("ruser", StaffChart.get(j++));
            m2.put("id", StaffChart.get(j++));
            m2.put("rcontent", StaffChart.get(j++));
            m2.put("rtime", StaffChart.get(j++));
            list.add(m2);
        }
        String json = JSONObject.toJSONString(list);
        response.setContentType("text/JavaScript; charset=GB2312");
        response.getWriter().print(json);
        System.out.println(json);
        application.setAttribute("TieziChart",StaffChart);
    }
}
