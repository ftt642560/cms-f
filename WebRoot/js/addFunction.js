// 回车
var addKeyCode = 13;

// 新增一行
function addTrTag(trFlag) {
// userChecked 用户用于校验的地方
if (addTrCheck()) {	  
	// 取得table对象
	var tab = document.getElementById("tab0");
	var newRow = tab.insertRow(-1);
	var rowId = "trId" + trFlag;
	newRow.id= rowId;

	// 取得当前有几个可见的行
	var reSortFlag = -1;
	for (var j = trFlag ; j >= 0; j--) {
		var trObj = document.getElementById("trId"+ j);
		if (trObj.style.display  != "none") {		  
			  reSortFlag++;
		}
	}
	
	// 取得模版
	var tds = document.getElementsByName("tempTd"); 
	for(var i = 0; i < tds.length; i++) {
	  var cell = newRow.insertCell(i);
	  
	  cell.innerHTML = tds(i).innerHTML.replace('#0#', rowId);
	  // TD的样式
	  if (reSortFlag % 2 == 0) {
		cell.className = "gridbar11";
	  } else {
		cell.className = "gridbar01";
	  }
	  // TD的对齐方式
	  cell.align = "center";
	  try {
		  if (i == 0) {
			if (trFlag == 0) {
			  cell.innerHTML = 1;
			} else {
			  // 是否全部删除
			  var isAllDelete = true;
			  for (var j = trFlag-1 ; j >= 0; j--) {
				var trObj = document.getElementById("trId"+ j);
				if (trObj.style.display  != "none") {
				   isAllDelete = false;
				   cell.innerHTML = parseInt(trObj.cells[0].innerHTML) + 1;
				   break;
				}
			  }
			  // 如果全部删除 序号为1
			  if (isAllDelete) {
				cell.innerHTML = 1;
			  }
			}
		  }
		  // 默认聚焦在序号后的第一个控件
		  if (i == 1) {
			cell.firstChild.focus();
		  }
	  } catch(exception){

	  } 
	}
// 行序号++	
 return (trFlag + 1);
}else {
  return trFlag;			
}

}
// 在最后新增
function addTrTagAtLastRow(trFlag, currentRowId) {
  if (event.keyCode == addKeyCode) {
		// 找出最后一个可见行
		var lastRow = 0;
		for (var i = trFlag-1; i >= 0; i--) {
			var trObj = document.getElementById("trId"+ i);
			if (trObj.style.display  != "none") {		  
			  lastRow = i;
			  break;
			}
		}
		var previousRowId= "trId" + lastRow;
		// 如果是在最后一行点击新增
		if (previousRowId == currentRowId) {
		  return addTrTag(trFlag);
		} else {
		  return trFlag;
		}
	}
}
//  按回车自动移到下个控件
function tabMove(objId, position) {
  if (tabCheck()) {
	if (event.keyCode == addKeyCode) {
	  document.getElementById(objId).childNodes[position].firstChild.focus();
	}
  }
}
// 删除一行
function delRow(objId) {
 var tr = document.getElementById(objId);
 var tds = tr.childNodes;
 for (var i = 0; i < tds.length; i++) {
   tds[i].innerHTML = ''; 
 }
 document.getElementById(objId).style.display = "none";
 
 // 序号重新排序
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
