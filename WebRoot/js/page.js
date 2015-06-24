
function pageClick(name){
	n = document.all("pageNo").value;
	if(name=="first"){
		document.all("pageNo").value = 1;
	}
	if(name=="upper"){
		document.all("pageNo").value = n - 1;
	}
	if(name=="next"){
		document.all("pageNo").value = n-0+1;
	}
	if(name=="last"){
		document.all("pageNo").value = document.all("pageCount").value;
	}
	
	if(document.all("action")!=null)
		document.all("action").value = "self";
	else
		document.all("method").value = "init"
	document.forms[0].submit();
}

function ppup(src,pageTitle,pageHeight,pageWidth){
			var pageTop = window.event.clientY+document.body.scrollTop + 240;
			var pageLeft = window.event.clientX+document.body.scrollLeft -300;

			var pageStye = "dialogHeight: "+ pageHeight + "px; "
							+"dialogWidth: "+ pageWidth + "px; "
							+"dialogTop: "+pageTop+"px; "
							+"dialog Left: "+pageLeft+"px; "
							+" center: no; help: no;status:no;title:no";

			y=window.showModalDialog(
			src,
			pageTitle,
			pageStye
			);
}

//弹出对话框
function openDialog(WINurl,WINwidth,WINheight,xyPosition){
	var center = xyPosition==0?'yes':'no';
	if(xyPosition==0){//屏幕中央   
		showx = (window.screen.availWidth  - WINwidth)/2;
		showy = (window.screen.availHeight - WINheight)/2;
  }else{//事件附近
		showx = event.screenX - event.offsetX - 4 - WINwidth ; // + deltaX;
		showy = event.screenY - event.offsetY + 18; // + deltaY;
	}
	newWINwidth = WINwidth + 4 + 18;
	var features =
		'dialogWidth:'  + newWINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'help:off;resizable:yes;status:no;center:'+center+';';
	var vDialog = window.showModalDialog(WINurl, null, features);
	return vDialog;
}

function jsgoto(sur)
{
	document.forms[0].action=sur;
	document.forms[0].submit();
}

function menuGoto(sur,targetName)
{
	document.forms[0].action=sur;
	document.forms[0].target = targetName;
	document.forms[0].submit();
}


function cancel(id)
{
	if(confirm("您确定作废该条数据？")){
		alert("作废成功！");
	}
}
function save()
{
	alert("保存成功！");
}
function back()
{
	history.back();
}

//检查是否是一个合法的数字
function checknum(obj)
{
	if (isNaN(obj.value))
	{
		alert("请输入数字！");
		obj.value="";
		obj.focus();
		return;
	}
}

//审核
function checkPass(num)
{
	if(num==0)
	{
		if(confirm("您确定不通过该要货计划？")){
			alert("不通过该要货计划！");
		}
	}
	else if(num==1)
	{
		if(confirm("您确定通过该要货计划？")){
			alert("通过该要货计划！");
		}
	}
}

function check(id)
{
	if(confirm("您确定已经阅读了该通知吗？")){
			alert("设置为已经阅读状态！");
	}
}

