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
import com.parkspace.controller.pojo.TrsRequestPojo;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.service.IMoneyService;
import com.parkspace.util.Constants;

@Controller
@RequestMapping("/trs")
public class TrsController {
	
	private static Log log = LogFactory.getLog(TrsController.class);
	@Resource
	private IMoneyService moneyService;
	
	/**
	 * 充值
	 * ex: http://localhost:8080/parkspace/trs/recharge
	 * {
	 * 	"amt" : "123.11",
	 * 	"remoteJnlNo" : "weixin123456",
	 * 	"payChannel" : "0"
	 * 
	 * }
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult recharge(@RequestBody TrsRequestPojo trs, HttpServletRequest req){
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.recharge(user.getUserId(), trs.getAmt(), trs.getRemoteJnlNo(), trs.getPayChannel());
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("recharge error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("recharge error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	
	/**
	 * 提现
	 * ex: http://localhost:8080/parkspace/trs/withdraw
	 * {
	 * 	"amt" : "123.11"
	 * 
	 * }
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult withdraw(@RequestBody TrsRequestPojo trs, HttpServletRequest req){
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.withdrawCash(user.getUserId(), trs.getAmt());
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("withdraw error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("withdraw error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	
	/**
	 * 交押金
	 * ex: http://localhost:8080/parkspace/trs/pledgeIn
	 * {
	 * 	"amt" : "123.11",
	 *  "remoteJnlNo" : "weixin-123sadfsa",
	 *  ""
	 * 
	 * }
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/pledgeIn", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult pledgeIn(@RequestBody TrsRequestPojo trs, HttpServletRequest req){
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.pledgeIn(user.getUserId(), trs.getAmt(), trs.getRemoteJnlNo(), trs.getPayChannel());
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("pledgeIn error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("pledgeIn error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	
	/**
	 * 提押金
	 * ex: http://localhost:8080/parkspace/trs/pledgeOut
	 * {
	 * 	"amt" : "123.11"
	 * }
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/pledgeOut", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult pledgeOut(@RequestBody TrsRequestPojo trs, HttpServletRequest req){
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.pledgeOut(user.getUserId(), trs.getAmt());
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("pledgeOut error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("pledgeOut error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
}
