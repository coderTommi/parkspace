package com.parkspace.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.service.IParkingSpaceService;

/**
 * @Title: ParkingSpaceServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:10:31</p>
*/
@Service("parkingSpaceService")
public class ParkingSpaceServiceImpl implements IParkingSpaceService{
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	@Override
	public ParkingSpace getParkingSpace(String spaceno) {
		return parkingSpaceDao.getParkingSpace(spaceno);
	}
}
