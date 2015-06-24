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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=gb2312">
	<META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
	<META HTTP-EQUIV="content-style-type" CONTENT="text/css">
	<title>出库单详细</title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
  </head>
  
	  <SCRIPT LANGUAGE="javaScript">
	  	$(function(){
	  		$(".save").click(function(){
				var flag = true;
				if(flag){
					if(document.getElementById('goodsNo').value==""){
						var sgoodsNo = document.getElementById('sgoodsNo');
						sgoodsNo.innerHTML = "<font color='red'>货号必须输入</font>";
						flag = false;
					}
					
					if(document.getElementById('brandName').value==""){
						var sbrandName = document.getElementById('sbrandName');
						sbrandName.innerHTML = "<font color='red'>品名必须输入</font>";
						flag = false;
					}
					
					if(document.getElementById('colorName').value==""){
						var scolorName = document.getElementById('scolorName');
						scolorName.innerHTML = "<font color='red'>色号必须输入</font>";
						flag = false;
					}
					
					if(document.getElementById('size').value==""){
						var ssize = document.getElementById('ssize');
						ssize.innerHTML = "<font color='red'>尺码必须输入</font>";
						flag = false;
					}
					
					if(document.getElementById("number").value==""){
						var snumber = document.getElementById('snumber');
						snumber.innerHTML= "<font color='red'>数量必须输入</font>";
						flag = false;
					}
				}
				
				var model = $("#model").val();
				if(model == "add"){
					if(flag){
						var myselect=document.getElementById("outNo"); 
						var index=myselect.selectedIndex ;
						var id = myselect.options[index].value;
						
						var myselect=document.getElementById("brandName"); 
						var index=myselect.selectedIndex ;
						var brandName = myselect.options[index].value;
						
						var myselect=document.getElementById("goodsNo"); 
						var index=myselect.selectedIndex ;
						var goodsNo = myselect.options[index].text; 
						
						var myselect=document.getElementById("colorName"); 
						var index=myselect.selectedIndex ;
						var colorName = myselect.options[index].text; 
						
						var myselect=document.getElementById("size"); 
						var index=myselect.selectedIndex ;
						var size = myselect.options[index].text; 
						
						var number = document.getElementById("number").value;
						
						$.ajax({
							  type: 'POST',
							  async: false,
							  dataType:"json",//设置需要返回的数据类型
						      url: '<%=basePath%>/outOrderDetail/save.action',
					          data :{"id" :id , "outOrderDetail.goodsNo" : goodsNo , "outOrderDetail.colorName":colorName , "outOrderDetail.size":size , "outOrderDetail.brandName":brandName , "outOrderDetail.number":number ,"model":"add" },
					          success:function(data){
	                        	  var d = eval("("+data+")");
								  for (var i=0;i<d.length;i++){ 
									var mess =  d[i];
									document.getElementById('div').innerHTML=mess.mess;
									$("#div").show();
								  }
					       	 }
						});
					}
				}
				
				if(model == "update"){
					if(flag){
						var number = $("#number").val();
						var detailId = $("#detailId").val();
						$.ajax({
							type : 'POST',
							async: false,
							dataType:"json",//设置需要返回的数据类型
							url: '<%=basePath%>/outOrderDetail/save.action',
					        data :{"number":number , "detailId":detailId ,  "model" : "update"},
					        success:function(data){
	                        	var d = eval("("+data+")");
							    for (var i=0;i<d.length;i++){ 
									var mess =  d[i];
									document.getElementById('div').innerHTML=mess.mess;
									$("#div").show();
								  }
					       	 }
						});
					}
				}
				
	  		});
	  	});
	  	
		function back()
		{
			history.back();
		}
		
		function oblurGoodsNo(){
			if(document.getElementById("goodsNo").value != ""){
				var sgoodsNo = document.getElementById('sgoodsNo');
				sgoodsNo.innerHTML = "<font color='red'></font>";
			}
		}
		
		function oblurBrandName(){
			if(document.getElementById("brandName").value != ""){
				var sbrandName = document.getElementById('sbrandName');
				sbrandName.innerHTML = "<font color='red'></font>";
			}
		}
		
		function onblurColorName(){
			if(document.getElementById("colorName").value != ""){
				var scolorName = document.getElementById('scolorName');
				scolorName.innerHTML = "<font color='red'></font>";
			}
		}
		
		function onblurSize(){
			if(document.getElementById("size").value != ""){
				var ssize = document.getElementById('ssize');
				ssize.innerHTML = "<font color='red'></font>";
			}
		}
		
		function onblurNumber(){
			if(document.getElementById("number").value != ""){
				var snumber = document.getElementById('snumber');
				snumber.innerHTML = "<font color='red'></font>";
			}
		}
		
		function onblurOutNo(){
			if(document.getElementById("outNo").value != ""){
					var soutNo = document.getElementById('soutNo');
					soutNo.innerHTML = "<font color='red'></font>";
			}
		}
	</SCRIPT>
  
  
  
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
	<FORM NAME="form1" ID="idmig0102" METHOD="POST"  ACTION="" ONSUBMIT=""> 
		<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
			<tr>
			  	<td class="headerbar61" width="15%" colspan="1">出库单详细</td>
			    <td class="headerbar63" width="85%" colspan="1">
			      <input type="button" name="save70302002" class="save" value=" 保 存 ">&nbsp;
			      <input type="button" name="back70302003" onClick="javascript:back()" value=" 返 回 ">
			    </td>
		  	</tr>
		</table>
		
		<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
			<tr>
				<td></td>
			</tr>
		</table>
		
		<s:if test="#request.model == 'update'">
			<table border="0" width="100%" id="table1" cellspacing="1"  cellpadding="2"  bgcolor="gray">
				<tr>
			  		<td class="textbar81" width="15%" colspan="1">货号</td>
			   		<td class="textbar01" width="85%" colspan="1">
			   			<input type="hidden" id="detailId" value="${outOrderDetailId }" readonly="readonly">
			   			<input type="hidden" id="model" value="update"/>
		    			<input id="goodsNo" name="outOrderDetail.goodsNo" readonly="readonly" value="${outOrderDetail.goodsNo }" style="width:210px;" type="text" onclick="oblurGoodsNo()" />
		   				<span id="sgoodsNo"></span>
			   		</td>
			   	</tr>
			   	<tr>
			  		<td class="textbar81" width="15%" colspan="1">品名</td>
			   		<td class="textbar01" width="85%" colspan="1">
			    		<input id="brandName" name="outOrderDetail.brandName" readonly="readonly" value="${outOrderDetail.brandName }" style="width:210px;" type="text"  onblur="oblurBrandName()" />
			    		<span id="sbrandName"></span>
			    	</td>
			  	</tr>
				
				<tr>
			  		<td class="textbar81" width="15%" colspan="1">色号</td>
			    	<td class="textbar01" width="85%" colspan="1">
			    		<input id="colorName" readonly="readonly" name="outOrderDetail.colorName" value="${outOrderDetail.colorName }" style="width:210px;" type="text"  onblur="onblurColorName()" />
			    		<span id="scolorName"></span>
			        </td>
			  	</tr>          
			  	<tr>
			  		<td class="textbar81" width="15%" colspan="1">尺码</td>
			    	<td class="textbar01" width="85%" colspan="1">
			    		<input id="size" readonly="readonly" name="outOrderDetail.colorName" value="${outOrderDetail.size }" style="width:210px;" type="text"  onblur="onblurSize()" />
			    		<span id="ssize"></span>
			        </td>
			  	</tr>  
			   
			  	<tr>
			  		<td class="textbar81" width="15%" colspan="1">出库单编号</td>
			  		<td class="textbar01" width="85%" colspan="1">
				    	<input id="outNo" readonly="readonly" name="id" value="${outNo }" style="width:210px;" type="text"  onblur="onblurOutNo()" />
			    		<span id="ssize"></span>
				    	<span id="soutNo"></span>
			  		</td>
			  	</tr>
			  	<tr>
				  	<td class="textbar81" width="15%" colspan="1">库存数量</td>
				    <td class="textbar01" width="85%" colspan="1">
				    	<input  name="leixing" readonly="readonly" value="${storeNumber }" style="width:210px;">
				    </td>
			  	</tr>
			  	
			  	<tr>
			  		<td class="textbar81" width="15%" colspan="1">出库数量</td>
			    	<td class="textbar01" width="85%" colspan="1">
			    		<input id="number" name="outOrderDetail.number" value="${outOrderDetail.number }" style="width:210px" onblur="onblurNumber();">
			    		<span id="snumber"></span>
			        </td>
			  	</tr>  
			</table>
		
			
		</s:if>
		
		<s:if test="#request.model == 'add'">
			<table border="0" width="100%" id="table1" cellspacing="1"  cellpadding="2"  bgcolor="gray">
				<tr>
			  		<td class="textbar81" width="15%" colspan="1">货号</td>
			   		<td class="textbar01" width="85%" colspan="1">
				   		<input type="hidden" id="model" value="add"/>
			    		<select id="goodsNo"  name="outOrderDetail.goodsNo"  style="width:210px;" onclick="oblurGoodsNo()">
			    			<option  value="" selected="selected"></option>
			    			<c:forEach items="${clothList }" var="clothList">
			    				<option value="${clothList.clothnum }">${clothList.clothnum }</option>
			    			</c:forEach>
			    		</select>
			    		<span id="sgoodsNo"></span>
			    	</td>
			  	</tr>
			  	
			  	<tr>
			  		<td class="textbar81" width="15%" colspan="1">品名</td>
			   		<td class="textbar01" width="85%" colspan="1">
			    		<select id="brandName" name="outOrderDetail.brandName"  style="width:210px;" onblur="oblurBrandName()">
			    			<option value="" selected="selected"></option>
			    			<c:forEach items="${clothList }" var="clothList">
			    				<option value="${clothList.type  }">${clothList.type }</option>
			    			</c:forEach>
			    		</select>
			    		<span id="sbrandName"></span>
			    	</td>
			  	</tr>
				
				<tr>
			  		<td class="textbar81" width="15%" colspan="1">色号</td>
			    	<td class="textbar01" width="85%" colspan="1">
			    		<select id="colorName" name="outOrderDetail.colorName" style="width: 210px" onblur="onblurColorName()">
			    			<option value="" selected="selected"></option>
			    			<c:forEach items="${clothList }" var="clothList">
			    				<option value="${clothList.color }">${clothList.color }</option>
			    			</c:forEach>
			    		</select>
			    		<span id="scolorName"></span>
			        </td>
			  	</tr>          
			  	<tr>
			  		<td class="textbar81" width="15%" colspan="1">尺码</td>
			    	<td class="textbar01" width="85%" colspan="1">
			    		<select id="size" name="outOrderDetail.size" style="width: 210px" onblur="onblurSize()">
			    			<option value="" selected="selected"></option>
			    			<c:forEach items="${clothList }" var="clothList">
			    				<option value="${clothList.size  }">${clothList.size }</option>
			    			</c:forEach>
			    		</select>
			    		<span id="ssize"></span>
			        </td>
			  	</tr>  
			   
			  	<tr>
			  		<td class="textbar81" width="15%" colspan="1">出库单编号</td>
			  		<td class="textbar01" width="85%" colspan="1">
			  			<select id="outNo" name="id" style="width: 210px" onblur="onblurOutNo()">
			  				
			  				<option value="${id }" selected="selected">${outNo }</option>
			  				
				    		<c:forEach items="${outOrderList }" var="outOrderList">
				    			<option value="${outOrderList.id }">${outOrderList.outNo }</option>
				    		</c:forEach>
				    	</select>
				    	<span id="soutNo"></span>
			  		</td>
			  	</tr>
			  	<tr>
				  	<td class="textbar81" width="15%" colspan="1">库存数量</td>
				    <td class="textbar01" width="85%" colspan="1">
				    	<input  name="leixing" value="${storeNumber }" style="width:210px;">
				    </td>
			  	</tr>
			  	
			  	<tr>
			  		<td class="textbar81" width="15%" colspan="1">出库数量</td>
			    	<td class="textbar01" width="85%" colspan="1">
			    		<input id="number" name="outOrderDetail.number" value="" style="width:210px" onblur="onblurNumber();">
			    		<span id="snumber"></span>
			        </td>
			        
			  	</tr>   
			  	        
			</table>
		</s:if>
		
		<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
			<tr>
				<td></td>
			</tr>
		</table>
		
	 </FORM>
	 
	 <center>
	 	<div  id="div"  style=" display:none; width:300px; height:40px; color: black; background-color: yellow ; font-weight: b; font-size: 18px; border: 1px ; border-radius : 10px; padding-top: 12px;">
	 	</div>
	 </center>
  </body>
</html>
