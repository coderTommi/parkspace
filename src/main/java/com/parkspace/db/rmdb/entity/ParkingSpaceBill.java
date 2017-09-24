package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: ParkingSpaceBill.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 车位订单，用来记录车位的订单信息
 * 预约
 * 使用中
 * 使用完成
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午5:48:12</p>
*/

public class ParkingSpaceBill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单号
	private String orderJnlNo;
	//用户id
	private String userId;
	//车牌号
	private String carno;
	//车位编号,形如3-101
	private String spaceno;
	//订单状态：1、预约中，2、使用中，3.延长使用中
	private Integer billStatus;
	//停车时长，申请停车时长，单位为小时，不能超过24小时
	private Integer parkHours;
	//单价，每小时计费
	private BigDecimal unitPrice;
	//预算：=单价*停车时长
	private BigDecimal budgetPrice;
	//创建时间
	private Date createTime;
	public String getOrderJnlNo() {
		return orderJnlNo;
	}
	public void setOrderJnlNo(String orderJnlNo) {
		this.orderJnlNo = orderJnlNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getSpaceno() {
		return spaceno;
	}
	public void setSpaceno(String spaceno) {
		this.spaceno = spaceno;
	}
	public Integer getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}
	public Integer getParkHours() {
		return parkHours;
	}
	public void setParkHours(Integer parkHours) {
		this.parkHours = parkHours;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getBudgetPrice() {
		return budgetPrice;
	}
	public void setBudgetPrice(BigDecimal budgetPrice) {
		this.budgetPrice = budgetPrice;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "ParkingSpaceBill [orderJnlNo=" + orderJnlNo + ", userId=" + userId + ", carno=" + carno + ", spaceno="
				+ spaceno + ", billStatus=" + billStatus + ", parkHours=" + parkHours + ", unitPrice=" + unitPrice
				+ ", budgetPrice=" + budgetPrice + ", createTime=" + createTime + "]";
	}
}
