package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
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
	//业主用户id
	private String spaceOwnerUserId;
	//车牌号
	private String carno;
	//车位编号,形如3-101
	private String spaceno;
	//订单状态：1、预约中，2、使用中，3.延长使用中
	private Integer billStatus;
	//订单状态多条件查询
	private Integer[] billStatusQuery;
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
	//上次结算时间:24小时结算一次，并且记录该时间，同时更新结算金额
	private Date lastPayTime;
	//已结算金额：截至目前一共结算的金额
	private BigDecimal payedMoney;
	
	
	
	//已经停车时长,格式00:00:00，当前时间-createTime
	private String usedParkHoursString;
	//车位最长停留时间
	private String maxParkHoursString;
	//可延长最长时间:maxParkHoursString-usedParkHoursString
	private String maxDelayParkHoursString;
	//记录实际的停车时长
	private BigDecimal actualParkHours;
	//实际价格：=单价*实际停车时长
	private BigDecimal actualPrice;
	//是否只查询快要到期的数据，默认否查询全部使用中的车位信息
	private Integer isQuerySoonExpire = 0;
	
	//最大费用：一天停车最多消费多少钱，默认0
	private BigDecimal maxPriceAllDay;
	//免费停车时长：单位分钟,默认0
	private Integer freeParkingMinutes;
	//免费费用
	private BigDecimal freePrice;
	
	//实际应该付费
	private BigDecimal actualPayPrice;
	
	
	/**
	 * 新增字段，用来判断与客户端的连接信息
	 */
	//是否授权成功：系统定时执行，如果授权成功表示开通临时权限，1成功，0不成功
	private Integer isGrantSuccess;
	//小区ID
	private String comid;
	//授权次数：调用socket授权的次数，用来限制最大使用数,默认为0
	private Integer tryGrantCount;
	
	//车主信息
	private Caruser caruser;
	
	
	public BigDecimal getActualPayPrice() {
		return actualPayPrice;
	}
	public void setActualPayPrice(BigDecimal actualPayPrice) {
		this.actualPayPrice = actualPayPrice;
	}
	public Integer[] getBillStatusQuery() {
		return billStatusQuery;
	}
	public void setBillStatusQuery(Integer[] billStatusQuery) {
		this.billStatusQuery = billStatusQuery;
	}
	public BigDecimal getMaxPriceAllDay() {
		return maxPriceAllDay;
	}
	public void setMaxPriceAllDay(BigDecimal maxPriceAllDay) {
		this.maxPriceAllDay = maxPriceAllDay;
	}
	public Integer getFreeParkingMinutes() {
		return freeParkingMinutes;
	}
	public void setFreeParkingMinutes(Integer freeParkingMinutes) {
		this.freeParkingMinutes = freeParkingMinutes;
	}
	public BigDecimal getFreePrice() {
		return freePrice;
	}
	public void setFreePrice(BigDecimal freePrice) {
		this.freePrice = freePrice;
	}
	public Date getLastPayTime() {
		return lastPayTime;
	}
	public void setLastPayTime(Date lastPayTime) {
		this.lastPayTime = lastPayTime;
	}
	public BigDecimal getPayedMoney() {
		return payedMoney;
	}
	public void setPayedMoney(BigDecimal payedMoney) {
		this.payedMoney = payedMoney;
	}
	public Integer getIsQuerySoonExpire() {
		return isQuerySoonExpire;
	}
	public void setIsQuerySoonExpire(Integer isQuerySoonExpire) {
		this.isQuerySoonExpire = isQuerySoonExpire;
	}
	public Caruser getCaruser() {
		return caruser;
	}
	public void setCaruser(Caruser caruser) {
		this.caruser = caruser;
	}
	public BigDecimal getActualParkHours() {
		return actualParkHours;
	}
	public void setActualParkHours(BigDecimal actualParkHours) {
		this.actualParkHours = actualParkHours;
	}
	public BigDecimal getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}
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
	public String getSpaceOwnerUserId() {
		return spaceOwnerUserId;
	}
	public void setSpaceOwnerUserId(String spaceOwnerUserId) {
		this.spaceOwnerUserId = spaceOwnerUserId;
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
	public Integer getIsGrantSuccess() {
		return isGrantSuccess;
	}
	public void setIsGrantSuccess(Integer isGrantSuccess) {
		this.isGrantSuccess = isGrantSuccess;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public Integer getTryGrantCount() {
		return tryGrantCount;
	}
	public void setTryGrantCount(Integer tryGrantCount) {
		this.tryGrantCount = tryGrantCount;
	}
	@Override
	public String toString() {
		return "ParkingSpaceBill [orderJnlNo=" + orderJnlNo + ", userId=" + userId + ", spaceOwnerUserId="
				+ spaceOwnerUserId + ", carno=" + carno + ", spaceno=" + spaceno + ", billStatus=" + billStatus
				+ ", billStatusQuery=" + Arrays.toString(billStatusQuery) + ", parkHours=" + parkHours
				+ ", delayParkHours=" + delayParkHours + ", delayParkHoursString=" + delayParkHoursString
				+ ", unitPrice=" + unitPrice + ", budgetPrice=" + budgetPrice + ", createTime=" + createTime
				+ ", lastPayTime=" + lastPayTime + ", payedMoney=" + payedMoney + ", usedParkHoursString="
				+ usedParkHoursString + ", maxParkHoursString=" + maxParkHoursString + ", maxDelayParkHoursString="
				+ maxDelayParkHoursString + ", actualParkHours=" + actualParkHours + ", actualPrice=" + actualPrice
				+ ", isQuerySoonExpire=" + isQuerySoonExpire + ", maxPriceAllDay=" + maxPriceAllDay
				+ ", freeParkingMinutes=" + freeParkingMinutes + ", freePrice=" + freePrice + ", actualPayPrice="
				+ actualPayPrice + ", isGrantSuccess=" + isGrantSuccess + ", comid=" + comid + ", tryGrantCount="
				+ tryGrantCount + ", caruser=" + caruser + "]";
	}
}
