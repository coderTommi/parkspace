package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.ParkingSpaceBillHis;

/**
 * @Title: ParkingSpaceBillHistDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 车位订单流水，记录该车为订单中的预定-使用-延长使用-结算整个流程
 * 状态为结算的订单作为对账订单
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface ParkingSpaceBillHisDao {
	/**
	 * @Title: getParkingSpaceBillHis
	 * <p>Description:根据主键查询历史订单信息</p>
	 * @param     UUID 历史订单主键
	 * @return ParkingSpaceBillHis    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:11:58</p>
	 */
	public ParkingSpaceBillHis getParkingSpaceBillHis(String UUID);
	/**
	 * @Title: addParkingSpaceBillHis
	 * <p>Description:
	 * 保存历史车位订单信息，id自动生成,在service层返回主键id
	 * </p>
	 * @param     parkingSpaceBillHis 历史车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:11:38</p>
	 */
	public void addParkingSpaceBillHis(ParkingSpaceBillHis parkingSpaceBillHis);
	/**
	 * @Title: getParkingSpaceBillHisList
	 * <p>Description:根据条件查询历史车位订单信息</p>
	 * @param     parkingSpaceBillHis 历史车位订单信息
	 * @return List<ParkingSpaceBillHis>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:11:16</p>
	 */
	public List<ParkingSpaceBillHis> getParkingSpaceBillHisList(ParkingSpaceBillHis parkingSpaceBillHis);
}
