import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.salary.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String id=request.getParameter("name");
        String pw=request.getParameter("password");
        String type=request.getParameter("type");
        System.out.println(id);
        System.out.println(pw);
        FunctionsOfMysql functionsOfMysql = new FunctionsOfMysql();
        ArrayList info=new ArrayList();
        int flag=0; //是否登录标志
        ServletContext application=getServletContext();
        if(type.equals("staff")) {
            try {
                flag = functionsOfMysql.StaffLogin(id, pw);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                flag = functionsOfMysql.AdminLogin(id, pw);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        application.setAttribute("flag",flag);
        System.out.println("im here");
        if(flag==1){ //登陆成功
            if(type.equals("staff")){
                try {
                    info=functionsOfMysql.getStaffInfoByIdByStaff(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    info=functionsOfMysql.getAdminInfoByIdByAdmin(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            info.add(type);
            application.setAttribute("info",info);
            if(type.equals("staff"))
                request.getRequestDispatcher("/staffpage.jsp").forward(request,response);
            else
                request.getRequestDispatcher("/adminpage.jsp").forward(request,response);
        }
        else {//登录失败
            response.sendRedirect("/SalarySystem/login.jsp?error=yes");
            System.out.println("跳转回主页面");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
