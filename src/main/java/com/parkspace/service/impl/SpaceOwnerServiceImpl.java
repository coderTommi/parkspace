package com.parkspace.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.BlackListDao;
import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.dao.SpaceOwnerDao;
import com.parkspace.db.rmdb.entity.BlackList;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.service.ISpaceOwnerService;

/**
 * @Title: SpaceOwnerServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:
 * 车位业主Service
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午8:44:42</p>
*/
@Service("spaceOwnerService")
public class SpaceOwnerServiceImpl implements ISpaceOwnerService{
	
	@Resource
	private SpaceOwnerDao spaceOwnerDao;
	
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	
	@Resource
	private BlackListDao blackListDao;
	
	/**
	 * @Title: getSpaceOwnerList
	 * <p>Description:
	 * 查询车位业主的基本信息
	 * </p>
	 * @param  spaceOwner 查询条件 
	 * @return List<SpaceOwner>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午10:01:14</p>
	 */
	@Override
	public List<SpaceOwner> getSpaceOwnerList(SpaceOwner spaceOwner) {
		List<SpaceOwner> list = new ArrayList<SpaceOwner>();
		if(spaceOwner == null){
			spaceOwner = new SpaceOwner();
		}
		list = spaceOwnerDao.getSpaceOwnerList(spaceOwner);
		return list;
	}
	/**
	 * @Title: getSpaceOwnerAllInfoList
	 * <p>Description:
	 * 根据条件查询车位业主信息，信息包括
	 * 车位信息、小区信息、行政区域信息、用户基本信息等
	 * </p>
	 * @param     spaceOwner 车位业主信息
	 * @return List<SpaceOwner>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 上午2:32:21</p>
	 */
	@Override
	public List<SpaceOwner> getSpaceOwnerAllInfoList(SpaceOwner spaceOwner) {
		List<SpaceOwner> list = new ArrayList<SpaceOwner>();
		if(spaceOwner == null){
			spaceOwner = new SpaceOwner();
		}
		list = spaceOwnerDao.getSpaceOwnerAllInfoList(spaceOwner);
		return list;
	}
	/**
	 * @Title: addBlackList
	 * <p>Description:需要加入黑名单的用户信息</p>
	 * @param     blackList 黑名单信息
	 * 包括：用户编号、备注信息等
	 * @return BlackList    返回类型，返回添加之后的信息
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午10:04:16</p>
	 */
	@Override
	public BlackList addBlackList(BlackList blackList) {
		//获取主键信息
		String uuid = UUID.randomUUID().toString();
		if(blackList != null){
			BlackList newBlackList = new BlackList();
			newBlackList.setCreateTime(new Date());
			//默认否
			newBlackList.setIsCancel(0);
			//加入黑名单的原因
			newBlackList.setMemo(blackList.getMemo());
			newBlackList.setModifyTime(new Date());
			newBlackList.setUserId(blackList.getUserId());
			newBlackList.setUUID(uuid);
			blackListDao.addBlackList(newBlackList);
			return newBlackList;
		}
		return null;
	}
	/**
	 * @Title: getBlackListAll
	 * <p>Description:
	 * 查看所有加入黑名单的业主
	 * </p>
	 * @param     blackList 过滤条件，如果为空查询所有
	 * @return List<BlackList>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午10:13:17</p>
	 */
	@Override
	public List<BlackList> getBlackListAll(BlackList blackList) {
		List<BlackList> list = new ArrayList<BlackList>();
		if(blackList == null){
			blackList = new BlackList();
		}
		list = blackListDao.getBlackListAllInfoList(blackList);
		return list;
	}
}
