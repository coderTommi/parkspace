package com.parkspace.db.rmdb.entity;

import java.io.Serializable;

/**
 * 计费策略
 * @Title: ChargeRule.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 上午11:07:54</p>
*/

public class ChargeRule implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	/** 0: 分配策略  1：押金 **/
	private Integer ruleType;
	private String comId;
	/** 分配策略   业主,物业,公司 **/
	private String ruleDef;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRuleType() {
		return ruleType;
	}
	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getRuleDef() {
		return ruleDef;
	}
	public void setRuleDef(String ruleDef) {
		this.ruleDef = ruleDef;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ChargeRule [id=" + id + ", ruleType=" + ruleType + ", comId=" + comId + ", ruleDef=" + ruleDef + "]";
	}
	
}