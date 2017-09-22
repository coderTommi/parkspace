package com.parkspace.db.rmdb.dao;

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
	public ParkingSpace getParkingSpace(String spaceno);
}
