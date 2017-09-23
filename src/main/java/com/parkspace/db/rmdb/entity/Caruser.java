package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Caruser.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 车主表（Caruser）
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午5:28:24</p>
*/

public class Caruser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户id
	private String userId;
	//车牌号
	private String carno;
	//是否认证 0:否 1：是，默认1，-1表示禁用
	private Integer isauth;
	
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
	public Integer getIsauth() {
		return isauth;
	}
	public void setIsauth(Integer isauth) {
		this.isauth = isauth;
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
		return "Caruser [userId=" + userId + ", carno=" + carno + ", isauth=" + isauth + ", memo=" + memo
				+ ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy + ", modifyTime="
				+ modifyTime + "]";
	}
}
