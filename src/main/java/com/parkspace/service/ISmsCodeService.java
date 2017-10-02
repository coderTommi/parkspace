package com.parkspace.service;

import com.parkspace.common.exception.ParkspaceServiceException;

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
	public void sendSMSCode(String telePhone) throws ParkspaceServiceException, Exception;
	/**
	 * 校驗短信驗證碼
	 * @param telePhone
	 * @param smsCode
	 */
	public void checkSmsCode(String telePhone, String smsCode) throws ParkspaceServiceException, Exception;

}
