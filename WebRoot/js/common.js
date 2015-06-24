/*
================================================================================
公用Javascript函数，对所有页面生效
Create:Dongyang,20040920
================================================================================
*/

/**
* 屏蔽鼠标右键方法
*/

//if (window.Event)
//    document.captureEvents(Event.MOUSEUP);
//function nocontextmenu() {
//    event.cancelBubble = true
//    event.returnValue = false;
//    return false;
//}
//function norightclick(e) {
//    if (window.Event) {
//        if (e.which == 2 || e.which == 3)
//        return false;
//    }
//    else
//    if (event.button == 2 || event.button == 3){
//        event.cancelBubble = true
//        event.returnValue = false;
//        return false;
//    }
//}
//document.oncontextmenu = nocontextmenu; // for IE5+
//document.onmousedown = norightclick; // for all others
//



/**
* 图片变换脚本
* Begin
*/
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}
//End


//关闭窗口
function closeWin(){
    window.close();
}

//返回
function backWin() {
    history.back();
}

//刷新
function refreshFn(){
    window.location.reload();
}


/**
* 重置FORM的所有内容
* @param this Form:Form的对象,可以是Form的名称或当前this
* 调用方法:resetForm(this)
*/
function resetForm(objTmp)
{
    while (objTmp.parentElement.tagName != "FORM") {
        objTmp = objTmp.parentElement;
    }
    objTmp = objTmp.parentElement;
    objTmp.reset();
}


/**
* 清空FORM:循环将FORM中指定元素的Value为空
* @param elements string:参数中间用"/"分隔
* 调用方法:clearForm(element1/element2/element3)
*/
function clearForm(elements)
{
    for (i = 0; i < elements.split("/").length; i++) {
        var elementTmp = elements.split("/")[i];
        var thisElement = document.getElementById(elementTmp);
        thisElement.value = "";
    }
}


