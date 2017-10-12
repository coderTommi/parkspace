package test.baseuser.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.dao.PrivilegeTicketDao;
import com.parkspace.db.rmdb.entity.PrivilegeTicket;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class TicketTest {
	private static Logger logger = LoggerFactory.getLogger(TicketTest.class);  
	
	@Resource
	private PrivilegeTicketDao privilegeTicketDao = null;
	@Test
	public void testsave(){
		PrivilegeTicket  ticket = new PrivilegeTicket();
		ticket.setUserId("f55dc148-5abf-4af3-a0f6-535c6cdebf4d");
		ticket.setId(UUID.randomUUID().toString());
		ticket.setCreateDate(new Date(System.currentTimeMillis()));
		ticket.setEndDate(new Date(System.currentTimeMillis()+48*60*60*1000));
		ticket.setUsed(0);
		ticket.setAmt(new BigDecimal("200"));
		privilegeTicketDao.save(ticket);
	}
	
	@Test
	public void testUpdate(){
		PrivilegeTicket  ticket = new PrivilegeTicket();
		ticket.setId("fa97ba20-694d-4834-bcf2-9c8e0b9e44d8");
		ticket.setUsed(1);
		privilegeTicketDao.update(ticket);
	}
	
	@Test
	public void testQry01() {
		PrivilegeTicket  ticket = new PrivilegeTicket();
		ticket.setUserId("f55dc148-5abf-4af3-a0f6-535c6cdebf4d");
//		ticket.setUsed(1);
//		List<PrivilegeTicket> tickets = privilegeTicketDao.getByUserId_amt(ticket);
		List<PrivilegeTicket> tickets = privilegeTicketDao.getByUserId_time(ticket);
		for(PrivilegeTicket t : tickets) {
			System.err.println(t);
		}
	}
	

}
