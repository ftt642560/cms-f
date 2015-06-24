<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="s" uri="/struts-tags"%>

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
<title>采购入库单详细</title>
<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
<script type="text/javascript" src="<%=basePath%>/js/page.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/cjcalendar.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/addFunction.js"></script>
</head>

<script language="javascript">
		var CalendarWebControl = new atCalendarControl();
	</script>
<SCRIPT LANGUAGE="javaScript">	var trFlag = 0;
	function tabMove0(objId, position){
	  if (event.keyCode == 13) {
	          document.getElementById(objId).childNodes[2].innerHTML='07长面包新款';
	          document.getElementById(objId).childNodes[3].innerHTML='045';
	          tabMove(objId, position);
	  }	
	}
	function jsgoto(strURL)
	{
		document.forms[0].action=strURL;
		document.forms[0].submit();
	}
	
	 
	function setValue(){
		document.forms[0].gys.value = "610";	
	}
	function delCom(id){
		if(id == "1"){
			document.forms[0].gys.value = "";	
		}else{
			document.forms[0].sccj.value = "";	
		}
	}
	function setValue1(){
		document.forms[0].sccj.value="";
	}
	
	function update(){
	    var orderin_details_id = new Array();
	    var counts = new Array();
	    var table = document.getElementById("tab0");
	    var rows = table.getElementsByTagName("tr");
	    for(var i = 1;i < rows.length;i++){
	        var row = rows[i];
	        var tds = row.getElementsByTagName("td");
	        orderin_details_id[orderin_details_id.length] = tds[0].getAttribute("id");
	        var lastTd = tds[tds.length-1];
	        var oldCount = row.getAttribute("id");
	        var newCount = lastTd.getElementsByTagName("input")[0].value;
	        counts[counts.length] = newCount;
	        if(oldCount.localeCompare(newCount)){
	            hasUpdate = new Boolean(0);
	        }
	    }
	    
	    var rawReceiptsNumber = document.getElementById("receiptsNumber").value;
	    var receiptsNumber = rawReceiptsNumber.substring(rawReceiptsNumber.indexOf("RC071105CEN00") + 13,rawReceiptsNumber.length);
	   
	    var inDate = document.getElementById("frmWRPT_OPT_DATE2_PJT70302").value;
	    var inDepot = document.getElementById("depot").value;
	    var source = document.getElementById("source").value;
	    var note = document.getElementById("note").value;
	    var who = document.getElementById("who").value;
	    var data = "";
	    for(var i = 0;i < counts.length;i++)
	        data = data + orderin_details_id[i] + "," + counts[i] + ";";
	        
	        
	    document.forms[0].action="<%=basePath%>/hjh/update_orderin_action.action?id_count=" 
	           + data  + "&receiptsNumber=" + receiptsNumber + "&inDate=" + inDate
	           + "&inDepot=" + inDepot + "&source=" + source + "&note=" + note + "&who=" + who;
	           
	    alert("<%=basePath%>/hjh/update_orderin_action.action?id_count=" 
	           + data  + "&receiptsNumber=" + receiptsNumber + "&inDate=" + inDate
	           + "&inDepot=" + inDepot + "&source=" + source + "&note=" + note);
		document.forms[0].submit();
	}

</SCRIPT>



