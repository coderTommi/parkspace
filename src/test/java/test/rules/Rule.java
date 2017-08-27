package test.rules;

public class Rule {
	/** seq **/
	private int ruleSeq;
	/** 规则类型 **/
	private String ruleType;
	/** 规则id **/
	private String ruleId;
	/** 规则详情 **/
	private String ruleDef;
	public int getRuleSeq() {
		return ruleSeq;
	}
	public void setRuleSeq(int ruleSeq) {
		this.ruleSeq = ruleSeq;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleDef() {
		return ruleDef;
	}
	public void setRuleDef(String ruleDef) {
		this.ruleDef = ruleDef;
	}
}
