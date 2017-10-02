package com.parkspace.service;

import java.math.BigDecimal;

import com.parkspace.common.exception.ParkspaceServiceException;

/**
 * @Title: IMoneyService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午10:09:25</p>
*/

public interface IMoneyService {
	/**
	 * 充值
	 * @Title: recharge
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:12:22</p>
	 */
	public void recharge(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception;
	
	/**
	 * 提现
	 * @Title: withdrawMondy
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:13:36</p>
	 */
	public void withdrawMondy(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception;
	/**
	 * 交押金
	 * @Title: payPledge
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:16:39</p>
	 */
	public void payPledge(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception;
	
}
