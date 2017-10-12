package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.ChargeRule;

/**
 * 规则
 * @Title: ChargeRuleDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 上午11:22:51</p>
*/

public interface ChargeRuleDao {
	
	public void save(ChargeRule rule) ;
	
	public void update(ChargeRule rule);
	
	public List<ChargeRule> qry(ChargeRule rule);
	
	/**
	 * 小区计费规则
	 * @Title: saveOrUpdate
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月10日 上午11:36:05</p>
	 */
	public void saveOrUpdate(ChargeRule rule);

}
