import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.salary.*;

@WebServlet(name = "adLoginServlet")

public class adLoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String id=request.getParameter("name");
        String pw=request.getParameter("password");
        String type=request.getParameter("type");
        System.out.println(id);
        System.out.println(pw);
        FunctionsOfMysql functionsOfMysql = new FunctionsOfMysql();
        ArrayList info=new ArrayList();
        int flag=0; //是否登录标志
        ServletContext application=getServletContext();
        if(type.equals("user")) {
            try {
                flag = functionsOfMysql.UserLogin(id, pw);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                flag = functionsOfMysql.AdminLogin(id, pw);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        application.setAttribute("flag",flag);
        System.out.println("im here");
        if(flag==1){ //登陆成功
            if(type.equals("user")){
                try {
                    info=functionsOfMysql.getUserInfoByIdByStaff(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    info=functionsOfMysql.getAdminInfoByIdByAdmin(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            info.add(type);
            application.setAttribute("info",info);
//            response.getOutputStream().print(JSON.toJSONString(flag));

            int size = info.size();

            Map<String,Object> m2 = new HashMap<String, Object>();
            m2.put("id", info.get(0));
            m2.put("name", info.get(1));
            m2.put("password", info.get(2));
            if(info.get(3)==null){m2.put("sex","");}
            else m2.put("sex", info.get(3));
            if(info.get(4)==null){m2.put("age","");}
            else m2.put("age", info.get(4));
            if (info.get(5)==null){m2.put("email","");}
            else m2.put("email",info.get(5));
            m2.put("school",info.get(6));
            m2.put("flag","1");
            System.out.println(JSON.toJSON(m2));
            JSONObject json = new JSONObject(m2);
            response.getWriter().print(json.toString());


////            response.getOutputStream().print(JSON.toJSONString(str));

        }
        else {
            Map<String,Object> m2 = new HashMap<String, Object>();
            m2.put("id", "");
            m2.put("name", "");
            m2.put("password", "");
            m2.put("sex", "");
            m2.put("age", "");
            m2.put("email","");
            m2.put("school","");
            m2.put("flag",flag);
            JSONObject json = new JSONObject(m2);
            response.getWriter().print(json.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
