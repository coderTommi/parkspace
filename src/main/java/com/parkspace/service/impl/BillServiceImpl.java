package com.parkspace.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.BillDao;
import com.parkspace.db.rmdb.entity.Bill;
import com.parkspace.service.IBillService;
import com.parkspace.util.Constants;

/**
 * @Title: BillServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午2:18:57</p>
*/
@Service("billService")
public class BillServiceImpl implements IBillService {

	private static Log log = LogFactory.getLog(BillServiceImpl.class);
	@Resource
	private BillDao billDao;
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public String save(Bill bill) throws ParkspaceServiceException, Exception {
		String billId = UUID.randomUUID().toString();
		bill.setBillId(billId);
		billDao.save(bill);
		return billId;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void updateWithTransaction(Bill bill) throws ParkspaceServiceException, Exception {
		billDao.update(bill);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateWithoutTransaction(Bill bill) throws ParkspaceServiceException, Exception {
		billDao.update(bill);
	}
	
	@Override
	public List<Bill> getList() throws ParkspaceServiceException, Exception {
		return null;
	}

}
