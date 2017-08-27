package test.rules;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

public class CachedRuleAttribute extends AutoFreshCachedData implements InitializingBean {
	private Map<String, Rule> sysRules;
	/**
	 * 容器启动时，加载应用规则
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.sysRules = loadDbRules();
	}
	private Map<String, Rule> loadDbRules() throws Exception{
		/** 查询数据库规则 **/
		//Map m = queryForMap("rule.loadRules");
		return null;
	}
	public Rule getRuleById(String ruleId) throws Exception {
		try {
			lockCache();
			return this.sysRules.get(ruleId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			unlockCache();
		}
	}
	@Override
	protected void updateCacheInner() {
		try {
			this.sysRules = loadDbRules();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
