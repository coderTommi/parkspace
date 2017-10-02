package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @Title: ShareConfig.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 共享时间设置表：记录车位的共享时间段的设置信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午4:39:47</p>
*/

public class ShareConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private String UUID;
	//车位编号,形如3-101
	private String spaceno;
	//状态，1：开启，0未开启；默认开启,-1表示删除
	private Integer isOpen;
	
	//状态查询条件
	private Integer[] isOpenQuery;
	
	//共享类型：1周期性时间段，0自定义时间段
	private Integer shareType;
	//是否全天：1是，0否，如果是全天对应的开始时间为00:00:00，截至时间23:59:59
	private Integer isAllDay;
	//开始日期，格式（YYYY-MM-DD）2017-09-10，共享类型为自定义时记录该日期
	private String startDate;
	//开始时间，格式（24h）13:24:00
	private String startTime;
	//截至日期，格式（YYYY-MM-DD）2017-09-10，共享类型为自定义时记录该日期
	private String endDate;
	//截至时间，格式（24h）14:24:00
	private String endTime;
	//周期（中间使用英文逗号分割），记录共享类型为周期性的星期数据
    //周期性时间段：1,2,3,4,5,6,7；表示星期天到星期六,使用mysql函数DAYOFWEEK处理
	//注意：如果同时选择星期六和星期天需要在最好增加星期天的编号，形成环路
	private String internalDate;
	//创建人
	private String createBy;
	//创建时间
	private Date createTime;
	//修改人
	private String modifyBy;
	//修改时间
	private Date modifyTime;
	
	//查询日期:格式2017-09-10
	private String queryDate;
	//查询时间：13:23:10
	private String queryTime;
	//当前日期是星期几：1,2,3,4,5,6,7；表示星期天到星期六
	private Integer nowWeek;
	//第二个是星期几
	private Integer nowNextWeek;
	//可停留的时间：A表示长期无限制，其他格式形如100:10:20（表示100个小时10分钟20秒）
	private String parkHourString;
	
	public Integer getNowWeek() {
		return nowWeek;
	}
	public void setNowWeek(Integer nowWeek) {
		this.nowWeek = nowWeek;
	}
	public Integer getNowNextWeek() {
		return nowNextWeek;
	}
	public void setNowNextWeek(Integer nowNextWeek) {
		this.nowNextWeek = nowNextWeek;
	}
	public String getParkHourString() {
		return parkHourString;
	}
	public void setParkHourString(String parkHourString) {
		this.parkHourString = parkHourString;
	}
	public String getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getSpaceno() {
		return spaceno;
	}
	public void setSpaceno(String spaceno) {
		this.spaceno = spaceno;
	}
	public Integer getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	public Integer getShareType() {
		return shareType;
	}
	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getInternalDate() {
		return internalDate;
	}
	public void setInternalDate(String internalDate) {
		this.internalDate = internalDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer[] getIsOpenQuery() {
		return isOpenQuery;
	}
	public void setIsOpenQuery(Integer[] isOpenQuery) {
		this.isOpenQuery = isOpenQuery;
	}
	public Integer getIsAllDay() {
		return isAllDay;
	}
	public void setIsAllDay(Integer isAllDay) {
		this.isAllDay = isAllDay;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "ShareConfig [UUID=" + UUID + ", spaceno=" + spaceno + ", isOpen=" + isOpen + ", isOpenQuery="
				+ Arrays.toString(isOpenQuery) + ", shareType=" + shareType + ", isAllDay=" + isAllDay + ", startDate="
				+ startDate + ", startTime=" + startTime + ", endDate=" + endDate + ", endTime=" + endTime
				+ ", internalDate=" + internalDate + ", createBy=" + createBy + ", createTime=" + createTime
				+ ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + ", queryDate=" + queryDate + ", queryTime="
				+ queryTime + ", nowWeek=" + nowWeek + ", nowNextWeek=" + nowNextWeek + ", parkHourString="
				+ parkHourString + "]";
	}
}
