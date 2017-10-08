package com.parkspace.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
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

}
