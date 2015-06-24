<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


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
	<title>品牌修改</title>
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
		if(newclothing())
		{
			alert("新建成功!");
		}
	}
	else if(updateclothing())
	{
		alert("修改成功！");

	}
	
}

function back()
{
	window.location.href="<%=basePath%>/zlinclothing/cleandatainsession.action";
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
			//alert("baocun");
			setdate();
		}
	}
	
	
	function checkpagefunc()
	{
		var id=null;
		
		//从session中获取ID值
		id='${sessionScope.clothingpo.id}';

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
  		
  		//对输入条件进行验证
  		function myvalidate()
  		{
  		var clothnum=document.getElementById("clothnum").value;//获取clothnum输入框的内容
 		var type=document.getElementById("type").value;//获取type输入框的内容
 		var fabric=document.getElementById("fabric").value;
 		var clothingMaterial=document.getElementById("clothingMaterial").value;
 		var factoryPrice=document.getElementById("factoryPrice").value;
		var retailPrice=document.getElementById("retailPrice").value;
		
		//获取色号选择框的内容
 		var selectcolor = document.getElementById("color"); //selectid
		var colorindex = selectcolor.selectedIndex; // 选中索引
		var colortext = selectcolor.options[colorindex].text; // 选中文本
		
		//获取大小选择框的内容
		var selectsize = document.getElementById("size");
		var sizeindex = selectsize.selectedIndex;
		var sizetext = selectsize.options[sizeindex].text;
  			if(clothnum=="" || type=="" || colortext=="请选择" || sizetext=="请选择" || fabric=="" || clothingMaterial=="" ||factoryPrice=="" ||retailPrice=="")
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
				
				//验证面料
				if(fabric=="")
				{
					addnode("必填","fabric");
				}
				else{
					removenode("fabric");
				}
				
				//验证里料
				if(clothingMaterial=="")
				{
					addnode("必填","clothingMaterial");
				}
				else{
					removenode("clothingMaterial");
				}
					
				//验证出厂价
				if(factoryPrice=="")
				{
					addnode("必填","factoryPrice");
				}
				else{
					removenode("factoryPrice");
				}
				
				//验证零售价
				if(retailPrice=="")
				{
					addnode("必填","retailPrice");
				}
				else{
					removenode("retailPrice");
				}
				return false;			
			}
			
			else{
				return true;
			}
		
  		}
  		
	
	
	//新建clothing
	function newclothing()
	{
		var clothnum=document.getElementById("clothnum").value;//获取clothnum输入框的内容
 		var type=document.getElementById("type").value;//获取type输入框的内容
 		var fabric=document.getElementById("fabric").value;
 		var clothingMaterial=document.getElementById("clothingMaterial").value;
 		var factoryPrice=document.getElementById("factoryPrice").value;
		var retailPrice=document.getElementById("retailPrice").value;
		
		//获取色号选择框的内容
 		var selectcolor = document.getElementById("color"); //selectid
		var colorindex = selectcolor.selectedIndex; // 选中索引
		var colortext = selectcolor.options[colorindex].text; // 选中文本
		
		//获取大小选择框的内容
		var selectsize = document.getElementById("size");
		var sizeindex = selectsize.selectedIndex;
		var sizetext = selectsize.options[sizeindex].text;
	
	
		/*var targetForm=document.forms[0];
		//动态修改表单的action属性
		targetForm.action="zlinclothing/newclothing.action?clothnum="+clothnum+
		"&type="+encodeURI(encodeURI(type))+"&color="+encodeURI(encodeURI(colortext))+"&size="+sizetext+
		"&fabric="+encodeURI(encodeURI(fabric))+"&clothingMaterial="+encodeURI(encodeURI(clothingMaterial))+
		"&factoryPrice="+factoryPrice+"&retailPrice="+retailPrice;*/
		
		//对输入的内容进行验证，验证成功，才进行跳转，不成功则不跳转
		if(myvalidate())
		{
		window.location.href="<%=basePath%>/zlinclothing/newclothing.action?clothnum="+encodeURI(encodeURI(clothnum))+"&type="+encodeURI(encodeURI(type))+
		"&color="+encodeURI(encodeURI(colortext))+"&size="+encodeURI(encodeURI(sizetext))+
		"&fabric="+encodeURI(encodeURI(fabric))+"&clothingMaterial="+encodeURI(encodeURI(clothingMaterial))+
		"&factoryPrice="+encodeURI(encodeURI(factoryPrice))+"&retailPrice="+encodeURI(encodeURI(retailPrice));
		return true;		
		}	
		else{
		return false;
		}
	
	}
	
	//更新货号
	function updateclothing()
	{
		var clothnum=document.getElementById("clothnum").value;//获取clothnum输入框的内容
 		var type=document.getElementById("type").value;//获取type输入框的内容
 		var fabric=document.getElementById("fabric").value;
 		var clothingMaterial=document.getElementById("clothingMaterial").value;
 		var factoryPrice=document.getElementById("factoryPrice").value;
		var retailPrice=document.getElementById("retailPrice").value;
		
		//获取色号选择框的内容
 		var selectcolor = document.getElementById("color"); //selectid
		var colorindex = selectcolor.selectedIndex; // 选中索引
		var colortext = selectcolor.options[colorindex].text; // 选中文本
		
		//获取大小选择框的内容
		var selectsize = document.getElementById("size");
		var sizeindex = selectsize.selectedIndex;
		var sizetext = selectsize.options[sizeindex].text;
			
		//对输入的内容进行验证，验证成功，才进行跳转，不成功则不跳转
		if(myvalidate())
		{
		window.location.href="<%=basePath%>/zlinclothing/updateclothing.action?clothnum="+encodeURI(encodeURI(clothnum))+"&type="+encodeURI(encodeURI(type))+
		"&color="+encodeURI(encodeURI(colortext))+"&size="+encodeURI(encodeURI(sizetext))+
		"&fabric="+encodeURI(encodeURI(fabric))+"&clothingMaterial="+encodeURI(encodeURI(clothingMaterial))+
		"&factoryPrice="+encodeURI(encodeURI(factoryPrice))+"&retailPrice="+encodeURI(encodeURI(retailPrice));
		return true;		
		}	
		else{
		return false;
		}

		//var targetForm=document.forms[0];
		//动态修改表单的action属性
		
		//targetForm.action="zlinclothing/updateclothing.action?clothnum="+clothnum+
		//"&type="+encodeURI(encodeURI(type))+"&color="+encodeURI(encodeURI(colortext))+"&size="+sizetext+
		//"&fabric="+encodeURI(encodeURI(fabric))+"&clothingMaterial="+encodeURI(encodeURI(clothingMaterial))+
		//"&factoryPrice="+encodeURI(encodeURI(factoryPrice))+"&retailPrice="+encodeURI(encodeURI(retailPrice));
		
		
	}
	
