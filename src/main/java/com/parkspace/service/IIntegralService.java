package com.parkspace.service;

import com.parkspace.common.exception.ParkspaceServiceException;

/**
 * 积分service
 * @author lidongliang
 *
 */
public interface IIntegralService {
	/**
	 * 根据UserID查询积分
	 * @param userId
	 * @return
	 * @throws ParkspaceServiceException
	 * @throws Exception
	 */
	public Integer getIntegral(String userId) throws ParkspaceServiceException, Exception;
	
}
