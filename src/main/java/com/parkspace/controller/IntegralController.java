package com.parkspace.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.service.IIntegralService;
import com.parkspace.util.Constants;

@Controller
@RequestMapping("/integral")
public class IntegralController {
	private Log log = LogFactory.getLog(IntegralController.class);
	@Resource
	private IIntegralService iIntegralService;
	
	@RequestMapping(method=RequestMethod.GET)
	public OperationResult getIntegral(HttpServletRequest req) {
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			int integeralVal = iIntegralService.getIntegral(user.getUserId());
			result.setFlag(true);
			result.setResData(integeralVal);
		}catch(ParkspaceServiceException e){
			log.error("getIntegral", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("getIntegral error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	
}
