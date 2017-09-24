package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 个人钱包
 * @author lidongliang
 *
 */
public class Wallet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	/** 押金. **/
	private BigDecimal pledge;
	/** 余额 **/
	private BigDecimal balance;
	/** 奖金. **/
	private BigDecimal bonus;
	/** 待结算金额.  **/
	private BigDecimal unclosedAmt;
	/** 上次交易时间 **/
	private Timestamp lastTrsTime;
	/**  开通时间 **/
	private Timestamp openTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getPledge() {
		return pledge;
	}
	public void setPledge(BigDecimal pledge) {
		this.pledge = pledge;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public BigDecimal getUnclosedAmt() {
		return unclosedAmt;
	}
	public void setUnclosedAmt(BigDecimal unclosedAmt) {
		this.unclosedAmt = unclosedAmt;
	}
	public Timestamp getLastTrsTime() {
		return lastTrsTime;
	}
	public void setLastTrsTime(Timestamp lastTrsTime) {
		this.lastTrsTime = lastTrsTime;
	}
	public Timestamp getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Timestamp openTime) {
		this.openTime = openTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
