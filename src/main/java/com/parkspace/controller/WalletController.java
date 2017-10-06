//package com.parkspace.controller;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.parkspace.common.OperationResult;
//import com.parkspace.common.exception.ParkspaceServiceException;
//import com.parkspace.db.rmdb.entity.BaseUser;
//import com.parkspace.db.rmdb.entity.Wallet;
//import com.parkspace.service.IMoneyService;
//import com.parkspace.util.Constants;
//
///**
// * 查询账户
// * @author lidongliang
// *
// */
//@Controller(("/wallet"))
//public class WalletController {
//	
//	private static Log log = LogFactory.getLog(WalletController.class);
//	@Resource
//	private IMoneyService moneyService;
//	
//	/**
//	 * 查询账户详情
//	 * ex: http://localhost:8080/parkspace/wallet/{telePhone}
//	 * @param telePhone
//	 * @return
//	 */
//	@RequestMapping(value = "/qry", method = RequestMethod.GET)
//    @ResponseBody
//	public OperationResult getDetail(HttpServletRequest req) {
//		OperationResult result = new OperationResult();
//		try {
//			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
//			Wallet wallet = moneyService.qryWallet(user.getUserId());
//			result.setFlag(true);
//			result.setResData(wallet);
//		}catch(ParkspaceServiceException e){
//			log.error("get wallet Detail error", e);
//			result.setErrCode(e.getMessageCode());
//			result.setFlag(false);
//		} catch (Exception e) {
//			log.error("get wallet Detail error", e);
//			result.setFlag(false);
//			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
//		}
//		return result;
//	}
//
//}
