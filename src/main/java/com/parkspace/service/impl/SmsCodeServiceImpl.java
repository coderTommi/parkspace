package com.parkspace.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.SMSCodeDao;
import com.parkspace.db.rmdb.entity.SMSCode;
import com.parkspace.service.ISmsCodeService;
import com.parkspace.util.CommonUtils;
import com.parkspace.util.Constants;

@Service("smsCodeService")
public class SmsCodeServiceImpl implements ISmsCodeService {
	private static Log logger = LogFactory.getLog(SmsCodeServiceImpl.class);
	private static final int TIMEOUT = 60;

	@Resource
	private SMSCodeDao sMSCodeDao;
	@Override
	public void sendSMSCode(String telePhone) throws ParkspaceServiceException, Exception{
		/*
		 * 检查是否有未过期短信，如果有,等过期再发送
		 */
		SMSCode oldSMSCode = sMSCodeDao.getNewestCodeByPhone(telePhone);
		if(System.currentTimeMillis() - oldSMSCode.getCreateTime() < TIMEOUT) {
			return;
		}
		
		Integer smsCode = CommonUtils.generateSMSCode();
		/*
		 * 發送短信
		 */
		SMSCode code = new SMSCode();
		code.setTelePhone(telePhone);
		code.setSmsCode(smsCode);
		code.setCreateTime(System.currentTimeMillis());
		sMSCodeDao.save(code);
	}

	@Override
	public void checkSmsCode(String telePhone, String smsCode) throws ParkspaceServiceException, Exception {
		SMSCode code = sMSCodeDao.getNewestCodeByPhone(telePhone);
		if(code == null || (System.currentTimeMillis() - code.getCreateTime() > TIMEOUT)) {
			logger.error("smscode is error");
			throw new ParkspaceServiceException(Constants.ERRORCODE.SMSCODE_IS_ERROR.toString());
		}
	}

}
