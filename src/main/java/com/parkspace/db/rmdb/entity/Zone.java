package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Zone.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 行政区域（Zone），记录区域的基本信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:04:49</p>
*/

public class Zone implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//区域ID
	private String zoneid;
	//区域名称
	private String zonename;
	//状态  0：否  1：是，-1:表示删除，默认0
	private Integer isenable;
	//省编码
	private String province;
	//市
	private String city;
	//区
	private String zone;
	//备注
	private String memo;
	//创建人
	private String createBy;
	//创建时间
	private Date createTime;
	//修改人
	private String modifyBy;
	//修改时间
	private Date modifyTime;
	public String getZoneid() {
		return zoneid;
	}
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}
	public String getZonename() {
		return zonename;
	}
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	public Integer getIsenable() {
		return isenable;
	}
	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
		return "Zone [zoneid=" + zoneid + ", zonename=" + zonename + ", isenable=" + isenable + ", province=" + province
				+ ", city=" + city + ", zone=" + zone + ", memo=" + memo + ", createBy=" + createBy + ", createTime="
				+ createTime + ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + "]";
	}
}
