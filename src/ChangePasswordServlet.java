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

@WebServlet(name = "ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String oldpw=request.getParameter("oldpw");
        String password=request.getParameter("npw");
        FunctionsOfMysql func=new FunctionsOfMysql();
        ServletContext application=getServletContext();
        ArrayList info =(ArrayList) application.getAttribute("info");
        if(oldpw.equals(info.get(2))){ //如果输入原密码匹配
            try {
                func.ChangePassword((String) info.get(0),(String)info.get(2),password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(info.get(7).equals("admin"))
                request.getRequestDispatcher("/adminpage.jsp").forward(request,response);
            else
                request.getRequestDispatcher("/staffpage.jsp").forward(request,response);
        }
        else{
            if(info.get(7).equals("admin"))
                response.sendRedirect("/SalarySystem/adminpage.jsp?pw=no");
            else
                response.sendRedirect("/SalarySystem/staffpage.jsp?pw=no");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
