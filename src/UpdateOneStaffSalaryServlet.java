import com.salary.FunctionsOfMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ASUS on 2019/1/11.
 */
@WebServlet(name = "UpdateOneStaffSalaryServlet")
public class UpdateOneStaffSalaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String id=request.getParameter("sid");
        String ssalary=request.getParameter("ssalary");
        String bonus=request.getParameter("bonus");
        String duty=request.getParameter("duty");
        String rent=request.getParameter("rent");
        String tax=request.getParameter("tax");
        String expense=request.getParameter("expense");
        int sum=0;
        sum+=Integer.parseInt(ssalary)+Integer.parseInt(bonus)+Integer.parseInt(duty)-Integer.parseInt(rent)-Integer.parseInt(tax)
                -Integer.parseInt(expense);

        FunctionsOfMysql func=new FunctionsOfMysql();
        int flag=0;
        try{
            flag=func.updateOneStaffSalary(id,ssalary,bonus,duty,rent,tax,expense,String.valueOf(sum));
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(flag==1)request.getRequestDispatcher("/servlet/SalaryServlet").forward(request,response);
        else response.sendRedirect("/salarypageadmin.jsp?error=yes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
