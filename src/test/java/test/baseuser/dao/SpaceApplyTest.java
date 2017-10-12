package test.baseuser.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.dao.BillDao;
import com.parkspace.db.rmdb.dao.SpaceApplyDao;
import com.parkspace.db.rmdb.entity.Bill;
import com.parkspace.db.rmdb.entity.SpaceApply;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class SpaceApplyTest {
	private static Logger logger = LoggerFactory.getLogger(SpaceApplyTest.class);  
	
	@Resource
	private SpaceApplyDao spaceApplyDao = null;
	@Test
	public void save() {
		SpaceApply apply = new SpaceApply();
		apply.setId(UUID.randomUUID().toString());
		apply.setUserId("1");
		apply.setApplyTime(new Timestamp(System.currentTimeMillis()));
		apply.setIdNo("123124123213");
		apply.setIdType(0);
		apply.setRealName("lidongliang");
		apply.setTelePhone("13822222222");
		apply.setState(0);
		spaceApplyDao.save(apply);
	}
	@Test
	public void update(){
		SpaceApply apply = new SpaceApply();
		apply.setId("7b6f5b80-2a17-40b8-a403-2f0c1e3fe211");
		apply.setState(1);
		apply.setAuthTime(new Timestamp(System.currentTimeMillis()));
		spaceApplyDao.update(apply);
	}
	@Test
	public void testQry(){
		SpaceApply apply = new SpaceApply();
		apply.setComId("1");
		apply.setState(1);
		List<SpaceApply> list = spaceApplyDao.qryList(apply);
		for(SpaceApply a : list)
		{
			System.out.println(a.getId());
		}
	}
	

}
 