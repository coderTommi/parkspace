package com.parkspace.service;
/**
 * 短信驗證碼service
 * @author lidongliang
 *
 */
public interface ISmsCodeService {
	/**
	 * 發送短信驗證碼
	 * @param telePhone
	 */
	public void sendSMSCode(String telePhone);
	/**
	 * 校驗短信驗證碼
	 * @param telePhone
	 * @param smsCode
	 * @return
	 */
	public boolean checkSmsCode(String telePhone, String smsCode);

}
