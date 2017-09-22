package com.parkspace.service;

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
	public ParkingSpace getParkingSpace(String spaceno);
}
