package com.parkspace.service.impl;

import com.parkspace.service.ICommonService;

public class CommonServiceImpl implements ICommonService {

	@Override
	public String genSMSCode(String telePhone) {
		int code = (int)((Math.random()*9+1)*100000);
		return code+"";
	}

	@Override
	public boolean verifySMSCode(String telePhone, String smsCode) {
		return false;
	}
	
	@Override
	public void sendSMS(String telePhone, String smsMsg) {
		
	}
}
