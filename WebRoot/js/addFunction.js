// �س�
var addKeyCode = 13;

// ����һ��
function addTrTag(trFlag) {
// userChecked �û�����У��ĵط�
if (addTrCheck()) {	  
	// ȡ��table����
	var tab = document.getElementById("tab0");
	var newRow = tab.insertRow(-1);
	var rowId = "trId" + trFlag;
	newRow.id= rowId;

	// ȡ�õ�ǰ�м����ɼ�����
	var reSortFlag = -1;
	for (var j = trFlag ; j >= 0; j--) {
		var trObj = document.getElementById("trId"+ j);
		if (trObj.style.display  != "none") {		  
			  reSortFlag++;
		}
	}
	
	// ȡ��ģ��
	var tds = document.getElementsByName("tempTd"); 
	for(var i = 0; i < tds.length; i++) {
	  var cell = newRow.insertCell(i);
	  
	  cell.innerHTML = tds(i).innerHTML.replace('#0#', rowId);
	  // TD����ʽ
	  if (reSortFlag % 2 == 0) {
		cell.className = "gridbar11";
	  } else {
		cell.className = "gridbar01";
	  }
	  // TD�Ķ��뷽ʽ
	  cell.align = "center";
	  try {
		  if (i == 0) {
			if (trFlag == 0) {
			  cell.innerHTML = 1;
			} else {
			  // �Ƿ�ȫ��ɾ��
			  var isAllDelete = true;
			  for (var j = trFlag-1 ; j >= 0; j--) {
				var trObj = document.getElementById("trId"+ j);
				if (trObj.style.display  != "none") {
				   isAllDelete = false;
				   cell.innerHTML = parseInt(trObj.cells[0].innerHTML) + 1;
				   break;
				}
			  }
			  // ���ȫ��ɾ�� ���Ϊ1
			  if (isAllDelete) {
				cell.innerHTML = 1;
			  }
			}
		  }
		  // Ĭ�Ͼ۽�����ź�ĵ�һ���ؼ�
		  if (i == 1) {
			cell.firstChild.focus();
		  }
	  } catch(exception){

	  } 
	}
// �����++	
 return (trFlag + 1);
}else {
  return trFlag;			
}

}
// ���������
function addTrTagAtLastRow(trFlag, currentRowId) {
  if (event.keyCode == addKeyCode) {
		// �ҳ����һ���ɼ���
		var lastRow = 0;
		for (var i = trFlag-1; i >= 0; i--) {
			var trObj = document.getElementById("trId"+ i);
			if (trObj.style.display  != "none") {		  
			  lastRow = i;
			  break;
			}
		}
		var previousRowId= "trId" + lastRow;
		// ����������һ�е������
		if (previousRowId == currentRowId) {
		  return addTrTag(trFlag);
		} else {
		  return trFlag;
		}
	}
}
//  ���س��Զ��Ƶ��¸��ؼ�
function tabMove(objId, position) {
  if (tabCheck()) {
	if (event.keyCode == addKeyCode) {
	  document.getElementById(objId).childNodes[position].firstChild.focus();
	}
  }
}
// ɾ��һ��
function delRow(objId) {
 var tr = document.getElementById(objId);
 var tds = tr.childNodes;
 for (var i = 0; i < tds.length; i++) {
   tds[i].innerHTML = ''; 
 }
 document.getElementById(objId).style.display = "none";
 
 // �����������
 var reSortFlag = 0;
 for (var j = 0 ; j < trFlag; j++) {
	var trObj = document.getElementById("trId"+ j);
	if (trObj.style.display  != "none") {
	  for (var i = 0; i < trObj.cells.length; i++) {
		  if (reSortFlag % 2 == 0) {
			trObj.cells[i].className = "gridbar11";
		  } else {
			trObj.cells[i].className = "gridbar01";
		  }
	  }
	  
	   trObj.cells[0].innerHTML =  1 + reSortFlag++;
	}
  }
 
}

function addTrCheck() {
  return true;
}

function tabCheck() {
  return true;
}
