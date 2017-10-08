package com.parkspace.service;

import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.Caruser;

/**
 * @Title: ICaruserService.java
 * @Package com.parkspace.service
 * <p>Description:车主service接口</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午9:23:24</p>
*/

public interface ICaruserService {
	/**
	 * @Title: getCaruserCount
	 * <p>Description:根据条件查询车主数量</p>
	 * @param     caruser 车主信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:10</p>
	 */
	public int getCaruserCount(Caruser caruser);
	
	/**
	 * @Title: getSpaceOwnerAllInfoList
	 * <p>Description:
	 * 根据条件查询车主信息，信息包括
	 * 小区信息、行政区域信息、用户基本信息等
	 * </p>
	 * @param     caruser 车位业主信息
	 * @return List<Caruser>    返回类型
	 * @throws Exception 
	 * @throws ParkspaceServiceException 
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
	 */
	public List<Caruser> getCaruserAllInfoList(Caruser caruser)
			throws ParkspaceServiceException, Exception;
	
	/**
	 * @Title: getSpaceOwnerCount
	 * <p>Description:
	 * 根据条件查询车主数量
	 * </p>
	 * @param     caruser 车主信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
	 */
	public int getCaruserAllInfoCount(Caruser caruser);
	
	/**
	 * 
	 * @Title: addCaruser
	 * <p>Description:车主认证</p>
	 * @param     spaceOwner 需要认证的用户信息
	 * @param     comid 小区id
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 上午9:35:16</p>
	 */
	public void addCaruser(Caruser caruser, String comid) 
			throws ParkspaceServiceException;
	
	/**
	 * @Title: getCaruser
	 * <p>Description:
	 * 根据用户编号和车牌号获取车主信息
	 * </p>
	 * @param     userId 用户编号
	 * @param     carno 车牌号
	 * @return Caruser    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:21:21</p>
	 */
	public Caruser getCaruser(String userId,String carno) ;
}
