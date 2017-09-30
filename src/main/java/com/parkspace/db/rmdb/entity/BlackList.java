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
	
	//用户基本信息
	private BaseUser baseUser;
	
	//用户名称
//	private String userName;
	//昵称
//	private String nickName;
	//'手机号码
//	private String telephone;
	//是否是管理员 0:否  1：是
//	private Integer isAdmin;
	//证件类型 0: 身份证
//	private Integer idtype;
	//证件号码
//	private String idno;
	//用户状态 0：正常	1：异常锁定,2：未交押金,3；已交押金
//	private String state;
	//微信账号,用来汇款的账号
//	private String weixinAccount;
	//头像
//	private String avator;
	
	
	@Override
	public String toString() {
		return "BlackList [UUID=" + UUID + ", userId=" + userId + ", isCancel=" + isCancel + ", memo=" + memo
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", baseUser=" + baseUser + "]";
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
