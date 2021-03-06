import com.salary.FunctionsOfMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "adAddOneTieziServlet")
public class adAddOneTieziServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String id=request.getParameter("sid");
        String title=request.getParameter("stitle");
        String content=request.getParameter("scontent");
        String img=request.getParameter("simg");
        String time=request.getParameter("stime");
        String school =request.getParameter("sschool");
        String typle=request.getParameter("styple");
        ArrayList info=new ArrayList();
        info.add(id);
        info.add(title);
        info.add(content);
        info.add(img);
        info.add(time);
        info.add(school);
        info.add(typle);
        FunctionsOfMysql func=new FunctionsOfMysql();
        int flag=0;
        try {
            flag=func.adAddOneTiezi(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flag==1)request.getRequestDispatcher("/servlet/adTieziChartServlet").forward(request,response);
        else
            ;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
