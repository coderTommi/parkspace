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
/**
 * 积分
 * @Title: IntegralController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月16日 下午8:48:00</p>
 */
@Controller
@RequestMapping("/integral")
public class IntegralController {
	private Log log = LogFactory.getLog(IntegralController.class);
	@Resource
	private IIntegralService iIntegralService;
	/**
	 * http://localhost:8080/parkspace/integral
	 * @Title: getIntegral
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月16日 下午8:48:12</p>
	 */
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
