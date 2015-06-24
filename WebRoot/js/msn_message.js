/**/ /**/ /**/ /*   
**    ==================================================================================================  
**    ������CLASS_MSN_MESSAGE  
**    ���ܣ��ṩ����MSN��Ϣ��  
**    ʾ����  
    ---------------------------------------------------------------------------------------------------  
  
	var MSG = new CLASS_MSN_MESSAGE("aa",200,120,"����Ϣ��ʾ��","����1����Ϣ","�������ҳԷ���");  
		MSG.show();  
  
    ---------------------------------------------------------------------------------------------------  
**    ���ߣ�ttyp  
**    �ʼ���ttyp@21cn.com  
**    ���ڣ�2005-3-18  
**    ==================================================================================================  
* */


/**/ /**/ /**/ /*
 *    ��Ϣ����  
  */
function CLASS_MSN_MESSAGE(id, width, height, caption, title, message, action, target) {
    this.id			= id;
    this.title		= title;
    this.caption	= caption;
    this.message	= message;
    this.target		= target;
    this.action		= action;
    this.width		= width ? width: 200;
    this.height		= height ? height: 120;
    this.timeout	= 150;
    this.speed		= 20;
    this.step		= 1;
    this.right		= screen.width - 1;
    this.bottom		= screen.height;
    this.left		= this.right - this.width;
    this.top		= this.bottom - this.height;
    this.timer		= 0;
    this.pause		= false;
    this.close		= false;
    this.autoHide	= true;
    this.messages	= new  Object();
    this.index		= 0;
}

CLASS_MSN_MESSAGE.prototype.addMessage = function (id, msg, title) {
    var item = new CLASS_MESSAGE_ITEM(id, msg, title);
    this.messages[this.index] = item;
    this.index++;
}

/**/ /**/ /**/ /*
 *    ������Ϣ����  
  */
CLASS_MSN_MESSAGE.prototype.hide = function() {
    if (this.onunload()) {
        var offset = this.height > this.bottom - this.top ? this.height: this.bottom - this.top;
        var me = this;

        if (this.timer > 0) {
            window.clearInterval(me.timer);
        }

        var fun = function () {
            if (me.pause == false || me.close) {
                var x = me.left;
                var y = 0;
                var width = me.width;
                var height = 0;
                if (me.offset > 0) {
                    height = me.offset;
                }

                y = me.bottom - height;

                if (y >= me.bottom) {
                    window.clearInterval(me.timer);
                    me.Pop.hide();
                } else {
                    me.offset = me.offset - me.step;
                }
                me.Pop.show(x, y, width, height);
            }
        }

        this.timer = window.setInterval(fun, this.speed)
    }
}

/**/ /**/ /**/ /*
 *    ��Ϣж���¼���������д  
  */
CLASS_MSN_MESSAGE.prototype.onunload = function () {
    return   true;
}
/**/ /**/ /**/ /*
 *    ��Ϣ�����¼���Ҫʵ���Լ������ӣ�����д��  
 *  
  */
CLASS_MSN_MESSAGE.prototype.oncommand = function () {
    window.open(this.action, this.target);

    // this.close = true;
    this.hide();
}
CLASS_MSN_MESSAGE.prototype.onlink = function (action) {
    window.open(action, this.target);
    this.hide();
}

function CLASS_MESSAGE_ITEM(link, msg, title) {
    this.link = link;
    this.msg = msg;
    this.title = title;
}
/**/ /**/ /**/ /*
 *    ��Ϣ��ʾ����  
  */
