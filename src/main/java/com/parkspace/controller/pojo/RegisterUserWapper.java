package com.parkspace.controller.pojo;

import java.io.Serializable;
/**
 * 用户注册
 * @author lidongliang
 *
 */
public class RegisterUserWapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String telePhone;
	private String userName;
	private String weixinAccount;
	private String realName;
	private String smsCode;
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWeixinAccount() {
		return weixinAccount;
	}
	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	@Override
	public String toString() {
		return "RegisterUserWapper [telePhone=" + telePhone + ", userName=" + userName + ", weixinAccount="
				+ weixinAccount + ", realName=" + realName + ", smsCode=" + smsCode + "]";
	}
	
}
