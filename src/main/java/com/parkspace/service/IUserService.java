package com.parkspace.service;

import javax.servlet.http.HttpServletRequest;

import com.parkspace.common.exception.PackspaceServiceException;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.db.rmdb.entity.BaseUser;

/**
 * 注册、登录service
 * @author lidongliang
 *
 */
public interface IUserService {
	/** 用户注册 **/
	public void registerUser(RegisterUserWapper user) throws PackspaceServiceException, Exception ;
	
	/** 用户登录 **/
	public String login(HttpServletRequest req, String telePhone, String smsCode) throws PackspaceServiceException, Exception;
	/** 车主认证 **/
	
	/** 业主认证 **/
}
