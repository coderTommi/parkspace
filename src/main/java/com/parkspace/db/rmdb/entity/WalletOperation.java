package com.parkspace.db.rmdb.entity;

import java.io.Serializable;

/**
 * @Title: BillOperation.java
 * @Package com.parkspace.db.rmdb.mapping
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 上午9:39:52</p>
*/

public class WalletOperation extends Wallet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
