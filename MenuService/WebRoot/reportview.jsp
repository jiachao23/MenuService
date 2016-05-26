<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reportview.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <link rel="stylesheet" type="text/css" href="css/easyui_themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/easyui_themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables.css" />
 <link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables.min.css" />
 <link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables_themeroller.css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" language="javascript" src="js/jquery.dataTables/jquery.js"></script>
<script type="text/javascript" language="javascript" src="js/jquery.dataTables/jquery.dataTables.js"></script>
  </head>
  <!-- datatable属性配置 -->
  <script type="text/javascript" language="javascript" class="init">
$(document).ready(function() {
    $('#example').dataTable( {
    	"processing": true,
        "ajax": "DataTable/data1.txt",
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "office" },
            { "data": "extn" },
            { "data": "start_date" },
            { "data": "salary" }
        ],
        
        /* set info messages */
        "oLanguage": {  
			"sLengthMenu": "每页显示 _MENU_条",  
			"sZeroRecords": "没有找到符合条件的数据",  
			"sProcessing": "&lt;img src=’./loading.gif’ /&gt;",  
			"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",  
			"sInfoEmpty": "木有记录",  
			"sInfoFiltered": "(从 _MAX_ 条记录中过滤)",  
			"sSearch": "搜索：",  
			"oPaginate": {  
				"sFirst": "首页",  
				"sPrevious": "前一页",  
				"sNext": "后一页",  
				"sLast": "尾页"  
			}
		}   
    } );
    
    /* $("#tt").datagrid({    
		url:"client/getOrderItem",  //这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function    //.......
		columns:[[
	{field:"id",title:"权限代码",width:"100",align:"center",sortable:true},
	{field:"table_No",title:"权限名称",width:"100",align:"center",sortable:true},
	{field:"table_Volum",title:"父节点ID",width:"80",align:"center",sortable:true},
	{field:"table_Status",title:"请求路径",align:"center",width:"200",sortable:true},
 		]],
	} ); */
} );
	</script>
	<!-- 日期选择框的样式设计 -->
	<script type="text/javascript">
        function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
    </script>
  <body>
    报表浏览器 <br>
    <table width="100%">
    	<tr>
    		<td style="padding:5px;width:240px;text-align:left">
    		<div class="easyui-panel" style="padding:5px">
    		<ul class="easyui-tree">
				<li><span>报表浏览器</span>
			<ul>
			<li data-options="state:'opened'">
			<span>日报表</span>
			<ul><li><span>按桌号</span></li>
				<li><span>按桌号汇总</span></li>
				<li><span>...</span></li>
			</ul>
			</li>
			<li><span>月报表</span>
			<ul>
				<li>按桌号</li>
				<li>按桌号汇总</li>
			 	<li>...</li>
			 </ul>
			 </li>
			
			 </ul>
			 </li>
			 </ul>
			 </div>
			  <!-- 报表类型：<input class="easyui-combobox" name="reporttype" style="width:160px;" data-options="
                url: 'reporttype.json',
                method: 'get',
                valueField:'value',
                textField:'text',
                groupField:'group'
            "> -->
            <div class="easyui-panel" style="padding:5px;width:240px">
    		起始时间：<input class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input><br>
    		截止时间：<input class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input><br>
    		<a href="#" class="easyui-linkbutton">浏览</a></div>
    		</td>
    		<td style="padding:5px;width:640px;text-align:left">
    		 <table id="dg" class="easyui-datagrid" title="Fluid DataGrid" style="width:700px;height:250px"
            data-options="singleSelect:true,collapsible:true,url:'client/getOrderItem',method:'get'">
        	<thead>
            <tr>                 
                <th data-options="field:'id'" width="100px">id</th>
                <th data-options="field:'table_No'" width="100px">table_No</th>
                <th data-options="field:'table_Volum'" width="100px">table_Volum</th>
                <th data-options="field:'table_Status'" width="100px">table_Status</th>
                <th data-options="field:'table_Name'" width="100px">table_Name</th>
                
            </tr>
        </thead>
    </table>
    		</td>
    		
    	</tr>
   
    </table>
  </body>
</html>
