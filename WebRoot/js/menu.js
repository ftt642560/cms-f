  //操作工具栏的关闭和打开
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
