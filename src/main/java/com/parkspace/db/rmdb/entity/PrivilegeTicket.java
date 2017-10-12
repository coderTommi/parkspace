package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * 优惠券
 * @Title: PrivilegeTicket.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月9日 上午11:01:30</p>
*/

public class PrivilegeTicket implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private BigDecimal amt;
	private Date createDate;
	private Date endDate;
	/** 0: 未使用  1：已用 **/
	private Integer used;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "PrivilegeTicket [id=" + id + ", userId=" + userId + ", amt=" + amt + ", createDate=" + createDate
				+ ", endDate=" + endDate + ", used=" + used + "]";
	}
}
