<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import="utils.Register"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>点餐系统后台管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script src="js/jquery-1.7.js" language="javascript" type="text/javascript"></script><!-- 必须放第一个  -->
		<script src="js/admin_frame.js" language="javascript" type="text/javascript"></script>
		<link href="css/admin_frame.css" rel="stylesheet" type="text/css" />
		
		
		<link href="css/pagination.css" rel="stylesheet" type="text/css" />
		<link href="css/zTreeStyle.css" rel="stylesheet" type="text/css" />
		<link href="js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />
		
		<script src="js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
		<script src="js/jquery.pagination.js" language="javascript" type="text/javascript"></script>
		<script src="js/jquery.ztree.core-3.5.js" language="javascript" type="text/javascript"></script>


	</head>
	
	<%if(Register.isRegister())
		{
			response.sendRedirect("table/getTableList");
		}
	else
	{
		response.sendRedirect("login.jsp");

	}
	//request.getRequestDispatcher("server resource").forward(request, response);}
	%>
	<body class="showmenu">
		<div class="pagemask"></div>
		<iframe class="iframemask"></iframe>
		<div class="head">
			<div class="top_logo">
				<img src="images/logo.jpg" alt="Logo" />
			</div> 
			<div class="top_link">
				<ul>
					<li class="welcome">
						 &nbsp;
						 <A style="color:blue;">
						 		<s:property value="#request.session.adminuser.username"/>
						 </A>,欢迎您
					</li>

					<li>
						<a href="../building.jsp" target="_blank">网站首页</a>
					</li>
					<li>
						<a href="userexitsystem" target="_top">[退出]</a>
					</li>

				</ul>
			</div>

		</div>
		<!-- header end -->
		<div class="left">
			<div class="menu" id="menu" style="height:100%;">

				<!-- 系统公告 -->
				<div id="items_announcement"> 
					<dl id="dl_items_1_1">
						<!-- <dt>
							车辆跟踪导航
						</dt> 
						<dd>
							<ul>
								<li>
									<a href="vehicleMap/main.jsp" target="main">车辆跟踪</a>
								</li>
								<li>
									<a href="vehicleMap/getAllVehicleList" target="main">车辆历史轨迹回放</a>
								</li>

							</ul>
						</dd>
					</dl>
					<dl id="dl_items_2_1">
						<dt>
							警报设置
						</dt>
						<dd>
							<ul>
								<li>
									<a href="vehicleAlarm/getAllVehicleList" target="main">超速报警设置</a>
								</li>
								<!--<li>
									  <a href="system/new_announcement.jsp" target="main">禁入报警设置</a>
								</li>

							</ul>
						</dd>-->
					</dl>
					<dl id="dl_items_3_1">
						<dt>
							信息管理
						</dt>
						<dd>
							<ul>
								<li>
									<!--  <a href="vehicle/vehicleManagePage?vehicleForm.query=false" target="main">管理员信息管理</a>-->
								</li>
								<li>
									<a href="waiter/waiterManagePage?waiterForm.query=false" target="main">服务员信息管理</a>
								</li>
								<li>
									<a href="table/tablesManagePage?tablesForm.query=false" target="main">餐桌信息管理</a>
								</li>
								<li>
									<a href="menu/menuManagePage?menuForm.query=false" target="main">菜谱信息管理</a>
								</li>
								<li>
									<a href="menuType/menuTypeManagePage?menuTypeForm.query=false" target="main">菜谱类型管理</a>
								</li>
							</ul>
						</dd>
					</dl>
					<dl id="dl_items_4_1">
						<dt>
							帮助
						</dt>
						<dd>
							<ul>
								<li>
									<a  target="main">帮助文档</a>
								</li>
								<li>
									<a  target="main">关于</a>
								</li>
							</ul>
						</dd>
					</dl>							
				</div>
			</div>
		</div>
		<div class="right" style="height:90%;">
			<div class="main">
				<iframe id="main" name="main" frameborder="0"
					src="menu/bill/menu.jsp"></iframe>
					
			</div>
		</div>

	</body>
</html>