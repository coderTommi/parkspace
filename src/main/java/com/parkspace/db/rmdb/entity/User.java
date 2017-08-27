package com.parkspace.db.rmdb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
public class User implements Serializable{
	private String userId;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
