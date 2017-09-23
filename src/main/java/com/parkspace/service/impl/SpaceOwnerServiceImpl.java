package com.parkspace.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.dao.SpaceOwnerDao;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.service.ISpaceOwnerService;

/**
 * @Title: SpaceOwnerServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:
 * 车位业主Service
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午8:44:42</p>
*/

public class SpaceOwnerServiceImpl implements ISpaceOwnerService{
	
	@Resource
	private SpaceOwnerDao spaceOwnerDao;
	
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	
	//查询车位业主的基本信息，包括车位信息、用户信息等
	@Override
	public List<SpaceOwner> getSpaceOwnerList(SpaceOwner spaceOwner) {
		List<SpaceOwner> list = new ArrayList<SpaceOwner>();
		if(spaceOwner != null){
			
		}
		return null;
	}

}
