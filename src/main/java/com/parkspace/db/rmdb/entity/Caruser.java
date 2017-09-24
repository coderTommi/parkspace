package com.parkspace.db.rmdb.entity;

import java.io.Serializable;

/**
 * @Title: Caruser.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 车主表（Caruser）
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午5:28:24</p>
*/

public class Caruser extends BaseUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//车牌号
	private String carno;
	//是否认证 0:否 1：是，默认1，-1表示禁用
	private Integer isauth;
	
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public Integer getIsauth() {
		return isauth;
	}
	public void setIsauth(Integer isauth) {
		this.isauth = isauth;
	}
}
