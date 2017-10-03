package com.parkspace.service;

import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;

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
	 * <p>Description:根据车位编号查询车位信息
	 * 包含共享信息
	 * </p>
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
	 * <p>Description:根据条件查询车位信息
	 * 包含共享信息
	 * </p>
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
	 * @param     parkingSpace 过滤条件
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
	 */
	public int getEnableParkingSpaceCount(ParkingSpace parkingSpace);
	/**
	 * @Title: getNoUseingParkingSpaceCount
	 * <p>Description:获取共享的车位数量：空闲</p>
	 * @param     parkingSpace 过滤条件
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
	 */
	public int getNoUseingParkingSpaceCount(ParkingSpace parkingSpace);
	
//	/**
//	 * @Title: getEnableParkingSpaceCountByComid
//	 * <p>Description:通过小区id获取共享的车位数量包括占用和空闲</p>
//	 * @param     comid 小区id
//	 * @return int    返回类型
//	 * @throws
//	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
//	 */
//	public int getEnableParkingSpaceCountByComid(String comid);
	
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
	/**
	 * @Title: orderParkingSpace
	 * <p>Description:车位预定</p>
	 * @param     参数
	 * @return ParkingSpaceBill    返回类型
	 * @throws ParkspaceServiceException
	 * <p>CreateDate:2017年10月3日 下午2:31:54</p>
	 */
	public ParkingSpaceBill addOrderParkingSpace(ParkingSpaceBill parkingSpaceBill) 
			throws ParkspaceServiceException;
	/**
	 * 
	 * @Title: getParkingSpaceParkHoursBySpaceno
	 * <p>Description:通过车位编号查询车位可共享的最长时间
	 * </p>
	 * @param     spaceno 车位编号
	 * @return String    返回类型，格式100:20:10(小时：分钟：秒)
	 * @throws ParkspaceServiceException
	 * <p>CreateDate:2017年10月3日 下午4:05:32</p>
	 */
	public String getParkingSpaceParkHoursBySpaceno(String spaceno) 
			throws ParkspaceServiceException;
	/**
	 * @Title: cancelOrderParkingSpace
	 * <p>Description:取消订单，只有预约的订单才能被取消
	 * 1.取消订单之后，订单信息写入订单历史表
	 * 2.更新车位的状态为空闲0
	 * </p>
	 * @param     orderJnlNo 订单编号
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午4:36:47</p>
	 */
	public void cancelOrderParkingSpace(String orderJnlNo)
			throws ParkspaceServiceException;
	/**
	 * @Title: confirmOrderParkingSpace
	 * <p>Description:确认停车
	 * 停车之前需要确认车位的合法性
	 * 1.订单状态由预约中变成使用中：1--2，并且更新当前时间
	 * 2.在历史表中增加一条预约订单信息
	 * </p>
	 * @param     orderJnlNo 订单编号
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午6:01:39</p>
	 */
	public void confirmOrderParkingSpace(String orderJnlNo)
			throws ParkspaceServiceException;
}
