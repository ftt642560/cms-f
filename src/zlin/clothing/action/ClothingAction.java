package zlin.clothing.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import zlin.clothing.po.ClothingPO;
import zlin.clothing.service.*;
import zlin.clothing.vo.PageBean;

public class ClothingAction extends ActionSupport{
private String huangjinhao = "shuaiguo";
	private ClothingService clothingservice;
	private ArrayList clothinglist;//页面中查询的结果
	
	private String clothnum;
	private String type;
	private String color;
	private String size;
	private String fabric;
	private String clothingMaterial;
	private String factoryPrice;
	private String retailPrice;
	private String id;
	
	private PageBean pageBean;
	
	private String clothingPageFunc;//判定clothing1001.jsp页面是通过那个查询结果的功能
	
	public ClothingService getClothingservice() {
		return clothingservice;
	}
	public void setClothingservice(ClothingService clothingservice) {
		this.clothingservice = clothingservice;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}


	private ClothingPO clothingpo;
	private int page;//用于分页
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public ClothingPO getClothingpo() {
		return clothingpo;
	}
	public void setClothingpo(ClothingPO clothingpo) {
		this.clothingpo = clothingpo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList getClothinglist() {
		return clothinglist;
	}
	public void setClothinglist(ArrayList clothinglist) {
		this.clothinglist = clothinglist;
	}
	
	
	public String findAllClothing()
	{
		//clothingservice=new ClothingService();
		
		//表示每页显示5条记录，page表示当前网页
        pageBean = clothingservice.findAllClothing(10, page);
        clothinglist=pageBean.getList();
       // HttpServletRequest request = ServletActionContext.getRequest();
        
        ActionContext.getContext().getSession().put("pageBean",pageBean);
       // request.setAttribute("pageBean", pageBean);
        
        clothingPageFunc="findallclothing";
        ActionContext.getContext().getSession().put("clothingPageFunc",clothingPageFunc);
        
        return "success";
		
	}
	
	
	/*
	//查询全部的clothing
	public String findAllClothing()
	{	
		//System.out.println("this is findallclothing action");
		clothingservice=new ClothingService();
		try{
			clothinglist=clothingservice.findAllClothing(); //查找全部clothing
			if(!clothinglist.isEmpty())//如果clothinglist不为空
			{
				//System.out.println("clothinglist not null");
				return "success";
			}
			
			else
			{
				System.out.println("clothinglist is Empty");
				return "error";				
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "input";//出现异常
		}
		
	}
	*/
	
	
	//按条件查询clothing
	public String findClothing()
	{
		//ActionContext ctx=ActionContext.getContext();
		//System.out.println("thit is findClothing Action");
		
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		clothnum=request.getParameter("clothnum");
		type=request.getParameter("type");
		color=request.getParameter("color");
		size=request.getParameter("size");
		
		
		
		try { 
			color = java.net.URLDecoder.decode(color,"UTF-8"); 
			//color = java.net.URLDecoder.decode(color,"UTF-8"); 
			
			type = java.net.URLDecoder.decode(type,"UTF-8");
			//type = java.net.URLDecoder.decode(type,"UTF-8");
			} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
			} 
		

		//System.out.println("clothnum="+clothnum+",color="+color+",type="+type+",size="+size+"\n");
		
		//clothingservice=new ClothingService();
		
		//try
		//{
			clothingPageFunc="findclothing";
			 ActionContext.getContext().getSession().put("clothingPageFunc",clothingPageFunc);
			
			 
			 pageBean=clothingservice.findClothing(clothnum, type, color, size, 10, page);
			 ActionContext.getContext().getSession().put("pageBean",pageBean);
			 
			clothinglist=pageBean.getList();
			return "success";
			/*
			if(!clothinglist.isEmpty())
			{
				return "success";
				
			}
			else{
				return "error";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return "input";
			
		}*/
		
	}
	
	
	
	
	/*
	//按条件查询clothing
	public String findClothing()
	{
		//ActionContext ctx=ActionContext.getContext();
		//System.out.println("thit is findClothing Action");
		
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		clothnum=request.getParameter("clothnum");
		type=request.getParameter("type");
		color=request.getParameter("color");
		size=request.getParameter("size");
		

		
		try { 
			color = java.net.URLDecoder.decode(color,"UTF-8"); 
			//color = java.net.URLDecoder.decode(color,"UTF-8"); 
			
			type = java.net.URLDecoder.decode(type,"UTF-8");
			//type = java.net.URLDecoder.decode(type,"UTF-8");
			} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
			} 
		

		//System.out.println("clothnum="+clothnum+",color="+color+",type="+type+",size="+size+"\n");
		
		clothingservice=new ClothingService();
		
		try
		{
			clothinglist=clothingservice.findClothing(clothnum, type, color, size);
			if(!clothinglist.isEmpty())
			{
				return "success";
				
			}
			else{
				return "error";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return "input";
			
		}
		
	}
	*/
	
