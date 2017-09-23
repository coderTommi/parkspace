package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
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
	//是否开启，1：开启，0未开启；默认开启
	private Integer isOpen;
	//共享类型：1周期性时间段，0自定义时间段
	private Integer shareType;
	//开始时间，格式（24h）13:24:00
	private String startTime;
	//截至时间，格式（24h）14:24:00
	private String endTime;
	//日期（中间使用英文逗号分割）
    //周期性时间段：0,1,2,3,4,5,6；表示星期天到星期六
    //自定义时间：2017-01-01,2017-02-03表示开始日期到截止日期
	private String internalDate;
	//创建人
	private String createBy;
	//创建时间
	private Date createTime;
	//修改人
	private String modifyBy;
	//修改时间
	private Date modifyTime;
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
	@Override
	public String toString() {
		return "ShareConfig [UUID=" + UUID + ", spaceno=" + spaceno + ", isOpen=" + isOpen + ", shareType=" + shareType
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", internalDate=" + internalDate + ", createBy="
				+ createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime
				+ "]";
	}
}
