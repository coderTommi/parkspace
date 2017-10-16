package com.parkspace.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.controller.pojo.LoginWapper;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.db.rmdb.entity.SpaceApply;
import com.parkspace.service.IUserService;
import com.parkspace.util.Constants;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Log log = LogFactory.getLog(UserController.class);
	@Resource
	private IUserService userService;
	
	/**
	 * ex: http://localhost:8080/parkspace/user/regist
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
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
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
	/**
	 * 车位认证申请
	 * http://localhost:8080/parkspace/spaceApply
	 * {
	 * 	"comId":"123123123",
	 *  "spaceId" : "sadfas123",
	 *   "realName" : "ldl",
	 *   "idType" : "0",
	 *   "idNo" : "370724198301011234",
	 *   "telePhone" : "13888888888"
	 * }
	 * @Title: spaceApply
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 下午6:37:18</p>
	 */
	@RequestMapping(value = "/spaceApply", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult spaceApply(HttpServletRequest request, @RequestBody SpaceApply apply){
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)request.getSession().getAttribute("_USER");
			apply.setUserId(user.getUserId());
			userService.parkspaceApply(apply);
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("spaceApply  error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("spaceApply  error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	/**
	 * 车位认证授权
	 * http://localhost:8080/parkspace/spaceAuth?applyId=123123124&state=0&memo=同意
	 * @Title: spaceAuth
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 下午6:37:11</p>
	 */
	@RequestMapping(value = "/spaceAuth", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult spaceAuth(HttpServletRequest request,
			@RequestParam(value="applyId", required=true) String applyId,
			@RequestParam(value="state", required=true) Integer state,
			@RequestParam(value="memo", required=true) String memo){
		OperationResult result = new OperationResult();
		try {
			userService.parkspaceAuth(applyId, memo, state);
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("spaceAuth  error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("spaceAuth  error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	/**
	 * 查询待授权车位 信息
	 * http://localhost:8080/parkspace/getUnAuthApplys
	 * @Title: getApplys
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 下午6:36:57</p>
	 */
	@RequestMapping(value = "/getUnAuthApplys", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult getApplys(HttpServletRequest request){
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)request.getSession().getAttribute("_USER");
			List<SpaceApply> list = userService.getSpaceApplys(user.getUserId(), 0);
			result.setFlag(true);
			result.setResData(list);
		}catch(ParkspaceServiceException e){
			log.error("getApplys  error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("getApplys  error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
}
