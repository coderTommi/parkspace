package com.parkspace.service;

import java.util.List;

import com.parkspace.db.rmdb.entity.ParkingSpace;

/**
 * @Title: IParkingSpaceService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:10:08</p>
*/

public interface IParkingSpaceService {
	/**
	 * @Title: getParkingSpace
	 * <p>Description:根据车位编号查询车位信息</p>
	 * @param     spaceno 车位编号
	 * @return ParkingSpace    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:10:51</p>
	 */
	public ParkingSpace getParkingSpace(String spaceno);
	/**
	 * @Title: addParkingSpace
	 * <p>Description:
	 * 保存车位信息，车位编号手动输入
	 * 车位编号需要有自动生成规则
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return ParkingSpace    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:10:04</p>
	 */
	public ParkingSpace addParkingSpace(ParkingSpace parkingSpace);
	/**
	 * @Title: updateParkingSpace
	 * <p>Description:更改车位信息</p>
	 * @param     parkingSpace 车位信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:51</p>
	 */
	public void updateParkingSpace(ParkingSpace parkingSpace);
	/**
	 * @Title: enableParkingSpace
	 * <p>Description:开放某个车位</p>
	 * @param     spaceno 车位编号
	 * @param     modifyBy 启用人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月26日 下午4:10:30</p>
	 */
	public void enableParkingSpace(String spaceno, String modifyBy);
	
	/**
	 * @Title: disableParkingSpace
	 * <p>Description:禁用某个车位</p>
	 * @param     spaceno 车位编号
	 * @param     modifyBy 禁用人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月26日 下午4:10:30</p>
	 */
	public void disableParkingSpace(String spaceno, String modifyBy);
	/**
	 * @Title: deleteParkingSpace
	 * <p>Description:
	 * 删除车位信息,修改parkStatus为N表示不对外开放）
	 * ,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:28</p>
	 */
	public void deleteParkingSpace(ParkingSpace parkingSpace);
	/**
	 * @Title: getParkingSpaceList
	 * <p>Description:根据条件查询车位信息</p>
	 * @param     parkingSpace 车位信息
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:07</p>
	 */
	public List<ParkingSpace> getParkingSpaceList(ParkingSpace parkingSpace);
	/**
	 * 
	 * @Title: getParkingSpaceListIncludeComAndZone
	 * <p>Description:
	 * 查询车位信息主要包含车位的基本信息、小区信息、行政区域信息
	 * 和业主的基本信息（包括用户名和id）
	 * </p>
	 * @param     parkingSpace 查询条件
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月26日 下午4:37:47</p>
	 */
	public List<ParkingSpace> getParkingSpaceListIncludeComAndZone(ParkingSpace parkingSpace);
	/**
	 * @Title: getFreeParkingSpaceCount
	 * <p>Description:获取空闲车位数量</p>
	 * @param     参数
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:06</p>
	 */
	public int getFreeParkingSpaceCount();
	/**
	 * @Title: getEnableParkingSpaceCount
	 * <p>Description:获取共享的车位数量包括占用和空闲</p>
	 * @param     参数
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
	 */
	public int getEnableParkingSpaceCount();
	/**
	 * @Title: getParkingSpaceListBySpacenoLike
	 * <p>Description:通过车位号模糊查询车位信息</p>
	 * @param     spaceno 车位号
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午4:38:06</p>
	 */
	public List<ParkingSpace> getParkingSpaceListBySpacenoLike(String spaceno);
	/**
	 * @Title: getParkingSpaceOnParkingList
	 * <p>Description:查询正在被使用的车位</p>
	 * @param     参数
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午4:41:45</p>
	 */
	public List<ParkingSpace> getParkingSpaceOnParkingList();
	
	/**
	 * @Title: getParkingSpaceListByComidAndParkHours
	 * <p>Description:根据小区编号和停车时长获取该小区可预订的车位信息</p>
	 * @param     comid 小区编号
	 * @param     parkHours 停车时长
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月28日 下午6:25:46</p>
	 */
	public List<ParkingSpace> getParkingSpaceListByComidAndParkHours(String comid, 
			Integer parkHours);
}
