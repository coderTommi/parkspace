package com.parkspace.service;

import java.util.List;

import com.parkspace.db.rmdb.entity.SpaceOwner;

/**
 * @Title: ISpaceOwnerService.java
 * @Package com.parkspace.service
 * <p>Description:
 * 车位业主Service
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月23日 下午8:43:58</p>
*/

public interface ISpaceOwnerService {
	//查询车位业主的基本信息，包括车位信息、用户信息等
	public List<SpaceOwner> getSpaceOwnerList(SpaceOwner spaceOwner);
}