/**
* 弹出确认信息小窗口
* @param href string:页面的URL
* @param title string:页面中的内容
* @param winTips string:窗口的标题
* 调用方法:confirmFn("http://www.google.com","Google网站","Welcome Google")
*/
function confirmFn(href,title,winTips) {
    var obj = new Object();
    if (href.indexOf("?")==-1) {
        href=href+"?sID="+Math.random();
    } else {
        href=href+"&sID="+Math.random();
    }
    obj.href = URLEncode(href.replace(/'/g,"''"));
    obj.title = URLEncode(title.replace(/'/g,"''"));
    obj.winTips = URLEncode(winTips.replace(/'/g,"''"));
    var returns=window.showModalDialog("./frame/confirmFrame.jsp?rnd="+Math.random(),obj,"dialogWidth:400px;dialogHeight:280px;center:1;scroll:0;help:0;status:0;resizable:0;edge:raised");
    if (returns=="OK"){
        window.location.reload();
    }
}


/**
* 转换URL中的字符
* @param url string:URL地址
*/
function URLEncode(url){
    var sReturn=url;
    sReturn=sReturn.replace(/\%/g,"%25");
    sReturn=sReturn.replace(/\?/g,"%3F");
    sReturn=sReturn.replace(/&/g,"%26");
    sReturn=sReturn.replace(/\"/g,"%22");
    sReturn=sReturn.replace(/\'/g,"%27");
    sReturn=sReturn.replace(/=/g,"%3D");
    sReturn=sReturn.replace(/\+/g,"%2B");
    return (sReturn);
}

/**
* 打开帮助窗口
* @param url string:页面的URL地址
*/
function showHelpWin(url)
{
    var width = 300;
    var height = 500;
    var left = screen.width-width-5;
    var top = 0;
    var properties = "toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no,width=" + width +",height=" + height + ",left=" + left +",top=" + top;
    openwin = window.open(url,'APMSHelp',properties);
    return openwin;
}


/**
* 去处字符串首尾空格
* @param strInput string:源字符串
* @return string:新的字符串
*/
function trim(strInput) {
    if (strInput != null) {
        return strInput.replace(/(^\s*)|(\s*$)/g,"");
    } else {
        return "";
    }
}


/**
* 判断字符串是否为数字
* @param ex string:输入的字符串
* @return boolean:false/true
*/
function isNumb(ex)
{
    if (ex.match(/^(\d)*$/g) == null){
        return false;
    }else{
        return true;
    }
}


/**
* 是否为x-y范围之间的整型数字
* @param ex string:输入的字符串
* @param x int:范围的开始
* @param y int:范围的结束
* @return boolean:false/true
*/
function isValidNumberBound(ex,x,y){
    if(ex == "") return false;
    if(ex >= x && ex <= y){
        return true;
    } else{
        return false;
    }
}


/**
* IP地址校验
* @param ipObject string:IP地址
* @return boolean:false/true
*/
function checkIpRule(ipObject){
    var str=trim(ipObject.value);
    var sip=str.split(".");
    if (str==""){
        alert('IP地址内容不能为空！');
        ipObject.focus();
        return false;
    }
    if (sip.length!=4){
        alert('IP地址必须是由\'.\'分隔的4段');
        return false;
    } else if(trim(sip[0]).length>3 || trim(sip[1]).length>3 || trim(sip[2]).length>3 || trim(sip[3]).length>3 || trim(sip[0]).length==0 ||trim(sip[1]).length==0 ||trim(sip[2]).length==0 ||trim(sip[3]).length==0 ){
        alert('IP地址每段长度必须在1-3之间！');
        ipObject.focus();
        return false;
    } else if (isNaN(sip[0]) || isNaN(sip[1]) || isNaN(sip[2]) || isNaN(sip[3])){
        alert('IP地址的每段必须是由整形数字组成！');
        ipObject.focus();
        return false;
    } else if (parseInt(sip[0])<0 || parseInt(sip[0])>255 || parseInt(sip[1])<0 || parseInt(sip[1])>255 || parseInt(sip[2])<0 || parseInt(sip[2])>255 || parseInt(sip[3])<0 || parseInt(sip[3])>255  ){
        alert('IP地址的每段必须是由0-255整形数字组成！');
        ipObject.focus();
        return false;
    } else {
        var ips0=parseInt(sip[0]);
        var ips1=parseInt(sip[1]);
        var ips2=parseInt(sip[2]);
        var ips3=parseInt(sip[3]);
        ipObject.value=ips0+"."+ips1+"."+ips2+"."+ips3;
    }
    return true;
}


/**
* 时间比较函数
* @param BeginDate string:起始日期
* @param EndDate string:结束日期
* @return boolean:false/true
*/
function fnDateCompare(BeginDate,EndDate) {
    var objBeginDate = document.getElementById(BeginDate);
    var objEndDate = document.getElementById(EndDate);
    if(objBeginDate.value!=""&&objEndDate.value!="") {
        if(objBeginDate.value>objEndDate.value) {
            alert("起始日期不能大于结束日期");
            return false;
        } else {
            return true;
        }
    }
}


/**
* 日期格式化函数
* @param CurDate string:输入的日期
* @param DefaultVar string:格式(D/T/DT)
*/
function fnCurDate(CurDate,DefaultVar) {
    var objCurDate = document.getElementById(CurDate);
    var objDate = new Date();
    var curYear = parseInt(objDate.getYear()).toString();
    var curMonth = parseInt(objDate.getMonth()+1).toString();
    var curDay = parseInt(objDate.getDate()).toString();
    var curHour = parseInt(objDate.getHours()).toString();
    var curMinute = parseInt(objDate.getMinutes()).toString();
    var curSecond = parseInt(objDate.getSeconds()).toString();

    if(curMonth.length==1) {
        curMonth = "0" + curMonth;
    }
    if(curDay.length==1) {
        curDay = "0" + curDay;
    }
    if(curHour.length==1) {
        curHour = "0" + curHour;
    }
    if(curMinute.length==1) {
        curMinute = "0" + curMinute;
    }
    if(curSecond.length==1) {
        curSecond = "0" + curSecond;
    }

    switch(DefaultVar)  {
    case "D":
        objCurDate.value = curYear + "-" + curMonth + "-" + curDay;
        break;
    case "DT":
        objCurDate.value = curYear + "-" + curMonth + "-" + curDay + " " + curHour + ":" + curMinute + ":" + curSecond;
        break;
    case "T":
        objCurDate.value = curHour + ":" + curMinute + ":" +curSecond;
        break;
    default:
        objCurDate.value = curYear + "-" + curMonth + "-" + curDay;
        break;
    }
}


/*
* 用于判断一个数字型字符串是否为整形，还可判断是否是正整数或负整数，返回值为true或false
* @param string string: 需要判断的字符串
* @param sign: 若要判断是正负数是使用，是正用+，负-，不用则表示不作判断
* @return boolean:true/false
* 调用方法:IsInteger(a,'+')判断是否为正整数
*/
function IsInteger(string,sign) {
    var integer;
    if ((sign!=null) && (sign!='-') && (sign!='+')) {
        alert('IsInter(string,sign)的参数出错： sign为null或"-"或"+"');
        return false;
    }
    integer = parseInt(string);
    if (isNaN(integer)) {
        return false;
    } else if (integer.toString().length==string.length) {
        if ((sign==null) || (sign=='-' && integer<0) || (sign=='+' && integer>0)) {
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}


/**
* 检测包含非法字符
* @param str string:输入的字符串
* @param charset string:包含的字符串
* @return boolean:true/false
*/
function contain(str,charset) {
    var i;
    for(i=0;i<charset.length;i++)
        if(str.indexOf(charset.charAt(i))>=0)
        return true;
        return false;
}


/**
* 字符串不可包含这些字符
* @param str string:输入的字符串
* @return boolean:true/false
*/
function validChar(str) {
    if ((contain(str, "%\(\)><*$^~\'?\"|!$#&"))) {
        return false;
    }
    return true;
}


/*
* 替换字符函数
* @param s1 string:原始字符串
* @param s2 string:将被替换的旧字符串
* @param s3 string:新字符串
* @return string:替换后的字符串
*/
function replaceAll(s1,s2,s3){
    var len1 , len2 , i;
    var str1 , str2 ;
    str1 = s1;
    len1 = str1.length;
    len2 = s2.length;
    if (len2 > len1){
        return s1;
    }
    else{
        for (i=1;i<= len1-len2+1 ; i++){
            str2 = str1.substring(i-1,i-1+len2);
            if (str2 == s2){
                str1 = str1.substring(0,i-1) + s3 + str1.substring(i+len2-1,len1 );
                i = 0;
                len1 = str1.length;
            }
        }
        return str1;
    }
}


/**
* 根据不同的角色权限以不同的方式退出用户自服务页面
* @param roleCode string:角色(admin/domain)
*/
function logoutSelfService(roleCode) {
    var roleCode = trim(roleCode);
    if(roleCode=="admin" || roleCode=="domain") {
        window.close();
    } else {
        location.href = "./logoutAction.do";
    }
}


/**
* 操作工具栏的关闭和打开
*/
function switchSysBar(){
    if (switchPoint.innerText=="3"){
            switchPoint.innerText="4"
            document.all("frmMenu").style.display="none"
    }
    else{
            switchPoint.innerText="3"
            document.all("frmMenu").style.display=""
    }
}


/**
* 获得某一个Radio控件的值
* @param radioArray obj:Radio控件
* 调用方法getRadioValue(document.radioName)
*/
function getRadioValue(radioArray){
    for (var i=0; i<radioArray.length; i++) { //radioArray.length是radio选项的个数
        if (radioArray[i].checked){
            return radioArray[i].value;
        }
    }
}
/**
* 添加附件的文件名输入
*/
fb_update = {
    addAffix : function (){
        var div_btn = event.srcElement.parentNode;
        var html = "";
        html += "<div><img src='../../images/delete.gif' style='cursor:hand' align='absmiddle' onclick='fb_update.removeAffix()'/> "+
                "<input name='AttachFilename' type='file' class='td_button' size='50' /></div>";
        div_btn.insertAdjacentHTML("beforeBegin",html);
    },
    removeAffix : function(){
        var div = event.srcElement.parentNode;
        var td = div.parentNode;
        td.removeChild(div);            
    }
}

//弹出对话框
function openDialog(WINurl,WINwidth,WINheight,xyPosition){
alert("1");
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

//打开窗口
function openWin(WINurl,WINwidth,WINheight,xyPosition){
	if(xyPosition==0){//屏幕中央   
		showx = (window.screen.availWidth  - WINwidth)/2;
    showy = (window.screen.availHeight - WINheight)/2;
	}else{//事件附近
  	showx = event.screenX - event.offsetX - 4 - WINwidth ; // + deltaX;
	  showy = event.screenY - event.offsetY + 18; // + deltaY;
	}
	newWINwidth = WINwidth + 4 + 18;
	var features =
		'width='  + newWINwidth  + ',' +
		'height=' + WINheight + ',' +
		'left='   + showx     + ',' +
		'top='    + showy     + ',' +
		'directories=no; location=no; menubar=no; status=no; toolbar=no;scrollbars=yes;Resizeable=no';
	var vWindow = window.open(WINurl, null, features);
	return vWindow;
}

//选择论坛下拉框
function forumLink(){
	var selectOption = document.all.forumList.options[document.all.forumList.selectedIndex].value;
	var directHref = selectOption + ".htm";
	window.location.href=directHref;
}


//显示审核人员输入框
function showModerationer(){
	var forumModerationMode = document.all.forumModerationMode.options[document.all.forumModerationMode.selectedIndex].value
	if (forumModerationMode == 1) {
			var moderationer = document.all.moderationer;
			moderationer.style.display=""
	} else {
			var moderationer = document.all.moderationer;
			moderationer.style.display="none"
	}
}

//鼠标放上的时候，列表行的样式class=“choose”
function moveClass(trId) {
	var objTr = document.getElementById(trId);
	var trClass=document.getElementById("trClass");
	if (trClass.value == "init") {
			trClass.value=objTr.className;
		}
	objTr.className="choose";
}

//鼠标离开后，列表行的样式恢复原样
function outClass(trId) {
	var trClass=document.getElementById("trClass");
	var objTr = document.getElementById(trId);
	if (trClass.value != "init") {
			objTr.className=trClass.value;
		}
	
	trClass.value="init";
}

//LearnBoard的main画面，收缩版面列表
function showhideCategory(category) {
  var objCategory = document.getElementById(category);
  if (objCategory == null) return;

  if ( objCategory.style.display == 'none' ) {
    objCategory.style.display = '';
  } else if ( objCategory.style.display == '' ) {
    objCategory.style.display = 'none';
  }
}