import com.salary.FunctionsOfMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "adAddUserServlet")
public class adAddUserServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String school=request.getParameter("school");
        String password=request.getParameter("password");
        ArrayList info=new ArrayList();
        info.add(name);
        info.add(phone);
        info.add(school);
        info.add(password);
        FunctionsOfMysql func=new FunctionsOfMysql();
        int flag=0;
        try {
            flag=func.adAddUser(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getOutputStream().print(flag);

            ;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
