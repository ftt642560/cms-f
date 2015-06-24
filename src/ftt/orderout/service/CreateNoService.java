package ftt.orderout.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import ftt.orderout.dao.OutOrderDao;
import ftt.orderout.domain.OutOrder;

public class CreateNoService {
	private OutOrderDao outOrderDao;
	public void setOutOrderDao(OutOrderDao outOrderDao) {
		this.outOrderDao = outOrderDao;
	}
	public OutOrderDao getOutOrderDao() {
		return outOrderDao;
	}
	private OutOrder outOrder;
	public String getOrderNo(){
		String time = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = dateFormat.format(new Date());
		time = time.replace("-", "");
		time = time.replace(" ", "");
		time = time.substring(2 , 8);
		String outOrderNo = "";
		long number = 0;
		String serial = "";
		long size = outOrderDao.findAllOutOrder();
		//如果数据库里面没有订单
		if(size == 0){
			number = 1;
		}else {
			outOrder = outOrderDao.getOne();
			String str = outOrder.getOutNo();
			String s = str.substring(str.length()-4,str.length());
			number = Long.parseLong(s.trim()) + 1;
		}
		//获取number的长度
		Long number1 = number;
		int length = number1.toString().length();
		if(length == 1){
			serial = "000" + number;
		}
		if(length == 2){
			serial = "00" + number;
		}
		if(length ==3){
			serial = "0" +number;
		}
		if(length == 4){
			serial ="" + number;
		}
		outOrderNo = "RC" + time + "CEN" + serial ;
		return outOrderNo;
	}
}
