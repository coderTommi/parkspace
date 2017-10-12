package test.baseuser.svc;

import java.math.BigDecimal;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.service.IMoneyService;
import com.parkspace.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"})  
public class MoneyServiceTest {
	
	private static Logger logger = LoggerFactory.getLogger(MoneyServiceTest.class);  
	
	@Resource
	private IMoneyService moneyService;
	@Test
	public void testRecharge () throws Exception{
		moneyService.recharge("f55dc148-5abf-4af3-a0f6-535c6cdebf4d",new BigDecimal("1"), null, null);
	}
	
	@Test
	public void testWithDraw () throws Exception{
		moneyService.withdrawCash("f55dc148-5abf-4af3-a0f6-535c6cdebf4d",new BigDecimal("10"));
	}
	
	@Test
	public void testpledgeIn() throws Exception {
		moneyService.pledgeIn("f55dc148-5abf-4af3-a0f6-535c6cdebf4d", new BigDecimal("100"), null, null);
	}
	
	@Test
	public void testpledgeOut() throws Exception {
		moneyService.pledgeOut("f55dc148-5abf-4af3-a0f6-535c6cdebf4d", new BigDecimal("100"));
	}
	
	@Test
	public void testOrder() throws Exception {
		moneyService.order("f55dc148-5abf-4af3-a0f6-535c6cdebf4d", "222222222222222", new BigDecimal("10"), "1");
	}
	
	@Test
	public void testbonusOut() throws Exception {
		moneyService.bonusOut("f55dc148-5abf-4af3-a0f6-535c6cdebf4d", new BigDecimal("11"));
	}
}
