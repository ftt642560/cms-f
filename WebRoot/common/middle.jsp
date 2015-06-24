<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<TITLE>button</TITLE>
	<META content="text/html; charset=gb2312" http-equiv=Content-Type>
	
	<SCRIPT>
		function oa_tool(){
		//alert(window.parent.oa_frame.cols);
		if(window.parent.sss.cols=="180,10,*"){
		frameshow.src="<%=basePath%>/image/m1.gif";
		oa_tree.title="显示子栏目"
		window.parent.sss.cols="0,10,*";
		}
		else{
		frameshow.src="<%=basePath%>/image/m2.gif";
		oa_tree.title="隐藏子栏目"
		window.parent.sss.cols="180,10,*";
		}
		}
	</SCRIPT>
	<META content="MSHTML 5.00.2920.0" name=GENERATOR>
	<style type="text/css">
		<!--
		.hand {
			cursor: hand;
		}
		-->
	</style>
	
  </head>
  
  <body leftMargin=0 topMargin=0>
    	<TABLE width="9" height="100%" border=0 cellPadding=0 cellSpacing=0>
		  <TBODY>
		 	 <TR>
		      	<TD align="center" valign="middle" bgcolor="#B3B3B3"> 
		      		<DIV id=oa_tree onclick=oa_tool(); title=隐藏子栏目>
		      		<IMG src="<%=basePath%>/image/m2.gif" width="10" height="67" align=middle class="hand"  
		             id=frameshow >
		     	 </DIV>
		      </TD>
		    </TR>
		  </TBODY>
		</TABLE>
  </body>
</html>
