/**
 * author:xpl
 * date：2019.1.10
 * description:IT主管查找自己部门的员工信息
 */

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

@WebServlet(name = "StaffChartServlet")
public class StaffChartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        ServletContext application=getServletContext();
        ArrayList info= (ArrayList) application.getAttribute("info");
        ArrayList StaffChart= new ArrayList();
        FunctionsOfMysql func=new FunctionsOfMysql();
        try {
            StaffChart=func.getStaffInfoByDepartment((String) info.get(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        application.setAttribute("staffchart",StaffChart);
        request.getRequestDispatcher("/staffcharts.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
