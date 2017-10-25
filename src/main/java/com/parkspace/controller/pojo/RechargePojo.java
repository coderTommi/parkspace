package com.parkspace.controller.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Title: RechargePojo.java
 * @Package com.parkspace.controller.pojo
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月23日 下午6:48:56</p>
*/

public class RechargePojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal amt;
	private String  remoteJnlNo;
	private Integer payChannel;
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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
