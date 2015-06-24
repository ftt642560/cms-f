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

	<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=gb2312">
	<META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
	<META HTTP-EQUIV="content-style-type" CONTENT="text/css">
	<title>用户详细</title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
  </head>
  
  
  <SCRIPT LANGUAGE="javaScript">
  $(function(){
	  var id = GetUrlParamValue(window.location.href, "id");
	  var usercode = GetUrlParamValue(window.location.href, "usercode");
	  var username = GetUrlParamValue(window.location.href, "username");
	  var password = GetUrlParamValue(window.location.href, "password");
	  var intro = GetUrlParamValue(window.location.href, "intro");
	  $("#userid").val(id);
	  $("#usercode").val(usercode);
	  $("#username").val(username);
	  $("#password").val(password);
	  $("#userintro").val(intro);
  })
  /*获取地址参数值*/
function GetUrlParamValue(Url, Param){
    var reg = new RegExp("(^|&)"+ Param +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

function save()
{
	if(document.getElementById('usercode').value.trim() == "") { 
	  alert("请输入登录账号");
	  return false; 
	} 
	if(document.getElementById('username').value.trim() == "") { 
	  alert("请输入用户名称");
	  return false; 
	} 
	if(document.getElementById('password').value.trim() == "") { 
	  alert("请输入登录密码");
	  return false; 
	} 
	var url = "";
	if(document.getElementById('userid').value.trim() == ""){
		alert("save");
		url = "<%=basePath%>user_userAdd.action";
	}else{
		url = "<%=basePath%>user_userUpdate.action";
	}
	alert(url);
	$.ajax({  
        url : url,  
        type : "POST",
        data:$("#addForm").serialize(),
        success : function(data) {
			alert(data.result);
			if(url=="<%=basePath%>user_userUpdate.action")
				location.href="<%=basePath%>/system/USER1001.jsp";
			else
				location.replace(location.href);
        },
        error:function(){
        	console.log("添加用户出现异常");
        }
    }); 
}

function back()
{
	location.href="<%=basePath%>/system/USER1001.jsp";
}
  
-->
</SCRIPT>
  
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
    	
<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
	<tr>
  	<td class="headerbar61" width="15%" colspan="1">用户详细</td>
    <td class="headerbar63" width="85%" colspan="1">
      <input type="button" name="save70302002" onClick="javascript:save()" value=" 保 存 ">&nbsp;
      <input type="button" name="back70302003" onClick="javascript:back()" value=" 返 回 ">
    </td>
  </tr>
</table>

<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
	<tr>
		<td></td>
	</tr>
</table>
<FORM ID="addForm" method="post" action=""> 
<input id="userid" name="user.id" value="" size="20" type="hidden">
<table border="0" width="100%" id="table1" cellspacing="1"  cellpadding="2"  bgcolor="gray">
	<tr>
  	<td class="textbar81" width="15%" colspan="1">用户登录号</td>
    <td class="textbar01" width="85%" colspan="1"><input id="usercode" name="user.usercode" value="" size="20"></td>
  </tr>          
  <tr>
  	<td class="textbar81" width="15%" colspan="1">用户姓名</td>
    <td class="textbar01" width="85%" colspan="1"><input id="username" name="user.username" value="" size="20"></td>
  </tr>          
	<tr>
  	<td class="textbar81" width="15%" colspan="1">用户密码</td>
    <td class="textbar01" width="85%" colspan="1"><input id="password" name="user.password" type="password" value="" size="21"></td>
  </tr>      
	<tr>
  	<td class="textbar81" width="15%" colspan="1">用户简介</td>
    <td class="textbar01" width="85%" colspan="1"><textarea id="userintro" name="user.intro"  style="width:352px"></textarea>
     </td>
  </tr>  
</table>
</FORM>
<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
	<tr>
		<td></td>
	</tr>
</table>

  </body>
</html>
