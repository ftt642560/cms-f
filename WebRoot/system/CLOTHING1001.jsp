<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

	<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
	<META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
	<META HTTP-EQUIV="content-style-type" CONTENT="text/css">
	<title>日志查询</title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
	<script type="text/javascript" src="<%=basePath%>/js/page.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/cjcalendar.js"></script>
  </head>
  
	  <script language="javascript">
		var CalendarWebControl = new atCalendarControl();
	</script>
	<SCRIPT LANGUAGE="javaScript">
	<!--
	function jsgoto(strURL)
	{
		document.forms[0].action=strURL;
		document.forms[0].submit();
	}
	function del(id)
	{
		if(confirm("您确定删除该条记录？")){
			deleteclothing(id);
			alert("删除成功！");
			
		}
	}
	
	function doAdd(){
		<%session.removeAttribute("clothingpo");%>
		document.forms[0].action="<%=basePath%>/system/CLOTHING1002.jsp";
		document.forms[0].submit();

	}
	 
	-->
	</SCRIPT>
  
  
  <script language="javascript">
  		
  		//验证查询输入的条件，如果有条件为空，则添加span节点，提示必填
  		function addnode(text,tagid)
  		{
  			var validate_text=document.createElement("span");//创建span节点
			var textnode=document.createTextNode(text);	//创建一个text节点
			validate_text.style.color="red";//对span样式进行设置			
			validate_text.appendChild(textnode);//把text节点添加到span节点

					
			var validate=document.getElementById(tagid);//获取需要验证的节点
			var parentobj=validate.parentNode;	//获取验证节点的父节点							
			var childobj=parentobj.childNodes;	//获取父节点的所有儿子节点
			
			var spanexit=parentobj.getElementsByTagName("span");//检索节点中是否包含span节点,返回一个数组
			if(spanexit.length==0)//如果不存在span节点，则添加验证提示的span节点
			{
				parentobj.appendChild(validate_text);//添加一个span子节点
			}
			
			
			//parentobj.removeChild(childobj[1]);
			
			
			//parentobj.insertBefore(validate_clothnum_text,childobj[1]);	
  			
  		}
  		
  		//查询时，如果已经输入条件，则移除提示节点span
  		function removenode(tagid)
  		{
  			var validate=document.getElementById(tagid);
			var parentobj=validate.parentNode;								
			var childobj=parentobj.childNodes;	
			
			var spanexit=parentobj.getElementsByTagName("span");
			for(var i=0;i<spanexit.length;i++)
				parentobj.removeChild(spanexit[i]);//把验证提示节点span移除
			
  		}
  		
  		//查询货号之前，先对输入的条件进行验证
  		function queryclothing()
  		{
  			var clothnum=document.getElementById("clothnum").value;//获取clothnum输入框的内容
  			var type=document.getElementById("type").value;//获取type输入框的内容
		
			//获取色号选择框的内容
  			var selectcolor = document.getElementById("color"); //selectid
			var colorindex = selectcolor.selectedIndex; // 选中索引
			var colortext = selectcolor.options[colorindex].text; // 选中文本
			
			//获取大小选择框的内容
			var selectsize = document.getElementById("size");
			var sizeindex = selectsize.selectedIndex;
			var sizetext = selectsize.options[sizeindex].text;
		
			//对输入条件进行验证
			if(clothnum=="" || type=="" || colortext=="请选择" || sizetext=="请选择")
			{
				
					/*
					//添加一个span结点在input后面，验证提示
					var validate_clothnum_text=document.createElement("span");
					var textnode=document.createTextNode("必填");	
					validate_clothnum_text.style.color="red";				
					validate_clothnum_text.appendChild(textnode);

					
					var clothnum_validate=document.getElementById("clothnum");
					var parentobj=clothnum_validate.parentNode;					
					var childobj=parentobj.childNodes;	
								
					//parentobj.removeChild(childobj[1]);
					parentobj.appendChild(validate_clothnum_text);
					//parentobj.insertBefore(validate_clothnum_text,childobj[1]);	
					*/
					
					//验证货号输入框
				if(clothnum=="")
				{
					addnode("必填","clothnum");
					
				}
				else{
					removenode("clothnum");
				}	
				
				//验证品名输入框
				if(type=="")
				{
					addnode("必填","type");				
				}
				else{
					removenode("type");
				}	
				
				
				//验证色号选择框
				if(colortext=="请选择")
				{
					addnode("必选","color");
				}
				else{
					removenode("color");
				}
				
				//验证尺码选择框
				if(sizetext=="请选择")
				{
					addnode("必选","size");
				}
				else{
					removenode("size");
				}
				
					
					return false;		
								
			}
				
				
			else{
			
  				var targetForm=document.forms[0];
				//动态修改表单的action属性
				targetForm.action="zlinclothing/findclothing.action?clothnum="+clothnum+
				"&type="+encodeURI(encodeURI(type))+"&color="+encodeURI(encodeURI(colortext))+"&size="+sizetext;
				//document.forms[0].submit();
				return true;
			}	
  		}
  		
		
		
  </script>
  
  
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
    	<FORM NAME="idFrmMain" ID="idmig0101" METHOD="POST"  ACTION="" ONSUBMIT="" >

