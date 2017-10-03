package com.parkspace.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.db.rmdb.dao.WalletLockDao;
import com.parkspace.service.IWalletLockService;

/**
 * @Title: WalletLockServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午2:24:31</p>
*/
@Service("walletLockService")
public class WalletLockServiceImpl implements IWalletLockService {
	private static Log log = LogFactory.getLog(WalletLockServiceImpl.class);
	@Resource
	private WalletLockDao walletLockDao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public boolean lockWallet(String userId) {
		try {
			walletLockDao.save(userId);
		} catch (Exception e) {
			log.error("lock  wallet error"+userId, e);
			return false;
		}
		return true;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public boolean releaseLock(String userId) {
		try {
			walletLockDao.delete(userId);
		} catch (Exception e) {
			log.error("release  wallet error"+userId, e);
			return false;
		}
		return true;
	}
	

}
