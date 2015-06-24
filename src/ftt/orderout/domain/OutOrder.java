package ftt.orderout.domain;

import java.util.HashSet;
import java.util.Set;

import zlin.store.po.StorePO;

public class OutOrder {//一方
	private Long id;
	private String outNo;
	private String outDate;
	private String receivePerson;
	private Long receivePhone;
	private String address;
	private String remark;
	private StorePO storePO;// 单向 n-1 的关联关系
	
	private Set<OutOrderDetail> outOrderDetails = new HashSet<OutOrderDetail>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOutNo() {
		return outNo;
	}
	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getReceivePerson() {
		return receivePerson;
	}
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<OutOrderDetail> getOutOrderDetails() {
		return outOrderDetails;
	}
	public void setOutOrderDetails(Set<OutOrderDetail> outOrderDetails) {
		this.outOrderDetails = outOrderDetails;
	}
	public Long getReceivePhone() {
		return receivePhone;
	}
	public void setReceivePhone(Long receivePhone) {
		this.receivePhone = receivePhone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public StorePO getStorePO() {
		return storePO;
	}
	public void setStorePO(StorePO storePO) {
		this.storePO = storePO;
	}
	@Override
	public String toString() {
		return "OutOrder [id=" + id + ", outNo=" + outNo + ", outDate="
				+ outDate + ", receivePerson=" + receivePerson
				+ ", receivePhone=" + receivePhone + ", address=" + address
				+ ", remark=" + remark + ", storePO=" + storePO + "]";
	}
}
