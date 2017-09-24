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
	//车位状态，1占用，0空闲，N不对外开放，默认为N
	private String parkStatus;
	//车位类型：P:物业、O:业主、E:其他，未知车位类型
	private String parkType;
	//位置描述,形如：靠近X号楼X单元
	private String parkPositionDes;
	//车位登记信息：用来记录表示车位所属人员信息，手机号码或者身份证号码
	private String spaceOwner;
	//用户基本信息
	
	//用户名称
	private String userName;
	//昵称
	private String nickName;
	//'手机号码
	private String telephone;
	//是否是管理员 0:否  1：是
	private Integer isAdmin;
	//证件类型 0: 身份证
	private Integer idtype;
	//证件号码
	private String idno;
	//用户状态 0：正常	1：异常锁定,2：未交押金,3；已交押金
	private String state;
	//微信账号,用来汇款的账号
	private String weixinAccount;
	//头像
	private String avator;
	//小区基本信息
	//区域ID
	private String zoneid;
	//小区名称
	private String comname;
	//小区地址
	private String address;
	//是否开通  0：否  1：是,默认0，如果是2表示禁用
	private Integer isenable;
	
	//行政区域基本信息
	//区域名称
	private String zonename;
	//状态  0：否  1：是，-1:表示删除，默认0
	private Integer zoneisenable;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getWeixinAccount() {
		return weixinAccount;
	}
	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}
	public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
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
				+ ", modifyTime=" + modifyTime + ", comid=" + comid + ", parkPositionFloor=" + parkPositionFloor
				+ ", parkPositionZone=" + parkPositionZone + ", parkPositionX=" + parkPositionX + ", parkPositionY="
				+ parkPositionY + ", parkStatus=" + parkStatus + ", parkType=" + parkType + ", parkPositionDes="
				+ parkPositionDes + ", spaceOwner=" + spaceOwner + ", userName=" + userName + ", nickName=" + nickName
				+ ", telephone=" + telephone + ", isAdmin=" + isAdmin + ", idtype=" + idtype + ", idno=" + idno
				+ ", state=" + state + ", weixinAccount=" + weixinAccount + ", avator=" + avator + ", zoneid=" + zoneid
				+ ", comname=" + comname + ", address=" + address + ", isenable=" + isenable + ", zonename=" + zonename
				+ ", zoneisenable=" + zoneisenable + ", province=" + province + ", city=" + city + ", zone=" + zone
				+ "]";
	}
}
