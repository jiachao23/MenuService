<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  
    <title>My JSP 'testDataTable1.jsp' starting page</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 	<link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables.min.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables_themeroller.css" />
 <!--   <script language="javascript" src="js/jquery-1.4.4.min.js"></script> -->
    <!--  <script type="text/javascript" language="javascript" src="js/jquery.dataTables/jquery.js"></script> -->
	<script type="text/javascript" language="javascript" src="js/jquery.dataTables/jqueryv1.11.1.js"></script>
	<script type="text/javascript" language="javascript" src="js/jquery.dataTables/jquery.dataTables.js"></script>
<!-- 	<script language="javascript" src="js/jquery.jqprint-0.3.js"></script> -->

	<!--引入打印js -->
	<!-- <script language="javascript" src="js/LodopFuncs.js"></script> -->
	
  </head>
  <script language="javascript">
		function printme()
		{
			var bdhtml=window.document.body.innerHTML;
			document.body.innerHTML=document.getElementById("tableStyle").innerHTML;
			window.print();
			document.body.innerHTML=bdhtml;
		}
</script>
    <script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
    	$('#example').dataTable( {
        
        /*  "ajax": "localhost:8080/MenuService/client/getOrderItem",
        "processing": true,
        "serverSide": true,  */
        /* get server-side data by ajax*/
        "ajax": {
            "url": "client/getOrderItem",
            "serverSide": true,
            "processing": true,
            "dataType": "json"
        },
        
        /* set columns */
        "columns": [
            { "data": "id" },
            { "data": "table_No" },
            { "data": "table_Volum" },
            { "data": "table_Status" },      
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
} );
	</script>
	
	
  <body>
  <div id="all">
  <a href="javascript:prn1_preview()">打印预览</a>
  <a href="javascript:prn1_print()">打印</a>
 <input type="button" onclick="a()" value="打印"/>
 <a href="javascript:printme()">打印</a>
 <form id="tableStyle" class="display">
    <table id="example" class="display" style="align:center;" cellspacing="0" width="100%">        
        <thead>
            <tr>
                <th>id</th>
                <th>table_No</th>
                <th>table_Volum</th>
                <th>table_Status</th>
             
            </tr>
        </thead>
 
        <!-- <tfoot>
            <tr>
                <th>id</th>
                <th>table_No</th>
                <th>table_Volum</th>
                <th>table_Status</th>
            </tr>
        </tfoot> -->
       
    </table>
    <!-- </div> -->
     </form>
     </div>
  </body>
<script language="javascript" type ="text/javascript">  
        var LODOP; //声明为全局变量
       function prn1_preview() {  
             CreateOneFormPage();       
             LODOP.PREVIEW();    
       };
        function prn1_print() {          
             CreateOneFormPage();
             LODOP.PRINT();      
       };
        function prn1_printA() {         
             CreateOneFormPage();
             LODOP.PRINTA();     
       };     
        function CreateOneFormPage(){
             LODOP=getLodop(); 
             LODOP.PRINT_INIT( "打印控件功能演示_Lodop功能_表单一" );
             var strBodyStyle="<style>table { border:1px;width: 100%;margin: 0 auto;clear: both;border-collapse:separate;border-spacing: 0; }</style>";
             
		     /* var strStyleCSS="<link href='css/jquery.dataTables/jquery.dataTables.css' type='text/css' rel='stylesheet'>"; */
               //tableStyle的与要打印的内容Form的样式id一致
              /* var strBodyStyle="<style>"+document.getElementById( "tableStyle" ).innerHTML+"</style>";   */
               //formContent的与要打印的内容Form的id一致 
              var strFormHtml=strBodyStyle+ "<body>" +document.getElementById("tableStyle").innerHTML+ "</body>" ;
             LODOP.PRINT_INIT( "打印控件功能演示_Lodop功能_样式风格" );    
              //LODOP.ADD_PRINT_TEXT(50,50,260,39,"打印与显示样式一致：");
             LODOP.ADD_PRINT_HTM(88,50,300,200,strFormHtml);
              //LODOP.PREVIEW();
 };   
                   
</script>

  
</html>
