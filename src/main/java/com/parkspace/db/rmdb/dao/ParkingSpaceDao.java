package com.parkspace.db.rmdb.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.parkspace.db.rmdb.entity.ParkingSpace;

/**
 * @Title: ParkingSpaceDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:05:51</p>
*/
@MapperScan
public interface ParkingSpaceDao {
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
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:10:04</p>
	 */
	public void addParkingSpace(ParkingSpace parkingSpace);
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
	 * @Title: deleteParkingSpace
	 * <p>Description:
	 * 删除车位信息,修改parkStatus为N（表示不对外开放）,需要同时更新编辑人和编辑时间
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
	 * @Title: getParkingSpaceALLInfoList
	 * <p>Description:根据条件查询车位信息
	 * 主要是车位基本信息、小区信息和行政区域信息
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:07</p>
	 */
	public List<ParkingSpace> getParkingSpaceALLInfoList(ParkingSpace parkingSpace);
	/**
	 * @Title: getParkingSpaceCount
	 * <p>Description:
	 * 根据具体条件查询车位的数量
	 * 包含空闲、占用、未共享的车位
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:16:23</p>
	 */
	public int getParkingSpaceCount(ParkingSpace parkingSpace);
	
	/**
	 * @Title: getNoUseingParkingSpaceCount
	 * <p>Description:获取共享的车位数量：空闲</p>
	 * @param     parkingSpace 过滤条件
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
	 */
	public int getNoUseingParkingSpaceCount(ParkingSpace parkingSpace);
	
	/**
	 * @Title: getParkingSpaceEnableBillList
	 * <p>Description:查询可预订的车位信息</p>
	 * @param     parkingSpace 查询条件
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月28日 下午6:25:46</p>
	 */
	public List<ParkingSpace> getParkingSpaceEnableBillList(ParkingSpace parkingSpace);
	
	/**
	 * @Title: payOrderParkingSpace
	 * <p>Description:订单完成更新使用次数和车位状态为空闲0</p>
	 * @param     spaceno 车位编号
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:47</p>
	 */
	public void payOrderParkingSpace(String spaceno);
}
