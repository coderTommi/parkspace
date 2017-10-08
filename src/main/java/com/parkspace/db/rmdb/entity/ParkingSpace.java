package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	//车位号模糊查询条件
	private String spacenoLikeQuery;
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
	
	//用户的基本信息
	private SpaceOwner spaceOwnerUser;
	//用户ID
//	private String userId;
	//用户名称
//	private String userName;
	//真实姓名
//	private String realName;
	//昵称
//	private String nickName;
	//电话
//	private String telePhone;
	//证件类型
//	private String idNo;
	//微信
//	private String weixinAccount;
	//头像
//	private String avator;
	//停车时长
	private Integer parkHours;
	//转化为字符串，停车时长,格式00:00:00
	//如果是A表示没有限制
	private String parkHoursString;
	//共享信息
	private List<ShareConfig> shareConfigList;
	
	//使用次数：默认0，每次订单成功之后+1
	
	private Integer useCount;
	
	
	
	public Integer getUseCount() {
		return useCount;
	}
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	public String getParkHoursString() {
		return parkHoursString;
	}
	public void setParkHoursString(String parkHoursString) {
		if(parkHours != null) {
			if(parkHours < 10) {
				this.parkHoursString = "0" + parkHours + ":00:00";;
			}else {
				this.parkHoursString = parkHours+":00:00";
			}
		}else {
			this.parkHoursString = parkHoursString;
		}
	}
	
	public List<ShareConfig> getShareConfigList() {
		return shareConfigList;
	}
	public void setShareConfigList(List<ShareConfig> shareConfigList) {
		this.shareConfigList = shareConfigList;
	}
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
	public String[] getParkStatusQuery() {
		return parkStatusQuery;
	}
	public void setParkStatusQuery(String[] parkStatusQuery) {
		this.parkStatusQuery = parkStatusQuery;
	}
	public String getParkType() {
		return parkType;
	}
	public void setParkType(String parkType) {
		this.parkType = parkType;
	}
	public String[] getParkTypeQuery() {
		return parkTypeQuery;
	}
	public void setParkTypeQuery(String[] parkTypeQuery) {
		this.parkTypeQuery = parkTypeQuery;
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
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
	public SpaceOwner getSpaceOwnerUser() {
		return spaceOwnerUser;
	}
	public void setSpaceOwnerUser(SpaceOwner spaceOwnerUser) {
		this.spaceOwnerUser = spaceOwnerUser;
	}
	public String getSpacenoLikeQuery() {
		return spacenoLikeQuery;
	}
	public void setSpacenoLikeQuery(String spacenoLikeQuery) {
		this.spacenoLikeQuery = spacenoLikeQuery;
	}
	@Override
	public String toString() {
		return "ParkingSpace [spaceno=" + spaceno + ", spacenoLikeQuery=" + spacenoLikeQuery + ", comid=" + comid
				+ ", parkPositionFloor=" + parkPositionFloor + ", parkPositionZone=" + parkPositionZone
				+ ", parkPositionX=" + parkPositionX + ", parkPositionY=" + parkPositionY + ", parkStatus=" + parkStatus
				+ ", parkStatusQuery=" + Arrays.toString(parkStatusQuery) + ", parkType=" + parkType
				+ ", parkTypeQuery=" + Arrays.toString(parkTypeQuery) + ", parkPositionDes=" + parkPositionDes
				+ ", spaceOwner=" + spaceOwner + ", memo=" + memo + ", createBy=" + createBy + ", createTime="
				+ createTime + ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + ", community=" + community
				+ ", zone=" + zone + ", spaceOwnerUser=" + spaceOwnerUser + ", parkHours=" + parkHours
				+ ", parkHoursString=" + parkHoursString + ", shareConfigList=" + shareConfigList + ", useCount="
				+ useCount + "]";
	}
	public Integer getParkHours() {
		return parkHours;
	}
	public void setParkHours(Integer parkHours) {
		this.parkHours = parkHours;
	}
}
