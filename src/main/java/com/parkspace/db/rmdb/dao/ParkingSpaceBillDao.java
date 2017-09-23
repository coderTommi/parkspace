package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.ParkingSpaceBill;

/**
 * @Title: ParkingSpaceBillDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 车位订单，用来记录车位的订单信息
 * 预约
 * 使用中
 * 使用完成
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface ParkingSpaceBillDao {
	//根据订单编号获取车位订单信息
	public ParkingSpaceBill getParkingSpaceBill(String orderJnlNo);
	//保存车位订单信息，id自动生成,在service层返回主键id
	public void addParkingSpaceBill(ParkingSpaceBill parkingSpaceBill);
	//更改车位订单信息,当状态发生变换时需要把状态之前的数据保存到历史订单中
	public void updateParkingSpaceBill(ParkingSpaceBill parkingSpaceBill);
	//删除车位订单信息，数据保存到历史订单中
	public void deleteParkingSpaceBill(String orderJnlNo);
	//根据条件查询车位订单信息
	public List<ParkingSpaceBill> getParkingSpaceBillList(ParkingSpaceBill parkingSpaceBill);
}
