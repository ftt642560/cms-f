<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>出库单查询</title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
	<script type="text/javascript" src="<%=basePath%>/js/cjcalendar.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/page.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
  </head>
  
	 <script language="javascript">
		var CalendarWebControl = new atCalendarControl();
	</script>
	
	<script type="text/javascript">
		$(function(){
				//1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消
				$(".delete").click(function(){
					var outNo = $(this).next(":input").val();//this代表a链接
					var flag = confirm("确定要删除"+ outNo +"的信息吗？");
					if(flag){//真的要删除
						var $tr = $(this).parent().parent();
						//执行删除，实行ajax的方法
						var url = this.href;
						var args = {"time":new Date()};
						$.post(url , args , function(data){//data如何返回
							alert("data======"+data);
							var d = eval("("+data+")");
							alert("d======"+d);
							$tr.remove();
							document.getElementById('div').innerHTML="删除成功";
							$("#allRows").text(d);
							
							$("#div").show();
						});
					}
					//(先需要)取消超链接的默认行为
					return false;
				});
				
				$("#check").click(function(){
					var outDate1 = $("#frmWRPT_OPT_DATE2_PJT70302").val();
					var outDate2 = $("#frmWRPT_OPT_DATE3_PJT70302").val();
					if(outDate1 > outDate2){
						$("#div").html("时间格式错误");
						$("#div").show();
					}else{
						document.forms[0].action="<%=basePath%>/outOrder/findPart.action";
						document.forms[0].submit();
					}
					
				});
		});
	</script>
	
	
	<SCRIPT LANGUAGE="javaScript">
	<!--
	
	function delCom(id){
		if(id == '1'){
			document.idFrmMain.gys.value = "";		
		}else{
			document.idFrmMain.sccj.value = "";
		}
	}
	
	function changesize(id){
		document.forms[0].action="<%=basePath%>/stock/order3001.jsp";
		document.forms[0].submit();
	}
	
	function jump(id){
		document.forms[0].action="<%=basePath%>/stock/order3001.jsp";
		document.forms[0].submit();	
	}
	
	function locatePage(id){
		document.forms[0].action="<%=basePath%>/stock/order3001.jsp";
		document.forms[0].submit();		
	}
	
	function setValue()
	{
		document.all.sccj.value="青岛雪中飞贸易有限公司";
	}
	-->
	
	function addOne()
	{
		alert("121");
		document.getElementsByName("form2").action="<%=basePath%>/outOrder/findAllStore.action";
		document.getElementsByName("form2").submit();
	}
	
	</SCRIPT>
  
  
  
  <body background="<%=basePath%>/image/bg.gif">
		<form name="form1" ID="idmig0101" method="post" action="<%=basePath%>/outOrder/getAllS.action" onsubmit="" >
			<input type="hidden" id="slide_img">
			<table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
				<tr>
					<td class="headerbar61">出库单查询30000001</td>
					<td class="headerbar61">
						<p align="right">		
							<input type="button" value="查询" id="check">
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
					<td class= "textbar81" width="15%">所出仓库</td>
					<td class="textbar01" width="35%">
						<select id="cStore" name="criteriaOutOrder.storeName" style="width: 152px">
							<option value="">-------</option>
							<c:forEach items="${stores }" var="stores">
								<option value="${stores.storename }">${stores.storename }</option>
							</c:forEach>
						</select>
					</td>
				  	<td class="textbar81" width="15%">单据编号</td>
				  	<td class="textbar01" width="35%">
						<input id="cOutNo" type="text" name="criteriaOutOrder.outNo" style="width:152px">
				  	</td>				
				</tr>
				
				<tr>			  
					<td class="textbar81" width="15%">出库日期</td>
					<td class="textbar01" width="35%" colspan="3">
						<input type="text" name="criteriaOutOrder.minOutStoreDate" id="frmWRPT_OPT_DATE2_PJT70302" value="" readonly="readonly" size="12">
						<img src="<%=basePath%>/image/calendar.gif" width="18" height="17" onclick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE2_PJT70302,'',forms[0].frmWRPT_OPT_DATE2_PJT70302);" title="显示日历"/>
						~ 
						<input type="text" name="criteriaOutOrder.maxOutStoreDate" id="frmWRPT_OPT_DATE3_PJT70302" value="" readonly="readonly" size="12">
				  		<img src="<%=basePath%>/image/calendar.gif" width="18" height="17" onclick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE3_PJT70302,'',forms[0].frmWRPT_OPT_DATE3_PJT70302);" title="显示日历"/>
				  	</td>
				</tr>	
				
			</table>
			
			<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
				<tr>
					<td></td>
				</tr>
			</table>
		</form>
		
		<form name="form2" id="idmig0101" action="" method="post" onsubmit="">
			<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
				<tr>
			  	<td class="headerbar61" width="100%" colspan="1">出库单明细3001</td>
					<td class="headerbar61">
						<p align="right" style="width: 120px">	
						 	<input type=button value="新增" onClick="window.location.href='<%=basePath%>/outOrder/findAllStore.action?model=add';"  />
						</p>
					</td>
				</tr>
			</table>
			
			
						
			<s:if test="#request.list == null || #request.list.size() == 0">
				<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table border="1" cellspacing="0" cellpadding="0" width="100%" height="">
					<tr>
						<td style="color: red ; font-weight: bolder;"><center>目前没有出库单！</center></td>
					</tr>
				</table>
			</s:if>
			<s:else>
				<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="0"  bgcolor="gray">
					<tr>
			  			<td  width="100%" colspan="1">
			  				<table  border="0" cellspacing="1" cellpadding="2" width="100%">
				  				<tr>
									<td  width="5%"  class="headerbar82">序号</td>
									<td  width="15%"  class="headerbar82">单据编号</td>
									<td  width="20%" class="headerbar82">所出仓库</td>
									<td  width="15%" class="headerbar82">出库日期</td>					
									<td  width="15%" class="headerbar82">接收人</td>
									<td  width="25%" class="headerbar82">发往地址</td>
									<td  class="headerbar82">操作</td>			
								</tr>
								
								<s:iterator value="#request.list.list" status="status">
								
									<s:if test="#status.odd">
										<tr>
											<td class="gridbar11" align="center">${id }</td>
											<td class="gridbar11" align="center"><a href="<%=basePath%>/outOrder/findAllStore.action?id=${id}&model=update">${outNo }</a></td>
											<td class="gridbar11" align="center">${storePO.storename }</td>
											<td class="gridbar11" align="center">${outDate }</td>
											<td class="gridbar11" align="center">${receivePerson }</td>
											<td class="gridbar11" align="center">${address }</td>
											<td class="gridbar11" align="center">
												<a href="<%=basePath%>/outOrder/del.action?id=${id}" class="delete">
													<input type="hidden" value="${id }">
													<img src="<%=basePath%>/image/del.gif" align="bottom" border="0" alt="作废" />
												</a>
											</td>
										</tr>
									</s:if>
									
									<s:else>
										<tr>
											<td class="gridbar01" align="center">${id }</td>
											<td class="gridbar01" align="center"><a href="<%=basePath%>/outOrder/findAllStore.action?id=${id}&model=update ">${outNo }</a></td>
											<td class="gridbar01" align="center">${storePO.storename }</td>
											<td class="gridbar01" align="center">${outDate }</td>
											<td class="gridbar01" align="center">${receivePerson }</td>
											<td class="gridbar01" align="center">${address }</td>
											<td class="gridbar01" align="center">
												<a href="<%=basePath%>/outOrder/del.action?id=${id}" class="delete">
													<input type="hidden" value="${outNo }">
													<img src="<%=basePath%>/image/del.gif" align="bottom" border="0" alt="作废" />
												</a>
											</td>
										</tr>
									</s:else>
								</s:iterator>
			  				</table>
			  			</td>
					</tr>
				</table>
			</s:else>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="2">
				<tr>
				  	<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
				  		共<span id="allRows">${list.totalItemNumber }</span>条
					  	&nbsp;&nbsp;
					  	第
					  		<s:property value="#request.list.pageNo"/>/
					  		<s:property value="#request.list.totalPageNumber"/>
					  	页
						&nbsp;&nbsp;
							<s:if test="#request.model == 'findAll'">
								<a href="<%=basePath%>/outOrder/findAll.action?method=findAll&pageNo=1">首页</a>
							</s:if>
							<s:if test="#request.model == 'findPart'">
								<a href="<%=basePath%>/outOrder/findPart.action?method=findAll&pageNo=1">首页</a>
							</s:if>
						&nbsp;&nbsp;
							<s:if test="#request.model == 'findAll'">
								<a href="<%=basePath%>/outOrder/findAll.action?pageNo=<s:property value='#request.list.getPrevPage()'/>">上一页</a>
							</s:if>
							<s:if test="#request.model == 'findPart'">
								<a href="<%=basePath%>/outOrder/findPart.action?pageNo=<s:property value='#request.list.getPrevPage()'/>">上一页</a>
							</s:if>
						&nbsp;&nbsp;
							<s:if test="#request.model == 'findAll'">
								<a href="<%=basePath%>/outOrder/findAll.action?pageNo=<s:property value='#request.list.getNextPage()'/>">下一页</a>
							</s:if>
							<s:if test="#request.model == 'findPart'">
								<a href="<%=basePath%>/outOrder/findPart.action?pageNo=<s:property value='#request.list.getNextPage()'/>">下一页</a>
							</s:if>
							
						&nbsp;&nbsp;
							<s:if test="#request.model == 'findAll'">
								<a href="<%=basePath%>/outOrder/findAll.action?pageNo=<s:property value='#request.list.getTotalPageNumber()'/>">末页</a>
							</s:if>
							<s:if test="#request.model == 'findPart'">
								<a href="<%=basePath%>/outOrder/findPart.action?pageNo=<s:property value='#request.list.getTotalPageNumber()'/>">末页</a>
							</s:if>
					</td>
			  	</tr>
			</table>
		</form>
		<div id="div" style="color: black; display:none; border: 1px; width: 200px; height: 50px ; 
			background-color: yellow ; z-index: 1; position: absolute; left: 400px;
			top: 180px; padding-top: 15px; font-weight: bolder;font-size: 18px;padding-left: 55px">
		</div>
  	</body>
</html>
