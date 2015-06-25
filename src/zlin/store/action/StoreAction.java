package zlin.store.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import zlin.clothing.vo.PageBean;
import zlin.store.po.StorePO;
import zlin.store.service.StoreService;

public class StoreAction {
	private PageBean pageBean;
	private StoreService storeservice;
	private ArrayList storelist;//ҳ���в�ѯ�Ľ��
	private String storePageFunc;//�ж�clothing1001.jspҳ����ͨ���Ǹ���ѯ���Ĺ���
	private int page;//���ڷ�ҳ
	private StorePO storepo;
	
	private String storenum;
	private String storename;
	private String linkman;//��ϵ��
	private String tele;//��ϵ�绰
	private String storagevolume;//�ִ���
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

	
	//����ȫ���ֿ���Ϣ
	public String findAllStore()
	{
		
		//��ʾÿҳ��ʾ5����¼��page��ʾ��ǰ��ҳ
        pageBean = storeservice.findAllStore(10, page);
        storelist=pageBean.getList();
       // HttpServletRequest request = ServletActionContext.getRequest();
        
        ActionContext.getContext().getSession().put("pageBean",pageBean);
       // request.setAttribute("pageBean", pageBean);
        
        storePageFunc="findallstore";
        ActionContext.getContext().getSession().put("storePageFunc",storePageFunc);
        
        return "success";
		
	}
	
	//������������Ϣ
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
	
	//�½��ֿ�
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
			if(id!=null)//���clothinglist��Ϊ��
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
			return "input";//�����쳣
		}
		
	
	}
	
	//ɾ��ֿ�
	public String deleteStore()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		id=request.getParameter("id");
		Long storeid=Long.parseLong(id);
		storeservice.deleteStore(storeid);
		return "success";
	}
	
	//����ID������һ���ֿ���Ϣ,���ڸ�����Ϣ
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
	
	//�޸Ļ�����Ϣ
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
		
		//��session������ȡ����Ҫ�����Ϣ��storepo�����IDֵ��
		StorePO s=new StorePO();
		s=(StorePO)ActionContext.getContext().getSession().get("storepo");		
		storepo.setId(s.getId());
		
		storeservice.updateStore(storepo);
		ActionContext.getContext().getSession().remove("storepo");
		return "success";
	}
	
	
	
	/*
	 * ����storepo��ʱ�򣬴�ǰ̨����һ��ID�ŵ���̨�����в�ѯ��Ҫ���µ�clothingpo����
	 * �Ѳ�ѯ���Ľ�����session�У�Ȼ����ǰ̨����storepo����ÿ���������и�ֵ
	 * ���ԣ������������ѯ���Ķ���֮�󣬾���Ҫ�����session���Ƴ�(����updatestore����Ƴ�)
	 * ���ԣ�����ɹ����Ƴ�һ�Σ��ڷ��ص�ʱ��Ҳ��Ҫ�Ƴ�һ��
	 * 
	 * 
	 * ���ص�ʱ�򣬵��ô˺������session�е�storepo�Ķ���
	 * */
	
	public String cleanStoreInSession()
	{
		ActionContext.getContext().getSession().remove("storepo");
		return "success";
		
	}
	
	
	//模糊查询
	public String criterialStore()
	{
		HttpServletRequest request=null;
		request=ServletActionContext.getRequest();
		storenum=request.getParameter("storenum");
		storename=request.getParameter("storename");
		
		
		
	
		try { 
			storename = java.net.URLDecoder.decode(storename,"UTF-8"); 
			//color = java.net.URLDecoder.decode(color,"UTF-8"); 
			
			storenum = java.net.URLDecoder.decode(storenum,"UTF-8");
			//type = java.net.URLDecoder.decode(type,"UTF-8");
			} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace(); 
			} 
		
		storepo.setStorenum(storenum);
		storepo.setStorename(storename);
		
		
			storePageFunc="criterialstore";
			 ActionContext.getContext().getSession().put("storePageFunc",storePageFunc);
			
			 
			// pageBean=clothingservice.findClothing(clothnum, type, color, size, 10, page);
			 
			 pageBean=storeservice.criteriaStore(storepo, 10, page);
			 
			 ActionContext.getContext().getSession().put("pageBean",pageBean);
			 
			storelist=pageBean.getList();
			return "success";
		
	}
	
}