	//新建一个货号
	public String newClothing()
	{
		System.out.println("this is new clothing action");
	//	clothingservice=new ClothingService();
		//ClothingPO clothingpo=new ClothingPO();
	//	clothingpo=new ClothingPO();
		
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		clothnum=request.getParameter("clothnum");
		type=request.getParameter("type");
		color=request.getParameter("color");
		size=request.getParameter("size");
		fabric=request.getParameter("fabric");
		clothingMaterial=request.getParameter("clothingMaterial");	
		factoryPrice=request.getParameter("factoryPrice");	
		retailPrice=request.getParameter("retailPrice");	
		
		System.out.println("clothnum="+clothnum+",color="+color+",type="+type+",size="+size+",clothingMaterial="+clothingMaterial+",factoryPrice="+factoryPrice+"\n");
		
		try { 
			clothnum= java.net.URLDecoder.decode(clothnum,"UTF-8");
			
			color = java.net.URLDecoder.decode(color,"UTF-8"); 
			//color = java.net.URLDecoder.decode(color,"UTF-8"); 
			
			type = java.net.URLDecoder.decode(type,"UTF-8");
			//type = java.net.URLDecoder.decode(type,"UTF-8");
			
			size=java.net.URLDecoder.decode(size,"UTF-8");
					
			fabric=java.net.URLDecoder.decode(fabric,"UTF-8");
			
			clothingMaterial=java.net.URLDecoder.decode(clothingMaterial,"UTF-8");
			
			factoryPrice=java.net.URLDecoder.decode(factoryPrice,"UTF-8");
			
			retailPrice=java.net.URLDecoder.decode(retailPrice,"UTF-8");
			
			} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
			} 
		
		clothingpo.setClothnum(clothnum);
		clothingpo.setClothingMaterial(clothingMaterial);
		clothingpo.setColor(color);
		clothingpo.setFabric(fabric);
		clothingpo.setFactoryPrice(factoryPrice);
		clothingpo.setRetailPrice(retailPrice);
		clothingpo.setSize(size);
		clothingpo.setType(type);
		
		try{
			Long id=clothingservice.newClothing(clothingpo);//返回新建货号的ID
			if(id!=null)//如果clothinglist不为空
			{
				return "success";
			}
			
			else
			{
				return "error";				
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "input";//出现异常
		}
		
	}
	
	
	//删除一个货号
	public String deleteClothing()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		id=request.getParameter("id");
		Long storeid=Long.parseLong(id);
		clothingservice.deleteClothing(storeid);
		return "success";		
		
	}
	
	//按照ID，查找到一个对象
	public String findAClothing()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();		
		Long clothingid;
		id=request.getParameter("id");	
		clothingid=Long.parseLong(id);
		
		clothingpo=clothingservice.findAClothing(clothingid);
		ActionContext.getContext().getSession().put("clothingpo",clothingpo);	
		
		return "success";
	}
	
	
	
	//更新一个货号信息
	public String updateClothing()
	{
		//clothingservice=new ClothingService();
		clothingpo=new ClothingPO();
		
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		clothnum=request.getParameter("clothnum");
		type=request.getParameter("type");
		color=request.getParameter("color");
		size=request.getParameter("size");
		fabric=request.getParameter("fabric");
		clothingMaterial=request.getParameter("clothingMaterial");	
		factoryPrice=request.getParameter("factoryPrice");	
		retailPrice=request.getParameter("retailPrice");	
			
		try { 
			clothnum= java.net.URLDecoder.decode(clothnum,"UTF-8");
			
			color = java.net.URLDecoder.decode(color,"UTF-8"); 
			//color = java.net.URLDecoder.decode(color,"UTF-8"); 
			
			type = java.net.URLDecoder.decode(type,"UTF-8");
			//type = java.net.URLDecoder.decode(type,"UTF-8");
			
			size=java.net.URLDecoder.decode(size,"UTF-8");
					
			fabric=java.net.URLDecoder.decode(fabric,"UTF-8");
			
			clothingMaterial=java.net.URLDecoder.decode(clothingMaterial,"UTF-8");
			
			factoryPrice=java.net.URLDecoder.decode(factoryPrice,"UTF-8");
			
			retailPrice=java.net.URLDecoder.decode(retailPrice,"UTF-8");
			
			} catch (UnsupportedEncodingException e1) { 
					e1.printStackTrace(); 
			} 
		
		//从session对象中取得需要更改信息的clothingpo对象的ID值，
		ClothingPO c=new ClothingPO();
		c=(ClothingPO)ActionContext.getContext().getSession().get("clothingpo");		
		clothingpo.setId(c.getId());
		
		
		clothingpo.setClothnum(clothnum);
		clothingpo.setClothingMaterial(clothingMaterial);
		clothingpo.setColor(color);
		clothingpo.setFabric(fabric);
		clothingpo.setFactoryPrice(factoryPrice);
		clothingpo.setRetailPrice(retailPrice);
		clothingpo.setSize(size);
		clothingpo.setType(type);
		
		clothingservice.updateClothing(clothingpo);
		ActionContext.getContext().getSession().remove("clothingpo");
		return "success";
						
	}
	
	
	/*
	 * 更新clothing的时候，从前台传过一个ID号到后台，进行查询需要更新的clothingpo对象。
	 * 把查询到的结果放入session中，然后在前台调用clothingpo，对每个输入框进行赋值
	 * 所以，用完了这个查询结果的对象之后，就需要把它从session中移除(即在updateClothing最后移除)
	 * 所以，保存成功，移除一次，在返回的时候，也需要移除一次
	 * 
	 * 
	 * 返回的时候，调用此函数清空session中的clothingpo的对象
	 * */
	
	public String cleanDataInSession()
	{
		ActionContext.getContext().getSession().remove("clothingpo");
		return "success";
		
	}
	
}
