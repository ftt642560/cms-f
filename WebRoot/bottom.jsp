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
	
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<title>开发管理分析系统</title>
	<style type="text/css">
		<!--
		@import url("./css/frame.css");
		-->
	</style>
  </head>
  
  <body>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td height="23" background="./image/bottom.gif" bgcolor="#50746E" >
		      <div align="center" class="copyright">版本V1.0.1 版权所有&copy;2007 公司</div>
		    </td>
		  </tr>
		</table>
  </body>
</html>
