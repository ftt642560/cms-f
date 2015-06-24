	
	var imgMin = new Image();
	imgMin.src = "../image/qq_minimize.gif";
	
	var imgMax = new Image();
	imgMax.src = "../image/qq_maximize.gif";

	var fileList = parent.g_FileList;
	
	function Widgets()
	{
		this.Top = null;
		this.Select = null;
		this.Best = null;
		this.Board = null;
		this.Search = null;

		this.divCount = 0;
		
		this.OpenText = "全部折叠";
		this.OpenAlt = "全部折叠";
		this.ClosedText = "全部展开";
		this.ClosedAlt = "全部展开";

		this.Display = "";
		this.Img = imgMin;
		this.Text = this.OpenText;
		this.Alt = this.OpenAlt;
		
		this.ToggleAll = ToggleAll;
		this.ToggleWidget = ToggleWidget;
		this.Add = Add;
	}		

	function Add(div, name)
	{
		tmpStr = "this." + name + " = new Widget(div);"
		eval(tmpStr);
		var thisWidget = eval("this." + name);
	}

	function Widget(div)
	{
		return div;
	}	

	function ToggleAll()
	{
		var OpenCloseFunc = Widget;

		if(this.Display == "")
		{
			this.Display = "none";
			this.Img = imgMax;
			this.Text = this.ClosedText;
			this.Alt = this.ClosedAlt;
			expparent00.title = this.ClosedAlt;
			OpenCloseFunc = CloseWidget;
		}
		else
		{
			this.Display = "";
			this.Img = imgMin;
			this.Text = this.OpenText;
			this.Alt = this.OpenAlt;
			expparent00.title = this.OpenAlt;
			OpenCloseFunc = OpenWidget;
		}
		exp00.src = this.Img.src;	
		textExp.innerText = this.Text;
		exp00.alt = this.Alt;
	
		if(this.Top)
		{
			OpenCloseFunc(this.Top)
		}
		if(this.Select)
		{
			OpenCloseFunc(this.Select)
		}
		if(this.Best)
		{
			OpenCloseFunc(this.Best)
		}
		if(this.Board)
		{
			OpenCloseFunc(this.Board)
		}
		if(this.Search)
		{
			OpenCloseFunc(this.Search)
		}
	}

	function ToggleNav()
	{
		if(divCollapsedNav.style.display == "none")
		{
			divCollapsedNav.style.display = "";
			divOpenNav.style.display = "none";
			parent.frmstOuter.cols = "20,*"
			parent.document.all ('frmToolbar').noResize = true;
		}
		else
		{
			divOpenNav.style.display = "";
			divCollapsedNav.style.display = "none";
			parent.frmstOuter.cols = "237,*"
			parent.document.all ('frmToolbar').noResize = false;
		}
	}
	



	function ToggleWidget(div)
	{
		if(div.style.display == "none")
		{
			OpenWidget(div);
			closeOtherMenu(div);
		}
		else
		{
			CloseWidget(div);
		}
	}

	function closeOtherMenu(div)
	{	
		var  divObjs   =   document.getElementsByTagName('div'); 
		for( var i=0;i<divObjs.length;i++)   
		{   
				var   thisDiv   =   divObjs[i];   
				var   thisId   =   thisDiv.id;  
				if(thisId!=div.id)
				{		
					if(thisId.substring(0,7)=="hideTop")
					{
						CloseWidget(thisDiv);
					}
				}
		 }
	}


	function OpenWidget(div)
	{
		document.all(div.img).src = imgMin.src;
		div.style.display = "";
		document.all(div.img).alt = div.openAlt;
		document.all(div.img + "1").title = div.openAlt;
		document.all(div.img + "2").title = div.openAlt;
	}

	function CloseWidget(div)
	{
		document.all(div.img).src = imgMax.src;
		div.style.display = "none";
		document.all(div.img).alt = div.closedAlt;
		document.all(div.img + "1").title = div.closedAlt;
		document.all(div.img + "2").title = div.closedAlt;
	}

	function load()
	{
		parent.g_WidgetsLoaded = true;

		if (parent.g_LoadingWidgets)
		{
			parent.g_LoadingWidgets = false;
			parent.location.reload();
		}
	}

	function unload()
	{
		parent.g_LoadingWidgets = true;
	}
	
		
	var widgets = new Widgets();


	function setClass(div)
{
	var  divObjs   =   document.getElementsByTagName('div'); 
	for( var i=0;i<divObjs.length;i++)   
	{   

			var   thisDiv   =   divObjs[i];   
			var   thisId   =   thisDiv.id;  
			
			if(thisId.substring(0,7)=="menuDiv")
			{
				if(thisId!=div.id)
				{			
					//alert("div.id=="+thisId)
					thisDiv.className="menu";
					
					 for (var j=0;j<thisDiv.children.length;j++)   
					  {   
						
						  if (thisDiv.children[j].tagName   ==   'IMG')   
						  {
								var thisImg = thisDiv.children[j];	
								thisImg.src = "../image/right2.gif";
						  }
							 
					  }
				}
				else
				{
					//alert("thisId=="+thisId)
					thisDiv.className="menu1";
					for (var j=0;j<thisDiv.children.length;j++)   
					  {   						 
						  if   (thisDiv.children[j].tagName   ==   'IMG')   
						  {
								var thisImg = thisDiv.children[j];	
								thisImg.src = "../image/right1.gif";
						  }
							 
					  }
				}
				
			}
			
	 }
}
