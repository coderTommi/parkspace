package com.parkspace.service;

import java.util.List;

import com.parkspace.db.rmdb.entity.SpaceOwner;

/**
 * @Title: ISpaceOwnerService.java
 * @Package com.parkspace.service
 * <p>Description:
 * 车位业主Service
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午8:43:58</p>
*/

public interface ISpaceOwnerService {
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
	public List<SpaceOwner> getSpaceOwnerList(SpaceOwner spaceOwner);
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
	public List<SpaceOwner> getSpaceOwnerAllInfoList(SpaceOwner spaceOwner);
	
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
	public int getSpaceOwnerCount(SpaceOwner spaceOwner);
}
