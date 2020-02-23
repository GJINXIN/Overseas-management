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

@WebServlet(name = "UpdateSelfsServlet")
public class UpdateSelfsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String id=request.getParameter("iid");
        String password=request.getParameter("ipassword");
        String name=request.getParameter("iname");
        String sex=request.getParameter("isex");
        String age=request.getParameter("iage");
        String phone=request.getParameter("iphone");
        String department=request.getParameter("idepartment");
        FunctionsOfMysql func=new FunctionsOfMysql();
        ArrayList info=new ArrayList();
        ArrayList info1=new ArrayList();
        info1.add(id);
        info1.add(name);
        info1.add(password);
        info1.add(sex);
        info1.add(age);
        info1.add(phone);
        info1.add(department);
        ServletContext application=getServletContext();
        info=(ArrayList) application.getAttribute("info");
        info1.add(info.get(7));
        application.setAttribute("info",info1);//更新info
        int flag=0;
        if(info.get(7).equals("staff")) {
            try {
                flag=func.updateOneStaffInfo(id,name,sex,age,phone,department);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/staffpage.jsp").forward(request,response);
        }
        else{
            try {
                flag=func.updateOneAdminInfo(id,name,sex,age,phone,department);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/adminpage.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
