package com.parkspace.controller.pojo;

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
import com.parkspace.common.exception.PackspaceServiceException;
import com.parkspace.service.IUserService;
import com.parkspace.util.Constants;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Log log = LogFactory.getLog(UserController.class);
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult registerUser(@RequestBody RegisterUserWapper user){
		OperationResult result = new OperationResult();
		try {
			userService.registerUser(user);
			result.setFlag(true);
		}catch(PackspaceServiceException e){
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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult login(HttpServletRequest request, 
			@RequestBody LoginWapper wapper){
		OperationResult result = new OperationResult();
		try {
			String sessionId = userService.login(request, wapper.getTelePhone(), wapper.getSmsCode());
			result.setFlag(true);
			result.setResData(sessionId);
		}catch(PackspaceServiceException e){
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
	
	@RequestMapping(value = "/qry", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult test01(HttpServletRequest request){
		OperationResult result = new OperationResult();
		try {
			Object obj = request.getSession().getAttribute("_USER");
			result.setFlag(true);
		}catch(PackspaceServiceException e){
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
}
