package com.parkspace.controller.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Title: WithDrawPojo.java
 * @Package com.parkspace.controller.pojo
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月23日 下午6:48:56</p>
*/

public class WithDrawPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal amt;
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

}
