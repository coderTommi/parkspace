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
	 * 查询提现明细
	 * @Title: qryWithdrawList
	 * <p>Description:</p>
	 * @param     参数
	 * @return List<Bill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:22:14</p>
	 */
	public List<Bill> qryWithdrawList(String userId,  Date beginDate, Date endDate) throws ParkspaceServiceException, Exception;

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
	/**
	 * 查询收益明细
	 * @Title: qryIncomeList
	 * <p>Description:</p>
	 * @param     参数
	 * @return List<Bill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:21:07</p>
	 */
	public List<Bill> qryIncomeList(String userId,  Date beginDate, Date endDate) throws ParkspaceServiceException, Exception;
	/**
	 * 
	 * @Title: qryIncomeDetailList
	 * <p>Description:</p>
	 * @param     参数
	 * @return List    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:24:02</p>
	 */
	public List qryIncomeDetailList(String usrId, Date beginDate, Date endDate)  throws ParkspaceServiceException, Exception;
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
