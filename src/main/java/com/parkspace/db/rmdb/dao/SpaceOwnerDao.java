package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.SpaceOwner;

/**
 * @Title: SpaceOwnerDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 车位业主信息表,包含物业车位信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface SpaceOwnerDao {
	/**
	 * 
	 * @Title: getSpaceOwner
	 * <p>Description:
	 * 根据车位编号获取车位业主信息
	 * </p>
	 * @param     spaceno 车位编号
	 * @return SpaceOwner    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:02:53</p>
	 */
	public SpaceOwner getSpaceOwner(String spaceno);
	/**
	 * 
	 * @Title: addSpaceOwner
	 * <p>Description:
	 * 保存车位业主信息
	 * </p>
	 * @param     spaceOwner 车位业主信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:03:19</p>
	 */
	public void addSpaceOwner(SpaceOwner spaceOwner);
	/**
	 * 
	 * @Title: updateSpaceOwner
	 * <p>Description:
	 * 更改车位业主信息
	 * </p>
	 * @param     spaceOwner 车位业主信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:03:38</p>
	 */
	public void updateSpaceOwner(SpaceOwner spaceOwner);
	/**
	 * 
	 * @Title: deleteSpaceOwner
	 * <p>Description:
	 * 删除车位业主信息,修改isauth为-1,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     spaceOwner 车位业主信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:04:00</p>
	 */
	public void deleteSpaceOwner(SpaceOwner spaceOwner);
	/**
	 * 
	 * @Title: getSpaceOwnerList
	 * <p>Description:
	 * 根据条件查询车位业主信息
	 * </p>
	 * @param     spaceOwner 车位业主信息
	 * @return List<SpaceOwner>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:04:21</p>
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
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
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
