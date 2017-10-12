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
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.ChargeRule;
import com.parkspace.service.IChargeRuleService;
import com.parkspace.util.Constants;

/**
 * @Title: ChargeRuleController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 下午2:50:07</p>
*/
@Controller
@RequestMapping("/rule")
public class ChargeRuleController {
	private Log log = LogFactory.getLog(ChargeRuleController.class);
	@Resource
	private IChargeRuleService chargeRuleService;
	/**
	 * http://localhost:8080/parkspace/rule/qryList
	 * @Title: getRules
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月10日 下午3:41:36</p>
	 */
	@RequestMapping(value = "/qryList", method=RequestMethod.GET)
	@ResponseBody
	public OperationResult getRules() {
		OperationResult result = new OperationResult();
		try {
			List<ChargeRule> rules = chargeRuleService.getRules();
			result.setFlag(true);
			result.setResData(rules);
		}catch(ParkspaceServiceException e){
			log.error("getRules error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("getRules error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	/**
	 * http://localhost:8080/parkspace/rule/setup
	 * {
	 * 	"comId" : "123123",
	 *  "ruleType" : "0",
	 *  "ruleDef" : "0.6,0.2,0.2"
	 * }
	 * @Title: setupRules
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月10日 下午3:41:59</p>
	 */
	@RequestMapping(value="/setup" , method=RequestMethod.POST)
	@ResponseBody
	public OperationResult setupRules(@RequestBody ChargeRule rule) {
		OperationResult result = new OperationResult();
		try {
			chargeRuleService.saveOrUpdate(rule.getComId(), rule.getRuleDef(), rule.getRuleType());
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("setup rules error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("setup rules error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}

}
