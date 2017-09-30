package com.parkspace.service;

import com.parkspace.common.exception.PackspaceServiceException;
import com.parkspace.db.rmdb.entity.BaseUser;

/**
 * 注册、登录service
 * @author lidongliang
 *
 */
public interface IUserService {
	/** 用户注册 **/
	public void registerUser(BaseUser baseUser) throws PackspaceServiceException, Exception ;
	
	/** 用户登录 **/
//	public BaseUser login() throws PackspaceServiceException, Exception;
	/** 车主认证 **/
	
	/** 业主认证 **/
}