</script>

	<script language="javascript">
		function setdate()
		{
			var id="${clothingpo.id}";
			var clothnum="${clothingpo.clothnum}";
			var type="${clothingpo.type}";
			var color="${clothingpo.color}";
			//alert("color="+color);
			var size="${clothingpo.size}";
			var fabric="${clothingpo.fabric}";
			var clothingMaterial="${clothingpo.clothingMaterial}";
			var factoryPrice="${clothingpo.factoryPrice}";
			var retailPrice="${clothingpo.retailPrice}";			
			
			document.getElementById("clothnum").value=clothnum;
			document.getElementById("type").value=type;
			document.getElementById("color").value=color;
			document.getElementById("size").value=size;
			document.getElementById("fabric").value=fabric;
			document.getElementById("clothingMaterial").value=clothingMaterial;
			document.getElementById("factoryPrice").value=factoryPrice;
			document.getElementById("retailPrice").value=retailPrice;
		
		}
	</script>

  
  <body BACKGROUND="<%=basePath%>/image/bg.gif">
    	<FORM NAME="idFrmMain" ID="idFrmMain" METHOD="POST"  ACTION="" ONSUBMIT=""> 

 
  <table border="0" width="100%"> 
    <tr> 
      <td width="100%" colspan="0" rowspan="0" align="center" valign="center"> <table border="0" width="100%" id="table1" cellspacing="0"  cellpadding="2"  bgcolor="gray"> 
          <tr> 
            <td class="headerbar61" width="50%">货号详细</td> 
            <td class="headerbar63" width="50%"> <input type="button" name="save70302002" onClick="javascript:save();" value=" 新 建 " id="okbutton" > 
            
              <input type="button" name="save70302002" onClick="javascript:back()" value=" 返 回 "> 
&nbsp; </td> 
          </tr> 
        </table></td> 
    </tr> 
    <tr> 
      <td width="100%" colspan="0" rowspan="0" align="center" valign="center"> <table border="0" width="100%" id="table1" cellspacing="1"  cellpadding="2"  bgcolor="gray"> 
          <tr> 
           <td class="textbar81" width="15%">货号</td> 
            <td class="textbar01" width="35%"> 
            	<input type="text" value="" size="15" style="width:210px " id="clothnum"> 
           	</td>  
			 <td class="textbar81" width="15%">品名</td> 
            <td class="textbar01" width="35%"> 
            	<input type="text" value="" size="15" style="width:210px " id="type"> 
           	</td> 
          </tr> 
          <tr> 
            <td width="15%" class="textbar81">色号</td> 
            <td class="textbar01" width="35%"> <select name="" style="width:210px " id="color"> 
               <option value="" selected="selected">请选择</option> 
                <option value="大红色">大红色</option> 
                <option value="浅红色">浅红色</option> 
                <option value="紫红色">紫红色</option> 
				<option value="纯白色">纯白色</option> 
                <option value="米白色">米白色</option> 
				<option value="深蓝色">深蓝色</option> 
                <option value="淡蓝色">淡蓝色</option> 
				<option value="黑色">黑色</option> 
                <option value="棕色">棕色</option> 
              </select></td> 
         
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
		   <tr> 
            <td width="15%" class="textbar81">面料</td> 
            <td class="textbar01" width="35%"> 
            	<input type="text" value="" size="15" style="width:210px " id="fabric"> 
           	</td> 
            <td class="textbar81" width="15%">里料</td> 
            <td class="textbar01" width="35%"> 
            	<input type="text" value="" size="15" style="width:210px " id="clothingMaterial"> 
           	</td> 
          </tr> 
          <tr> 
            <td width="15%" class="textbar81">出厂价</td> 
            <td class="textbar01" width="35%"> <input type="text" value="" size="15" style="width:210px " id="factoryPrice"> </td> 
            <td class="textbar81" width="15%">零售价</td> 
            <td class="textbar01" width="35%"> <input type="text" value="" size="15" style="width:210px " id="retailPrice"> </td> 
          </tr> 
          
 
        </table> 
        <table border=0 cellspacing=0 cellpadding=0 width="100%" height=5> 
          <tr> 
            <td></td> 
          </tr> 
        </table></td> 
    </tr> 
  </table> 
</FORM> 
  </body>
</html>