CLASS_MSN_MESSAGE.prototype.show = function () {

    var oPopup = window.createPopup();
    // IE5.5+

    this.Pop = oPopup;

    var w = this.width;
    var h = this.height;

    var str = " <DIV style='BORDER-RIGHT: #455690 1px solid; BORDER-TOP: #a6b4cf 1px solid; Z-INDEX: 99999; LEFT: 0px; BORDER-LEFT: #a6b4cf 1px solid; WIDTH:  " + w + " px; BORDER-BOTTOM: #455690 1px solid; POSITION: absolute; TOP: 0px; HEIGHT:  " + h + " px; BACKGROUND-COLOR: #c9d3f3'> "  ;
    str += " <TABLE style='BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid' cellSpacing=0 cellPadding=0 width='100%' bgColor=#cfdef4 border=0> ";
    str += " <TR> ";
    str += " <TD style='FONT-SIZE: 12px;COLOR: #0f2c8c' width=30 height=24></TD> ";
    str += " <TD style='PADDING-LEFT: 4px; FONT-WEIGHT: normal; FONT-SIZE: 12px; COLOR: #1f336b; PADDING-TOP: 4px' vAlign=center width='100%'> " + this.caption + " </TD> ";
    str += " <TD style='PADDING-RIGHT: 2px; PADDING-TOP: 2px' vAlign=center align=right width=19> ";
    str += " <SPAN title=�ر� style='FONT-WEIGHT: bold; FONT-SIZE: 12px; CURSOR: hand;  MARGIN-RIGHT: 4px' id='btSysClose'>��</SPAN></TD> ";
    str += " </TR> ";
    str += " <TR> ";
    str += " <TD style='PADDING-RIGHT: 1px;PADDING-BOTTOM: 1px' colSpan=3 height= " + (h - 28 ) + "> ";
    str += " <DIV style='BORDER-RIGHT: #b9c9ef 1px solid; PADDING-RIGHT: 8px; BORDER-TOP: #728eb8 1px solid; PADDING-LEFT: 8px; FONT-SIZE: 12px; PADDING-BOTTOM: 8px; BORDER-LEFT: #728eb8 1px solid; WIDTH: 100%; COLOR: #1f336b; PADDING-TOP: 8px; BORDER-BOTTOM: #b9c9ef 1px solid; HEIGHT: 100%'> " + this.title.replace(" {size} ", this.index > 0 ? this.index: 1) + " <BR><BR> ";
    str += " <DIV style='WORD-BREAK: break-all' align=left> ";
    if (this.index > 0) {
        for (i = 0; i < this.index; i ++) {
            if (typeof ( this.messages[i]) != "undefined") {
                str += "&nbsp;&nbsp;<A href='" + ( this.messages[i].link != null ? this.messages[i].link: this.action) + "' hidefocus=true id='btCommand1' title='" + this.messages[i].title + "'><FONT color=#485D9D>" + this.messages[i].msg +"</FONT></A><br>";                
            }
        }
        str += " </DIV> ";
    } else{
        str += " <A href='javascript:void(0)' hidefocus=true id='btCommand'><FONT color=#ff0000> " + this.message + " </FONT></A></DIV> ";
	}
    str += " </DIV> ";
    str += " </TD> ";
    str += " </TR> ";
    str += " </TABLE> ";
    str += " </DIV> ";

    oPopup.document.body.innerHTML = str;

    this.offset = 0;
    var me = this;

    oPopup.document.body.onmouseover = function () {
        me.pause = true;
    }
    oPopup.document.body.onmouseout = function () {
        me.pause = false;
    }

    var fun = function () {
        var x = me.left;
        var y = 0;
        var width = me.width;
        var height = me.height;

        if (me.offset > me.height) {
            height = me.height;
        } else {
            height = me.offset;
        }

        y = me.bottom - me.offset;
        if (y <= me.top) {
            me.timeout --;
            if (me.timeout == 0) {
                window.clearInterval(me.timer);
                if (me.autoHide) {
                    me.hide();
                }
            }
        } else {
            me.offset = me.offset + me.step;
        }
        me.Pop.show(x, y, width, height);

    }

    this.timer = window.setInterval(fun, this.speed)


    var btClose = oPopup.document.getElementById("btSysClose");

    btClose.onclick = function () {
        me.close = true;
        me.hide();
    }

    var btCommand = oPopup.document.getElementById("btCommand");
    if (btCommand != null) {
        btCommand.onclick = function () {
            me.oncommand();
        }
    } else {
        var i, a;
        for (i = 0; (a = oPopup.document.getElementsByTagName("a")[i]); i ++) {
            a.onclick = function () {
                me.onlink(this);
            }
        }

    }
}
/**/ /**/ /**/ /*
** �����ٶȷ��� 
* */
CLASS_MSN_MESSAGE.prototype.speed = function (s) {
    var t = 20;
    try {
        t = praseInt(s);
    } catch (e) {
    }
    this.speed = t;
}
/**/ /**/ /**/ /*
** ���ò������� 
* */
CLASS_MSN_MESSAGE.prototype.step = function (s) {
    var t = 1;
    try {
        t = praseInt(s);
    } catch (e) {
    }
    this.step = t;
}

CLASS_MSN_MESSAGE.prototype.rect = function (left, right, top, bottom) {
    try {
        this.left	= left != null ? left: this.right - this.width;
        this.right	= right != null ? right: this.left + this.width;
        this.bottom = bottom != null ? (bottom > screen.height ? screen.height:bottom):screen.height;
        this.top	= top != null ? top: this.bottom - this.height;
    } catch (e) {
    }
}