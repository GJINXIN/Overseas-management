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

@WebServlet(name = "adUserServlet")
public class adUserServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            ServletContext application = getServletContext();
            ArrayList info = (ArrayList) application.getAttribute("info");
            ArrayList SalaryInfo=new ArrayList();
            String id=(String)info.get(0);
            String department=(String)info.get(6);
            FunctionsOfMysql func=new FunctionsOfMysql();
            if(info.get(7).equals("user")){
                try{
                    SalaryInfo=func.getUserbyuser(id);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                application.setAttribute("salaryinfo",SalaryInfo);
                request.getRequestDispatcher("/salarypagestaff.jsp").forward(request,response);
            }
            else{
                try{
                    SalaryInfo=func.getuserByAdmin(department);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                application.setAttribute("salaryinfo",SalaryInfo);
                request.getRequestDispatcher("/salarypageadmin.jsp").forward(request,response);
            }
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
        }
}
