import com.salary.FunctionsOfMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "adAddReplyServlet")
public class adAddReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String rtzid=request.getParameter("rtieziid");
        String tzuserid=request.getParameter("tzuserid");
        String ruserid=request.getParameter("ruserid");
        String rusername=request.getParameter("rusername");
        String rcontent=request.getParameter("rcontent");
        String rtime=request.getParameter("rtime");
        ArrayList info=new ArrayList();
        info.add(rtzid);
        info.add(tzuserid);
        info.add(rusername);
        info.add(ruserid);
        info.add(rcontent);
        info.add(rtime);
        FunctionsOfMysql func=new FunctionsOfMysql();
        int flag=0;
        try {
            flag=func.adAddreply(info);
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
