package com.parkspace.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.parkspace.common.exception.PackspaceServiceException;
import com.parkspace.db.rmdb.dao.SMSCodeDao;
import com.parkspace.db.rmdb.entity.SMSCode;
import com.parkspace.service.ISmsCodeService;
import com.parkspace.util.CommonUtils;
import com.parkspace.util.Constants;

public class SmsCodeServiceImpl implements ISmsCodeService {
	private static Log logger = LogFactory.getLog(SmsCodeServiceImpl.class);
	private static final int TIMEOUT = 60;

	@Resource
	private SMSCodeDao sMSCodeDao;
	@Override
	public void sendSMSCode(String telePhone) {
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
	public boolean checkSmsCode(String telePhone, String smsCode) {
		SMSCode code = sMSCodeDao.getNewestCodeByPhone(telePhone);
		if(code == null || (System.currentTimeMillis() - code.getCreateTime() > TIMEOUT)) {
			logger.error("smscode is error");
			return false;
		}
		return true;
	}

}