<table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
<tr>
	<td class="headerbar61">货号查询</td>
    <td class="headerbar63" width="50%" colspan="1"><p align="right">

    	<input type=submit value=" 查 询 " onClick="return queryclothing();" id="querysubmit"></p></td>

  </tr>
<!-- </tr>-->
</table>

<table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
<tr>
	<td></td>
</tr>
</table>

<table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
<tr>

    <td class="textbar81" width="15%">货号</td>
		<td class="textbar01" width="35%">			
			<input type="text" name="hh" value="" style="width:210px "  id="clothnum" />	 
		</td>    
	<td class="textbar81" width="15%">品名</td>
		<td class="textbar01" width="35%">			
			<input type="text" name="hh" value="" style="width:210px " id="type" />	  
		</td>    
</tr>   
   <tr> 
            <td width="15%" class="textbar81">色号</td> 
            <td class="textbar01" width="35%"> 
            <select name="" style="width:210px " id="color"> 
               <option value="" selected="selected">请选择</option> 
                <option value="1">大红色</option> 
                <option value="2">浅红色</option> 
                <option value="3">紫红色</option> 
				<option value="4">纯白色</option> 
                <option value="5">米白色</option> 
				<option value="6">深蓝色</option> 
                <option value="7">淡蓝色</option> 
				<option value="8">黑色</option> 
                <option value="9">棕色</option> 
              </select>
              </td> 
         
            <td width="15%" class="textbar81">尺码</td> 
            <td class="textbar01" width="35%"> <select name="" style="width:210px " id="size"> 
               <option value="" selected="selected">请选择</option> 
                <option value="150">150</option> 
                <option value="155">155</option> 
                <option value="160">160</option> 
				<option value="165">165</option> 
                <option value="170">170</option> 
				<option value="175">175</option> 
                <option value="180">180</option> 
				<option value="185">185</option> 
                <option value="190">190</option> 
              </select> </td>            
          </tr>  
</table>

<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
<tr>
	<td></td>
</tr>
</table>

