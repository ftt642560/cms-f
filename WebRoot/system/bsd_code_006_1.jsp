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
	<title> </title>
	<link rel="stylesheet" href="<%=basePath%>/css/cjpm.css">
	<script type="text/javascript" src="<%=basePath%>/js/cjcalendar.js"></script>
	<script language="javascript" src="<%=basePath%>/js/page.js"></script>
  </head>
  
	  <script language="javascript">
		var CalendarWebControl = new atCalendarControl();
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
	
	function save()
	{
			var okbutton=document.getElementById("okbutton");
			if(okbutton.value==" 新 建 ")
			{			
				if(newstore())
				{
					alert("新建成功!");
				}
			}
			else if(updatestore())
			{
					alert("修改成功！");

			}
			
	}
	
	function back()
	{
		window.location.href="<%=basePath%>/zlinstore/cleanstoreinsession.action";
		//history.back();
	}
	
	</SCRIPT>
	
	
	<script language="javascript">
	window.onload=function()
	{
		checkpagefunc();
		var okbutton=document.getElementById("okbutton");		
		if(okbutton.value==" 保 存 ")
		{
			setdate();
		}
	}
	
	
	function checkpagefunc()
	{
		var id=null;
		
		//从session中获取ID值
		id='${sessionScope.storepo.id}';

		var okbutton=document.getElementById("okbutton");
		
		if(id=="")
		{
			okbutton.value=" 新 建 ";
		}
		else
		{
			okbutton.value=" 保 存 ";
		}
	}
	
	
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
  		
  		//对输入条件进行验证
  		function myvalidate()
  		{
  		var storenum=document.getElementById("storenum").value;
 		var storename=document.getElementById("storename").value;
 		var linkman=document.getElementById("linkman").value;
 		var tele=document.getElementById("tele").value;
 		var storagevolume=document.getElementById("storagevolume").value;
		
  			if(storenum=="" || storename=="" || linkman=="" ||tele==""||storagevolume=="")
			{
				
				if(storenum=="")
				{
					addnode("必填","storenum");
					
				}
				else{
					removenode("storenum");
				}	
				
				
				if(storename=="")
				{
					addnode("必填","storename");				
				}
				else{
					removenode("storename");
				}	
				
				
				
				if(linkman=="")
				{
					addnode("必填","linkman");
				}
				else{
					removenode("linkman");
				}
				
				
				if(tele=="")
				{
					addnode("必填","tele");
				}
				else{
					removenode("tele");
				}
					
				
				if(storagevolume=="")
				{
					addnode("必填","storagevolume");
				}
				else{
					removenode("storagevolume");
				}
				
				return false;			
			}
			
			else{
				return true;
			}
		
  		}
  		
	
	
	//新建仓库
	function newstore()
	{
		var storenum=document.getElementById("storenum").value;
 		var storename=document.getElementById("storename").value;
 		var linkman=document.getElementById("linkman").value;
 		var tele=document.getElementById("tele").value;
 		var storagevolume=document.getElementById("storagevolume").value;
		
		//对输入的内容进行验证，验证成功，才进行跳转，不成功则不跳转
		if(myvalidate())
		{
		window.location.href="<%=basePath%>/zlinstore/newstore.action?storenum="+encodeURI(encodeURI(storenum))+"&storename="+encodeURI(encodeURI(storename))+
		"&linkman="+encodeURI(encodeURI(linkman))+"&tele="+encodeURI(encodeURI(tele))+
		"&storagevolume="+encodeURI(encodeURI(storagevolume));
		return true;		
		}	
		else{
		return false;
		}
	
	}
	
	//更新仓库
	function updatestore()
	{
		var storenum=document.getElementById("storenum").value;
 		var storename=document.getElementById("storename").value;
 		var linkman=document.getElementById("linkman").value;
 		var tele=document.getElementById("tele").value;
 		var storagevolume=document.getElementById("storagevolume").value;
		
		//对输入的内容进行验证，验证成功，才进行跳转，不成功则不跳转
		if(myvalidate())
		{
		window.location.href="<%=basePath%>/zlinstore/updatestore.action?storenum="+encodeURI(encodeURI(storenum))+"&storename="+encodeURI(encodeURI(storename))+
		"&linkman="+encodeURI(encodeURI(linkman))+"&tele="+encodeURI(encodeURI(tele))+
		"&storagevolume="+encodeURI(encodeURI(storagevolume));
		return true;		
		}	
		else{
		return false;
		}
		
	}
	
</script>

	<script language="javascript">
		function setdate()
		{
			var storenum="${storepo.storenum}";
			var storename="${storepo.storename}";
			var linkman="${storepo.linkman}";
			var tele="${storepo.tele}";
			var storagevolume="${storepo.storagevolume}";
	
			document.getElementById("storenum").value=storenum;
			document.getElementById("storename").value=storename;
			document.getElementById("linkman").value=linkman;
			document.getElementById("tele").value=tele;
			document.getElementById("storagevolume").value=storagevolume;
		
		}
	</script>
	
	
	
	
	
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
	 <FORM NAME="idFrmMain" ID="idFrmMain" METHOD="POST"  ACTION="" ONSUBMIT=""> 
		<table border="0" width="100%">
		    <tr>
		    	<td width="100%" colspan="0" rowspan="0" align="center" valign="center">
		      	<table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray">
		        	<tr>
		          	<td class="headerbar61" width="50%" colspan="1">仓库详细</td>
		            <td class="headerbar63" width="50%" colspan="1">
		            	<input type="button" name="save70302002" onclick="javascript:save()" value=" 保 存 " id="okbutton">
		              <input type="button" name="save70302002" onclick="javascript:back()" value=" 返 回 ">&nbsp;              
		            </td>
		          </tr>
		        </table>
		      </td>
		    </tr>
		
		    <tr>
		    	<td width="100%" colspan="0" rowspan="0" align="center" valign="center">
		      	<table border="0" width="100%" id="table1" cellspacing="1"  cellpadding="2"  bgcolor="gray">
		        	<tr>
		          	<td class="textbar81" width="15%" colspan="1">仓库编号</td>
		            <td class="textbar01" width="85%" colspan="1">
		            	<input type="text" value="" size="15" id="storenum">
		            </td>
		          </tr>
		
		         <tr>        	          
				<td class="textbar81" width="15%">仓库名称</td>
				<td class="textbar01" width="85%">
					<input type="text" value="" size="15" id="storename">						
				</td>         		
		         </tr>   
		
		
		         <tr>        	          
				<td class="textbar81" width="15%">联系人</td>
				<td class="textbar01" width="85%">
		 <input type="text" value="" size="15" id="linkman">								
				</td>         		
		         </tr>  
		
			            <tr>        	          
				<td class="textbar81" width="15%">联系电话</td>
				<td class="textbar01" width="85%">
		 <input type="text" value="" size="15" id="tele">								
				</td>         		
		         </tr>  
		         
		             <tr>        	          
				<td class="textbar81" width="15%">仓储量</td>
				<td class="textbar01" width="85%">
		 <input type="text" value="" size="15" id="storagevolume">								
				</td>         		
		         </tr>       	   	
		        </table>
						<table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
							<tr>
								<td></td>
							</tr>
						</table>
		      </td>
		    </tr>
		</table>
		</FORM>
  </body>
</html>
