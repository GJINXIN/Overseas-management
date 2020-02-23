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

@WebServlet(name = "adSearchTieziInfoServlet")
public class adSearchTieziInfoServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String str=request.getParameter("search");//获取搜索框输入的内容
        ServletContext application=getServletContext();
        ArrayList info=(ArrayList) application.getAttribute("info");
        FunctionsOfMysql func=new FunctionsOfMysql();
        ArrayList info1=new ArrayList();
        try{
            info1=func.adSerchTieziInfo(str,info.get(6).toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
        application.setAttribute("staffchart",info1);
        request.getRequestDispatcher("/staffcharts.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