<body BACKGROUND="<%=basePath%>/image/bg.gif">
	<FORM NAME="mig0101" ID="idmig0101" METHOD="POST" ACTION="" ONSUBMIT="">

		<table border=0 cellspacing=0 cellpadding=2 width="100%"
			bgcolor="gray">
			<tr>
				<td class="headerbar61">入库单详细</td>
				<td class="headerbar61">
				    <p align="right">
						<!--  <input type="button" value="确认">  -->
						<input type=button value="  更新 "
							onClick="JavaScript:update();"> 
						<input type=button value=" 返 回 " onClick="JavaScript:history.back();">
					</p>
				</td>
			</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
			<tr>
				<td></td>
			</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=2 width="100%"
			bgcolor="gray">
			<tr>
				<td class="textbar81" width="10%">单据编号</td>
				<td class="textbar01" width="25%"><input id="receiptsNumber" type="text" readonly="readonly"
					value="RC071105CEN00<s:property value="inOrder.receiptsNumber"/>" size="20" style="font-size: 15px;"></td>
				<td class="textbar81" width="10%">入库日期</td>
				<td class="textbar01" width="25%">
				    <input type="text" readonly="readonly" name="frmWRPT_OPT_DATE2_PJT70302" id="frmWRPT_OPT_DATE2_PJT70302"
					 value ="<s:property value="inOrder.inDate"/>"size="12" style="font-size: 15px ;"/>
				    <img src="<%=basePath%>/image/calendar.gif" width="18" height="17" onclick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE3_PJT70302,'',forms[0].frmWRPT_OPT_DATE3_PJT70302);" title="显示日历"/>
				</td>
			</tr>
			<tr>
				<td class="textbar81" width="10%">所入仓库</td>
				<td class="textbar01" width="25%">
				<select id="depot" style="width:152px;font-size: 20px">
				    <option selected="selected" value="<s:property value="inOrder.repertory"/>"><s:property value="inOrder.repertory"/></option>
				     <s:iterator value="storenames">
				          <option value= "<s:property/>"><s:property/></option>
				     </s:iterator>
				</select>
				</td>
				<td class="textbar81" width="10%">来源</td>
				<td class="textbar01" width="25%">
				     <input id="source" type="text"  value="<s:property value="inOrder.source"/>" name="frmWRPT" value="" size="20" style="font-size: 15px;"/>
				</td>
				<td class="textbar81" width="10%">经办人</td>
				<td class="textbar01" width="25%">
				     <input id="who" type="text" value="<s:property value="inOrder.operator"/>" name="frmWRPT" value="" size="20" style="font-size: 15px;"/>
				</td>
			</tr>
			<tr>
				<td class="textbar81" width="15%">备注</td>
				<td class="textbar01" width="85%" colspan="3">
				<textarea id="note" name="textarea" cols="80" rows="2" style="font-size: 15px;">
				<s:property value="inOrder.note"/>
				</textarea>
				</td>
			</tr>


		</table>

		<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
			<tr>
				<td></td>
			</tr>
		</table>

		<table border="0" width="100%" id="table1" cellspacing="0"
			cellpadding="2" bgcolor="gray">
			<tr>
				<td class="headerbar61" colspan="1">入库单明细</td>
				<td class="headerbar61" colspan="1">
				   <p align="right">
				   <a href="<%=basePath%>/hjh/add_orderindetail_action.action?status=getPage&inOrderId=<s:property value="inOrder.receiptsNumber"/>">新建入库单明细</a>
				   </p>
				</td>
			</tr>
		</table>


		<table class="1" id="tab0" border="0" cellspacing="1" cellpadding="2"
			width="100%" bgcolor="gray" >
			<tr id="0">
				<td width="5%" class="headerbar82">序号</td>
				<td width="17%" class="headerbar82">货号</td>
				<td width="17%" class="headerbar82">品名</td>
				<td width="17%" class="headerbar82">色号</td>
				<td width="17%" class="headerbar82">尺码</td>
				<td width="17%" class="headerbar82">数量</td>
			</tr>
			<% int i = 1; %>
			<s:iterator value="inOrder.inOrderDetails">
			   <tr id= "<s:property value="count"/>" >
				    <td id="<s:property value="inorderdetail_id"/>" class="gridbar01" align="center" style="height:25px"> <%= i++ %></td>
					<td class="gridbar01" align="center" style="height:25px"><s:property value="articleNumber"/></td>
				    <td class="gridbar01" align="center" style="height:25px"><s:property value="type"/></td>
					<td class="gridbar01" align="center" style="height:25px"><s:property value="color"/></td>
					<td class="gridbar01" align="center" style="height:25px"><s:property value="size"/></td>
					<td class="gridbar01" align="center" style="height:25px">
					<input type="text" value="<s:property value="count"/>">
					</td>
			  </tr>
			</s:iterator>
		</table>
	</FORM>

</body>
</html>




