package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
/**
 * 账单
 * @author lidongliang
 *
 */
public class Bill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billId;
	private String payer;
	private String payee;
	private Date transDate;
	private Timestamp transTime;
	private Integer billType;
	private BigDecimal amount;
	private Integer state;
	private String orderJnlno;
	private String remoteJnlNo;
	private Integer payChannel;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public Integer getBillType() {
		return billType;
	}
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getOrderJnlno() {
		return orderJnlno;
	}
	public void setOrderJnlno(String orderJnlno) {
		this.orderJnlno = orderJnlno;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public Timestamp getTransTime() {
		return transTime;
	}
	public void setTransTime(Timestamp transTime) {
		this.transTime = transTime;
	}
	
	public String getRemoteJnlNo() {
		return remoteJnlNo;
	}
	public void setRemoteJnlNo(String remoteJnlNo) {
		this.remoteJnlNo = remoteJnlNo;
	}
	
	public Integer getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}
}
