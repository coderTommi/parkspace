package com.parkspace.service;

import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;

/**
 * @Title: IParkingSpaceBillService.java
 * @Package com.parkspace.service
 * <p>Description:订单service接口</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午10:11:30</p>
*/

public interface IParkingSpaceBillService {
	/**
	 * @Title: getParkingSpaceBill
	 * <p>Description:根据订单编号获取车位订单信息</p>
	 * @param     orderJnlNo 订单编号
	 * @return ParkingSpaceBill    返回类型
	 * @throws ParkspaceServiceException
	 * <p>CreateDate:2017年9月23日 下午9:16:29</p>
	 */
	public ParkingSpaceBill getParkingSpaceBill(String orderJnlNo) 
			throws ParkspaceServiceException;
	/**
	 * @Title: addParkingSpaceBill
	 * <p>Description:
	 * 保存车位订单信息，id自动生成,在service层返回主键id
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:16:08</p>
	 */
	public void addParkingSpaceBill(ParkingSpaceBill parkingSpaceBill);
	/**
	 * @Title: updateParkingSpaceBill
	 * <p>Description:
	 * 更改车位订单信息,当状态发生变换时需要把状态之前的数据保存到历史订单中
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:51</p>
	 */
	public void updateParkingSpaceBill(ParkingSpaceBill parkingSpaceBill);
	/**
	 * @Title: delayParkingSpaceBill
	 * <p>Description:
	 * 延长订单,当状态发生变换时需要把状态之前的数据保存到历史订单中
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:51</p>
	 */
	public void delayParkingSpaceBill(ParkingSpaceBill parkingSpaceBill);
	/**
	 * @Title: deleteParkingSpaceBill
	 * <p>Description:
	 * 删除车位订单信息，数据保存到历史订单中
	 * </p>
	 * @param     orderJnlNo 车位订单编号
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:24</p>
	 */
	public void deleteParkingSpaceBill(String orderJnlNo);
	/**
	 * @Title: getParkingSpaceBillList
	 * <p>Description:根据条件查询车位订单信息</p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return List<ParkingSpaceBill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:03</p>
	 */
	public List<ParkingSpaceBill> getParkingSpaceBillList(ParkingSpaceBill parkingSpaceBill);
	/**
	 * @Title: getParkingSpaceBill
	 * <p>Description:根据订单编号获取车位订单信息</p>
	 * @param     orderJnlNo 订单编号
	 * @param     delayParkHours 延长时间，用来判断停车的合法性
	 * @return ParkingSpaceBill    返回类型
	 * @throws ParkspaceServiceException
	 * <p>CreateDate:2017年9月23日 下午9:16:29</p>
	 */
	public ParkingSpaceBill getParkingSpaceBill(String orderJnlNo, int delayParkHours) 
			throws ParkspaceServiceException;
	
	/**
	 * @Title: getOverdueOrderParkingSpaceBillList
	 * <p>Description:查询超过预留时间的预约订单</p>
	 * @param     参数
	 * @return List<ParkingSpaceBill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月11日 上午8:47:49</p>
	 */
	public List<ParkingSpaceBill> getOverdueOrderParkingSpaceBillList();
	/**
	 * @Title: getNoPayedParkingSpaceBillListInPayInterval
	 * <p>Description:查询结算周期内未付款的订单</p>
	 * @param     参数
	 * @return List<ParkingSpaceBill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月11日 下午2:11:15</p>
	 */
	public List<ParkingSpaceBill> getNoPayedParkingSpaceBillListInPayInterval();
	/**
	 * @Title: payParkingSpaceBill
	 * <p>Description:
	 * 24小时结算一次订单
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:51</p>
	 */
	public void payParkingSpaceBill(ParkingSpaceBill parkingSpaceBill);
	/**
	 * 
	 * @Title: getNoPayedParkingSpaceBillListInPayInterval
	 * <p>Description:查询为没有开通临时权限的订单数据</p>
	 * @return List<ParkingSpaceBill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月17日 下午4:05:33</p>
	 */
	public List<ParkingSpaceBill> getNoGrantParkingSpaceBillList();
	/**
	 * @Title: updateGrantParkingSpaceBill
	 * <p>Description:更新开通权限状态和次数</p>
	 * @param     parkingSpaceBill 待处理订单
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月17日 下午4:06:31</p>
	 */
	public void updateGrantParkingSpaceBill(ParkingSpaceBill parkingSpaceBill);
}
