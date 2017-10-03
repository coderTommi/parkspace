package com.parkspace.controller.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Title: BillQueryWapper.java
 * @Package com.parkspace.controller.pojo
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午5:21:59</p>
*/

public class BillQueryWapper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date beginDate;
	private Date endDate;
	private Integer billType;
	private Integer billState;
	private String userId;
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getBillType() {
		return billType;
	}
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	public Integer getBillState() {
		return billState;
	}
	public void setBillState(Integer billState) {
		this.billState = billState;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "BillQueryWapper [beginDate=" + beginDate + ", endDate=" + endDate + ", billType=" + billType
				+ ", billState=" + billState + ", userId=" + userId + "]";
	}
}
