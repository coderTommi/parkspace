package com.parkspace.db.rmdb.dao;

import com.parkspace.db.rmdb.entity.SMSCode;

/**
 * 短信驗證碼
 * @author lidongliang
 *
 */
public interface SMSCodeDao {
	
	public void save(SMSCode code);
	
	public SMSCode getNewestCodeByPhone(String phone);

}
