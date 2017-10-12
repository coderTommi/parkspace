package com.parkspace.service;
/**
 * 优惠券
 * @Title: ITicketService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月10日 上午10:23:57</p>
*/

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.PrivilegeTicket;

public interface ITicketService {
	/**
	 * 获取用户优惠券
	 * @Title: getUserTicket
	 * <p>Description:</p>
	 * @param     参数 used:是否使用
	 * @return PrivilegeTicket    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月10日 上午10:24:29</p>
	 */
	public List<PrivilegeTicket> getUserTicket(String userId, String orderBy, Integer used, Date endDate) throws ParkspaceServiceException, Exception;
	/**
	 * 新增优惠券
	 * @Title: saveTicket
	 * <p>Description:</p>
	 * @param     参数 timeout:超时时间
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月10日 上午10:25:54</p>
	 */
	public void saveTicket(String userId, BigDecimal amt, int timeout) throws ParkspaceServiceException, Exception;
	/**
	 * 更新已使用的优惠券
	 * @Title: updateTicket
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月10日 上午10:36:50</p>
	 */
	public void updateTicket(String ticketId)  throws ParkspaceServiceException, Exception;

}
