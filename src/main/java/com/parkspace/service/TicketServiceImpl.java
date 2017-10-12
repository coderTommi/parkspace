package com.parkspace.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.PrivilegeTicketDao;
import com.parkspace.db.rmdb.entity.PrivilegeTicket;

/**
 * @Title: TicketServiceImpl.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 上午10:27:41</p>
*/
@Service("ticketService")
public class TicketServiceImpl implements ITicketService {
	@Resource
	private PrivilegeTicketDao privilegeTicketDao;

	@Override
	public List<PrivilegeTicket> getUserTicket(String userId, String orderBy, Integer used, Date endDate) 
			throws ParkspaceServiceException, Exception {
		PrivilegeTicket ticket = new PrivilegeTicket();
		List<PrivilegeTicket> ticketes = null;
		ticket.setUserId(userId);
		if(used != null) {
			ticket.setUsed(used);
		}
		if(endDate != null) {
			ticket.setEndDate(endDate);
		}
		if("amt".equals(orderBy)) {
			ticketes = privilegeTicketDao.getByUserId_amt(ticket);
		} else {
			ticketes = privilegeTicketDao.getByUserId_time(ticket);
		}
		return ticketes;
	}

	@Override
	public void saveTicket(String userId, BigDecimal amt, int timeout) throws ParkspaceServiceException, Exception {
		PrivilegeTicket ticket = new PrivilegeTicket();
		ticket.setAmt(amt);
		ticket.setUserId(userId);
		ticket.setId(UUID.randomUUID().toString());
		ticket.setCreateDate(new Date(System.currentTimeMillis()));
		ticket.setEndDate(new Date(System.currentTimeMillis()+ timeout*24*60*60*1000));
		ticket.setUsed(0);
		privilegeTicketDao.save(ticket);
	}
	
	@Override
	public void updateTicket(String ticketId) throws ParkspaceServiceException, Exception {
		PrivilegeTicket ticket = new PrivilegeTicket();
		ticket.setId(ticketId);
		ticket.setUsed(1);
		privilegeTicketDao.update(ticket);
	}
	

}
