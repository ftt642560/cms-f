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
	
	 
	function save(){
		alert('保存成功');
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

	function delDetail(id) {
		var table = document.getElementById("tab0");
		var old = document.getElementById(id);
		table.removeChild(old);
	}

	function copyDetail() {
	alert("333");
		var firstRow = document.getElementById("1");
		alert(firstRow);
		var table = document.getElementById("tab0");
		var newRow = firstRow.cloneNode(true);
		var lastNode = table.getElementsByTagName("tr")[table.getElementsByTagName("tr").length-1];
		newRow.setAttribute("id",new Number(lastNode.getAttribute("id")) + 1);
		var textNode = document.createTextNode(new Number(lastNode.getAttribute("id")) + 1);
		var tdNode = document.createElement("td");
		tdNode.appendChild(textNode);
		tdNode.setAttribute("class", "gridbar01");
		tdNode.setAttribute("align", "center");
		newRow.replaceChild(tdNode, newRow.childNodes[1]);
		newRow.childNodes[newRow.childNodes.length - 2].childNodes[1].setAttribute(
		     "onClick","JavaScript:delDetail('" + (new Number(lastNode.getAttribute("id")) + 1) +"');");
		table.appendChild(newRow);
	}
	
	function m_save(){
	    var inDate = document.getElementById("frmWRPT_OPT_DATE2_PJT70302").value;
	    var repotory = document.getElementById("repotory").value;
	    var source = document.getElementById("source").value;
	    var note = document.getElementById("note").value;
	    var who = document.getElementById("who").value;
	    
	    if(inDate == "" || repotory == "0" || source == "" || who == ""){
	        alert("请填写完整入库单信息！");
	        return;
	    }
	    var table = document.getElementById("tab0");
	    var rows = table.getElementsByTagName("tr");
	    
	    if(rows.length == 2){
	        var row = rows[1];
	        var column = row.childNodes;
	        var huohao = column[3].childNodes[1].value;
	        var pingming = column[5].childNodes[1].value;
	        var color = column[7].childNodes[1].value;
	        var size = column[9].childNodes[1].value;
	        var count = column[11].childNodes[1].value;
	        
	        alert(huohao + "-" + pingming + "-" + color + "- " +size + "-" + count);
	        if(count == "" && huohao == "0" && pingming == "0" && 
	              color == "0" && size =="0"){
	              alert("不同时新建入库单明细");
	              if(confirm("确定新建一条入库单吗？")){
	                 window.location.href=
	                   "<%=basePath%>/hjh/add_orderin_action.action?inDate=" + inDate +
	                    "&repotory=" + repotory+ "&source=" + source + "&note=" + note + "&who=" + who; 
	                 document.getElementById("frmWRPT_OPT_DATE2_PJT70302").value="";
	                 document.getElementById("repotory")[0].selected=true;
	                 document.getElementById("source").value="";
	                 document.getElementById("note").value="";
	              }
	             
	              return;
	        }else if(count != "" && huohao != "0" && pingming != "0" && 
	              color != "0" && size !="0"){
	              if(isNaN(count)){
	                  alert("请输入有效的数量");
	                  column[11].childNodes[1].value = "";
	                  return;
	              }
	              alert("同时新建yitiao入库单明细");
	             if(confirm("确定新建一条入库单吗？")){
	                window.location.href= "<%=basePath%>/hjh/add_orderin_action.action?inDate=" + inDate +
	                "&repotory=" + repotory+ "&source=" + source +"&note=" + note + "&who=" + who + "&orderInDetails="+
	                huohao + "," + pingming + "," + color + "," + size + "," + count; 
	                column[3].childNodes[1][0].selected = true;
	                column[5].childNodes[1][0].selected = true;
	                column[7].childNodes[1][0].selected = true;
	                column[9].childNodes[1][0].selected = true;
	                column[11].childNodes[1].value=""; 
	                document.getElementById("frmWRPT_OPT_DATE2_PJT70302").value="";
	                document.getElementById("repotory")[0].selected=true;
	                document.getElementById("source").value="";
	                document.getElementById("note").value="";
	              }
	             return;
	        }else if(count == "" || huohao == "0" || pingming == "0" || color == "0" || size =="0"){
	            alert("入库单明细填写不完整");
	            return;    
	        }
	    }else{
	     var inOrderDetails = "";
	     for(i = 1;i < rows.length;i++){
	        var row = rows[i];
	        var column = row.childNodes;
	        var huohao = column[3].childNodes[1].value;
	        var pingming = column[5].childNodes[1].value;
	        var color = column[7].childNodes[1].value;
	        var size = column[9].childNodes[1].value;
	        var count = column[11].childNodes[1].value;
	        if(count == "" || huohao == "0" || pingming == "0" || color == "0" || size =="0"){
	            alert("保存新的入库单之前请保证每条入库单明细填写完整！");
	            return;    
	        }
	        if(isNaN(count)){
	            alert("请输入有效的数量");
	            column[11].childNodes[1].value = "";
	            return;
	        }
	        
	        
	        var rowParam = huohao+"," + pingming+"," + color+"," +size+"," + count+";";
	        inOrderDetails += rowParam;
	    }
	    
	   alert("入库单和入库单明细都已填好");
	   if(confirm("确定新建一条入库单吗？")){
	    window.location.href= "<%=basePath%>/hjh/add_orderin_action.action?inDate=" + inDate +
	                "&repotory=" + repotory+ "&source=" + source + "&note=" + note + "&who=" + who + "&orderInDetails="+ inOrderDetails; 
	      document.getElementById("frmWRPT_OPT_DATE2_PJT70302").value="";
	      document.getElementById("repotory")[0].selected=true;
	      document.getElementById("source").value="";
	      document.getElementById("note").value="";
	      var column = rows[1].childNodes;
	      column[3].childNodes[1][0].selected=true;
	      column[5].childNodes[1][0].selected=true;
	      column[7].childNodes[1][0].selected=true;
	      column[9].childNodes[1][0].selected=true;
	      column[11].childNodes[1].value = "";
	    }
	    }
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
						<input type=button value=" 保 存 "
							onClick="JavaScript:m_save();"> 
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
				<td class="textbar01" width="20%"><input type="text"
					value="自动编号..." readonly size="20" style="font-size: 15px;"></td>
				<td class="textbar81" width="10%">入库日期</td>
				<td class="textbar01" width="20%">
				    <input type="text" 
					name="frmWRPT_OPT_DATE2_PJT70302" id="frmWRPT_OPT_DATE2_PJT70302"
					readonly="readonly" size="12" style="font-size: 15px ;"/>
					<img src="<%=basePath%>/image/calendar.gif" width="18" height="17"
					onClick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE2_PJT70302,'',forms[0].frmWRPT_OPT_DATE2_PJT70302);"
					title="显示日历" /></td>
				
			</tr>
			<tr>
				<td class="textbar81" width="10%">所入仓库</td>
				<td class="textbar01" width="20%">
				<select id="repotory" name="storeRoom" style="width:152px;font-size: 20px" >
						<option value="0">------</option>
						<s:iterator value="repertories">
							<option value="<s:property/>"><s:property/></option>
						</s:iterator>
				</select></td>
				<td class="textbar81" width="10%">来源</td>
				<td class="textbar01" width="20%">
				     <input id="source" type="text" name="frmWRPT" value="" size="20" style="font-size: 15px;"/>
				</td>
				<td class="textbar81" width="10%">经办人</td>
				<td class="textbar01" width="20%">
				     <input id="who" type="text" name="frmWRPT" value="" size="20" style="font-size: 15px;"/>
				</td>
			</tr>
			<tr>
				<td class="textbar81" width="15%">备注</td>
				<td class="textbar01" width="85%" colspan="3">
				<textarea id="note" name="textarea" cols="90" rows="2" style="font-size: 15px;"></textarea>
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
				<td class="headerbar63" colspan="1">
					<p align="right">

						<input type=button value=" 新增明细 "
							onClick="JavaScript:copyDetail();">
					</p></td>
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
				<td class="headerbar82">操作</td>
			</tr>
			<tr id="1">
				<td class="gridbar01" align="center" style="height:25px">1</td>
				<s:iterator value="clothingInfos">
					<td class="gridbar01" align="center" style="height:25px">
					  <select  style="width:180px; height:25px;font-size: 17px;">
					       <option value ="0">----</option>
							<s:iterator value="top" id="object">
								<option value="<s:property value="#object" />" style="height:20px;">
									<s:property value="#object" />
								</option>
							</s:iterator>
					</select></td>
				</s:iterator>
				<td style="background-color: white;">   
				    <input type="text" style="width:180px ;height:25px;font-size: 20px;border-width: 2px;border-color: white;">
				</td>
				<td class="gridbar01" align="center" style="height:25px ">
				    <img src="<%=basePath%>/image/del.gif" align="bottom" border="0"alt="删除" 
				         style="height:20px"/>
				</td>
			</tr>
		</table>
	</FORM>

</body>
</html>




