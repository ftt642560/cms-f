package zlin.store.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import zlin.clothing.po.ClothingPO;
import zlin.clothing.vo.PageBean;
import zlin.store.po.StorePO;
import zlin.store.service.StoreService;

public class StoreAction {
	private PageBean pageBean;
	private StoreService storeservice;
	private ArrayList storelist;//页面中查询的结果
	private String storePageFunc;//判定clothing1001.jsp页面是通过那个查询结果的功能
	private int page;//用于分页
	private StorePO storepo;
	
	private String storenum;
	private String storename;
	private String linkman;//联系人
	private String tele;//联系电话
	private String storagevolume;//仓储量
	private String id;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StorePO getStorepo() {
		return storepo;
	}

	public void setStorepo(StorePO storepo) {
		this.storepo = storepo;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getStoragevolume() {
		return storagevolume;
	}

	public void setStoragevolume(String storagevolume) {
		this.storagevolume = storagevolume;
	}

	public String getStorenum() {
		return storenum;
	}

	public void setStorenum(String storenum) {
		this.storenum = storenum;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public StoreService getStoreservice() {
		return storeservice;
	}

	public void setStoreservice(StoreService storeservice) {
		this.storeservice = storeservice;
	}

	public ArrayList getStorelist() {
		return storelist;
	}

	public void setStorelist(ArrayList storelist) {
		this.storelist = storelist;
	}
	
	


	public String getStorePageFunc() {
		return storePageFunc;
	}

	public void setStorePageFunc(String storePageFunc) {
		this.storePageFunc = storePageFunc;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	
	//查找全部仓库信息
	public String findAllStore()
	{
		
		//表示每页显示5条记录，page表示当前网页
        pageBean = storeservice.findAllStore(10, page);
        storelist=pageBean.getList();
       // HttpServletRequest request = ServletActionContext.getRequest();
        
        ActionContext.getContext().getSession().put("pageBean",pageBean);
       // request.setAttribute("pageBean", pageBean);
        
        storePageFunc="findallstore";
        ActionContext.getContext().getSession().put("storePageFunc",storePageFunc);
        
        return "success";
		
	}
	
	//按条件查找信息
	public String findStore()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		storenum=request.getParameter("storenum");
		storename=request.getParameter("storename");
				
		try { 
			storenum = java.net.URLDecoder.decode(storenum,"UTF-8"); 
			storename = java.net.URLDecoder.decode(storename,"UTF-8");
			
			} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
			} 
		

			storePageFunc="findstore";
			 ActionContext.getContext().getSession().put("storePageFunc",storePageFunc);
			
			 
			 pageBean=storeservice.findStore(storenum,storename,10,page);
			 ActionContext.getContext().getSession().put("pageBean",pageBean);
			 
			storelist=pageBean.getList();
			return "success";
		}
	
	//新建仓库
	public String newStore()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		storenum=request.getParameter("storenum");
		storename=request.getParameter("storename");
		linkman=request.getParameter("linkman");
		tele=request.getParameter("tele");
		storagevolume=request.getParameter("storagevolume");
		
		try { 
			storenum= java.net.URLDecoder.decode(storenum,"UTF-8");
			
			storename = java.net.URLDecoder.decode(storename,"UTF-8"); 
			//color = java.net.URLDecoder.decode(color,"UTF-8"); 
			
			linkman = java.net.URLDecoder.decode(linkman,"UTF-8");
			//type = java.net.URLDecoder.decode(type,"UTF-8");
			
			tele=java.net.URLDecoder.decode(tele,"UTF-8");
					
			storagevolume=java.net.URLDecoder.decode(storagevolume,"UTF-8");
			
			
			} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
			} 
		
		storepo.setStorenum(storenum);
		storepo.setStorename(storename);
		storepo.setLinkman(linkman);
		storepo.setTele(tele);
		storepo.setStoragevolume(storagevolume);
		
		
		try{
			Long id=storeservice.newStore(storepo);
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
	
	//删除仓库
	public String deleteStore()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		id=request.getParameter("id");
		Long storeid=Long.parseLong(id);
		storeservice.deleteStore(storeid);
		return "success";
	}
	
	//按照ID，查找一个仓库信息,用于更新信息
	public String findAStore()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();		
		Long storeid;
		id=request.getParameter("id");	
		storeid=Long.parseLong(id);
		
		storepo=storeservice.findAStore(storeid);
		ActionContext.getContext().getSession().put("storepo",storepo);	
		
		return "success";
	}
	
	//修改货号信息
	public String updateStore()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		storenum=request.getParameter("storenum");
		storename=request.getParameter("storename");
		linkman=request.getParameter("linkman");
		tele=request.getParameter("tele");
		storagevolume=request.getParameter("storagevolume");
		
		try { 
			storenum= java.net.URLDecoder.decode(storenum,"UTF-8");
			
			storename = java.net.URLDecoder.decode(storename,"UTF-8"); 
			//color = java.net.URLDecoder.decode(color,"UTF-8"); 
			
			linkman = java.net.URLDecoder.decode(linkman,"UTF-8");
			//type = java.net.URLDecoder.decode(type,"UTF-8");
			
			tele=java.net.URLDecoder.decode(tele,"UTF-8");
					
			storagevolume=java.net.URLDecoder.decode(storagevolume,"UTF-8");
			
			
			} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
			} 
		
		storepo.setStorenum(storenum);
		storepo.setStorename(storename);
		storepo.setLinkman(linkman);
		storepo.setTele(tele);
		storepo.setStoragevolume(storagevolume);
		
		//从session对象中取得需要更改信息的storepo对象的ID值，
		StorePO s=new StorePO();
		s=(StorePO)ActionContext.getContext().getSession().get("storepo");		
		storepo.setId(s.getId());
		
		storeservice.updateStore(storepo);
		ActionContext.getContext().getSession().remove("storepo");
		return "success";
	}
	
	
	
	/*
	 * 更新storepo的时候，从前台传过一个ID号到后台，进行查询需要更新的clothingpo对象。
	 * 把查询到的结果放入session中，然后在前台调用storepo，对每个输入框进行赋值
	 * 所以，用完了这个查询结果的对象之后，就需要把它从session中移除(即在updatestore最后移除)
	 * 所以，保存成功，移除一次，在返回的时候，也需要移除一次
	 * 
	 * 
	 * 返回的时候，调用此函数清空session中的storepo的对象
	 * */
	
	public String cleanStoreInSession()
	{
		ActionContext.getContext().getSession().remove("storepo");
		return "success";
		
	}
	
	
	
}
