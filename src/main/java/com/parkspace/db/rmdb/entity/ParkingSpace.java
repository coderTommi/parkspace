package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ParkingSpace.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:
 * 停车位:记录车位的基本信息，该信息应该有物业或系统管理员进行初始化
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午9:27:42</p>
*/

public class ParkingSpace implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//车位编号,形如3-101
	private String spaceno;
	//小区ID
	private String comid;
	//车位楼层,用来标记车位所在的楼层比如：地面0，地下-1，地下-2等
	private String parkPositionFloor;
	//表示车位所在的区域，比如A区、B区
	private String parkPositionZone;
	//车位横坐标
	private String parkPositionX;
	//车位纵坐标
	private String parkPositionY;
	//车位状态，1占用，0空闲，-1不对外开放
	private String parkStatus;
	private String[] parkStatusQuery;
	//车位类型：P:物业、O:业主、E:其他，未知车位类型
	private String parkType;
	private String[] parkTypeQuery;
	//位置描述,形如：靠近X号楼X单元
	private String parkPositionDes;
	//车位登记信息：用来记录表示车位所属人员信息，手机号码或者身份证号码
	private String spaceOwner;
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
	public String getSpaceno() {
		return spaceno;
	}
	public void setSpaceno(String spaceno) {
		this.spaceno = spaceno;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public String getParkPositionFloor() {
		return parkPositionFloor;
	}
	public void setParkPositionFloor(String parkPositionFloor) {
		this.parkPositionFloor = parkPositionFloor;
	}
	public String getParkPositionZone() {
		return parkPositionZone;
	}
	public void setParkPositionZone(String parkPositionZone) {
		this.parkPositionZone = parkPositionZone;
	}
	public String getParkPositionX() {
		return parkPositionX;
	}
	public void setParkPositionX(String parkPositionX) {
		this.parkPositionX = parkPositionX;
	}
	public String getParkPositionY() {
		return parkPositionY;
	}
	public void setParkPositionY(String parkPositionY) {
		this.parkPositionY = parkPositionY;
	}
	public String getParkStatus() {
		return parkStatus;
	}
	public void setParkStatus(String parkStatus) {
		this.parkStatus = parkStatus;
	}
	public String getParkType() {
		return parkType;
	}
	public void setParkType(String parkType) {
		this.parkType = parkType;
	}
	public String getParkPositionDes() {
		return parkPositionDes;
	}
	public void setParkPositionDes(String parkPositionDes) {
		this.parkPositionDes = parkPositionDes;
	}
	public String getSpaceOwner() {
		return spaceOwner;
	}
	public void setSpaceOwner(String spaceOwner) {
		this.spaceOwner = spaceOwner;
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
	
	public String[] getParkStatusQuery() {
		return parkStatusQuery;
	}
	public void setParkStatusQuery(String[] parkStatusQuery) {
		this.parkStatusQuery = parkStatusQuery;
	}
	public String[] getParkTypeQuery() {
		return parkTypeQuery;
	}
	public void setParkTypeQuery(String[] parkTypeQuery) {
		this.parkTypeQuery = parkTypeQuery;
	}
}
