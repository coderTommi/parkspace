package com.parkspace.db.rmdb.entity;


public class SMSCode {
	private String telePhone;
	private Integer smsCode;
	private Long createTime;
	public Integer getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(Integer smsCode) {
		this.smsCode = smsCode;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	@Override
	public String toString() {
		return "SMSCode [telePhone=" + telePhone + ", smsCode=" + smsCode + ", createTime=" + createTime + "]";
	}
	
	
}
