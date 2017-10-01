package com.parkspace.controller.pojo;

import java.io.Serializable;

public class LoginWapper implements Serializable {
	
	private String telePhone;
	private String smsCode;
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
