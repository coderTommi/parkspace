package com.parkspace.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.dao.SpaceOwnerDao;
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
	 * @Title: getSpaceOwnerCount
	 * <p>Description:
	 * 根据条件查询车位业主数量
	 * </p>
	 * @param     spaceOwner 车位业主信息
	 * @return List<SpaceOwner>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
	 */
	@Override
	public int getSpaceOwnerCount(SpaceOwner spaceOwner) {
		if(spaceOwner == null) {
			spaceOwner = new SpaceOwner();
		}
		return this.spaceOwnerDao.getSpaceOwnerCount(spaceOwner);
	}
}
