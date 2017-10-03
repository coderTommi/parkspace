package com.parkspace.db.rmdb.dao;


/**
 * @Title: WalletLockDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午11:13:39</p>
*/

public interface WalletLockDao {
	
	public void save(String userId) ;
	
	public void delete(String userId) ;

}
