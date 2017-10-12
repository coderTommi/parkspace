package com.parkspace.controller;

import java.sql.Date;
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
import com.parkspace.db.rmdb.entity.ShareConfig;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.service.IBillService;
import com.parkspace.service.IMoneyService;
import com.parkspace.util.Constants;

/**
 * bill
 * @author lidongliang
 *
 */
@Controller
@RequestMapping("/bill")
public class BillController {
	
	private static Log log = LogFactory.getLog(BillController.class);
	@Resource
	private IBillService billService;
	@Resource
	private IMoneyService moneyService;
	
	/**
	 * 查询账户详情
	 * ex: http://localhost:8080/parkspace/bill/{telePhone}
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
	 * 查询收益
	 * @Title: qryIncomeList
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 下午4:55:33</p>
	 */
	@RequestMapping(value = "/income", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult qryIncomeList(HttpServletRequest req, 
			@RequestParam(value="beginDate", required=true) Date beginDate, 
			@RequestParam(value="endDate", required=true) Date endDate,
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize) {
		OperationResult result = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			List<Bill> list = billService.qryIncomeDetailList(user.getUserId(), beginDate, endDate);
			if(list != null && list.size() > 0) {
				PageInfo<Bill> listPage = new PageInfo<Bill>(list);
				result.setResData(listPage);
			}else {
				result.setResData(list);
			}
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("qryIncomeList error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("qryIncomeList error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}
	
	
	/**
	 * 查询提现
	 * @Title: qryIncomeList
	 * <p>Description:</p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 下午4:55:33</p>
	 */
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult qryWithDrawList(HttpServletRequest req, 
			@RequestParam(value="beginDate", required=true) Date beginDate, 
			@RequestParam(value="endDate", required=true) Date endDate,
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize) {
		OperationResult result = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			List<Bill> list = billService.qryWithdrawList(user.getUserId(), beginDate, endDate);
			if(list != null && list.size() > 0) {
				PageInfo<Bill> listPage = new PageInfo<Bill>(list);
				result.setResData(listPage);
			}else {
				result.setResData(list);
			}
			result.setFlag(true);
		}catch(ParkspaceServiceException e){
			log.error("qryIncomeList error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("qryIncomeList error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	}

}
