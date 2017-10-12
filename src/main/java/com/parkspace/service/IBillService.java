package com.parkspace.service;

import java.sql.Date;
import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.Bill;

/**
 * @Title: IBillService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午2:18:07</p>
*/

public interface IBillService {
	
	public String save(Bill bill) throws ParkspaceServiceException, Exception;
	
	public void updateWithTransaction(Bill bill) throws ParkspaceServiceException, Exception;
	
	public void updateWithoutTransaction(Bill bill) throws ParkspaceServiceException, Exception;
	
	public List<Bill> getList() throws ParkspaceServiceException, Exception;
	
	public List<Bill> getListByUserId(String userId)  throws ParkspaceServiceException, Exception;
	
	/**
	 * 查询收入明细
	 * @Title: qryIncomeDetailList
	 * <p>Description:</p>
	 * @param     参数
	 * @return List    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 上午10:24:02</p>
	 */
	public List qryIncomeDetailList(String usrId, Date beginDate, Date endDate)  throws ParkspaceServiceException, Exception;

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

}
