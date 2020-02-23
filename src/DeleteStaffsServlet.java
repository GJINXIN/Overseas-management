import com.salary.FunctionsOfMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteStaffsServlet")
public class DeleteStaffsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String str=request.getParameter("ids");
        String[] ids=str.split("-");
        for(int i=0;i<ids.length;i++)
            System.out.println(ids[i]);
        FunctionsOfMysql func=new FunctionsOfMysql();
        try{
            func.DeleteStaffs(ids);
        }catch (SQLException e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/servlet/StaffChartServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
