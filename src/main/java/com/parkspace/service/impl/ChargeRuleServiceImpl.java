package com.parkspace.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.ChargeRuleDao;
import com.parkspace.db.rmdb.entity.ChargeRule;
import com.parkspace.service.IChargeRuleService;
import com.parkspace.util.Constants;

/**
 * @Title: ChargeRuleServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 下午2:31:20</p>
*/
@Service("chargeRuleService")
public class ChargeRuleServiceImpl implements IChargeRuleService {

	private Log log = LogFactory.getLog(ChargeRuleServiceImpl.class);
	@Resource
	private ChargeRuleDao chargeRuleDao;
	@Override
	public List<ChargeRule> getRules() throws ParkspaceServiceException, Exception {
		return chargeRuleDao.qry(new ChargeRule());
	}

	@Override
	public void saveOrUpdate(String comId, String ruleDef, Integer ruleType) throws ParkspaceServiceException, Exception {
		if(ruleType == Constants.RuleType.CHARGE.getValue()) {
			String [] strs = ruleDef.split(",");
			if(strs.length != 3 || (Double.valueOf(strs[0]) + Double.valueOf(strs[1]) + Double.valueOf(strs[2])  != 1.0))
			{
				log.error("setup rule error, ruleDef= " + ruleDef + " comId="+comId);
				throw new ParkspaceServiceException(Constants.ERRORCODE.CHARGERULES_SETUP_ERROR.toString());
			}
		}
		ChargeRule rule = new ChargeRule();
		rule.setId(UUID.randomUUID().toString());
		rule.setComId(comId);
		rule.setRuleDef(ruleDef);
		rule.setRuleType(ruleType);
		chargeRuleDao.save(rule);
	}
	
	@Override
	public ChargeRule getRuleByComId(String comId, Integer ruleType) throws ParkspaceServiceException, Exception {
		ChargeRule rule = new ChargeRule();
		rule.setComId(comId);
		rule.setRuleType(ruleType);
		List<ChargeRule> rules = chargeRuleDao.qry(rule);
		if(rules == null || rules.size() == 0) {
			rule.setComId("0");
			rules = chargeRuleDao.qry(rule);
		} 
		if(rules == null || rules.size() == 0) {
			return null;
		}
		return rules.get(0);
	}
}
