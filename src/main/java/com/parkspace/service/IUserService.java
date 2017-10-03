package com.parkspace.service;

import javax.servlet.http.HttpServletRequest;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.db.rmdb.entity.BaseUser;

/**
 * 注册、登录service
 * @author lidongliang
 *
 */
public interface IUserService {
	/** 用户注册 **/
	public void registerUser(RegisterUserWapper user) throws ParkspaceServiceException, Exception ;
	
	/** 用户登录 **/
	public String login(HttpServletRequest req, String telePhone, String smsCode) throws ParkspaceServiceException, Exception;
	/** 用户登出 **/
	public void logout(HttpServletRequest req)  throws ParkspaceServiceException, Exception;
	
	/** 车主认证 **/
	
	/** 业主认证 **/
	
	/** 获取管理员userId **/
	public String getAdminUserId() throws ParkspaceServiceException, Exception;
}
