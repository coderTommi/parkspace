package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Arrays;

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
	//小区ID
//	private String comid;
	//车位楼层,用来标记车位所在的楼层比如：地面0，地下-1，地下-2等
//	private String parkPositionFloor;
	//表示车位所在的区域，比如A区、B区
//	private String parkPositionZone;
	//车位横坐标
//	private String parkPositionX;
	//车位纵坐标
//	private String parkPositionY;
	//车位状态，1占用，0空闲，-1不对外开放
//	private String parkStatus;
	//车位类型：P:物业、O:业主、E:其他，未知车位类型
//	private String parkType;
	//位置描述,形如：靠近X号楼X单元
//	private String parkPositionDes;
	//车位登记信息：用来记录表示车位所属人员信息，手机号码或者身份证号码
//	private String spaceOwner;
	
	//小区基本信息
	private Community community;
	//区域ID
//	private String zoneid;
	//小区名称
//	private String comname;
	//小区地址
//	private String address;
	//状态  0：未开放  1：封闭式小区，2：开放式小区,默认0，如果是-1表示禁用
//	private Integer isenable;
	//状态查询条件
//	private Integer[] isenableQuery;
	
	//行政区域基本信息
	private Zone zone;
	//区域名称
//	private String zonename;
	//状态  0：未开放  1：已开放，-1,表示删除，默认0
//	private Integer zoneisenable;
	//行政区域状态查询
//	private Integer[] zoneIsenableQuery;
	//省编码
//	private String province;
	//市
//	private String city;
	//区
//	private String zone;
	
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
	@Override
	public String toString() {
		return "SpaceOwner [spaceno=" + spaceno + ", isauth=" + isauth + ", isauthQuery=" + Arrays.toString(isauthQuery)
				+ ", carno=" + carno + ", parkingSpace=" + parkingSpace + ", zone=" + zone + ", community=" + community
				+ "]";
	}
}
