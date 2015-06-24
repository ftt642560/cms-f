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

		<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
		<META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
		<META HTTP-EQUIV="content-style-type" CONTENT="text/css">
		<title>采购入库管理</title>
		<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
		<script type="text/javascript" src="<%=basePath%>/js/cjcalendar.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/page.js"></script>
  </head>
  
  <script language="javascript">
	var CalendarWebControl = new atCalendarControl();
</script>
<SCRIPT LANGUAGE="javaScript">
<!--

function goSearch()
{
    var receiptsNumber = document.getElementById("receiptsNumber").value;
    var inDepot = document.getElementById("inDepot").value;
    var dateStart = document.getElementById("frmWRPT_OPT_DATE2_PJT70302").value;
    var dateEnd = document.getElementById("frmWRPT_OPT_DATE3_PJT70302").value;
	document.forms[0].action="<%=basePath%>hjh/query_orderin_action.action?receiptsNumber2=" + receiptsNumber +
	    "&inDepot=" + inDepot + "&dateStart=" + dateStart + "&dateEnd=" + dateEnd + 
	    "&firstPage=1";
	document.forms[0].submit();
}
function goSearch2(){
    document.forms[0].action="<%=basePath%>hjh/query_orderin_action.action?receiptsNumber2=&inDepot=0&dateStart=&dateEnd=&firstPage=1";
    document.forms[0].submit();
}
function delOrderInById(url)
{	
   if(confirm("确定要删除这条入库单吗？删除后不会减少仓库存储量哦！")){
     document.forms[0].action= url;
	 document.forms[0].submit();
   }
}
function delCom(id){
	if(id == '1'){
		document.idFrmMain.gys.value = "";		
	}else{
		document.idFrmMain.sccj.value = "";
	}
}

function changesize(id){
	document.forms[0].action="<%=basePath%>/stock/order2001.jsp";
	document.forms[0].submit();
}

function jump(id){
	document.forms[0].action="<%=basePath%>/stock/order2001.jsp";
	document.forms[0].submit();	
}

function locatePage(id){
	document.forms[0].action="<%=basePath%>/stock/order2001.jsp";
	document.forms[0].submit();		
}

