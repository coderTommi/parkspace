package com.parkspace.controller;

import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.service.IBillService;
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
	
	/**
	 * 查询账户详情
	 * ex: http://localhost:8080/parkspace/bill/{telePhone}
	 * @param telePhone
	 * @return
	 */
	@RequestMapping(value = "/qry", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult getDetail(HttpServletRequest req, 
			@RequestParam(value="beginDate", required=true) Date beginDate, 
			@RequestParam(value="endDate", required=true) Date endDate, 
			@RequestParam(value="billState", required=true) Integer billState) {
		OperationResult result = new OperationResult();
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
//			billService.
			result.setFlag(true);
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

}
