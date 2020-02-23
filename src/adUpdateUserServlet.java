import com.salary.FunctionsOfMysql;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "adUpdateUserServlet")
public class adUpdateUserServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String id=request.getParameter("sid");
        String name=request.getParameter("sname");
        String password=request.getParameter("spassword");
        String sex=request.getParameter("ssex");
        String age=request.getParameter("sage");
        String email=request.getParameter("semail");
        String school=request.getParameter("sschool");
        String shenhe=request.getParameter("sshenhe");
        FunctionsOfMysql func=new FunctionsOfMysql();
        System.out.println(id+name+password+sex+age+email+school);
        int flag=0;
        try{
            flag=func.adUpdateUser(id,name,password,sex,age,email,school,shenhe);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(flag==1)request.getRequestDispatcher("/servlet/adUserServlet").forward(request,response);
        else response.sendRedirect("../salarypageadmin.jsp?error=yes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
