package com.parkspace.service;
/**
 * 公共服务
 * @author lidongliang
 *
 */
public interface ICommonService {
	/** 获取短信验证码 **/
	public String genSMSCode(String telePhone);
	/** 验证短信验证码 **/
	public boolean verifySMSCode(String telePhone, String smsCode);
	
	public void sendSMS(String telePhone, String smsMsg) ;
}
