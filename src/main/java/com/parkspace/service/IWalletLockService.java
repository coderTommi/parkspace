package com.parkspace.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: IWalletLockService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午2:23:48</p>
*/

public interface IWalletLockService {
	/**
	 * 锁定账户，修改前先锁定，改完后再删除
	 * @Title: lockWallet
	 * <p>Description:</p>
	 * @param     参数
	 * @return boolean    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午11:25:56</p>
	 */
	public boolean lockWallet(String userId) ;
	/**
	 * 释放账户锁
	 * @Title: releaseLock
	 * <p>Description:</p>
	 * @param     参数
	 * @return boolean    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午2:27:50</p>
	 */
	public boolean releaseLock(String userId);
	

	
	
}
