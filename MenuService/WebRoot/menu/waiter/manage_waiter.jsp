<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>管理员用户信息管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script src="../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
		<link href="../css/admin_main.css" rel="stylesheet" type="text/css" />
		<link href="../css/pagination.css" rel="stylesheet" type="text/css" />
		<link href="../css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
		<link href="../js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />

		<script src="../js/jquery.pagination.js" language="javascript" type="text/javascript"></script>
		<script src="../js/jquery.loadmask.min.js" language="javascript" type="text/javascript"></script>
		<script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
		<script src="../js/tools.js" language="javascript" type="text/javascript"></script>
	</head>
	<body>
		<div class="mtitle">
			<h1>
				服务员信息管理
			</h1>
		</div>
		<!--  搜索表单  -->
		<form action="<s:url />" id="selectForm" name="selectForm">
			<table width='100%' border='0' cellpadding='1' cellspacing='1'
				align="center" style="margin-top: 8px">

				<tr bgcolor='#f8f8f8'>
					<td align='center'>
						<table border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td>
									用户名：
								</td>
								<td width='160'>
									<input id="waiter_Name" name='waiter.waiter_Name' type='text'
										class="txt" style='width: 150px'
										value="<s:property value='#request.waiterForm.waiter_Name' />" />
									<!-- 隐藏属性  说明 是 要查询数据 -->
									<input id="waiterForm.query" name='waiterForm.query'
										type='hidden' value="true" />
								</td>
								<td>
									<button class="btn1"
										onclick="window.location.href = creatUrlByForm(document.getElementsByTagName('FORM')[0]);">
										搜索
									</button>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<div class="tform">
			<button type='button' class="btn1" onclick="location.href='../menu/waiter/add_waiter.jsp'">
				新增服务员
			</button>
			<button type='button' class="btn1" onclick="deleteWaiter();">
				删除服务员
			</button>
		</div>


		<table class="tlist" id="recordlist">
			<thead>
				<tr class="title">
					<th align="left" style="width:10">
						全选
						<input type="checkbox" onclick="SelectAllCheckBox('wait_checked',this)" class="allCheck" />
					</th>
					<th align="left" style="width:10">
						序号
					</th>
					<th align="left" style="width:10">
						编号
					</th>
					<th align="left" style="width:15">
						姓名
					</th>
					<th align="left" style="width:15">
						性别
					</th>
					<th align="left" style="width:15">
						年龄
					</th>
					<th align="left" style="width:15">
						电话
					</th>
					<th align="left" style="width:10">
						操作
					</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div class="pagelist" style="height:25px">
			<div id="Pagination" style="float: right"></div>
		</div>

		
	</body>

<script type="text/javascript" >
	// 分页每页信息条数   
  	var items_per_page=5;  
  	//页号
  	var pageIndex =0;	
  	//查询到 数据条数   adminuser_manage.action 返回的	
	var recordtotal =<s:property value="#request.waiterCount"/>; 
 	//查询到 数据条数时 并返回了  是否是 有查询条件  action_recordtotal.action 返回的
   	var query ="<s:property value="#request.waiterForm.query"/>";  
   
	$(document).ready(function(){
	    
	    //判断返回的数据条数是否为0，如果为零则提示”无数据“
		if(recordtotal==0){
			var recordlist =$("#recordlist > tbody:first"); 
			recordlist.empty();
			recordlist.html("<tr><td style='color:red' align='center' colspan='11'>无相应数据</td></tr>");
		} else{
			//分页-只初始化一次  
			$("#Pagination").pagination(recordtotal, { 
				callback:PageCallback,   
				items_per_page: items_per_page,  //每页显示的条目数
				num_display_entries: 10,  //默认值10可以不修改
				num_edge_entries: 2,  //两侧显示的首尾分页的条目数 
				prev_text: "上一页",  
				next_text: "下一页",  
				current_page: pageIndex //当前页索引 
			});  
		//分页-只初始化一次         
		
		}                   
	});
                   
	//翻页调用  
	function PageCallback(index, jq) {
		InitTable(index);
		return false;          
	}  
	//请求数据  
	function InitTable(pageIndex) { 
		
		var waiter_Name=$("#waiter_Name").val(); 
		$("#recordlist").mask("数据加载中...");
		$.ajax({  
			type: "POST",  
			url: "../waiter/getWaiterList",  
			cache:false,
			data: {"waiterForm.pageIndex":pageIndex,"waiterForm.pageSize":items_per_page,"waiterForm.waiter_Name":waiter_Name,"waiterForm.query":query},  
			dataType: 'json',  
			contentType: "application/x-www-form-urlencoded",  
			success: function(data){
				//清空显示层中的数据 
				var recordlist =$("#recordlist > tbody:first"); 
				recordlist.empty();   
				$.each(data,function(i,value){
					var sex;
					if(value.waiter_Sex == 0)
				    {
				    	sex = "男";
				    }
				    else
				    {
				    	sex = "女";
				    }
					var recordlistcontent="<tr><td><input type='checkbox' value='"+value.id+"' name='wait_checked_"+value.id+"' /></td>"+
			   			"<td align='left' style='width:10%'>"+(items_per_page * pageIndex + i + 1 )+"</td>"+  
				       	"<td>"+value.waiter_No+"</td>"+
				       	"<td>"+value.waiter_Name+"</td>"+
						"<td>"+sex+"</td>"+
						"<td>"+value.waiter_Age+"</td>"+
						"<td>"+value.waiter_Telphone+"</td>";
				       	recordlistcontent+="<td class='ta_r'><a href='updateWaiterPage?waiter.id="+value.id+"'>编辑</a></td></tr>";
				       	recordlist.append(recordlistcontent); 
				       	$("#recordlist").unmask("数据加载中..."); 
				       	//alert($("table td").size());
				       	//window.location.reload();
				}); 
			}//success函数结束                                  
		});//ajax请求结束	 
	} 
	function deleteWaiter(){
	
		var idlist = getCheckBoxsValue("wait_checked_") ;
		if(idlist.length!=0)
		{
			if(confirm("确定要删除选中数据吗？"))
			{
				$.ajax({  
					type: "POST",  
					url: "deleteWaiter",  
					data: "waiterForm.idList="+idlist,  
	 				dataType: 'text',  
					contentType: "application/x-www-form-urlencoded",  
					success: function(data){
						art.dialog({
					    time: 1,
					    content: data
						});
						//InitTable(pageIndex);//从新请求更新本页数据
						window.location.reload(); 	
			    	}//回调函数结束                                  
	     		});//ajax请求结束
			}
		}
		else
		{
			art.dialog({
				    time: 2,
				    content: '请勾选菜谱信息再进行删除！'
			});
		}
	}
	//删除结束
</script>


</html>