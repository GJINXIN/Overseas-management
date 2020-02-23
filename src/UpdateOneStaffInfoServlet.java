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

@WebServlet(name = "UpdateOneStaffInfoServlet")
public class UpdateOneStaffInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String id=request.getParameter("sid");
        String name=request.getParameter("sname");
        String sex=request.getParameter("ssex");
        String age=request.getParameter("sage");
        String phone=request.getParameter("sphone");
        String dep=request.getParameter("sdep");
        FunctionsOfMysql func=new FunctionsOfMysql();
        int flag=0;
        try{
            flag=func.updateOneStaffInfo(id,name,sex,age,phone,dep);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(flag==1)request.getRequestDispatcher("/servlet/StaffChartServlet").forward(request,response);
        else response.sendRedirect("/staffcharts.jsp?error=yes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
