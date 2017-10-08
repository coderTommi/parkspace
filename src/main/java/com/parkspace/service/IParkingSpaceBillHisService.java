package com.parkspace.service;

import java.util.List;

import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.db.rmdb.entity.ParkingSpaceBillHis;

/**
 * @Title: IParkingSpaceBillHis.java
 * @Package com.parkspace.service
 * <p>Description:历史订单service接口</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午4:51:47</p>
*/

public interface IParkingSpaceBillHisService {
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
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:11:38</p>
	 */
	public void addParkingSpaceBillHis(ParkingSpaceBill parkingSpaceBill);
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
