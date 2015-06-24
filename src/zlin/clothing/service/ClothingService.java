package zlin.clothing.service;

import java.util.ArrayList;
import zlin.clothing.dao.*;
import zlin.clothing.po.*;
import zlin.clothing.vo.PageBean;

public class ClothingService {
	private ClothingDao clothingdao;
	private ClothingPO clothingpo;
	private ArrayList clothinglist;
	
	
	
	
	
	public ClothingDao getClothingdao() {
		return clothingdao;
	}

	public void setClothingdao(ClothingDao clothingdao) {
		this.clothingdao = clothingdao;
	}

	public ClothingPO getClothingpo() {
		return clothingpo;
	}

	public void setClothingpo(ClothingPO clothingpo) {
		this.clothingpo = clothingpo;
	}

	public ArrayList getClothinglist() {
		return clothinglist;
	}

	public void setClothinglist(ArrayList clothinglist) {
		this.clothinglist = clothinglist;
	}

	/**
     * pageSize为每页显示的记录数
     * page为当前显示的网页
	 * @throws Exception 
     */
	public PageBean findAllClothing(int pageSize, int page)
	{
		 PageBean pageBean = new PageBean();
		// clothingdao=new ClothingDao();
	        String hql = "from ClothingPO";
	        
	        int allRows = clothingdao.getAllRowCount(hql);
	        
	        int totalPage = pageBean.getTotalPages(pageSize, allRows);
	        
	        int currentPage = pageBean.getCurPage(page);
	        
	        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
	        
	        try{
	        	ArrayList<ClothingPO> list = (ArrayList<ClothingPO>)clothingdao.findAllClothing(offset, pageSize);
		        pageBean.setList(list);
		        pageBean.setAllRows(allRows);
		        pageBean.setCurrentPage(currentPage);
		        pageBean.setTotalPage(totalPage);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }

	        
	        return pageBean;
		
	}

	/*
	//获得clothing全部对象
	public ArrayList<ClothingPO> findAllClothing()
	{
		//System.out.println("this is ClothingService");
		clothingdao=new ClothingDao();
		clothinglist=new ArrayList<ClothingPO>();
		try{
			clothinglist=(ArrayList<ClothingPO>)clothingdao.findAllClothing();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return clothinglist;
	}
	*/
	
	
	
	public PageBean findClothing(String CLOTHNUM,String TYPE,String COLOR,String SIZE,int pageSize, int page)
	{
		PageBean pageBean = new PageBean();
		//clothingdao=new ClothingDao();
		//clothinglist=new ArrayList<ClothingPO>();
		String clothnum=CLOTHNUM;
		String type=TYPE;
		String color=COLOR;
		String size=SIZE;
		
		
		 String hql = "from ClothingPO clothing where clothing.clothnum='"+clothnum+
		    		"' and clothing.type='"+type+"' and clothing.color='"+color+"' and clothing.size='"+size+"'";
		 
	        
	        int allRows = clothingdao.getAllRowCount(hql);
	        
	        int totalPage = pageBean.getTotalPages(pageSize, allRows);
	        
	        int currentPage = pageBean.getCurPage(page);
	        
	        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
	        
	        try{
	        	ArrayList<ClothingPO> list = (ArrayList<ClothingPO>)clothingdao.findClothing(clothnum, type, color, size, offset, pageSize);
	        	pageBean.setList(list);
	        	pageBean.setAllRows(allRows);
	        	pageBean.setCurrentPage(currentPage);
	        	pageBean.setTotalPage(totalPage);
	        }
	        
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        
	        return pageBean;
		
	}
	
	
	
	
	
	
	/*
	//按条件查询clothing
	public ArrayList<ClothingPO> findClothing(String CLOTHNUM,String TYPE,String COLOR,String SIZE)
	{
		clothingdao=new ClothingDao();
		clothinglist=new ArrayList<ClothingPO>();
		String clothnum=CLOTHNUM;
		String type=TYPE;
		String color=COLOR;
		String size=SIZE;
		try
		{
			clothinglist=(ArrayList<ClothingPO>)clothingdao.findClothing(clothnum, type, color, size);
		
			//System.out.println("clothing service findclothing");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return clothinglist;
		
	}
	*/
	
	//新建一个货号
	public Long newClothing(ClothingPO CLOTHINGPO)
	{
		System.out.println("this is newclothing service");
		Long id = null;
		//clothingdao=new ClothingDao();
		//ClothingPO clothingpo = new ClothingPO();
	    clothingpo.setClothnum(CLOTHINGPO.getClothnum());//货号
	    clothingpo.setColor(CLOTHINGPO.getColor());//色号
	    clothingpo.setFabric(CLOTHINGPO.getFabric());//面料
	    clothingpo.setSize(CLOTHINGPO.getSize());//尺码
	    clothingpo.setType(CLOTHINGPO.getType());//品名
	    clothingpo.setRetailPrice(CLOTHINGPO.getRetailPrice());//零售价
	    clothingpo.setFactoryPrice(CLOTHINGPO.getFactoryPrice());//出厂价
	    clothingpo.setClothingMaterial(CLOTHINGPO.getClothingMaterial());//里料
		
	    try
		{
			id=clothingdao.newClothing(clothingpo);//返回新建货号的ID值

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	    
		return id;
		
	}
	
	
	
	
	//删除一个clothing
	public void deleteClothing(Long CLOTHINGID)
	{

		try{
			clothingdao.deleteClothing(CLOTHINGID);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//更新一个clothing
	public void updateClothing(ClothingPO clothingpo)
	{
		try{
			clothingdao.updateClothing(clothingpo);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	//更新一个信息的时候，查找到需要更新的一条信息
	public ClothingPO findAClothing(Long CLOTHINGID)
	{
		try{
			clothingpo=clothingdao.findAClothing(CLOTHINGID);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return clothingpo;
	}
	
	
}