<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
	<tr>
  	<td class="headerbar61" width="50%" colspan="1">货号明细</td>
    <td class="headerbar63" width="50%" colspan="1"><p align="right">
    	<input type=submit value=" 新 增 " onClick="JavaScript:doAdd();"></p></td>
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
 
  
  <!-- 改变clothingtable 行的样式，奇数行显示一个样式，偶数行显示一个样式 -->	
  	<script language="javascript">
  		window.onload=function(){
  		chtd();  			
  		}
  		
  		
  	//	function setpagenum(){
        	//	var text='${sessionScope.pageBean.totalPage}';
        	//	alert("text="+text);
        	//	var totalpage=document.getElementById("totalpage");
        	//	var textnode=document.createTextNode(text);	//创建一个text节点					
			//	totalpage.appendChild(textnode);//把text节点添加到span节点
        //	}
  		
  		
  		function chtd()
  		{
  			var clothingtable=document.getElementById("clothingtable");
  			var trs = clothingtable.getElementsByTagName("tr");			
  			var i=1;
  			for(i=1;i<trs.length;i++)
  			{
 				var atr=trs[i];	
  				if( (i & 1) != 0) //通过位运算判定如果是奇数行
  				{				
  					var tds= atr.childNodes;
  					var j=0;
  					//tds[j].innerText=i;//设置clothingtable中的序号 
  					
  					//判断浏览器类型，判断是火狐或者IE浏览器
  					if(navigator.userAgent.indexOf("Firefox") != -1 || +[1,])
  					{
  						tds[j+1].textContent=i;	 
  					}
  					
  					else{
  						tds[j].innerText=i;//设置clothingtable中的序号 
  					}	
  										
 					for(j=0;j<tds.length;j++)//遍历行数的td子结点
  					{
  						atd=tds[j];  						
  						atd.className="gridbar01";						
  					}
  				}
  				//如果是偶数行
  				else{
  					var tds= atr.childNodes;
  					var z=0;
  					//tds[z].innerText=i;//设置clothingtable中的序号
  					//tds[z].innerContent=i;
  					
  					//判断浏览器是否 火狐 活着 IE 浏览器
  					if(navigator.userAgent.indexOf("Firefox") != -1 || +[1,])
  					{
  						tds[z+1].textContent=i;	 
  					}
  					
  					else{
  						tds[z].innerText=i;//设置clothingtable中的序号 
  					}	
  					
					
  					for(z=0;z<tds.length;z++)
  					{
  						atd=tds[z];
  						//alert("atd"+atd);
  						atd.className="gridbar11";
  						//atd.setAttribute("class","gridbar11");
  						
  					}
  				}
  			}
  			
  		}
  		
  	</script>
  						
  		<table  id="clothingtable" border="0" cellspacing="1" cellpadding="2" width="100%">
				<tr>
					<td  width="5%" class="headerbar82">序号</td>
					<td  width="10%" class="headerbar82">货号</td>
					<td  width="10%" class="headerbar82">色号</td>
					<td  width="10%" class="headerbar82">尺码</td>
					<td  width="15%" class="headerbar82">品名</td>
                    <td  width="10%" class="headerbar82">出厂价</td>
					<td  width="10%" class="headerbar82">零售价</td>
					<td   class="headerbar82">操作</td>
				</tr>
				
				<script language="javascript">
					function deleteclothing(id)
					{
						//alert("delete");
						//var targetForm=document.forms[0];
						//动态修改表单的action属性
						//targetForm.action="zlinclothing/deleteclothing.action?id="+id;
						window.location.href="<%=basePath%>/zlinclothing/deleteclothing.action?id="+id;

						
					}
					
					//去信息更新页面
					function gotoupdate(id)
					{
					//	alert("gotoupdate");
					//	alert("id="+id);

						window.location.href="<%=basePath%>/zlinclothing/findaclothing.action?id="+id;
					}
				</script>
				
			
				<s:iterator value="clothinglist" id="clothinglist" status="status">
					<!--  <tr <s:if test="#status.even">style="color:#000000;background:#FFFFCC;font-family:宋体,Verdana,Arial;font-size:9pt;line-height:16px;"</s:if>
						<s:elseif test="#status.odd">style="color:#000000;background-color:#FFFFFF;font-family:宋体,Verdana,Arial;font-size:9pt;line-height:16px;"</s:elseif> >-->
					<tr>	
						<td align="center">															
								<!--<s:property value="#clothinglist.id"/>	-->					
						</td>
						<td align="center">
							<a href="<%=basePath%>/system/CLOTHING1002.jsp"  onclick="gotoupdate(<s:property value="#clothinglist.id"/>);">
								<s:property value="#clothinglist.clothnum" />
							</a>		
						</td>
						<td align="center"><s:property value="#clothinglist.color"/></td>
						<td align="center"><s:property value="#clothinglist.size"/></td>
						<td align="center"><s:property value="#clothinglist.type"/></td>
						<td align="center"><s:property value="#clothinglist.factoryPrice"/></td>
						<td align="center"><s:property value="#clothinglist.retailPrice"/></td>
						<td align="center">
							<img src="<%=basePath%>/image/del.gif" align="bottom" border="0" alt="删除" onClick="del(<s:property value="#clothinglist.id"/>);"  style="cursor:hand"/>
						</td>
					</tr>
				</s:iterator>


		</table>
		
	  </td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="1" cellspacing="2" >
        	<tr>
          	<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							&nbsp; 共<s:property value="#session.pageBean.allRows"/>条 &nbsp; 
							<s:if test="#session.pageBean.allRows==0">第0</s:if>
							<s:else>第<s:property value="#session.pageBean.currentPage"/></s:else>/<s:property value="#session.pageBean.totalPage"/>页 &nbsp;      				
		<s:if test="#session.pageBean.currentPage == 1">
         		 首页&nbsp;&nbsp;&nbsp;上一页
      	</s:if>
      				  
      	<s:else>
      		<s:if test="#session.clothingPageFunc=='findallclothing'">
           		 <a  name="homepage" href="<%=basePath%>/zlinclothing/findallclothing.action?page=0">首页</a>
           		 &nbsp;&nbsp;&nbsp;
           		 <a id="prepage" href="<%=basePath%>/zlinclothing/findallclothing.action?page=<s:property value="#session.pageBean.currentPage - 1"/>" >上一页</a>
            </s:if>
            
            <s:if test="#session.clothingPageFunc=='findclothing'">
            		 <a  name="homepage" href="<%=basePath%>/zlinclothing/findclothing.action">首页</a>
           			&nbsp;&nbsp;&nbsp;
           		 	<a id="prepage" href="<%=basePath%>/zlinclothing/findclothing.action?page=<s:property value="#session.pageBean.currentPage - 1"/>" >上一页</a>
            </s:if>
            
            
        </s:else>
        
        <s:if test="#session.pageBean.currentPage != #session.pageBean.totalPage">
        	<s:if test="#session.clothingPageFunc=='findallclothing'">
        		<s:if test="#session.pageBean.totalPage==0">
          			  下一页&nbsp;&nbsp;&nbsp;尾页
          		</s:if>
          		
          		<s:else>
           		 <a id="nextpage" href="<%=basePath%>/zlinclothing/findallclothing.action?page=<s:property value="#session.pageBean.currentPage + 1"/>">下一页</a>
          		  &nbsp;&nbsp;&nbsp;
         		   <a id="lastpage" href="<%=basePath%>/zlinclothing/findallclothing.action?page=<s:property value="#session.pageBean.totalPage"/>">尾页</a>
       			</s:else>
       		</s:if>
       		
       		<s:if test="#session.clothingPageFunc=='findclothing'">
       			<s:if test="#session.pageBean.totalPage==0">
          			  下一页&nbsp;&nbsp;&nbsp;尾页
          		</s:if>
       		
       			<s:else>
       				 <a id="nextpage" href="<%=basePath%>/zlinclothing/findclothing.action?page=<s:property value="#session.pageBean.currentPage + 1"/>">下一页</a>
          			  &nbsp;&nbsp;&nbsp;
         		 	  <a id="lastpage" href="<%=basePath%>/zlinclothing/findclothing.action?page=<s:property value="#session.pageBean.totalPage"/>">尾页</a>
       			</s:else>
       		</s:if>
       		
       		
        </s:if>
        
        <s:else>
          	  下一页&nbsp;&nbsp;&nbsp;尾页
        </s:else>
      				  
      				  
					
			</td>
          </tr>
        </table>
        
        
        
        
 

		<!-- 	</td>
		</tr>
		</table>-->
</FORM>
  </body>
</html>
