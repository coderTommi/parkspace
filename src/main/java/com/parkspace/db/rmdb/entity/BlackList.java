package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: BlackList.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 黑名单，记录用户黑名单信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午1:14:54</p>
*/

public class BlackList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private String UUID;
	//用户id
	private String userId;
	//是否取消，1，是，0否，默认0
	private Integer isCancel;
	//备注
	private String memo;
	//创建时间
	private Date createTime;
	//修改时间
	private Date modifyTime;
	//用户类型：0业主，1车主，2，物业，3其他
	private Integer userType;
	
	//用户基本信息
	private BaseUser baseUser;
	
	@Override
	public String toString() {
		return "BlackList [UUID=" + UUID + ", userId=" + userId + ", isCancel=" + isCancel + ", memo=" + memo
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", userType=" + userType + ", baseUser="
				+ baseUser + "]";
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public BaseUser getBaseUser() {
		return baseUser;
	}

	public void setBaseUser(BaseUser baseUser) {
		this.baseUser = baseUser;
	}
}
