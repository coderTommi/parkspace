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
import com.parkspace.db.rmdb.entity.PrivilegeTicket;
import com.parkspace.service.ITicketService;
import com.parkspace.util.Constants;

/**
 * 查询优惠券
 * @Title: TicketController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 上午10:38:22</p>
*/
@Controller
@RequestMapping("/ticket")
public class TicketController {
	private Log log = LogFactory.getLog(TicketController.class);
	@Resource
	private ITicketService ticketService;
	
	@RequestMapping(value = "/qryList", method = RequestMethod.GET)
    @ResponseBody
	public OperationResult getUserTickets(HttpServletRequest req, 
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize){

		OperationResult result = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			BaseUser user = (BaseUser)req.getSession().getAttribute("_USER");
			List<PrivilegeTicket> list = ticketService.getUserTicket(user.getUserId(), "time", null, null);
			result.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<PrivilegeTicket> listPage = new PageInfo<PrivilegeTicket>(list);
				result.setResData(listPage);
			}else {
				result.setResData(list);
			}
		}catch(ParkspaceServiceException e){
			log.error("get ticket list error", e);
			result.setErrCode(e.getMessageCode());
			result.setFlag(false);
		} catch (Exception e) {
			log.error("get ticket list error", e);
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.UNKNOWERROR.toString());
		}
		return result;
	
	}
	

}
