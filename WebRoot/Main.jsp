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
	<title>服装库存管理系统</title>
	
  </head>
  
  
    <frameset rows="82,*,23" frameborder="NO" border="0" framespacing="0">
	 	 <frame src="<%=basePath%>/Menu.jsp"  name="topFrame" scrolling="NO" noresize>
	 	 <frame src="<%=basePath%>/common/index.jsp" name="midFrame"  scrolling="NO">
	  	<frame src="<%=basePath%>/bottom.jsp" name="bottomFrame" scrolling="NO" noresize>
	</frameset>
	<noframes>
		<body></body>
	</noframes>
  
</html>
