package com.parkspace.db.rmdb.entity;

import java.io.Serializable;

/**
 * 积分类
 * @author lidongliang
 *
 */
public class Integral implements Serializable {
	
	private String userId;
	/** 积分值 **/
	private Integer val;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getVal() {
		return val;
	}
	public void setVal(Integer val) {
		this.val = val;
	}
}
