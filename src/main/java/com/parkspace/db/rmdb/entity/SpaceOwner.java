package com.parkspace.db.rmdb.entity;

import java.io.Serializable;

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
	//车位类型：P:物业、O:业主、E:其他，未知车位类型
	private String parkType;
	//位置描述,形如：靠近X号楼X单元
	private String parkPositionDes;
	//车位登记信息：用来记录表示车位所属人员信息，手机号码或者身份证号码
	private String spaceOwner;
	
	//小区基本信息
	//区域ID
	private String zoneid;
	//小区名称
	private String comname;
	//小区地址
	private String address;
	//状态  0：未开放  1：封闭式小区，2：开放式小区,默认0，如果是-1表示禁用
	private Integer isenable;
	//状态查询条件
	private Integer[] isenableQuery;
	
	//行政区域基本信息
	//区域名称
	private String zonename;
	//状态  0：未开放  1：已开放，-1,表示删除，默认0
	private Integer zoneisenable;
	//行政区域状态查询
	private Integer[] zoneIsenableQuery;
	//省编码
	private String province;
	//市
	private String city;
	//区
	private String zone;
	
	
	public String getZonename() {
		return zonename;
	}
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	public Integer getZoneisenable() {
		return zoneisenable;
	}
	public void setZoneisenable(Integer zoneisenable) {
		this.zoneisenable = zoneisenable;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getZoneid() {
		return zoneid;
	}
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsenable() {
		return isenable;
	}
	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
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
	public Integer[] getIsenableQuery() {
		return isenableQuery;
	}
	public void setIsenableQuery(Integer[] isenableQuery) {
		this.isenableQuery = isenableQuery;
	}
	public Integer[] getIsauthQuery() {
		return isauthQuery;
	}
	public void setIsauthQuery(Integer[] isauthQuery) {
		this.isauthQuery = isauthQuery;
	}
	public Integer[] getZoneIsenableQuery() {
		return zoneIsenableQuery;
	}
	public void setZoneIsenableQuery(Integer[] zoneIsenableQuery) {
		this.zoneIsenableQuery = zoneIsenableQuery;
	}
}
