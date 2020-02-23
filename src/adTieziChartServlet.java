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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "adTieziChartServlet")
public class adTieziChartServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        ServletContext application=getServletContext();
        ArrayList info= (ArrayList) application.getAttribute("info");
        ArrayList StaffChart= new ArrayList();
        FunctionsOfMysql func=new FunctionsOfMysql();
        try {
            StaffChart=func.adgetTieziinfoBytype((String) info.get(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        application.setAttribute("staffchart",StaffChart);
        System.out.println(StaffChart);
        request.getRequestDispatcher("/staffcharts.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
