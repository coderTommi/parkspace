package com.parkspace.db.rmdb.entity;
/**
 * @Title: WalletLock.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午11:12:33</p>
*/

import java.io.Serializable;
import java.sql.Timestamp;

public class WalletLock implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Timestamp lockTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getLockTime() {
		return lockTime;
	}
	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}
	@Override
	public String toString() {
		return "WalletLock [userId=" + userId + ", lockTime=" + lockTime + "]";
	}
	
}
