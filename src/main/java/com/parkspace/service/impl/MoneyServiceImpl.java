package com.parkspace.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.BillDao;
import com.parkspace.db.rmdb.dao.WalletDao;
import com.parkspace.db.rmdb.entity.Bill;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.service.IMoneyService;

/**
 * 钱
 * @Title: MoneyServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午10:25:07</p>
*/
@Service("moneyService")
public class MoneyServiceImpl implements IMoneyService {
	private static Log log = LogFactory.getLog(MoneyServiceImpl.class);
	
	@Resource
	private WalletDao walletDao;
	@Resource
	private BillDao billDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void recharge(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
//		billDao
	}

	@Override
	public void withdrawMondy(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bill> qryWithdrawList(String userId, Date beginDate, Date endDate)
			throws ParkspaceServiceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void payPledge(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bill> qryIncomeList(String userId, Date beginDate, Date endDate)
			throws ParkspaceServiceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List qryIncomeDetailList(String usrId, Date beginDate, Date endDate)
			throws ParkspaceServiceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wallet qryWallet(String userId) throws ParkspaceServiceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
