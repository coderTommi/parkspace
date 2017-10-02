package com.parkspace.service;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.SpaceOwner;

/**
 * @Title: IAuthService.java
 * @Package com.parkspace.service
 * <p>Description:车主认证</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午9:22:04</p>
*/

public interface IAuthService {
	/**
	 * 车主申请认证
	 * @Title: applyAuthSpaceOwner
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午9:24:27</p>
	 */
	public void applyAuthSpaceOwner(SpaceOwner spaceOwner) throws ParkspaceServiceException, Exception; 
	
}
