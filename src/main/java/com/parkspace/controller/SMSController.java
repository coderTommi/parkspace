package com.parkspace.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.service.ISmsCodeService;
import com.parkspace.util.Constants;

/**
 * 短信验证码
 * @author lidongliang
 *
 */
@Controller(("/sms"))
public class SMSController {
	
	private static Log log = LogFactory.getLog(SMSController.class);
	@Resource
	private ISmsCodeService smsCodeService;
	
	/**
	 * ex: http://localhost:8080/parkspace/sms/{telePhone}
	 * @param telePhone
	 * @return
	 */
	@RequestMapping(value = "/{telePhone}", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult generateSMSCode(@PathVariable String telePhone) {
		OperationResult result = new OperationResult();
		try {
			smsCodeService.sendSMSCode(telePhone);
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("gen smscode error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("gen smscode error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}

}
