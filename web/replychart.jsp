<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>留学通信息管理系统</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- Google fonts - Poppins -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.png">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <script type="text/javascript" src="js/Functions.js"></script>
</head>
<body>
<%
    ArrayList info=(ArrayList)application.getAttribute("info");
    ArrayList salaryinfo=(ArrayList)application.getAttribute("salaryinfo");
%>
<div class="page">
    <!-- Main Navbar-->
    <header class="header">
        <nav class="navbar">
            <!-- Search Box-->
            <div class="search-box">
                <button class="dismiss"><i class="icon-close"></i></button>
                <form id="searchForm" method="post" action="servlet/adSearchUserInfoServlet" role="search">
                    <input name="sss" type="search" placeholder="输入id或名字查找员工工资信息" class="form-control">
                </form>
            </div>
            <div class="container-fluid">
                <div class="navbar-holder d-flex align-items-center justify-content-between">
                    <!-- Navbar Header-->
                    <div class="navbar-header">
                        <!-- Navbar Brand --><a href="index.html" class="navbar-brand d-none d-sm-inline-block">
                        <div class="brand-text d-none d-lg-inline-block"><strong>留学通信息管理系统</strong></div>
                        <div class="brand-text d-none d-sm-inline-block d-lg-none"><strong>BD</strong></div></a>
                        <!-- Toggle Button--><a id="toggle-btn" href="#" class="menu-btn active"><span></span><span></span><span></span></a>
                    </div>
                    <!-- Navbar Menu -->
                    <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                        <!-- Search-->
                        <li class="nav-item d-flex align-items-center"><a id="search" href="#"><i class="icon-search"></i></a></li>
                        <!-- Notifications-->
                        <li class="nav-item dropdown"> <a id="notifications" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-bell-o"></i><span class="badge bg-red badge-corner">12</span></a>
                            <ul aria-labelledby="notifications" class="dropdown-menu">
                                <li><a rel="nofollow" href="#" class="dropdown-item">
                                    <div class="notification">
                                        <div class="notification-content"><i class="fa fa-envelope bg-green"></i>你有6条新消息 </div>
                                        <div class="notification-time"><small>4 分钟前</small></div>
                                    </div></a></li>
                                <li><a rel="nofollow" href="#" class="dropdown-item">
                                    <div class="notification">
                                        <div class="notification-content"><i class="fa fa-upload bg-orange"></i>系统消息</div>
                                        <div class="notification-time"><small>4 分钟前</small></div>
                                    </div></a></li>
                                <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong>浏览所有未读信息                                           </strong></a></li>
                            </ul>
                        </li>
                        <!-- Messages                        -->
                        <li class="nav-item dropdown"> <a id="messages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-envelope-o"></i><span class="badge bg-orange badge-corner">10</span></a>
                            <ul aria-labelledby="notifications" class="dropdown-menu">

                                <li><a rel="nofollow" href="#" class="dropdown-item d-flex">
                                    <div class="msg-profile"> <img src="img/avatar-2.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                    <div class="msg-body">
                                        <h3 class="h5">郭金鑫</h3><span>有发送给你的消息</span>
                                    </div></a></li>

                                <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong>浏览所有消息   </strong></a></li>
                            </ul>
                        </li>
                        <!-- Logout    -->
                        <li class="nav-item"><a href="javascript:logout()" class="nav-link logout"> <span class="d-none d-sm-inline">注销</span><i class="fa fa-sign-out"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="page-content d-flex align-items-stretch">
        <!-- Side Navbar -->
        <nav class="side-navbar">
            <!-- Sidebar Header-->
            <div class="sidebar-header d-flex align-items-center">
                <div class="title">
                    <h1 class="h4"><%=info.get(1)%><span><h6>，欢迎您！</h6></span></h1>
                    <p><%=info.get(6)%>部  管理员</p>
                </div>
            </div>
            <!-- Sidebar Navidation Menus--><span class="heading">管理菜单</span>
            <ul class="list-unstyled">
                <li><a href="adminpage.jsp"> <i class="icon-home"></i>个人信息 </a></li>
                <li><a href="servlet/getDepartmentInfoServlet"> <i class="icon-grid"></i>部门信息 </a></li>
                <li><a href="servlet/adSchoolServlet"> <i class="icon-padnote"></i>学校管理 </a></li>
                <li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>帖子分类管理 </a>
                    <ul id="exampledropdownDropdown" class="collapse list-unstyled " >
                        <script>
                            if('<%=info.get(6)%>'=='房屋租赁'){
                                document.write("<li><a  href=\"servlet/adTieziChartServlet\" id=\"it\">房屋租赁贴</a></li><li><a  href=\"javascript:alert(\'没有权限！\')\" id=\"sale\">车辆租赁贴</a></li><li><a  href=\"javascript:alert('没有权限！')\" id=\"logistics\">攻略求助贴</a></li><li><a  href=\"javascript:alert('没有权限！')\" id=\"logistics\">点评贴</a></li>")
                            }
                            else if('<%=info.get(6)%>'=='车辆租赁'){
                                document.write("<li><a  href=\"javascript:alert('没有权限！')\" id=\"it\">房屋租赁贴</a></li><li><a  href=\"servlet/adTieziChartServlet\" id=\"sale\">车辆租赁贴</a></li><li><a  href=\"javascript:alert('没有权限！')\" id=\"logistics\">攻略求助贴</a></li><li><a  href=\"javascript:alert('没有权限！')\" id=\"logistics\">点评贴</a></li>")
                            }
                            else if('<%=info.get(6)%>'=='攻略求助'){
                                document.write("<li><a  href=\"javascript:alert('没有权限！')\" id=\"it\">房屋租赁贴</a></li><li><a  href=\"javascript:alert('没有权限！')\"id=\"sale\">车辆租赁贴</a></li><li><a  href=\"servlet/adTieziChartServlet\" id=\"logistics\">攻略求助贴</a></li><li><a  href=\"javascript:alert('没有权限！')\" id=\"logistics\">点评贴</a></li>")
                            }
                            else {
                                document.write("<li><a  href=\"javascript:alert('没有权限！')\" id=\"it\">房屋租赁贴</a></li><li><a  href=\"javascript:alert('没有权限！')\"id=\"sale\">车辆租赁贴</a></li><li><a  href=\"javascript:alert('没有权限！')\" id=\"logistics\">攻略求助贴</a></li><li><a  href=\"servlet/adTieziChartServlet\" id=\"logistics\">点评贴</a></li>")
                            }
                        </script>
                    </ul></li>
                <li><a href="servlet/adUserServlet"> <i class="icon-padnote"></i>用户管理 </a></li>
                <li class="active"><a href="servlet/adReplyServlet"> <i class="icon-padnote"></i>帖子评论管理 </a></li>
                <li><a href="servlet/SalaryStatisticServlet"> <i class="fa fa-bar-chart"></i>统计</a><li/>
            </ul>
        </nav>

        <div class="content-inner">
            <!-- Page Header-->
            <header class="page-header">
                <div class="container-fluid">
                    <h2 class="no-margin-bottom">帖子评论管理</h2>
                </div>
            </header>
            <div class="card-body">
                <div class="table-responsive">
                    <%--<button type="button" class="btn btn-primary" onclick="deletesalaryinfo();">删除评论信息</button>--%>
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr style="border-top:#a3a3a3 solid 2px;">
                            <th rowspan="2" style="vertical-align:middle;text-align:center;border-left:#a3a3a3 solid 2px;"> </th>
                            <th rowspan="2" style="vertical-align: middle;text-align:top;border-left:#a3a3a3 solid 2px;">帖子ID</th>
                            <th rowspan="2" style="vertical-align: middle;text-align:top;">发帖人ID</th>
                            <th colspan="1" style="vertical-align: middle;text-align:center;border-left:#a3a3a3 solid 2px;border-right:#a3a3a3 solid 2px;">评论用户姓名</th>
                            <th colspan="1" style="vertical-align: middle;text-align:center;border-right:#a3a3a3 solid 2px;border-right:#a3a3a3 solid 2px;">评论用户ID</th>
                            <th rowspan="1" style="vertical-align: middle;text-align:top;border-right:#a3a3a3 solid 2px;">评论内容</th>
                            <th rowspan="1" style="vertical-align: middle;text-align:top;border-right:#a3a3a3 solid 2px;">时间</th>

                        </tr>
                        <%--<tr>--%>
                        <%--<th>基本</th>--%>
                        <%--<th>津贴</th>--%>
                        <%--<th style="border-right:#a3a3a3 solid 2px;">提成</th>--%>
                        <%--<th>房租</th>--%>
                        <%--<th>水电</th>--%>
                        <%--<th>个人所得税</th>--%>
                        <%--</tr>--%>
                        </thead>
                        <tbody>
                        <%
                            int count =salaryinfo.size()/7;
                            int n=0;
                            for(int i=0;i<=count;i++){%>
                        <tr>
                            <form method="post" action="servlet/adUpdateUserServlet" >
                                <td> <input name="Fruitss" type="checkbox" value="<%=salaryinfo.get(n)%>" style="BACKGROUND-COLOR: transparent;border:0; "size="2"/></td>
                                <td><input name="sid" readonly="readonly" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="6"></td>
                                <td ><input name="sname" onmouseover="this.select()" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="8"></td>
                                <td ><input name="spassword" onmouseover="this.select()" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="8"></td>
                                <td ><input name="ssex" onmouseover="this.select()" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="8"></td>
                                <td ><input name="sage" onmouseover="this.select()" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="8"></td>
                                <td ><input name="semail" onmouseover="this.select()" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="8"></td>
                                <%--<td ><input name="expense" onmouseover="this.select()" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="8"></td>--%>
                                <%--<td ><input name="sum" onmouseover="this.select()" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="8"></td>--%>
                                <%--<td><button type="submit" class="btn btn-primary" onclick="return change();">保存修改</button></td>--%>
                            </form>
                                <%
                              }
                                %>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Page Footer-->

        </div>
    </div>
</div>
<!-- JavaScript files-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/popper.js/umd/popper.min.js"> </script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="vendor/chart.js/Chart.min.js"></script>
<script src="vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="js/charts-custom.js"></script>
<!-- Main File-->
<script src="js/front.js"></script>
<script>
    function deletesalaryinfo(){
        var a=confirm("确定删除所选评论信息？");
        if(a==true){
            var arr=Array();
            var elem=document.getElementsByName("Fruitss");
            for(var i=0;i<elem.length;i++){
                if(elem[i].checked){
                    arr.push(elem[i].value);
                }
            }
            var str=arr.join("-");
            window.location.href="servlet/adDeleteUserServlet?ids="+str;
        }
        else
            window.location.reload();
    }
</script>
</body>
</html>