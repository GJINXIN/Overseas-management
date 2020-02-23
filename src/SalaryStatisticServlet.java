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

@WebServlet(name = "SalaryStatisticServlet")
public class SalaryStatisticServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        ServletContext application=getServletContext();
        ArrayList info= (ArrayList) application.getAttribute("info");
        FunctionsOfMysql func=new FunctionsOfMysql();
        ArrayList salaryinfo= null;
        ArrayList date=new ArrayList();
        try {
            salaryinfo = func.getSalaryStaticByAdmin((String) info.get(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList salarystatisticinfo=new ArrayList();
//        int count=salaryinfo.size()/9;
//        int sum=0;
//        int p=count;
//        double avg=0,s=0;
//        int aa=0,bb=0,cc=0,dd=0,ee=0,ff=0,gg=0,hh=0;
//        for(int i=0;i<count;i++){
//            salarystatisticinfo.add(salaryinfo.get(i*9));
//            salarystatisticinfo.add(salaryinfo.get(i*9+1));
//            salarystatisticinfo.add(salaryinfo.get(i*9+8));
//            if(salaryinfo.get(i*9+8)!=null) {
//                int temp=Integer.parseInt((String) salaryinfo.get(i * 9 + 8));
//                sum += temp;
//                if(temp<2000)aa++;
//                else if(temp<5000)bb++;
//                else if(temp<8000)cc++;
//                else if(temp<11000)dd++;
//                else if(temp<14000)ee++;
//                else if(temp<17000)ff++;
//                else if(temp<20000)gg++;
//                else hh++;
//            }
//            else
//                p--;
//        }
//        avg=sum/p;
//        for(int i=0;i<count;i++){
//            if(salaryinfo.get(i*9+8)!=null)
//                s=s+(Integer.parseInt(salaryinfo.get(i*9+8).toString())-avg)*(Integer.parseInt(salaryinfo.get(i*9+8).toString())-avg);
//        }
//        s=s/p;
//        salarystatisticinfo.add(String.valueOf(avg));
//        salarystatisticinfo.add(String.valueOf(s));
//        date.add(aa);
//        date.add(bb);
//        date.add(cc);
//        date.add(dd);
//        date.add(ee);
//        date.add(ff);
//        date.add(gg);
//        date.add(hh);
//        application.setAttribute("dates",date);
        application.setAttribute("salarystatisticinfo",salaryinfo);
        request.getRequestDispatcher("/statistic.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
