package com.parkspace.service;

import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.ChargeRule;

/**
 * 计费规则
 * @Title: IChargeRuleService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 下午2:23:00</p>
*/

public interface IChargeRuleService {
	
	public List<ChargeRule> getRules() throws ParkspaceServiceException, Exception;
	
	public void saveOrUpdate(String comId, String ruleDef, Integer ruleType) throws ParkspaceServiceException, Exception;
	
	public ChargeRule getRuleByComId(String comId, Integer ruleType) throws ParkspaceServiceException, Exception;
	
}
