package com.parkspace.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.controller.pojo.LoginWapper;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.service.IUserService;
import com.parkspace.util.Constants;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Log log = LogFactory.getLog(UserController.class);
	@Resource
	private IUserService userService;
	
	/**
	 * ex: http://localhost:8080/parkspace/user/registe
	 * {
	 * 	"telePhone" : "13888888888",
	 * 	"smsCode" : "123456",
	 * 	"weixinAccount" : "21312sad",
	 * 	"realName" : "lidongliang",
	 * 
	 * }
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/registe", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult registerUser(@RequestBody RegisterUserWapper user){
		OperationResult result = new OperationResult();
		try {
			userService.registerUser(user);
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("register user error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("register user error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	/**
	 * ex: http://localhost:8080/parkspace/user/login
	 * {
	 * 	"telePhone" : "13888888888",
	 * 	"smsCode" : "321123"
	 * }
	 * @param request
	 * @param wapper
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult login(HttpServletRequest request, 
			@RequestBody LoginWapper wapper){
		OperationResult result = new OperationResult();
		try {
			String sessionId = userService.login(request, wapper.getTelePhone(), wapper.getSmsCode());
			result.setFlag(true);
			result.setResData(sessionId);
		}catch(ParkspaceServiceException e){
			log.error("login user error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("login user error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	
	/**
	 * ex: http://localhost:8080/parkspace/user/logout
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult logout(HttpServletRequest request){
		OperationResult result = new OperationResult();
		try {
			userService.logout(request);
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("logout  error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("logout  error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	
}
