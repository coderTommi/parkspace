package com.parkspace.service;

import java.util.List;

import com.parkspace.db.rmdb.entity.BlackList;
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
	 * @Title: addBlackList
	 * <p>Description:需要加入黑名单的用户信息
	 * 将某个业主加入黑名单，要写明事由
	 * </p>
	 * @param     blackList 黑名单信息
	 * 包括：用户编号、备注信息等
	 * @return BlackList    返回类型，返回添加之后的信息
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午10:04:16</p>
	 */
	public BlackList addBlackList(BlackList blackList);
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
	public List<BlackList> getBlackListAll(BlackList blackList);
}
