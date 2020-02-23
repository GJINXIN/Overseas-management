<%--
  Created by IntelliJ IDEA.
  User: xpl
  Date: 2019/1/8
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>登陆</title>
    <%@page import="com.salary.FunctionsOfMysql" %>
    <script type="text/javascript" src="js/Functions.js">
    </script>
</head>
<body>

<table border="1" cellspacing="0" cellpadding="10" align="center">
    <tr>
        <td>
            <form action="servlet/LoginServlet" method="post" onsubmit="return LoginCheck(this);">
                用户名<input type="text" name="name"/></br></br>
                密  码  <input type="password" name="password"/></br></br>
                登陆选择
                <input type="radio" name="type" value="staff" checked/>用户
                <input type="radio" name="type" value="admin"/>部门主管</br></br>
                <input type="submit" value="登录" name="submit" />
            </form>
        </td>
    </tr>
</table>
</body>
</html>
<script>
    var f='<%=request.getParameter("error")%>'
    if(f=='yes')
        alert("用户账号不存在或密码错误");
</script>