-->
</SCRIPT>
  
  
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
	<FORM NAME="idFrmMain" ID="idmig0101" METHOD="POST"  ACTION="" ONSUBMIT="">
	 
	<table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
	<tr>
		<td class="headerbar61">入库单查询</td>
		<td class="headerbar61">
		<p align="right">
			<input type=submit value=" 查 询 " onClick="JavaScript:goSearch();">	
		</p>
		</td>
	</tr>
	</table>
	
	
	<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
	<tr>
		<td></td>
	</tr>
	</table>
	
	
	<table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">	
		<tr>
			<td class= "textbar81" width="15%">所入仓库</td>
			<td class="textbar01" width="35%">
				<select id="inDepot" name="storeRoom" style="width:152px">
					<option value="0">------</option>
					<s:iterator value="repertories">
					    <option value="<s:property/>"><s:property/></option>
					</s:iterator>	
				</select>
			</td>
		  <td class="textbar81" width="15%">单据编号</td>
			<td class="textbar01" width="35%">
				<input id="receiptsNumber" type="text" name="number" style="width:152px">
			</td>				
	
		</tr>
		
		<tr>			  
			<td class="textbar81" width="15%">入库日期</td>
			<td class="textbar01" width="35%" colspan="3">
				<input value="2007-6-10" type="text" name="frmWRPT_OPT_DATE2_PJT70302" id="frmWRPT_OPT_DATE2_PJT70302" readonly="readonly" size="12">
				<img src="<%=basePath%>/image/calendar.gif" width="18" height="17" onclick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE2_PJT70302,'',forms[0].frmWRPT_OPT_DATE2_PJT70302);" title="显示日历"/>
				~ 
				<input value="2015-11-10" type="text" name="frmWRPT_OPT_DATE3_PJT70302" id="frmWRPT_OPT_DATE3_PJT70302"  readonly="readonly" size="12">
				<img src="<%=basePath%>/image/calendar.gif" width="18" height="17" onclick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE3_PJT70302,'',forms[0].frmWRPT_OPT_DATE3_PJT70302);" title="显示日历"/>
		  </td>
			
		</tr>	
		
	</table>
	
	<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
	<tr>
		<td></td>
	</tr>
	</table>
	
	
	<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
		<tr>
	  	<td class="headerbar61" width="100%" colspan="1">
	  	入库单列表<a href="<%=basePath%>hjh/get_orderinpage_action.action" style="margin-left: 985px">新&nbsp;&nbsp;增&nbsp;</a>
	  	</td>
	  	</tr>
	  	
	</table>
	
	<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="0"  bgcolor="gray">
		<tr>
	  	<td  width="100%" colspan="1">
	    	<table  border="0" cellspacing="1" cellpadding="2" width="100%">
	    	
					<tr>
						<td  width="5%"  class="headerbar82">序号</td>
						<td   width="15%"  class="headerbar82">单据编号</td>
						<td   width="20%" class="headerbar82">所入仓库</td>
						<td   width="15%" class="headerbar82">入库日期</td>					
						<td   width="15%" class="headerbar82">经办人</td>
						<td   width="25%" class="headerbar82">来源</td>
						<td  class="headerbar82">操作</td>			
					</tr>
					<s:if test="inOrders == null">
					     <tr><td colspan="6"><p style="color:red ;text-align: center;font-size: larger;">没有入库单记录！</p></td></tr>
					</s:if>
					<s:elseif test="inOrders.size == 0">
					     <tr><td colspan="6"><p style="color:red;text-align: center;font-size: larger;">没有入库单记录！</p></td></tr>
					</s:elseif>
					
					<s:else>
					<% int index = 1; %>
					<s:iterator value="inOrders" id="inOrder">
						<tr>
							<td  class="gridbar11" align="center"><%=index++ %></td>
							<td  class="gridbar11" align="center"><a href="hjh/get_orderin_action.action?orderInId=<s:property value="#inOrder.receiptsNumber"/>">RC071105CEN00<s:property value="#inOrder.receiptsNumber"/></a></td>
							<td  class="gridbar11" align="center"><s:property value="#inOrder.repertory"/> </td>
							<td  class="gridbar11" align="center"><s:property value="#inOrder.inDate"/> </td>
							<td  class="gridbar11" align="center"><s:property value="#inOrder.operator"/></td>
							<td  class="gridbar11" align="center"><s:property value="#inOrder.source"/></td>
							<td  class="gridbar11" align="center">
							<img src="<%=basePath%>/image/del.gif" align="bottom" border="0" alt="作废" 
							onclick="JavaScript:delOrderInById('<%=basePath%>hjh/delete_orderin_action.action?delReceiptsNumber=<s:property value="#inOrder.receiptsNumber"/>&receiptsNumber=<s:property value="receiptsNumber2"/>&inDepot=<s:property value="inDepot"/>&dateStart=<s:property value="dateStart" />&dateEnd=<s:property value="dateEnd"/>&firstPage=<s:property value="1"/>');"/>
							</td>
						</tr>
					</s:iterator>
					</s:else>
				</table>
		  </td>
		</tr>
			
	</table>

					
	
	<table width="100%" border="0" cellpadding="0" cellspacing="2">
		<tr>
	  	<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
		&nbsp; 共<s:property value="pages"/>页 &nbsp;&nbsp; 第<s:property value="currentpage"/>/<s:property value="pages"/>页 &nbsp;&nbsp;
			<a  href="http://localhost:8080/cms-final/hjh/query_orderin_action.action?receiptsNumber2=<s:property value="receiptsNumber2"/>&inDepot=<s:property value="inDepot"/>&dateStart=<s:property value="dateStart" />&dateEnd=<s:property  value="dateEnd"/>&firstPage=1" style="cursor:hand">首页</a>&nbsp;&nbsp; 
	 		<a style="cursor:hand" href="http://localhost:8080/cms-final/hjh/query_orderin_action.action?receiptsNumber2=<s:property value="receiptsNumber2"/>&inDepot=<s:property value="inDepot"/>&dateStart=<s:property value="dateStart" />&dateEnd=<s:property value="dateEnd"/>&firstPage=<s:property value="prepage"/>" >上一页</a>
	 		&nbsp;&nbsp; 
	 		<a style="cursor:hand" href="http://localhost:8080/cms-final/hjh/query_orderin_action.action?receiptsNumber2=<s:property value="receiptsNumber2"/>&inDepot=<s:property value="inDepot"/>&dateStart=<s:property value="dateStart" />&dateEnd=<s:property value="dateEnd"/>&firstPage=<s:property value="nextpage"/> ">下一页</a>
	 		&nbsp;&nbsp; 
	 		<a style="cursor:hand" href="http://localhost:8080/cms-final/hjh/query_orderin_action.action?receiptsNumber2=<s:property value="receiptsNumber2"/>&inDepot=<s:property value="inDepot"/>&dateStart=<s:property value="dateStart" />&dateEnd=<s:property value="dateEnd"/>&firstPage=<s:property value="pages"/> ">尾页</a>
	 		&nbsp;&nbsp;  
		</td>
	  </tr>
	</table>
	</FORM>
  </body>
</html>
