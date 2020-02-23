import com.salary.FunctionsOfMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "adDeleteowntieziServlet")
public class adDeleteowntieziServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String str=request.getParameter("tid");
        FunctionsOfMysql func=new FunctionsOfMysql();
        try {
            func.adDeleteowntiezi(str);
        }catch(SQLException e){
            e.printStackTrace();
        }
        response.getOutputStream().print(1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
