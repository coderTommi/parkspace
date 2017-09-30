package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @Title: Community.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 小区表:记录小区的基本信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 上午9:09:57</p>
*/

public class Community implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//小区ID
	private String comid;
	private String[] comidQuery;
	//区域ID
	private String zoneid;
	//小区名称
	private String comname;
	//小区地址
	private String address;
	//状态  0：未开放  1：封闭式小区，2：开放式小区,默认0，如果是-1表示禁用
	private Integer isenable;
	//状态查询条件
	private Integer[] isenableQuery;
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
	
	//行政区域基本信息
	private Zone zone;
	//区域名称
//	private String zonename;
	//状态  0：未开放  1：已开放，-1,表示删除，默认0
//	private Integer zoneisenable;
	//行政区域状态查询
//	private Integer[] zoneIsenableQuery;
	//省编码
//	private String province;
	//市
//	private String city;
	//区
//	private String zone;
	
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public String getZoneid() {
		return zoneid;
	}
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsenable() {
		return isenable;
	}
	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
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
	public Integer[] getIsenableQuery() {
		return isenableQuery;
	}
	public void setIsenableQuery(Integer[] isenableQuery) {
		this.isenableQuery = isenableQuery;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public String[] getComidQuery() {
		return comidQuery;
	}
	public void setComidQuery(String[] comidQuery) {
		this.comidQuery = comidQuery;
	}
	@Override
	public String toString() {
		return "Community [comid=" + comid + ", comidQuery=" + Arrays.toString(comidQuery) + ", zoneid=" + zoneid
				+ ", comname=" + comname + ", address=" + address + ", isenable=" + isenable + ", isenableQuery="
				+ Arrays.toString(isenableQuery) + ", memo=" + memo + ", createBy=" + createBy + ", createTime="
				+ createTime + ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + ", zone=" + zone + "]";
	}
}
