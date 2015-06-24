<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
	<META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
	<META HTTP-EQUIV="content-style-type" CONTENT="text/css">
	<title>出库单</title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css"> 
	<script type="text/javascript" src="<%=basePath%>/js/cjcalendar.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/addFunction.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	
	<script language="javascript">
		var CalendarWebControl = new atCalendarControl();
	</script>
	
	<script type="text/javascript">
		$(function(){
				//1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消
				$(".deleteDetail").click(function(){
					var outNo = $(this).next(":input").val();//this代表a链接
					var flag = confirm("确定要删除"+ outNo +"的信息吗？");
					if(flag){//真的要删除
						var $tr = $(this).parent().parent();
						//执行删除，实行ajax的方法
						var url = this.href;
						var args = {"time":new Date()};
						$.post(url , args , function(data){//data如何返回
							//若data的返回值为1.则提示删除成功，且把当前行删除，
							if(data == 1){
								$tr.remove();
								document.getElementById('div').innerHTML="删除成功";
							}else{
								//若data的返回值不是1，提示删除失败
								document.getElementById('div').innerHTML="删除失败！";
							}
							$("#div").show();
						});
					}
					//(先需要)取消超链接的默认行为
					return false;
				});
			});
	</script>
	
	<script type="text/javascript">
		$(function(){
			$(".save").click(function(){
				var flag = true;
				if(flag){
						
					if (document.getElementById("rp").value=="") {
						var srp = document.getElementById('srp');
						srp.innerHTML = "<font color='red'>rp必须输入</font>";
						flag = false;
					}
					if (document.getElementById("rpp").value=="") {
						var srpp = document.getElementById('srpp');
						srpp.innerHTML = "<font color='red'>接收人电话必须输入</font>";
						flag = false;
					}
					if (document.getElementById("add").value=="") {
						var sadd = document.getElementById('sadd');
						sadd.innerHTML = "<font color='red'>接收人地址必须输入</font>";
						flag = false;
					}
				}
				if(flag){
					var id = $('#outId').val();
					var outNo = $('#outNo').val();
					var outDate = $('#frmWRPT_OPT_DATE2_PJT70302').val();
					var stores = $('#stores').val();
					var rp = $('#rp').val();
					var rpp = $('#rpp').val();
					var add = $('#add').val();
					var remark = document.getElementById('remark').value;
					
					if(id==0){
						$.ajax({
							type: 'POST',
			            	url: '<%=basePath%>/outOrder/outOrderSave.action',
			            	data: { "outOrder.outNo" : outNo  , "outOrder.outDate": outDate , "outOrder.storePO.id" : stores , "outOrder.receivePerson":rp , "outOrder.receivePhone" :rpp , "outOrder.address":add , "outOrder.remark" : remark , "method":"add"},
			            	success:function(data){
								var d = eval("("+data+")");
								for (var i=0;i<d.length;i++){ 
									var outOrderNo=  d[i];
									if(outOrderNo.outNo != null){
										$('#outOrderNo').val(outOrderNo.outNo);
										$("#div").text("添加成功！");
									}else{
										$("#div").text("添加失败！");
									}
									$("#div").show();
								}
			            	}
						});
					}else{
						$.ajax({
							type: 'POST',
			            	url: '<%=basePath%>/outOrder/outOrderSave.action',
			            	data: { "outOrder.outNo" : outNo , "outOrder.outDate": outDate , "outOrder.storePO.id" : stores , "outOrder.receivePerson":rp , "outOrder.receivePhone" :rpp , "outOrder.address":add , "outOrder.remark" : remark , "method":"update"},
			            	success:function(data){
				            	if(data == 1){
									$("#div").text("修改成功！");
								}else{
									$("#div").text("修改失败！");
								}
								$("#div").show();
			            	}
						});
					}
				}
			});
			
			$(".edit").click(function(){
				var flag = confirm("确定要查询要修改此信息吗？");
				if(flag){
					var outNo = $('#outNo').val();
					$.ajax({
						type : 'POST',
						url : '<%=basePath%>/outOrder/getOrderByOutNo.action',
						data : {"outOrder.outNo" : outNo},
						dataType : 'json',
						success : function(data){
							var d = eval("("+data+")");
							for (var i=0;i<d.length;i++){ 
								var order=  d[i];
								$('#add').val(order.address);
								$('#frmWRPT_OPT_DATE2_PJT70302').val(order.outDate);
								$("#stores").val(order.storePO.id);
								$("#stores").find("option:selected").text(order.storePO.storename);
								
								$("#rp").val(order.receivePerson);
								
								
								$("#rpp").val(order.receivePhone);
								$(document.getElementById('remark')).val(order.remark);
							}	
						}
					});
				}
			
			});
				
			
		});
		
		
		
	</script>
	
	
	<SCRIPT LANGUAGE="javaScript">
		function jsgoto(strURL)
		{
			document.forms[0].action=strURL;
			document.forms[0].submit();
		}
		
		function del(id)
		{
			if(confirm("您确定删除该条明细？")){
				alert("删除成功！");
			}
		}
		
		function goSearch(){
			document.forms[0].action="<%=basePath%>/stock/order3002.jsp";
			document.forms[0].submit();
		}
		
		function onblurRP(){
			if (document.getElementById("rp").value!="") {
				var srp = document.getElementById('srp');
				srp.innerHTML = "<font color='red'></font>";
			}
		}
		
		function onblurRPP(){
			if(document.getElementById("rpp").value != ""){
				var srpp = document.getElementById('srpp');
				srpp.innerHTML = "<font color='red'></font>";
			}
		}
		
		function onblurA(){
			if(document.getElementById("add").value != ""){
				var sadd = document.getElementById('sadd');
				sadd.innerHTML = "<font color='red'></font>";
			}
		}
	</SCRIPT>
  </head>
	
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
		<form name="form1" ID="idmig0101" method="post" action="" onsubmit="" >
			<table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
				<tr>
					<td class="headerbar61">出库单详细3000002</td>
					<td class="headerbar61">
						<p align="right">
							<input type=button value=" 保 存 " class = "save">
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
			
			<s:if test="#request.model == 'add'">
				<table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
					<tr>
						<td class="textbar81" width="15%">单据编号</td>
						<td class="textbar01" width="35%">
							<input type="hidden" name="outOrder.id" id="outId" value="0"/>
							<input type="text" id="outOrderNo" name="outOrder.outNo" value="自动生成" readonly="readonly"/>
					    </td>
					    <td class="textbar81" width="15%">单据日期</td>
						<td class="textbar01" width="35%">
							<input type="text" name="outOrder.outDate" id="frmWRPT_OPT_DATE2_PJT70302" value="2007-06-21" readonly="readonly" size="12">
							<img src="<%=basePath%>/image/calendar.gif" width="18" height="17" onClick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE2_PJT70302,'',forms[0].frmWRPT_OPT_DATE2_PJT70302);" title="显示日历" />
						</td>	
					</tr>
					<tr>
					    <td class="textbar81" width="15%">所出仓库</td>
						<td class="textbar01" width="35%">
							<select id="stores" name="outOrder.storePO.id" style="width:152px">
								<option id="options" value="" selected="selected"></option>
								<c:forEach items="${stores}" var="stores">
									<option value="${stores.id }">${stores.storename }</option> 
								</c:forEach>
							</select>
							<span style="color: red;" id="outStores"></span>
						</td>
						
						
					    <td class="textbar81" width="15%">接收人</td>
						<td class="textbar01" width="35%">
							<input id="rp" type="text" name="outOrder.receivePerson" style="width:200px" onblur="onblurRP()">
							<span id="srp"></span>
						</td>
					</tr>
					<tr>
					    <td class="textbar81" width="15%">接收人电话</td>
						<td class="textbar01" width="35%">
					    	<input id="rpp" type="text" name="outOrder.receivePhone" style="width:200px" onblur="onblurRPP()">  
					    	<span id="srpp"></span>
					    </td>    
					
					    <td class="textbar81" width="15%">发往地址</td>
						<td class="textbar01"  width="35%">
					       <input id="add" type="text" name="outOrder.address" value="" style="width:200px" onblur="onblurA()">    
					    	<span id="sadd"></span>
					    </td>     
					
					</tr>
					<tr>  
						<td class="textbar81" width="15%">备注</td>
					    <td class="textbar01" colspan="3" width="85%">
					    	<textarea id="remark" name="outOrder.remark" cols="80" rows="4"></textarea>    
					    </td>
					</tr>
				</table>
			</s:if>
			
			<s:if test="#request.model == 'update'">
				<table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
					<tr>
						<td class="textbar81" width="15%" style="color: red">单据编号</td>
						<td class="textbar01" width="35%">
							<input type="text" id ="outNo" name="outOrder.outNo" value="${outOrder.outNo } " readonly="readonly"/>
					    </td>
					    <td class="textbar81" width="15%">单据日期</td>
						<td class="textbar01" width="35%">
							<input type="text" name="outOrder.outDate" id="frmWRPT_OPT_DATE2_PJT70302" value="${outOrder.outDate }" readonly="readonly" size="12">
							<img src="<%=basePath%>/image/calendar.gif" width="18" height="17" onClick="CalendarWebControl.show(forms[0].frmWRPT_OPT_DATE2_PJT70302,'',forms[0].frmWRPT_OPT_DATE2_PJT70302);" title="显示日历" />
						</td>	
					</tr>
					<tr>
					    <td class="textbar81" width="15%">所出仓库</td>
						<td class="textbar01" width="35%">
							<select id="stores" name="outOrder.storePO.id" style="width:152px">
								<option id="options" value="" selected="selected">${outOrder.storePO.storename }</option>
								<c:forEach items="${stores}" var="stores">
									<option value="${stores.id }">${stores.storename }</option> 
								</c:forEach>
							</select>
							<span style="color: red;" id="outStores"></span>
						</td>
						
						
					    <td class="textbar81" width="15%">接收人</td>
						<td class="textbar01" width="35%">
							<input id="rp" value="${outOrder.receivePerson }" type="text" name="outOrder.receivePerson" style="width:200px" onblur="onblurRP()">
							<span id="srp"></span>
						</td>
					</tr>
					<tr>
					    <td class="textbar81" width="15%">接收人电话</td>
						<td class="textbar01" width="35%">
					    	<input id="rpp" type="text" name="outOrder.receivePhone" value="${outOrder.receivePhone }" style="width:200px" onblur="onblurRPP()">  
					    	<span id="srpp"></span>
					    </td>    
					
					    <td class="textbar81" width="15%">发往地址</td>
						<td class="textbar01"  width="35%">
					       <input id="add" type="text" name="outOrder.address" value="${outOrder.address }" style="width:200px" onblur="onblurA()">    
					    	<span id="sadd"></span>
					    </td>     
					
					</tr>
					<tr>  
						<td class="textbar81" width="15%">备注</td>
					    <td class="textbar01" colspan="3" width="85%">
					    	<textarea id="remark" name="outOrder.remark" cols="80" rows="4">${outOrder.remark }</textarea>    
					    </td>
					</tr>
				</table>
			</s:if>
		</form>
		
		<form >
			<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
			<tr>
				<td></td>
			</tr>
			</table>
			
			<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
				<tr>
			    	<td class="headerbar61"  colspan="1">出库单明细</td>
			        <td class="headerbar63"  colspan="1">
			        	<p align="right">
			            	<input type=button value=" 新增明细 " onClick="jsgoto('<%=basePath%>/outOrderDetail/findAllCloth.action?outNo=${outOrder.outNo }&id=${outOrder.id }&model=add');">
			            </p>
			        </td>                    
			    </tr>
			</table>
			
			<table id="tab0" border="0" cellspacing="1" cellpadding="2" width="100%" bgcolor="gray">
				<s:if test="#request.orderDetails == null || #request.orderDetails.size() == 0">
					<tr>
						<td>
							<center>没有出库单明细</center>
						</td>
					</tr>
				</s:if>
				
				<s:else>
					<tr>
						<td  width="5%" class="headerbar82">序号</td>
						<td  width="20%" class="headerbar82">货号</td>
						<td  width="20%" class="headerbar82">品名</td>
						<td  width="15%" class="headerbar82">色号</td>
						<td  width="15%" class="headerbar82">尺码</td>
						<td  width="15%" class="headerbar82">数量</td>		
						<td  class="headerbar82">操作</td>
					<tr>
					
					<s:iterator value="#request.orderDetails" status="details">
						<s:if test="#details.odd">
							<tr>
								<td   class="gridbar11" align="center">${id }</td>
								<td   class="gridbar11" align="center"><a href="<%=basePath%>/outOrderDetail/findAllCloth.action?id=${id}&model=update ">${goodsNo}</a></td>
								<td   class="gridbar11">${brandName }</td>
								<td   class="gridbar11">${colorName }</td>
								<td  class="gridbar11">${size }</td>
								<td   class="gridbar11" align="center">${number }</td>
								<td  class="gridbar11" align="center">
									<a href ="<%=basePath%>/outOrderDetail/delete.action?id=${id}" class="deleteDetail" onclick="del()">
										<input type="hidden" value="${id }">
										<img src="<%=basePath%>/image/del.gif" align="bottom" border="0" alt="作废" />
									</a>
								</td>	
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td   class="gridbar01" align="center">${id }</td>
								<td   class="gridbar01" align="center"><a href="<%=basePath%>/outOrderDetail/findAllCloth.action?id=${id}&model=update ">${goodsNo}</a></td>
								<td   class="gridbar01">${brandName }</td>
								<td   class="gridbar01">${colorName }</td>
								<td  class="gridbar01">${size }</td>
								<td   class="gridbar01" align="center">${number }</td>
								<td  class="gridbar01" align="center">
									<a href = "<%=basePath%>/outOrderDetail/delete.action?id=${id}" class="deleteDetail">
										<input type="hidden" value="${id }">
										<img src="<%=basePath%>/image/del.gif" align="bottom" border="0" alt="作废" />
									</a>
								</td>	
							</tr>
						</s:else>
					</s:iterator>
				</s:else>
			</table>
		</form>
		
		<div id="div" style="display:none; border: 1px;color: black; width: 200px; height: 50px ; 
			background-color: yellow ; z-index: 1; position: absolute; left: 400px;
			top: 180px; padding-top: 15px; font-weight: bolder;font-size: 18px; padding-left: 55px;">
		</div>
  </body>
</html>
