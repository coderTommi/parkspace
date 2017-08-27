package test.rules;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationcontext.xml"}) 
public class TestDemo {
	@Resource
	private static CachedRuleAttribute cachedRuleAttribute ;
	@Resource
	private CheckDateRange checkDateRange;
	@Test
	public void testRules() {
		try {
			/** 获取规则详情 **/
			Rule rule = cachedRuleAttribute.getRuleById("CheckDate.CommonDateRange");
			String ruleDef = rule.getRuleDef();
			checkDateRange.checkDate("CheckDate.CommonDateRange", new Date("20170520"), new Date());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private boolean checkDateByDef(String ruleDef, Date beginDate, Date endDate){
		return false;
	}
}
