package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
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

public class SpaceOwner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//车位编号,形如3-101
	private String spaceno;
	//用户id
	private String userId;
	//是否认证:0未认证，1认证，默认0，-1表示禁用不在公开车位
	private Integer isauth;
	//车牌号,对于车主的车牌号只做记录，不做校验，可以不输入
	private String carno;
	//备注
	private String memo;
	//创建人
	private String createBy;
	//创建时间
	private Date createTime;
	//修改人
	private String modifyBy;
	//修改时间
	private Date modifyTime;
	
	//车位信息
	private ParkingSpace parkingSpace;
	//用户基本信息
	
	
	
	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}
	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	public String getSpaceno() {
		return spaceno;
	}
	public void setSpaceno(String spaceno) {
		this.spaceno = spaceno;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "SpaceOwner [spaceno=" + spaceno + ", userId=" + userId + ", isauth=" + isauth + ", carno=" + carno
				+ ", memo=" + memo + ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy
				+ ", modifyTime=" + modifyTime + ", parkingSpace=" + parkingSpace + "]";
	}
}
