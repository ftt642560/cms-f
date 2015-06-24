<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>

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
	<title>入库单详细</title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
	<script type="text/javascript" src="<%=basePath%>/js/page.js"></script>
  </head>
 	<SCRIPT LANGUAGE="javaScript">
	<!--
	function save(id)
	{
	    var huohao =  document.getElementById("huohao").value;
	    var pingming = document.getElementById("pingming").value;
	    var color = document.getElementById("color").value;
	    var size = document.getElementById("size").value;
	    var count = document.getElementById("count").value;
	    if(huohao == "0" || pingming == "0" || color == "0" || size=="0")
	    {
	      alert("请把信息填写完整");
	      return ;
	    }
	    if(isNaN(count)){
	     alert("请输入正确的数量");
	     return;
	    }
	    
	     document.forms[0].action=
	     "<%=basePath%>/hjh/add_orderindetail_action.action?status=add&inOrderId=" + id +
	     "&huohao=" + huohao + "&pingming=" + pingming + "&color=" + color + 
	     "&size=" + size + "&count=" + count;
	      alert("<%=basePath%>/hjh/add_orderindetail_action.action?status=add&inOrderId=" + id +
	     "&huohao=" + huohao + "&pingming=" + pingming + "&color=" + color + 
	     "&size=" + size + "&count=" + count);
		document.forms[0].submit();
	}
	
	function back()
	{
		history.back();
	}
	 
	 
	-->
	</SCRIPT>
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
	 <FORM NAME="idFrmMain" ID="idmig0102" METHOD="POST"  ACTION="" ONSUBMIT=""> 
		<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
			<tr>
		  	<td class="headerbar61" width="15%" colspan="1">入库单详细</td>
		    <td class="headerbar63" width="85%" colspan="1">
		      <input type="button" name="save70302002" onClick="javascript:save(<s:property value="inOrderId"/>)" value=" 保 存 ">&nbsp;
		      <input type="button" name="back70302003" onClick="javascript:back()" value=" 返 回 ">
		    </td>
		  </tr>
		</table>
		
		<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
			<tr>
				<td></td>
			</tr>
		</table>
		
		<table border="0" width="100%" id="table1" cellspacing="1"  cellpadding="2"  bgcolor="gray">
			<tr>
		  	<td class="textbar81" width="15%" colspan="1">货号</td>
		   <td class="textbar01" width="85%" colspan="1">
		    	 <select id="huohao" name="" style="width:210px "> 
		               <option value="0" selected="selected">请选择</option> 
		             <s:iterator value="infos[0]">
		               <option value="<s:property/>"><s:property/></option>
		             </s:iterator>
		          </select>
		       </td>
		  </tr>
			<tr>
		  	<td class="textbar81" width="15%" colspan="1">品名</td>
		    <td class="textbar01" width="85%" colspan="1">
		      <select id="pingming" name="" style="width:210px "> 
		               <option value="0" selected="selected">请选择</option> 
		                <s:iterator value="infos[1]">
		               <option value="<s:property/>"><s:property/></option>
		             </s:iterator>
		       </select>
		       </td>
		  </tr>          
		  <tr>
		  	<td class="textbar81" width="15%" colspan="1">色号</td>
		    <td class="textbar01" width="85%" colspan="1">
		      <select id="color" name="" style="width:210px "> 
		           <option value="0" selected="selected">请选择</option> 
		          <s:iterator value="infos[2]">
		               <option value="<s:property/>"><s:property/></option>
		          </s:iterator>
		       </select></td>
		  </tr>   
		   <tr>
		  	<td class="textbar81" width="15%" colspan="1">尺码</td>
		    <td class="textbar01" width="85%" colspan="1">
		      <select id="size" name="" style="width:210px "> 
		           <option value="0" selected="selected">请选择</option> 
		          <s:iterator value="infos[3]">
		               <option value="<s:property/>"><s:property/></option>
		          </s:iterator>
		       </select></td>
		  </tr>          
			<tr>
		  	<td class="textbar81" width="15%" colspan="1">数量</td>
		    <td class="textbar01" width="85%" colspan="1">
		    <input id="count"  name="leixing" value="200" style="width:210px;">
		    </td>
		  </tr>      
			
		</table>
		
		<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
			<tr>
				<td></td>
			</tr>
		</table>
	 </FORM>
  </body>
</html>
