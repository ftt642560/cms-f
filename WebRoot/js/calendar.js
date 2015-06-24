
var gdCtrl = new Object();
var goSelectTag = new Array();
var gcGray = "#808080";
var gcToggle = "#ffff00";
var gcBG = "#cccccc";

var gdCurDate = new Date();
var maxYear = 2020//gdCurDate.getYear();
var giYear = gdCurDate.getFullYear();
var giMonth = gdCurDate.getMonth()+1;
var giDay = gdCurDate.getDate();

//****************************************************************************
// Param: popCtrl is the widget beyond which you want this calendar to appear;
//        dateCtrl is the widget into which you want to put the selected date.
// i.e.: <input type="text" name="dc" style="text-align:center" readonly>
//   <INPUT type="button" value="V" onclick="fPopCalendar(dc,dc);return false">
//****************************************************************************
function fPopCalendar(popCtrl, dateCtrl){
  event.cancelBubble=true;
  gdCtrl = dateCtrl;
  fSetYearMon(giYear, giMonth);
  var point = fGetXY(popCtrl);
  with (VicPopCal.style) {
   left = point.x+10;
 top  = point.y+popCtrl.offsetHeight+15;
 width = VicPopCal.offsetWidth;
 height = VicPopCal.offsetHeight;
 fToggleTags(point);
 visibility = 'visible';
  }
  VicPopCal.focus();
}
function y2k(number) {
 return (number < 1000) ? number + 1900 : number;
}
function padout(number) {
 return (number < 10) ? '0' + number : number;
}
function fSetDate(iYear, iMonth, iDay){

  gdCtrl.value = y2k(iYear)+ "-"+ padout(iMonth)+"-"+padout(iDay); //Here, you could modify the locale as you need !!!!
  fHideCalendar();
}

function fHideCalendar(){
  VicPopCal.style.visibility = "hidden";
  for (i in goSelectTag)
   goSelectTag[i].style.visibility = "visible";
  goSelectTag.length = 0;
}

function fSetSelected(aCell){
  var iOffset = 0;
  var iYear = parseInt(tbSelYear.value);
  var iMonth = parseInt(tbSelMonth.value);

  aCell.bgColor = gcBG;
  with (aCell.children["cellText"]){
   var iDay = parseInt(innerText);
   if (color==gcGray)
  iOffset = (Victor<10)?-1:1;
 iMonth += iOffset;
 if (iMonth<1) {
  iYear--;
  iMonth = 12;
 }else if (iMonth>12){
  iYear++;
  iMonth = 1;
 }
  }
  fSetDate(iYear, iMonth, iDay);
}

function Point(iX, iY){
 this.x = iX;
 this.y = iY;
}

function fBuildCal(iYear, iMonth) {
  var aMonth=new Array();
  for(i=1;i<7;i++)
   aMonth[i]=new Array(i);

  var dCalDate=new Date(iYear, iMonth-1, 1);
  var iDayOfFirst=dCalDate.getDay();
  var iDaysInMonth=new Date(iYear, iMonth, 0).getDate();
  var iOffsetLast=new Date(iYear, iMonth-1, 0).getDate()-iDayOfFirst+1;
  var iDate = 1;
  var iNext = 1;

  for (d = 0; d < 7; d++)
 aMonth[1][d] = (d<iDayOfFirst)?-(iOffsetLast+d):iDate++;
  for (w = 2; w < 7; w++)
   for (d = 0; d < 7; d++)
  aMonth[w][d] = (iDate<=iDaysInMonth)?iDate++:-(iNext++);
  return aMonth;
}

function fDrawCal(iYear, iMonth, iCellHeight, iDateTextSize) {
  var WeekDay = new Array("SUN","MON","TUE","WED","THU","FRI","SAT");
  var styleTD = " bgcolor='"+gcBG+"' bordercolor='"+gcBG+"' valign='middle' align='center' height='"+iCellHeight+"' style='font:bold "+iDateTextSize+";";            file://Coded by Liming Weng(Victor Won) email:victorwon@netease.com

  with (document) {
 write("<tr>");
 for(i=0; i<7; i++)
  write("<td "+styleTD+"color:#990099' >" + WeekDay[i] + "</td>");
 write("</tr>");

   for (w = 1; w < 7; w++) {
  write("<tr>");
  for (d = 0; d < 7; d++) {
   write("<td id=calCell "+styleTD+"cursor:hand;' onMouseOver='this.bgColor=gcToggle' onMouseOut='this.bgColor=gcBG' onclick='fSetSelected(this)'>");
   write("<font id=cellText Victor='Liming Weng'> </font>");
   write("</td>")
  }
  write("</tr>");
 }
  }
}

