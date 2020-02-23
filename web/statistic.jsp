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
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
    <script type="text/javascript" src="js/jscharts.js"></script>
  </head>
  <body>
    <%
      ArrayList info=(ArrayList) application.getAttribute("info");
      ArrayList salarystatisticinfo=(ArrayList)application.getAttribute("salarystatisticinfo");
      ArrayList dates=(ArrayList)application.getAttribute("dates");
    %>
    <div class="page">
      <!-- Main Navbar-->
      <header class="header">
        <nav class="navbar">
          <!-- Search Box-->
          <div class="search-box">
            <button class="dismiss"><i class="icon-close"></i></button>
            <form id="searchForm" action="#" role="search">
              <input type="search" placeholder="What are you looking for..." class="form-control">
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
                          <div class="notification-content"><i class="fa fa-envelope bg-green"></i>你有6条新消息</div>
                          <div class="notification-time"><small>4 分钟前</small></div>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item"> 
                        <div class="notification">
                          <div class="notification-content"><i class="fa fa-upload bg-orange"></i>系统消息</div>
                          <div class="notification-time"><small>4 分钟前</small></div>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong>浏览所有未读信息                                            </strong></a></li>
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
            <li ><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>帖子分类管理 </a>
              <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
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
            <li><a href="servlet/adReplyServlet"> <i class="icon-padnote"></i>帖子评论管理 </a></li>
            <li class="active"><a href="servlet/SalaryStatisticServlet"> <i class="fa fa-bar-chart"></i>统计</a><li/>
          </ul>
          </nav>
        <div class="content-inner">
          <!-- Page Header-->
          <header class="page-header">
            <div class="container-fluid">
              <h2 class="no-margin-bottom"><%=info.get(6)%>统计概览</h2>
            </div>
          </header>
          <!-- Charts Section-->
          
           
                <!-- Line Charts-->
                <div class="col-lg-12">
                  <div class="line-chart-example card">
                 
                    <div class="card-body">
                       <div class="table-responsive">   
                        <table class="table table-striped table-sm">
                          <thead>
                            <tr style="border-top:#a3a3a3 solid 2px;">
                              <th style="vertical-align: middle;text-align:center;border-left:#a3a3a3 solid 2px;">排序</th>
                              <th  style="vertical-align: middle;text-align:center;border-left:#a3a3a3 solid 2px;border-right:#a3a3a3 solid 2px;">学校名&nbsp;&nbsp;</th>
                              <th style="vertical-align: middle;text-align:center;border-left:#a3a3a3 solid 2px;border-right:#a3a3a3 solid 2px;">发帖数量&nbsp;&nbsp;</th>
							  
                            </tr>
							
                          </thead>
                          <tbody>
                          <%
                            int count =salarystatisticinfo.size()/2;
                            int n=0;
                            int a=0;
                            for(int i=0;i<count;i++){%>
                          <tr>
                            <form>
                              <th scope="row"><input disabled="disabled" value="<%=++a%>" style="BACKGROUND-COLOR: transparent;border:0;vertical-align: middle;text-align:center;" ></th>
                              <td ><input name="sid" disabled="disabled" value="<%=salarystatisticinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;vertical-align: middle;text-align:center;" ></td>
                              <td ><input name="sname" disabled="disabled" value="<%=salarystatisticinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;vertical-align: middle;text-align:center;" ></td>
                              <%--<td ><input name="ssalary" disabled="disabled" value="<%=salarystatisticinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;vertical-align: middle;text-align:center;" ></td>--%>
                              <td ></td>
                              <td></td>
                            </form>
                              <%
                              }
                                %>

                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>




                <%--<!-- Bar Charts-->--%>
                  <%--<div class="col-lg-12" ">--%>
                    <%--<div class="bar-chart-example card">--%>
                  <%--<div id="columnchart">loding chart</div>--%>
                  <%--<script>--%>
                    <%--var mydate=new Array(['<2000元',<%=dates.get(0)%>],['2000~4999元',<%=dates.get(1)%>],['5000~7999元',<%=dates.get(2)%>],['8000~10999元',<%=dates.get(3)%>],['11000~13999元',<%=dates.get(4)%>],['14000~16999元',<%=dates.get(5)%>],['17000~19999元',<%=dates.get(6)%>],['>20000元',<%=dates.get(7)%>]);--%>
                    <%--var myChart=new JSChart('columnchart','bar');--%>
                    <%--myChart.setDataArray(mydate);--%>
                    <%--myChart.setTitle('<%=info.get(6)%>部员工工资分布柱状图');--%>
                    <%--myChart.setSize(800,300);--%>
                    <%--myChart.setAxisNameX('区间');--%>
                    <%--myChart.setAxisNameY('人数/个');--%>
                    <%--myChart.setAxisValuesDecimals(0);--%>
                    <%--myChart.setBarSpacingRatio(65);//柱子间隔--%>
                    <%--myChart.setTitleColor('#0F0F0F');--%>
                    <%--myChart.setTitleFontSize(14);--%>
                    <%--myChart.draw();--%>
                  <%--</script>--%>
                  <%--</div>--%>
                  <%--</div>--%>
                <!-- Pie Chart -->
                  <%--<div class="col-lg-12" ">--%>
                    <%--<div class="bar-chart-example card">--%>
                <%--<div id="piechart">loding chart</div>--%>
                  <%--<script>--%>
                    <%--var myChart=new JSChart('piechart','pie');--%>
                    <%--myChart.setDataArray([['A',<%=dates.get(0)%>],['B',<%=dates.get(1)%>],['C',<%=dates.get(2)%>],['D',<%=dates.get(3)%>],['E',<%=dates.get(4)%>],['F',<%=dates.get(5)%>],['G',<%=dates.get(6)%>],['H',<%=dates.get(7)%>]]);--%>
                    <%--myChart.colorize(['#99CDFB','#3366FB','#0000FA','#F8CC00','#F89900','#F76600','#9c9c9c', '#C32121']);--%>
                    <%--myChart.setTitle('<%=info.get(6)%>部员工工资分布饼图');--%>
                    <%--myChart.setSize(800,400);--%>
                    <%--myChart.setTitleColor('#0F0F0F');--%>
                    <%--myChart.setTitleFontSize(14);--%>
                    <%--myChart.setPieRadius(150);--%>
                    <%--myChart.setPieValuesColor('#FFFFFF');--%>
                    <%--myChart.setShowXValues(false);--%>
                    <%--myChart.setLegend('#99CDFB', '<2000元');--%>
                    <%--myChart.setLegend('#3366FB', '2000~4999元');--%>
                    <%--myChart.setLegend('#0000FA', '5000~7999元');--%>
                    <%--myChart.setLegend('#F8CC00', '8000~10999元');--%>
                    <%--myChart.setLegend('#F89900', '11000~13999元');--%>
                    <%--myChart.setLegend('#F76600', '14000~16999元');--%>
                    <%--myChart.setLegend('#9c9c9c','17000~19999元');--%>
                    <%--myChart.setLegend('#C32121','>20000元');--%>
                    <%--myChart.setLegendShow(true);--%>
                    <%--myChart.setLegendFontFamily('Times New Roman');--%>
                    <%--myChart.setLegendFontSize(10);--%>
                    <%--myChart.setLegendPosition(700, 80);--%>
                    <%--myChart.setPieAngle(30);--%>
                    <%--myChart.set3D(true);--%>
                    <%--myChart.draw();--%>
                  <%--</script>--%>
                  <%--</div>--%>
                  <%--</div>--%>
          </section>
          
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
    </body>
</html>