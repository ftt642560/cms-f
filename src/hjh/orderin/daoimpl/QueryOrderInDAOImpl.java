package hjh.orderin.daoimpl;

import hjh.orderin.dao.QueryOrderInDAO;
import hjh.orderin.domain.InOrder;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class QueryOrderInDAOImpl implements QueryOrderInDAO {
	private SessionFactory sessionFactory;
	private static int pages;
    private static int currentpages;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public static int getPages() {
		return pages;
	}

	public static void setPages(int pages) {
		QueryOrderInDAOImpl.pages = pages;
	}

	public static int getCurrentpages() {
		return currentpages;
	}

	public static void setCurrentpages(int currentpages) {
		QueryOrderInDAOImpl.currentpages = currentpages;
	}

	public List<InOrder> query(int firstPage,String receiptsNum,String repotory,String startDate,String endDate) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<InOrder> inOrders = null;
		try {
			Query query = null;
			if(receiptsNum.equals("") && repotory.equals("0") && startDate.equals("") && endDate.equals("")){
				query = session.createQuery("from InOrder");
				
				Query sqlQuery =  session.createSQLQuery("select count(*) from inorder");
				int pages = (int) Math.ceil(((BigInteger)sqlQuery.list().get(0)).doubleValue()/10);
				if(firstPage <= 0){
					currentpages = 1;
					query.setFirstResult(0);
				}else if(firstPage > pages){
					currentpages = pages;
					query.setFirstResult((pages - 1) * 10);
				}else {
					currentpages = firstPage;
					query.setFirstResult((firstPage - 1) * 10);
				}
				this.pages = pages;
			    query.setMaxResults(10);
			    
				inOrders = query.list();
			}
			 else if(!"".equals(receiptsNum)) {
					String s1 = "from InOrder  where receiptsNumber =  :receiptsNum";
					query = session.createQuery(s1).setLong("receiptsNum",Long.valueOf(receiptsNum));
					 
//					SQLQuery sqlQuery =  session.createSQLQuery("select count(*) from inorder");
//				    int pages = (int) Math.ceil(((BigInteger)sqlQuery.list().get(0)).doubleValue()/10);
					int pages = 1;
					if(firstPage <= 0){
						currentpages = 1;
						query.setFirstResult(0);
					}else if(firstPage > pages){
						currentpages = pages;
						query.setFirstResult((pages - 1) * 10);
					}else{
						currentpages = firstPage;
						query.setFirstResult((firstPage - 1) * 10);
					}
					this.pages = pages;
				    query.setMaxResults(10);
				    
					inOrders = query.list();
				
                if(inOrders.size() == 0){
                	//拼接查询实体的hql语句和计算满足当前条件的记录数的原生sql语句
    				StringBuffer countSql = new StringBuffer("select count(*) from inorder where ");
                	StringBuffer otherSql = new StringBuffer("from InOrder where ");
    				if(!repotory.equals("0")){
                		countSql.append("repertory = '" + repotory + "' and ");
    					otherSql.append("repertory = :repertory and ");
    				}
    				if(!"".equals(startDate)){
    					countSql.append("inDate >= '" + startDate +"' and ");
    					otherSql.append("inDate >= :sDate and ");
    				}
    				if(!"".equals(endDate)){
    					countSql.append("inDate <= '" + endDate + "'");
    					otherSql.append("inDate <= :eDate");
    				}
    				if(otherSql.toString().endsWith("and ")){
    					otherSql = otherSql.replace(otherSql.toString().lastIndexOf("and "),otherSql.toString().length() - 1,"");
    				}
    				if(countSql.toString().endsWith("and ")){
    					countSql = countSql.replace(countSql.toString().lastIndexOf("and "),countSql.toString().length() - 1,"");
    				}
    				if(otherSql.toString().endsWith("where ")){
    					otherSql = otherSql.replace(otherSql.toString().lastIndexOf("where "),otherSql.toString().length() - 1,"");
    				}
    				if(countSql.toString().endsWith("where ")){
    					countSql = countSql.replace(countSql.toString().lastIndexOf("where "),countSql.toString().length() - 1,"");
    				}
    				
                	query = session.createQuery(otherSql.toString());
                	if(!repotory.equals("0")) query.setString("repertory",repotory);
                	if(!"".equals(startDate)){
                		String[] dates = startDate.split("-");
    					Date sDate = new Date(Integer.valueOf(dates[0])-1900,
    							Integer.valueOf(dates[1]) - 1,Integer.valueOf(dates[2]));
                		query.setDate("sDate",sDate);
                	}
                	if(!"".equals(endDate)) {
    					String[] dates2 = endDate.split("-");
    					Date eDate = new Date(Integer.valueOf(dates2[0])-1900,
    							Integer.valueOf(dates2[1]) - 1,Integer.valueOf(dates2[2]));
                		query.setDate("eDate",eDate);
    				}
                	
                	
                	//计算总页数和当前要加载哪页并调用setFirstResult和setMaxResults方法
                	SQLQuery sqlQuery2 =  session.createSQLQuery(countSql.toString());
 					int pages2 = (int) Math.ceil(((BigInteger)sqlQuery2.list().get(0)).doubleValue()/10);
 					if(firstPage <= 0){
						currentpages = 1;
						query.setFirstResult(0);
					}else if(firstPage > pages2) {
 						currentpages = pages2;
 						query.setFirstResult((pages2 - 1) * 10);
 					}else{
 						currentpages = firstPage;
 						query.setFirstResult((firstPage - 1) * 10);
 					}
 					this.pages = pages2;
				    query.setMaxResults(10);
    				inOrders = query.list();
                }
			}else{
				//拼接查询实体的hql语句和计算满足当前条件的记录数的原生sql语句
				StringBuffer otherSql = new StringBuffer("from InOrder where ");
				StringBuffer countSql = new StringBuffer("select count(*) from inorder where ");
				if(!repotory.equals("0")){
					otherSql.append("repertory = :repertory and ");
					countSql.append("repertory = '" + repotory + "' and ");
				}
				if(!"".equals(startDate)) {
					otherSql.append("inDate >= :sDate and ");
					countSql.append("inDate >= '" + startDate +"' and ");
				}
				if(!"".equals(endDate)) {
					otherSql.append("inDate <= :eDate");
					countSql.append("inDate <= '" + endDate +"'");
				}
				if(otherSql.toString().endsWith("and ")){
					otherSql = otherSql.replace(otherSql.toString().lastIndexOf("and "),otherSql.toString().length() - 1,"");
				}
				if(countSql.toString().endsWith("and ")){
					countSql = countSql.replace(countSql.toString().lastIndexOf("and "),countSql.toString().length() - 1,"");
				}
				query = session.createQuery(otherSql.toString());
				if(!repotory.equals("0")) query.setString("repertory",repotory);
				if(!"".equals(startDate)){
            		String[] dates = startDate.split("-");
					Date sDate = new Date(Integer.valueOf(dates[0])-1900,
							Integer.valueOf(dates[1]) - 1,Integer.valueOf(dates[2]));
            		query.setDate("sDate",sDate);
            	}
            	if(!"".equals(endDate)) {
					String[] dates2 = endDate.split("-");
					Date eDate = new Date(Integer.valueOf(dates2[0])-1900,
							Integer.valueOf(dates2[1]) - 1,Integer.valueOf(dates2[2]));
            		query.setDate("eDate",eDate);
				}
            	System.out.println(otherSql.toString());
            	System.out.println(countSql.toString());
            	
            	//计算总页数和当前要加载哪页并调用setFirstResult和setMaxResults方法
            	SQLQuery sqlQuery2 =  session.createSQLQuery(countSql.toString());
				int pages2 = (int) Math.ceil(((BigInteger)sqlQuery2.list().get(0)).doubleValue()/10);
				System.out.println(pages2);
				System.out.println("记录数---" + ((BigInteger)sqlQuery2.list().get(0)).doubleValue());
				if(firstPage <= 0){
					currentpages = 1;
					query.setFirstResult(0);
				}else if(firstPage > pages2){
					currentpages = pages2;
					query.setFirstResult((pages2 - 1) * 10);
				}else {
					currentpages = firstPage;
					query.setFirstResult((firstPage - 1) * 10);
				}
				this.pages = pages2;
			    query.setMaxResults(10);
				inOrders = query.list();
			}
			
			tx.commit();
			return inOrders;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public int getAllPages() {
		return pages;
	}

	@Override
	public int getCurrentPage() {
		return currentpages;
	}

}
