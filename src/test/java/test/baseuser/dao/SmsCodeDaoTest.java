package test.baseuser.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.dao.SMSCodeDao;
import com.parkspace.db.rmdb.entity.SMSCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class SmsCodeDaoTest {

	@Resource
	private SMSCodeDao sMSCodeDao = null;
	@Test
	public void testsave(){
		SMSCode code = new SMSCode();
		code.setCreateTime(System.currentTimeMillis());
		code.setSmsCode(23456);
		code.setTelePhone("13888888888");
		sMSCodeDao.save(code);
	}
	
	@Test
	public void testGetNewest() {
		SMSCode code = sMSCodeDao.getNewestCodeByPhone("13888888888");
		System.out.println(code.getSmsCode());
	}
	
	
}
