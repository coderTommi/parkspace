package com.parkspace.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.CommunityDao;
import com.parkspace.db.rmdb.dao.PropertyMgmtUserDao;
import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.PropertyMgmtUser;
import com.parkspace.db.rmdb.entity.Zone;
import com.parkspace.service.ICommunityService;

/**
 * @Title: CommunityServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:
 * 小区service实现类
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月24日 上午9:18:33</p>
*/
@Service("communityService")
public class CommunityServiceImpl implements ICommunityService{
	@Resource
	private CommunityDao communityDao;
	@Resource
	private PropertyMgmtUserDao propertyMgmtUserDao;
	/**
	 * @Title: getCommunityAllInfoList
	 * <p>Description:根据条件查询小区信息
	 * 包括小区的基本信息，行政区域的基本信息
	 * </p>
	 * @param     community 小区信息
	 * @return List<Community>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:17:00</p>
	 */
	@Override
	public List<Community> getCommunityAllInfoList(Community community) {
		if(community == null){
			community = new Community();
		}
		return this.communityDao.getCommunityAllInfoList(community);
	}
	/**
	 * @Title: getCommunityListByCity
	 * <p>Description:
	 * 显示该城市如济南所有已推广小区
	 * </p>
	 * @param     city 城市编号
	 * @param     comStatus 小区状态（0：未开放  1：封闭式小区，2：开放式小区，-1：删除）
	 * @param     zoneStatus 行政区域状态（0：未开放  1：已开放，-1,表示删除）
	 * @return List<Community>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 上午9:23:19</p>
	 */
	@Override
	public List<Community> getCommunityListByCity(String city,
			Integer[] comStatus,Integer[] zoneStatus) {
		Community community = new Community();
		
		Zone zone = new Zone();
		zone.setCity(city);
		zone.setIsenableQuery(zoneStatus);
		
		community.setIsenableQuery(comStatus);
		community.setZone(zone);
		return this.getCommunityAllInfoList(community);
	}
	/**
	 * @Title: getCommunity
	 * <p>Description:根据小区id获取小区信息</p>
	 * @param     comid 小区编号
	 * @return Community    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:18:13</p>
	 */
	public Community getCommunity(String comid){
		if(comid != null && !"".equals(comid)){
			return this.communityDao.getCommunity(comid);
		}
		return null;
	}
	/**
	 * @Title: addCommunity
	 * <p>Description:
	 * 保存小区信息id自动生成,在service层返回主键id
	 * </p>
	 * @param     community 小区信息
	 * @return void    返回类型 Community
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:17:58</p>
	 */
	public Community addCommunity(Community community){
		if(community != null){
			Community newCommunity = new Community();
			
			newCommunity.setAddress(community.getAddress());
			newCommunity.setComid(UUID.randomUUID().toString());
			newCommunity.setComname(community.getComname());
			newCommunity.setCreateBy(community.getCreateBy());
			newCommunity.setCreateTime(new Date());
			//默认开通
			newCommunity.setIsenable(1);
			newCommunity.setMemo(community.getMemo());
			newCommunity.setModifyBy(community.getModifyBy());
			newCommunity.setModifyTime(new Date());
			newCommunity.setZoneid(community.getZoneid());
			
			this.communityDao.addCommunity(newCommunity);
			return newCommunity;
		}else{
			return null;
		}
	}
	/**
	 * @Title: updateCommunity
	 * <p>Description:更改小区信息</p>
	 * @param     community 小区信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:17:44</p>
	 */
	public void updateCommunity(Community community){
		if(community != null){
			community.setModifyTime(new Date());
			this.communityDao.updateCommunity(community);
		}
	}
	/**
	 * @Title: deleteCommunity
	 * <p>Description:
	 * 删除小区信息,修改isenable为-1,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     community 小区信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:17:26</p>
	 */
	public void deleteCommunity(Community community){
		if(community != null){
			community.setModifyTime(new Date());
			this.communityDao.deleteCommunity(community);
		}
	}
	/**
	 * @Title: getCommunityList
	 * <p>Description:根据条件查询小区信息</p>
	 * @param     community 小区信息
	 * @return List<Community>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:17:00</p>
	 */
	public List<Community> getCommunityList(Community community){
		if(community == null){
			community = new Community();
		}
		return this.communityDao.getCommunityList(community);
	}
	/**
	 * @Title: getCommunityListByZoneId
	 * <p>Description:
	 * 查询某个行政区域如历下区已推广的小区
	 * </p>
	 * @param     zoneid 参数行政区域编号
	 * @param     comStatus 小区状态（0：未开放  1：封闭式小区，2：开放式小区，-1：删除）
	 * @param     zoneStatus 行政区域状态（0：未开放  1：已开放，-1,表示删除）
	 * @return List<Community>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 下午1:27:17</p>
	 */
	@Override
	public List<Community> getCommunityListByZoneId(String zoneid,
			Integer[] comStatus,Integer[] zoneStatus) {
		Community community = new Community();
		
		Zone zone = new Zone();
		zone.setIsenableQuery(zoneStatus);
		
		community.setIsenableQuery(comStatus);
		community.setZoneid(zoneid);
		community.setZone(zone);
		return this.getCommunityAllInfoList(community);
	}
	/**
	 * @Title: getCommunityListByComName
	 * <p>Description:
	 * 通过小区名称查询小区信息
	 * </p>
	 * @param     comName 小区名称
	 * @param     comStatus 小区状态（0：未开放  1：封闭式小区，2：开放式小区，-1：删除）
	 * @param     zoneStatus 行政区域状态（0：未开放  1：已开放，-1,表示删除）
	 * @return List<Community>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 下午1:32:17</p>
	 */
	@Override
	public List<Community> getCommunityListByComName(String comName, 
			Integer[] comStatus, Integer[] zoneStatus) {
		Community community = new Community();
		
		Zone zone = new Zone();
		zone.setIsenableQuery(zoneStatus);
		
		community.setIsenableQuery(comStatus);
		community.setComname(comName);
		community.setZone(zone);
		return this.getCommunityAllInfoList(community);
	}
	/**
	 * @Title: getPropertyMgmtUser
	 * <p>Description:
	 * 根据用户id获取物业信息
	 * </p>
	 * @param     userId 用户id
	 * @return PropertyMgmtUser    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:08:13</p>
	 */
	@Override
	public PropertyMgmtUser getPropertyMgmtUser(String userId) {
		return this.propertyMgmtUserDao.getPropertyMgmtUser(userId);
	}
	
	/**
	 * @Title: addUserCommunity
	 * <p>Description:增加用户与小区的关联关系</p>
	 * @param     comid 小区id
	 * @param     userId 用户id
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 下午6:06:15</p>
	 */
	public void addUserCommunity(String comid, String userId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("comid", comid);
		map.put("userId", userId);
		this.communityDao.addUserCommunity(map);
	}
	
	/**
	 * @Title: deleteUserCommunity
	 * <p>Description:删除用户与小区的关联关系</p>
	 * @param     comid 小区id
	 * @param     userId 用户id
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 下午6:06:15</p>
	 */
	public void deleteUserCommunity(String comid, String userId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("comid", comid);
		map.put("userId", userId);
		this.communityDao.deleteUserCommunity(map);
	}

}
