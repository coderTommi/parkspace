package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: PropertyMgmtUser.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 物业人员信息表（PropertyMgmtUser）:维护物业人员的管理关系
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午12:43:18</p>
*/

public class PropertyMgmtUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//小区ID
	private String comid;
	//用户id
	private String userId;
	//是否是管理员 0:否  1：是,默认为0，如果为-1表示禁用
	private Integer isAdmin;
	
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
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
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
		return "PropertyMgmtUser [comid=" + comid + ", userId=" + userId + ", isAdmin=" + isAdmin + ", memo=" + memo
				+ ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy + ", modifyTime="
				+ modifyTime + "]";
	}
}