function fUpdateCal(iYear, iMonth) {
  myMonth = fBuildCal(iYear, iMonth);
  var i = 0;
  for (w = 0; w < 6; w++)
 for (d = 0; d < 7; d++)
  with (cellText[(7*w)+d]) {
   Victor = i++;
   if (myMonth[w+1][d]<0) {
    color = gcGray;
    innerText = -myMonth[w+1][d];
   }else{
    color = ((d==0)||(d==6))?"red":"black";
    innerText = myMonth[w+1][d];
   }
  }
}

function fSetYearMon(iYear, iMon){
  tbSelMonth.options[iMon-1].selected = true;
  for (i = 0; i < tbSelYear.length; i++)
 if (tbSelYear.options[i].value == iYear)
  tbSelYear.options[i].selected = true;
  fUpdateCal(iYear, iMon);
}

function fPrevMonth(){
  var iMon = tbSelMonth.value;
  var iYear = tbSelYear.value;

  if (--iMon<1) {
   iMon = 12;
   iYear--;
  }

  fSetYearMon(iYear, iMon);
}

function fNextMonth(){
  var iMon = tbSelMonth.value;
  var iYear = tbSelYear.value;

  if (++iMon>12) {
   iMon = 1;
   iYear++;
  }

  fSetYearMon(iYear, iMon);
}

function cleartext()
{
    gdCtrl.value = "";
}

function fToggleTags(){
  with (document.all.tags("SELECT")){
  for (i=0; i<length; i++)
   if ((item(i).Victor!="Won")&&fTagInBound(item(i))){
    item(i).style.visibility = "hidden";
    goSelectTag[goSelectTag.length] = item(i);
   }
  }
}

function fTagInBound(aTag){
  with (VicPopCal.style){
   var l = parseInt(left);
   var t = parseInt(top);
   var r = l+parseInt(width);
   var b = t+parseInt(height);
 var ptLT = fGetXY(aTag);
 return !((ptLT.x>r)||(ptLT.x+aTag.offsetWidth<l)||(ptLT.y>b)||(ptLT.y+aTag.offsetHeight<t));
  }
}

function fGetXY(aTag){
  var oTmp = aTag;
  var pt = new Point(0,0);
  do {
   pt.x += oTmp.offsetLeft;
   pt.y += oTmp.offsetTop;
   oTmp = oTmp.offsetParent;
  } while(oTmp.tagName!="BODY");
  return pt;
}

var gMonths = new Array("01","02","03","04","05","06","07","08","09","10","11","12");

