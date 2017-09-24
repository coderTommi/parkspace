package test.baseuser;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.dao.IntegralDao;
import com.parkspace.db.rmdb.entity.Integral;
import com.parkspace.db.rmdb.entity.Wallet;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class IntegralDaoTest {
	private static Logger logger = LoggerFactory.getLogger(IntegralDaoTest.class);  
	
	@Resource
	private IntegralDao integralDao = null;
	@Test
	public void testsave(){
		Integral integral = new Integral();
		integral.setUserId("b46fbe51-3927-45e2-b71a-9cd381769ba9");
		integral.setVal(111);
		integralDao.save(integral);
	}
	
	
	@Test
	public void getByUserId(){
		Integral integral = integralDao.getByUserId("b46fbe51-3927-45e2-b71a-9cd381769ba9");
		System.out.println(integral.getVal());
	}
	@Test
	public void update(){
		Integral integral = new Integral();
		integral.setUserId("b46fbe51-3927-45e2-b71a-9cd381769ba9");
		integral.setVal(222);
		integralDao.update(integral);
	}
	

}
 