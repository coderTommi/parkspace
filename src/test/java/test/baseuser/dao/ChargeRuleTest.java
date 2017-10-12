package test.baseuser.dao;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.dao.ChargeRuleDao;
import com.parkspace.db.rmdb.entity.ChargeRule;
import com.parkspace.util.Constants;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class ChargeRuleTest {
	private static Logger logger = LoggerFactory.getLogger(ChargeRuleTest.class);  
	
	@Resource
	private ChargeRuleDao chargeRuleDao = null;
	@Test
	public void testsave(){
		ChargeRule rule = new ChargeRule();
		rule.setId(UUID.randomUUID().toString());
		rule.setComId("123");
		rule.setRuleDef("0.2,0.3,0.5");
		rule.setRuleType(Constants.RuleType.CHARGE.getValue());
		chargeRuleDao.save(rule);
	}
	@Test
	public void testQry() {
		ChargeRule rule = new ChargeRule();
		rule.setComId("123");
		rule.setRuleType(Constants.RuleType.CHARGE.getValue());
		List<ChargeRule> rules = chargeRuleDao.qry(rule);
		for(ChargeRule r: rules)
		{
			System.err.println(r);
		}
	}
	@Test
	public void testSaveOrUpdate()
	{
		ChargeRule rule = new ChargeRule();
		rule.setComId("123");
		rule.setRuleType(Constants.RuleType.CHARGE.getValue());
		rule.setRuleDef("1,1,1");
		chargeRuleDao.saveOrUpdate(rule);
	}
	
	
}
 