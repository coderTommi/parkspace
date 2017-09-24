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
	//用户名称
	private String userName;
	//昵称
	private String nickName;
	//'手机号码
	private String telephone;
	//是否是管理员 0:否  1：是
	private Integer isAdmin;
	//证件类型 0: 身份证
	private Integer idtype;
	//证件号码
	private String idno;
	//用户状态 0：正常	1：异常锁定,2：未交押金,3；已交押金
	private String state;
	//微信账号,用来汇款的账号
	private String weixinAccount;
	//头像
	private String avator;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getWeixinAccount() {
		return weixinAccount;
	}
	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}
	public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
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
	@Override
	public String toString() {
		return "BlackList [UUID=" + UUID + ", userId=" + userId + ", isCancel=" + isCancel + ", memo=" + memo
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", userName=" + userName + ", nickName="
				+ nickName + ", telephone=" + telephone + ", isAdmin=" + isAdmin + ", idtype=" + idtype + ", idno="
				+ idno + ", state=" + state + ", weixinAccount=" + weixinAccount + ", avator=" + avator + "]";
	}
}
