/*
================================================================================
����Javascript������������ҳ����Ч
Create:Dongyang,20040920
================================================================================
*/

/**
* ��������Ҽ�����
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
* ͼƬ�任�ű�
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


//�رմ���
function closeWin(){
    window.close();
}

//����
function backWin() {
    history.back();
}

//ˢ��
function refreshFn(){
    window.location.reload();
}


/**
* ����FORM����������
* @param this Form:Form�Ķ���,������Form�����ƻ�ǰthis
* ���÷���:resetForm(this)
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
* ���FORM:ѭ����FORM��ָ��Ԫ�ص�ValueΪ��
* @param elements string:�����м���"/"�ָ�
* ���÷���:clearForm(element1/element2/element3)
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
* ����ȷ����ϢС����
* @param href string:ҳ���URL
* @param title string:ҳ���е�����
* @param winTips string:���ڵı���
* ���÷���:confirmFn("http://www.google.com","Google��վ","Welcome Google")
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
* ת��URL�е��ַ�
* @param url string:URL��ַ
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
* �򿪰�������
* @param url string:ҳ���URL��ַ
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
* ȥ���ַ�����β�ո�
* @param strInput string:Դ�ַ���
* @return string:�µ��ַ���
*/
function trim(strInput) {
    if (strInput != null) {
        return strInput.replace(/(^\s*)|(\s*$)/g,"");
    } else {
        return "";
    }
}


/**
* �ж��ַ����Ƿ�Ϊ����
* @param ex string:������ַ���
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
* �Ƿ�Ϊx-y��Χ֮�����������
* @param ex string:������ַ���
* @param x int:��Χ�Ŀ�ʼ
* @param y int:��Χ�Ľ���
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
* IP��ַУ��
* @param ipObject string:IP��ַ
* @return boolean:false/true
*/
function checkIpRule(ipObject){
    var str=trim(ipObject.value);
    var sip=str.split(".");
    if (str==""){
        alert('IP��ַ���ݲ���Ϊ�գ�');
        ipObject.focus();
        return false;
    }
    if (sip.length!=4){
        alert('IP��ַ��������\'.\'�ָ���4��');
        return false;
    } else if(trim(sip[0]).length>3 || trim(sip[1]).length>3 || trim(sip[2]).length>3 || trim(sip[3]).length>3 || trim(sip[0]).length==0 ||trim(sip[1]).length==0 ||trim(sip[2]).length==0 ||trim(sip[3]).length==0 ){
        alert('IP��ַÿ�γ��ȱ�����1-3֮�䣡');
        ipObject.focus();
        return false;
    } else if (isNaN(sip[0]) || isNaN(sip[1]) || isNaN(sip[2]) || isNaN(sip[3])){
        alert('IP��ַ��ÿ�α�����������������ɣ�');
        ipObject.focus();
        return false;
    } else if (parseInt(sip[0])<0 || parseInt(sip[0])>255 || parseInt(sip[1])<0 || parseInt(sip[1])>255 || parseInt(sip[2])<0 || parseInt(sip[2])>255 || parseInt(sip[3])<0 || parseInt(sip[3])>255  ){
        alert('IP��ַ��ÿ�α�������0-255����������ɣ�');
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
* ʱ��ȽϺ���
* @param BeginDate string:��ʼ����
* @param EndDate string:��������
* @return boolean:false/true
*/
function fnDateCompare(BeginDate,EndDate) {
    var objBeginDate = document.getElementById(BeginDate);
    var objEndDate = document.getElementById(EndDate);
    if(objBeginDate.value!=""&&objEndDate.value!="") {
        if(objBeginDate.value>objEndDate.value) {
            alert("��ʼ���ڲ��ܴ��ڽ�������");
            return false;
        } else {
            return true;
        }
    }
}


/**
* ���ڸ�ʽ������
* @param CurDate string:���������
* @param DefaultVar string:��ʽ(D/T/DT)
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
* �����ж�һ���������ַ����Ƿ�Ϊ���Σ������ж��Ƿ���������������������ֵΪtrue��false
* @param string string: ��Ҫ�жϵ��ַ���
* @param sign: ��Ҫ�ж�����������ʹ�ã�������+����-���������ʾ�����ж�
* @return boolean:true/false
* ���÷���:IsInteger(a,'+')�ж��Ƿ�Ϊ������
*/
function IsInteger(string,sign) {
    var integer;
    if ((sign!=null) && (sign!='-') && (sign!='+')) {
        alert('IsInter(string,sign)�Ĳ������� signΪnull��"-"��"+"');
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
* �������Ƿ��ַ�
* @param str string:������ַ���
* @param charset string:�������ַ���
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
* �ַ������ɰ�����Щ�ַ�
* @param str string:������ַ���
* @return boolean:true/false
*/
function validChar(str) {
    if ((contain(str, "%\(\)><*$^~\'?\"|!$#&"))) {
        return false;
    }
    return true;
}


/*
* �滻�ַ�����
* @param s1 string:ԭʼ�ַ���
* @param s2 string:�����滻�ľ��ַ���
* @param s3 string:���ַ���
* @return string:�滻����ַ���
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
* ���ݲ�ͬ�Ľ�ɫȨ���Բ�ͬ�ķ�ʽ�˳��û��Է���ҳ��
* @param roleCode string:��ɫ(admin/domain)
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
* �����������Ĺرպʹ�
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
* ���ĳһ��Radio�ؼ���ֵ
* @param radioArray obj:Radio�ؼ�
* ���÷���getRadioValue(document.radioName)
*/
function getRadioValue(radioArray){
    for (var i=0; i<radioArray.length; i++) { //radioArray.length��radioѡ��ĸ���
        if (radioArray[i].checked){
            return radioArray[i].value;
        }
    }
}
/**
* ��Ӹ������ļ�������
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

//�����Ի���
function openDialog(WINurl,WINwidth,WINheight,xyPosition){
alert("1");
	var center = xyPosition==0?'yes':'no';
	if(xyPosition==0){//��Ļ����   
		showx = (window.screen.availWidth  - WINwidth)/2;
		showy = (window.screen.availHeight - WINheight)/2;
  }else{//�¼�����
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

//�򿪴���
function openWin(WINurl,WINwidth,WINheight,xyPosition){
	if(xyPosition==0){//��Ļ����   
		showx = (window.screen.availWidth  - WINwidth)/2;
    showy = (window.screen.availHeight - WINheight)/2;
	}else{//�¼�����
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

//ѡ����̳������
function forumLink(){
	var selectOption = document.all.forumList.options[document.all.forumList.selectedIndex].value;
	var directHref = selectOption + ".htm";
	window.location.href=directHref;
}


//��ʾ�����Ա�����
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

//�����ϵ�ʱ���б��е���ʽclass=��choose��
function moveClass(trId) {
	var objTr = document.getElementById(trId);
	var trClass=document.getElementById("trClass");
	if (trClass.value == "init") {
			trClass.value=objTr.className;
		}
	objTr.className="choose";
}

//����뿪���б��е���ʽ�ָ�ԭ��
function outClass(trId) {
	var trClass=document.getElementById("trClass");
	var objTr = document.getElementById(trId);
	if (trClass.value != "init") {
			objTr.className=trClass.value;
		}
	
	trClass.value="init";
}

//LearnBoard��main���棬���������б�
function showhideCategory(category) {
  var objCategory = document.getElementById(category);
  if (objCategory == null) return;

  if ( objCategory.style.display == 'none' ) {
    objCategory.style.display = '';
  } else if ( objCategory.style.display == '' ) {
    objCategory.style.display = 'none';
  }
}