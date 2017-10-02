package com.parkspace.model;

import java.io.Serializable;

/**
 * @Title: ParkingSpaceSurvey.java
 * @Package com.parkspace.model
 * <p>Description:车位概要信息
 * 车位数
 * 空闲车位数
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 下午6:28:12</p>
*/

public class ParkingSpaceSurvey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//车位数量:空闲和占用的
	private Integer enableSpacenoCount;
	
	//未使用的车位数量
	private Integer noUseingSpacenoCount;
	
	
	public Integer getNoUseingSpacenoCount() {
		return noUseingSpacenoCount;
	}
	public void setNoUseingSpacenoCount(Integer noUseingSpacenoCount) {
		this.noUseingSpacenoCount = noUseingSpacenoCount;
	}
	public Integer getEnableSpacenoCount() {
		return enableSpacenoCount;
	}
	public void setEnableSpacenoCount(Integer enableSpacenoCount) {
		this.enableSpacenoCount = enableSpacenoCount;
	}
	@Override
	public String toString() {
		return "ParkingSpaceSurvey [enableSpacenoCount=" + enableSpacenoCount + ", noUseingSpacenoCount="
				+ noUseingSpacenoCount + "]";
	}

}
