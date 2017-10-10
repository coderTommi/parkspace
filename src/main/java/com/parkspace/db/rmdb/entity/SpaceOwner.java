package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @Title: SpaceOwner.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 车位业主信息表,包含物业车位信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午4:04:24</p>
*/

public class SpaceOwner extends BaseUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//车位编号,形如3-101
	private String spaceno;
	//状态:0未认证，1认证，默认0，-1表示禁用不在公开车位
	private Integer isauth;
	//状态查询
	private Integer[]  isauthQuery;
	//车牌号,对于车主的车牌号只做记录，不做校验，可以不输入
	private String carno;
	//车位信息
	private ParkingSpace parkingSpace;
	//认证时间，认证之后记录的时间，不可修改
	private Date certifiedTime;
	//小区基本信息
	private Community community;
	//行政区域基本信息
	private Zone zone;
	
	public String getSpaceno() {
		return spaceno;
	}
	public void setSpaceno(String spaceno) {
		this.spaceno = spaceno;
	}
	public Integer getIsauth() {
		return isauth;
	}
	public void setIsauth(Integer isauth) {
		this.isauth = isauth;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public Integer[] getIsauthQuery() {
		return isauthQuery;
	}
	public void setIsauthQuery(Integer[] isauthQuery) {
		this.isauthQuery = isauthQuery;
	}
	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}
	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
	}
	public Date getCertifiedTime() {
		return certifiedTime;
	}
	public void setCertifiedTime(Date certifiedTime) {
		this.certifiedTime = certifiedTime;
	}
	@Override
	public String toString() {
		return "SpaceOwner [spaceno=" + spaceno + ", isauth=" + isauth + ", isauthQuery=" + Arrays.toString(isauthQuery)
				+ ", carno=" + carno + ", parkingSpace=" + parkingSpace + ", certifiedTime=" + certifiedTime
				+ ", community=" + community + ", zone=" + zone + "]";
	}
}
