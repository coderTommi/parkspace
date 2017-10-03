package com.parkspace.controller.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.parkspace.util.Constants;

/**
 * @Title: TrsPojo.java
 * @Package com.parkspace.controller.pojo
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午4:51:01</p>
*/

public class TrsRequestPojo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal amt;
	private String remoteJnlNo;
	private Integer payChannel = Constants.PayChannel.WEIXIN.getValue();
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
