import com.salary.FunctionsOfMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ASUS on 2019/1/13.
 */
@WebServlet(name = "DeleteSalaryServlet")
public class DeleteSalaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String str=request.getParameter("ids");
        String[] ids=str.split("-");
        FunctionsOfMysql func=new FunctionsOfMysql();
        try {
            func.DeleteSalary(ids);
        }catch(SQLException e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/servlet/SalaryServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
