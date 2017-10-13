package com.parkspace.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.Bill;
import com.parkspace.db.rmdb.entity.Wallet;

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
	public void recharge(String userId, BigDecimal amt ,
			String remoteJnlNo, Integer payChannel) throws ParkspaceServiceException, Exception;
	
	/**
	 * 提现
	 * @Title: withdrawCash
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:13:36</p>
	 */
	public void withdrawCash(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception;

	/**
	 * 交押金
	 * @Title: payPledge
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:16:39</p>
	 */
	public void pledgeIn(String userId, BigDecimal amt, String remoteJnlNo, Integer payChannel) throws ParkspaceServiceException, Exception;
	/**
	 * 提押金
	 * @Title: pledgeOut
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 上午11:00:48</p>
	 */
	public void pledgeOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception;
	/**
	 * 分配资金
	 * @Title: payOut
	 * <p>Description: 对账后，管理员账户将资金打入到用户账户</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 上午11:01:43</p>
	 */
	public void payOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception;
	/**
	 * 订单
	 * @Title: order
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 上午11:03:45</p>
	 */
	public void order(String payer, String payee, BigDecimal amt, String comId, String orderId)
			throws ParkspaceServiceException, Exception; 
	/**
	 * 发放奖金
	 * @Title: bonusOut
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 上午11:04:03</p>
	 */
	public void bonusOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception;
	/**
	 * 查询钱包详情
	 * @Title: qryWallet
	 * <p>Description:</p>
	 * @param     参数
	 * @return Wallet    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:26:18</p>
	 */
	public Wallet qryWallet(String userId)   throws ParkspaceServiceException, Exception;
	
}
