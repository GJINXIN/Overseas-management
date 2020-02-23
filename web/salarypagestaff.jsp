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
    <title>企业工资管理系统</title>
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
            <form id="searchForm" action="#" role="search">
              <input type="search" placeholder="What are you looking for..." class="form-control">
            </form>
          </div>
          <div class="container-fluid">
            <div class="navbar-holder d-flex align-items-center justify-content-between">
              <!-- Navbar Header-->
              <div class="navbar-header">
                <!-- Navbar Brand --><a href="index.html" class="navbar-brand d-none d-sm-inline-block">
                  <div class="brand-text d-none d-lg-inline-block"><strong>企业工资管理系统</strong></div>
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
                        <div class="msg-profile"> <img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">柴立轩</h3><span>有发送给你的消息</span>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-2.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">郭金鑫</h3><span>有发送给你的消息</span>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-3.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">谢沛良</h3><span>有发送给你的消息</span>
                        </div></a></li>
					<li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-4.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">王屿涵</h3><span>有发送给你的消息</span>
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
              <h1 class="h4"><%=info.get(1)%><span><h6>，欢迎您！</h6></h1>
              <p><%=info.get(6)%>部  主管</p>
            </div>
          </div>
          <!-- Sidebar Navidation Menus--><span class="heading">管理菜单</span>
          <ul class="list-unstyled">
                    <li><a href="staffpage.jsp"> <i class="icon-home"></i>个人信息 </a></li>
                    <li><a href="servlet/getDepartmentInfoServlet"> <i class="icon-grid"></i>部门信息 </a></li>
                    <li class="active"><a href="javascript:window.location.reload()"> <i class="icon-padnote"></i>工资查询 </a></li>
          </ul></nav>
        <div class="content-inner">
          <!-- Page Header-->
          <header class="page-header">
            <div class="container-fluid">
              <h2 class="no-margin-bottom">员工个人工资信息</h2>
            </div>
          </header>
                     <div class="card-body">
                      <div class="table-responsive">   
                        <table class="table table-striped table-sm">
                          <thead>
                            <tr style="border-top:#a3a3a3 solid 2px;">
                              <th rowspan="2" style="vertical-align: middle;text-align:top;border-left:#a3a3a3 solid 2px;">ID</th>
                              <th rowspan="2" style="vertical-align: middle;text-align:top;">姓名</th>
                              <th colspan="3" style="vertical-align: middle;text-align:center;border-left:#a3a3a3 solid 2px;border-right:#a3a3a3 solid 2px;">工资</th>
                              <th colspan="3" style="vertical-align: middle;text-align:center;border-right:#a3a3a3 solid 2px;border-right:#a3a3a3 solid 2px;">扣除</th>
							  <th rowspan="2" style="vertical-align: middle;text-align:top;border-right:#a3a3a3 solid 2px;">实发</th>
							  
                            </tr>
							<tr>
							  <th>基本</th>
							  <th>津贴</th>
							  <th style="border-right:#a3a3a3 solid 2px;">提成</th>
							  <th>房租</th>
							  <th>水电</th>
                              <th>个人所得税</th>
							</tr>
                          </thead>
                          <tbody>
                          <%
                              int n=0;
                              %>
                            <tr>
                                <form method="post"  >
                                    <th scope="row"><input name="sid" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></th>
                                    <td ><input name="sname" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                    <td ><input name="ssalary" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                    <td ><input name="bonus" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                    <td ><input name="duty" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                    <td ><input name="rent" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                    <td ><input name="tax" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                    <td ><input name="expense" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                    <td ><input name="sum" disabled="disabled" value="<%=salaryinfo.get(n++)%>" style="BACKGROUND-COLOR: transparent;border:0;" size="10"></td>
                                </form>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>

          
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