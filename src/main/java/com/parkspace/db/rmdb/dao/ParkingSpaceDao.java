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
	//根据车位编号查询车位信息
	public ParkingSpace getParkingSpace(String spaceno);
	//保存车位信息，车位编号手动输入
	//车位编号需要有自动生成规则
	public void addParkingSpace(ParkingSpace parkingSpace);
	//更改车位信息
	public void updateParkingSpace(ParkingSpace parkingSpace);
	//删除车位信息,修改parkStatus为N（表示不对外开放）,需要同时更新编辑人和编辑时间
	public void deleteParkingSpace(ParkingSpace parkingSpace);
	//根据条件查询车位信息
	public List<ParkingSpace> getParkingSpaceList(ParkingSpace parkingSpace);
}
