import javax.servlet.http.HttpServlet;
import com.salary.FunctionsOfMysql;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UpdateOneSchoolServlet")
public class UpdateOneSchoolServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String tid=request.getParameter("stid");
        String id=request.getParameter("sid");
        String title=request.getParameter("stitle");
        String content=request.getParameter("scontent");
        String img=request.getParameter("simg");
        String time=request.getParameter("stime");
        String school=request.getParameter("sschool");
        FunctionsOfMysql func=new FunctionsOfMysql();
        int flag=0;
        try{
            flag=func.adupdateSchoolInfo(tid,id,title,content,img,time,school);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(flag==1)request.getRequestDispatcher("/servlet/adSchoolServlet").forward(request,response);
        else response.sendRedirect("/school.jsp?error=yes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
