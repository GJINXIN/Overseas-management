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

@WebServlet(name = "ItDepartmentInfoServlet")
public class getDepartmentInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        ServletContext application=getServletContext();
        ArrayList info=(ArrayList) application.getAttribute("info");
        ArrayList DepartmenInfo=new ArrayList();
        String name=(String)info.get(6);
        FunctionsOfMysql func=new FunctionsOfMysql();
        try {
            DepartmenInfo=func.getDepInfoByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        application.setAttribute("departmentinfo",DepartmenInfo);
        if(info.get(7).equals("staff"))
            request.getRequestDispatcher("/departmentinfo.jsp").forward(request,response);
        else
            request.getRequestDispatcher("/departmentinfobyadmin.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
