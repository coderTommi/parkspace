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
	//停车时长，申请停车时长，单位为小时，不能超过24小时，包括延长停车时长
	private Integer parkHours;
	
	//延长停车时长，默认为0
	private Integer delayParkHours;
	
	//延长停车时长，转化为字符串,格式00:00:00
	private String delayParkHoursString;
	
	//单价，每小时计费
	private BigDecimal unitPrice;
	//预算：=单价*停车时长
	private BigDecimal budgetPrice;
	//创建时间
	private Date createTime;
	//已经停车时长,格式00:00:00，当前时间-createTime
	private String usedParkHoursString;
	//车位最长停留时间
	private String maxParkHoursString;
	//可延长最长时间:maxParkHoursString-usedParkHoursString
	private String maxDelayParkHoursString;
	
	public String getMaxParkHoursString() {
		return maxParkHoursString;
	}
	public void setMaxParkHoursString(String maxParkHoursString) {
		this.maxParkHoursString = maxParkHoursString;
	}
	public String getMaxDelayParkHoursString() {
		return maxDelayParkHoursString;
	}
	public void setMaxDelayParkHoursString(String maxDelayParkHoursString) {
		this.maxDelayParkHoursString = maxDelayParkHoursString;
	}
	public String getUsedParkHoursString() {
		return usedParkHoursString;
	}
	public void setUsedParkHoursString(String usedParkHoursString) {
		this.usedParkHoursString = usedParkHoursString;
	}
	public String getOrderJnlNo() {
		return orderJnlNo;
	}
	public void setOrderJnlNo(String orderJnlNo) {
		this.orderJnlNo = orderJnlNo;
	}
	public Integer getDelayParkHours() {
		return delayParkHours;
	}
	public void setDelayParkHours(Integer delayParkHours) {
		this.delayParkHours = delayParkHours;
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
	public String getDelayParkHoursString() {
		return delayParkHoursString;
	}
	public void setDelayParkHoursString(String delayParkHoursString) {
		if(delayParkHours != null) {
			if(delayParkHours < 10) {
				this.delayParkHoursString = "0" + delayParkHours + ":00:00";;
			}else {
				this.delayParkHoursString = delayParkHours+":00:00";
			}
		}else {
			this.delayParkHoursString = delayParkHoursString;
		}
		
	}
	@Override
	public String toString() {
		return "ParkingSpaceBill [orderJnlNo=" + orderJnlNo + ", userId=" + userId + ", carno=" + carno + ", spaceno="
				+ spaceno + ", billStatus=" + billStatus + ", parkHours=" + parkHours + ", delayParkHours="
				+ delayParkHours + ", delayParkHoursString=" + delayParkHoursString + ", unitPrice=" + unitPrice
				+ ", budgetPrice=" + budgetPrice + ", createTime=" + createTime + ", usedParkHoursString="
				+ usedParkHoursString + ", maxParkHoursString=" + maxParkHoursString + ", maxDelayParkHoursString="
				+ maxDelayParkHoursString + "]";
	}
}
