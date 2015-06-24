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
		<title>在线通知</title>
		<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
		<style type="text/css">
		<!--
		body {
			background-color: #f8f7f7;
		}
		-->
		</style>
  </head>
  
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
  
  </body>
</html>
