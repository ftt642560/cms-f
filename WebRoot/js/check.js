//����ֻ������ĸ�����֡���_������
function isCo(s)
{
    var patrn = /^[A-Za-z0-9_]+$/;
    if (!patrn.exec(s)) return false 
    return true
}
//У��ֻ������ĸ�����ֹ���
function isCn(s)
{
    var patrn = /^[A-Za-z0-9]+$/;
    if (!patrn.exec(s)) return false 
    return true
}

//У�鲻�ܺ��пո�,���������ַ�
function isSpace(s)
{
   var patrn = /^\S+$/;
   if (!patrn.exec(s)) return false 
    return true    
}

//У�����룺ֻ������6-20����ĸ�����֡��»��� 
function isPasswd(s) 
{ 
    var patrn=/^(\w){4,20}$/; 
    if (!patrn.exec(s)) return false 
    return true 
} 

//У��绰��ֻ�������֡���-���������� ����
function isTel(s) 
{ 
    var patrn = /^[0-9-,]+$/;
    if (!patrn.exec(s)) return false 
    return true 
} 
//�ж��Ƿ�Ϊ��
function isEmpty (str) { 
if ((str==null)||(str.length==0)) return true; 
else return(false); 
}

//��Ԫ��ͨ����֤����
//NU  ����Ƿ�Ϊ��
//CO   ֻ����ĸ�����֡�������������
//CN   ֻ����ĸ�����ֹ���
//TL   ���绰���룬ֻ�����֡���-��������������
//LE=12  ��ⳤ��
//SP   �����ո���ַ�������
function validateForm(add){    
	var alertMessage = "";
	var elArr = eval(document.forms[0].name).elements;	
	var strYY = "";
	var strMM = "";
	var strDD = "";
	for(var i = 0; i < elArr.length; i++){	    
		if(elArr[i].id){		   
		  var aryList = (elArr[i].id).split(',');		
		  var aryListTitle = (elArr[i].title).split(',');		 
		  for(var j=0; j<aryList.length; j++){			           
		  	  if(aryList[j].substring(0,2) == "NU"){			  	       	   
		  	  	if(elArr[i].value==""){	  	 		  	  	    
		  	  		alertMessage =  alertMessage + eval(aryListTitle[j]) +"\n" ;
		  	  	}
		  	  }
		  	  if(aryList[j].substring(0,2) == "CO"){		  	    
		  	  	if(elArr[i].value !=""){		  	  	   
    		  	  	if(isCo(elArr[i].value)==false){       		  	  	     		  	  	   
    		  	  		alertMessage = alertMessage + eval(aryListTitle[j])+ "\n";    		  	  		
    		  	  	}
		  		 }
		  	  }
		  	  if(aryList[j].substring(0,2) == "CN"){		  	    
		  	  	if(elArr[i].value !=""){		  	  	   
    		  	  	if(isCn(elArr[i].value)==false){ 
    		  	  		alertMessage = alertMessage + eval(aryListTitle[j])+ "\n";    		  	  		
    		  	  	}
		  		 }
		  	  }
		  	  if(aryList[j].substring(0,2) == "TL"){		  	    
		  	  	if(elArr[i].value !=""){		  	  	   
    		  	  	if(isTel(elArr[i].value)==false){       		  	  	     		  	  	   
    		  	  		alertMessage = alertMessage + eval(aryListTitle[j])+ "\n";    		  	  		
    		  	  	}
		  		 }
		  	  }	
		  	  if(aryList[j].substring(0,2) == "SP"){		  	    
		  	  	if(elArr[i].value !=""){		  	  	   
    		  	  	if(isSpace(elArr[i].value)==false){       		  	  	     		  	  	   
    		  	  		alertMessage = alertMessage + eval(aryListTitle[j])+ "\n";    		  	  		
    		  	  	}
		  		 }
		  	  }	  	  
		  	  if(aryList[j].substring(0,2) == "LE"){
		  	  	 if(elArr[i].value !=""){
		  	       var eleLength = elArr[i].value.length;
		  	       var ordLength = aryList[j].substring(3,100);		  	    
		  	  	   if( eleLength > ordLength){		  	  	    
		  	  	      alertMessage = alertMessage + eval(aryListTitle[j]) + "\n";		  	  		
		  	  	   }
		  		  }
		  	  }
		   }
		}
	}	
	if(add == "true"){
	     alertMessage =  addMessage(alertMessage);
	}	
	if(alertMessage != ""){	 
	   
		alert(alertMessage);
		return false;
	}else{
		return true;
	}
}
function checkboxChecked(checkboxName)
{     
    var count=0;
    var formname = document.forms[0].name;
	if(eval("document." + formname + "." + checkboxName)!=null)
	{
		if(eval("document." + formname + "." + checkboxName).length!=null)
		{
			for (var i = 0; i < eval("document." + formname + "." + checkboxName).length; i++)       
			{
				if (eval("document." + formname + "." + checkboxName)[i].checked == true)
				{
					count=count+1;
				}
			}
		}
		else
		{    
			if (eval("document." + formname + "." + checkboxName).checked == true)
			{
				count=count+1;
			}
		} 
	}
    return count;
}

function checkboxEmpty (checkboxName)
{     
    var checkbox = document.all(checkboxName);
	if(checkbox!=null)
	{
		if( checkbox.length!=null)
		{
			for (var i = 0; i < checkbox.length; i++)       
			{
				checkbox[i].checked = false;
			}
		}
		else
			checkbox.checked = false;
	}
}
