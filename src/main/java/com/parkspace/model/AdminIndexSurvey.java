package com.parkspace.model;

import java.io.Serializable;

/**
 * @Title: AdminIndexSurvey.java
 * @Package com.parkspace.model
 * <p>Description:首页概况信息
 * 推广的物业数、用户注册数、管理的车位数
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 下午5:59:56</p>
*/

public class AdminIndexSurvey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//推广的物业数
	private int propertyMgmtUserCount;
	//用户注册数
	private int userCout;
	//管理的车位数
	private int spaceCount;
	public int getPropertyMgmtUserCount() {
		return propertyMgmtUserCount;
	}
	public void setPropertyMgmtUserCount(int propertyMgmtUserCount) {
		this.propertyMgmtUserCount = propertyMgmtUserCount;
	}
	public int getUserCout() {
		return userCout;
	}
	public void setUserCout(int userCout) {
		this.userCout = userCout;
	}
	public int getSpaceCount() {
		return spaceCount;
	}
	public void setSpaceCount(int spaceCount) {
		this.spaceCount = spaceCount;
	}
	@Override
	public String toString() {
		return "AdminIndexSurvey [propertyMgmtUserCount=" + propertyMgmtUserCount + ", userCout=" + userCout
				+ ", spaceCount=" + spaceCount + "]";
	}
}
