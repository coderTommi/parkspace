package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ParkingSpaceBillHis.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 车位订单流水，记录该车为订单中的预定-使用-延长使用-结算整个流程
 * 状态为结算的订单作为对账订单
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午6:38:15</p>
*/

public class ParkingSpaceBillHis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private String UUID;
	//订单号
	private String orderJnlNo;
	//用户id
	private String userId;
	//车牌号
	private String carno;
	//车位编号,形如3-101
	private String spaceno;
	//订单状态：1、预约中，2、使用中，3.延长使用中，4，已结算、5取消订单
	private Integer billStatus;
	//停车时长，申请停车时长，单位为小时，不能超过24小时
	private Integer parkHours;
	//单价，每小时计费
	private Double unitPrice;
	//预算：=单价*停车时长
	private Double budgetPrice;
	//创建时间
	private Date createTime;
	//记录实际的停车时长
	private Double actualParkHours;
	//预算：=单价*实际停车时长
	private Double actualPrice;
	//记录时间，表示该流水记录的时间
	private Date recodeTime = new Date();
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
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
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getBudgetPrice() {
		return budgetPrice;
	}
	public void setBudgetPrice(Double budgetPrice) {
		this.budgetPrice = budgetPrice;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Double getActualParkHours() {
		return actualParkHours;
	}
	public void setActualParkHours(Double actualParkHours) {
		this.actualParkHours = actualParkHours;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	public Date getRecodeTime() {
		return recodeTime;
	}
	public void setRecodeTime(Date recodeTime) {
		this.recodeTime = recodeTime;
	}
	@Override
	public String toString() {
		return "ParkingSpaceBillHis [UUID=" + UUID + ", orderJnlNo=" + orderJnlNo + ", userId=" + userId + ", carno="
				+ carno + ", spaceno=" + spaceno + ", billStatus=" + billStatus + ", parkHours=" + parkHours
				+ ", unitPrice=" + unitPrice + ", budgetPrice=" + budgetPrice + ", createTime=" + createTime
				+ ", actualParkHours=" + actualParkHours + ", actualPrice=" + actualPrice + ", recodeTime=" + recodeTime
				+ "]";
	}
}
