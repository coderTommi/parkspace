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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.controller.pojo.RechargePojo;
import com.parkspace.controller.pojo.WithDrawPojo;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.db.rmdb.entity.Bill;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.service.IBillService;
import com.parkspace.service.IMoneyService;
import com.parkspace.util.Constants;

/**
 * 查询账户
 * @author lidongliang
 *
 */
@Controller
@RequestMapping("/wallet")
public class WalletController {
	
	private static Log log = LogFactory.getLog(WalletController.class);
	@Resource
	private IMoneyService moneyService;
	@Resource
	private IBillService billService;
	
	/**
	 * 查询账户详情
	 * ex: http://localhost:8080/parkspace/wallet/{telePhone}
	 * @param telePhone
	 * @return
	 */
	@RequestMapping(value = "/qry", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult getDetail(HttpServletRequest req) {
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			Wallet wallet = moneyService.qryWallet(user.getUserId());
			result.setFlag(true);
			result.setResData(wallet);
		}catch(ParkspaceServiceException e){
			log.error("get wallet Detail error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("get wallet Detail error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	/**
	 * 查询用户账单
	 * http://localhost:8080/parkspace/qryList?page=1&pageSize=10
	 * @Title: getUserBillList
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月9日 上午8:54:35</p>
	 */
	@RequestMapping(value = "/qryList", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult getUserBillList(HttpServletRequest req,
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize) {
		OperationResult result = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			List<Bill> list = billService.getListByUserId(user.getUserId());
			result.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<Bill> listPage = new PageInfo<Bill>(list);
				result.setResData(listPage);
			}else {
				result.setResData(list);
			}
		}catch(ParkspaceServiceException e){
			log.error("get bill list error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("get bill list error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	/**
	 * POST
	 *  http://localhost:8080/parkspace/recharge
	 *  {
	 *  	"amt" : 12.22,
	 *  	"remoteJnlno" : "123asdfsadfdsa",
	 *  	"payChannel" : 1
	 *  }
	 * @Title: recharge
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月23日 下午6:51:56</p>
	 */
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult recharge(HttpServletRequest req, @RequestBody RechargePojo pojo ) {
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.recharge(user.getUserId(), pojo.getAmt(), 
					pojo.getRemoteJnlNo(), pojo.getPayChannel());
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
	 * POST:
	 * http://localhost:8080/parkspace/withdraw
	 * {
	 * 	"amt" : "100"
	 * }
	 * @Title: withdraw
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月23日 下午6:58:12</p>
	 */
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult withdraw(HttpServletRequest req, @RequestBody WithDrawPojo pojo ) {
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.withdrawCash(user.getUserId(), pojo.getAmt());
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
	 * http://localhost:8080/parkspace/pledgeIn
	 *  {
	 *  	"amt" : "100",
	 *  	"remoteJnlno" : "123asdfsadfdsa",
	 *  	"payChannel" : "1"
	 *  }
	 * @Title: pledgeIn
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月23日 下午7:00:48</p>
	 */
	@RequestMapping(value = "/pledgeIn", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult pledgeIn(HttpServletRequest req, @RequestBody RechargePojo pojo ) {
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.pledgeIn(user.getUserId(), pojo.getAmt(), pojo.getRemoteJnlNo(), pojo.getPayChannel());
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
	 * POST
	 * http://localhost:8080/parkspace/pledgeOut
	 * {
	 * 	"amt" : "100"
	 * }
	 * @Title: pledgeOut
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月23日 下午7:02:10</p>
	 */
	@RequestMapping(value = "/pledgeOut", method = RequestMethod.POST)
    @ResponseBody
	public OperationResult pledgeOut(HttpServletRequest req, @RequestBody WithDrawPojo pojo ) {
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			moneyService.pledgeOut(user.getUserId(), pojo.getAmt());
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
