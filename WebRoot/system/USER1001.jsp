<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


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

	<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
	<META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
	<META HTTP-EQUIV="content-style-type" CONTENT="text/css">
	<title>用户管理</title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
  </head>
  
  <SCRIPT LANGUAGE="javaScript">
  $(function(){
    findUserInfo("user_userList?pagerBean.page=1","GET","");
  	
  })
  
  function findUserInfo(url,type,param){
	  $.ajax({  
        url : url,  
        type : type,
        data:param,
        success : function(data) {
			show(data);
        },
        error:function(){
        	console.log("查询用户列表信息有异常");
        }
    }); 
  }
  function show(data){
	  var list = data.pagerBean.list;
	  var t = "<tr>"+
					"<td  width='5%' class='headerbar82'>序号</td>"+
					"<td  width='35%' class='headerbar82'>用户登录号</td>"+
					"<td  width='35%' class='headerbar82'>用户姓名</td>"+
					"<td  class='headerbar82'>操作</td>"+
				"</tr>";
	 var s = "";
	  if(list.length==0){
		  $("#userInfo").html("<tr><td colspan='4'>没有查询到相关信息</td></tr>");
		  $("#trPager").html("");
	  }else{
		  for(var i=0;i<list.length;i++){
				s+="<tr><td class='gridbar11' align='center'>"+list[i].id+"</td>" +
					"<td  class='gridbar11' align='center'>"+list[i].usercode+"</td>" +
					"<td  class='gridbar11' align='center'><a href='javascript:update("+list[i].id+",\""+list[i].usercode+"\",\""
					+list[i].username+"\",\""+list[i].password+"\",\""+list[i].intro+"\")'>"
					+list[i].username+"</a></td>" +
"<td  class='gridbar11' align='center'><a href = 'javascript:del("+list[i].id+")' '><img src='<%=basePath%>/image/del.gif' align='bottom' border='0' alt='删除'/></a></td>" 
					+
					"</tr>";
		  }
		  $("#userInfo").html(t+s)
		  var action="";
		  //alert(JSON.stringify(data));
		  //alert(JSON.stringify(data.pagerBean.currentPage))
		  if(data.searchFlag==1){
		      action = "user_userConList";
		  }else{
			  action = "user_userList";
		  }
		  var s1 = "";
		  if(data.pagerBean.totalPage==1){
			  s1="首页&nbsp;上一页&nbsp;下一页&nbsp;尾页&nbsp;&nbsp;";
		  }else if(data.pagerBean.currentPage==1){
			  s1="首页&nbsp;上一页&nbsp;<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page="+parseInt(data.pagerBean.currentPage+1)+"\")'>下一页</a>&nbsp;" +
			  "<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page="+data.pagerBean.totalPage+"\")'>尾页</a>&nbsp;&nbsp;";
		  }else if(data.pagerBean.currentPage < data.pagerBean.totalPage){
			  s1="<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page=1\")'>首页</a>&nbsp; " +
			  "<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page="+parseInt(data.pagerBean.currentPage-1)+"\")'>上一页</a>&nbsp;" +
			  "<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page="+parseInt(data.pagerBean.currentPage+1)+"\")'>下一页</a>&nbsp;" +
			  "<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page="+data.pagerBean.totalPage+"\")'>尾页</a>&nbsp;&nbsp;;";
		  }else if(data.pagerBean.currentPage == data.pagerBean.totalPage){
			  s1="<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page=1\")'>首页</a>&nbsp; " +
			  "<a style='cursor:hand' href='javascript:findUserInfo(\""+action+"?pagerBean.page="+parseInt(data.pagerBean.currentPage-1)+"\")'>上一页</a>&nbsp;" +
			  "下一页&nbsp;尾页&nbsp;&nbsp;";
		  }
		  $("#trPager").html("<td colspan='2' align='right' height=20' nowrap class='textbar3' >" +
		  					"&nbsp; 共<span id='allRow'></span>条 &nbsp; 第<span id='curPage'></span>/<span id='tolRow'></span>页 &nbsp; " +
		  					s1+"</td>");
		  $("#allRow").html(data.pagerBean.allRow);
		  $("#curPage").html(data.pagerBean.currentPage);
		  $("#tolRow").html(data.pagerBean.totalPage);
	  }
	  	
  }
function jsgoto(){
	location.href="<%=basePath%>/system/USER1002.jsp";
}
 
function del(id){
	if(confirm("您确定删除该条数据？")){
		$.ajax({  
	        url : "<%=basePath%>user_userDel.action?user.id="+id,  
	        type : "GET",
	        success : function(data) {
				alert(data.result);
				location.replace(location.href);
	        },
	        error:function(){
	        	console.log("删除用户出现异常");
	        }
	    }); 
	}
}

function update(){
	//location.href="../system/USER1002.jsp?id="+arguments[0]+"&usercode="+arguments[1]+"&username="+arguments[2]+"&password="+arguments[3]+"&intro="+arguments[4];
	location.href="<%=basePath%>/system/USER1002.jsp?id="+arguments[0]+"&usercode="+escape(arguments[1])+"&username="+escape(arguments[2])+"&password="+arguments[3]+"&intro="+arguments[4];
}

function goSearch(){
	if($("#usercode").val()=="" && $("#username").val()==""){
		findUserInfo("<%=basePath%>user_userList.action?pagerBean.page=1","GET","");
	}else{
		findUserInfo("<%=basePath%>user_userConList.action?pagerBean.page=1","POST",$("#userForm").serialize());	
	}
	
}
 
-->
</SCRIPT>
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
    	<FORM ID="userForm" method="post"  action=""  >
          <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
			<tr>
				<td class="headerbar61">用户明细查询</td>
				<td class="headerbar61"><p align="right"><input type="button" value=" 查 询 " onClick="goSearch();"></p></td>
			</tr>
  </table>
			
			<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
			<tr>
				<td></td>
			</tr>
			</table>
		  <table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
			<tr>
				<td class="textbar81" width="15%">用户姓名</td>
				<td class="textbar01" width="35%">
					<input type="text" id="username" name="user.username" size="20">
				</td>
				<td class="textbar81" width="15%">用户登录号</td>
				<td class="textbar01" width="35%">
					<input type="text" id="usercode" name="user.usercode" size="20">
				</td>
			</tr>
			
			</table>
<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
	<tr>
		<td></td>
	</tr>
</table>
</FORM>
		    <table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
                <tr>
                    <td class="headerbar61" width="50%" colspan="1">用户明细表</td>
                    <td class="headerbar63" width="50%" colspan="1">
                   <input type="button" name="add" tabindex="1" onClick="javascript:jsgoto()" value=" 新 增 "></td>
                </tr>
            </table>

<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
	<tr>
		<td></td>
	</tr>
</table>

      	<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="0"  bgcolor="gray">
        	<tr>
          	<td  width="100%" colspan="1">
          		<table  border="0" cellspacing="1" cellpadding="2" width="100%" id="userInfo">
					
					
			  </table>
			</td>
			</tr>
	  </table>


		<table width="100%" border="0" cellpadding="1" cellspacing="2" >
          <tr id="trPager">
          	
          </tr>
        </table>



  </body>
</html>