with (document) {
write("<Div id='VicPopCal' onclick='event.cancelBubble=true' style='POSITION:absolute;visibility:hidden;width:10;z-index:100;'>");
write("<table border='1' bgcolor='#6699cc'>");
write("<TR>");
write("<td valign='middle' align='center'><input type='button' name='PrevMonth' value='<' style='height:20;width:20;FONT:bold' onClick='fPrevMonth()'>");
write("&nbsp;&nbsp;<SELECT name='tbSelYear' onChange='fUpdateCal(tbSelYear.value, tbSelMonth.value)' Victor='Won'>");
// modify by wjs 
//for(i=1999;i<2003;i++)
for(i=maxYear;i>maxYear-100;i--)
// end
 write("<OPTION value='"+i+"'>"+i+"&nbsp;&nbsp;</OPTION>");
write("</SELECT>");
write("&nbsp;&nbsp;&nbsp;<select name='tbSelMonth' onChange='fUpdateCal(tbSelYear.value, tbSelMonth.value)' Victor='Won'>");
for (i=0; i<12; i++)
 write("<option value='"+(i+1)+"'>"+gMonths[i]+"</option>");
write("</SELECT>");
write("&nbsp;&nbsp;<input type='button' name='PrevMonth' value='>' style='height:20;width:20;FONT:bold' onclick='fNextMonth()'>");
write("&nbsp;&nbsp;<input type='button' name='clear' value='清除' style='height:20;width:40;FONT:bold' onclick='cleartext()'>");
write("</td>");
write("</TR><TR>");
write("<td align='center'>");
write("<DIV style='background-color:teal'><table width='100%' border='0' cellpadding='2'>");
fDrawCal(giYear, giMonth, 12, 12);
write("</table></DIV>");
write("</td>");
write("</TR><TR><TD align='center'>");
//write("<B style='cursor:hand; font: bold 14' onclick='fSetDate(giYear,giMonth,giDay)' onMouseOver='this.style.color=gcToggle' onMouseOut='this.style.color=0'>TODAY:"+padout(giMonth)+"-"+padout(giDay)+"-"+y2k(giYear)+"</B>");
write("<B style='cursor:hand; font: bold 14' onclick='fSetDate(giYear,giMonth,giDay)' onMouseOver='this.style.color=gcToggle' onMouseOut='this.style.color=0'>今天:"+y2k(giYear)+"-"+padout(giMonth)+"-"+padout(giDay)+"</B>");
write("</TD></TR>");
write("</TABLE></Div>");
write("<SCRIPT event=onclick() for=document>fHideCalendar()</SCRIPT>");
}


function turnit(ss,ii,IntroUrl)
{
 if (ss.style.display=="none")
  {
    ss.style.display="";
    ii.src="../images/minus.gif";
	document.frmMain.location=IntroUrl;
  }
 else
  {
    ss.style.display="none";
    ii.src="../images/plus.gif";
  }
}

function ShowTitle(name,sUrl,sTarget)
{
document.write("<tr>");
document.write("<td height='5' width='17'><img border='0' src='../images/line.gif' width='16' height='22'></td>");
document.write("<td height='5' width='13'><img border='0' src='../images/a.gif' width='8' height='8'></td>");
document.write("<td height='5' width='150'><a href=\"JavaScript:TransferThroughFormTargetName('frmMain','",sTarget,"','",sUrl,"')\">",name,"</a></td>");
document.write("</tr>");
}

function ShowGroup(GroupId,GroupName,ImgId,IntroUrl)
{
document.write("<table border=0 width=150 cellspacing=0 cellpadding=0 class=\"Sys\">");
document.write("<tr>");
document.write("<td width=20 height=20 STYLE='cursor:hand' language='JScript' onmouseup=turnit(",GroupId,",",ImgId,",'",IntroUrl,"');>");
document.write("<img border=0 src='../images/plus.gif' width=18 height=22 name='",ImgId,"'></td>");
document.write("<td width=20 height=20><img border=0 src='../images/5.gif' width=10 height=10></td>");
document.write("<td width=180 height=20 STYLE='cursor:hand' language='JScript' onmouseup=turnit(",GroupId,",",ImgId,",'",IntroUrl,"');>",GroupName,"</td>");
document.write("</tr>");
document.write("</table>");
}

function InitStatus(TableControl)
{
    TableControl.style.display="none";
}

function TransferThroughFrameName(FrameName,ActionName)
{
	document.topWnd.action = ActionName;
	document.topWnd.target = FrameName;
	document.topWnd.submit();
}

function TransferThroughFormTargetName(FormName,FrameName,ActionName)
{
	eval("document." + FormName).action = ActionName;
	eval("document." + FormName).target = FrameName;
	eval("document." + FormName).submit();
}

// 于前面日历选择无关
/**
* 得到下拉框选中项
*/
function getSelectedOfCombox(TypeSel)
{
	var strSelValue = "";
	if(TypeSel!=null)
	{
		var nLength = TypeSel.length;

		for(var i=0;i<nLength;i++)
		{
			if(TypeSel.options[i].selected == true)
			{
				strSelValue=TypeSel.options[i].value;
				break;
			}
		}
    }
	return strSelValue;
}