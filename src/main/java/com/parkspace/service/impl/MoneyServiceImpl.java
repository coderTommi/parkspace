package com.parkspace.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.WalletDao;
import com.parkspace.db.rmdb.dao.WalletLockDao;
import com.parkspace.db.rmdb.entity.Bill;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.db.rmdb.entity.WalletOperation;
import com.parkspace.service.IBillService;
import com.parkspace.service.IMoneyService;
import com.parkspace.service.IRemoteTrsService;
import com.parkspace.service.IUserService;
import com.parkspace.service.IWalletLockService;
import com.parkspace.util.Constants;

/**
 * 钱
 * @Title: MoneyServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午10:25:07</p>
*/
@Service("moneyService")
public class MoneyServiceImpl implements IMoneyService {
	private static Log log = LogFactory.getLog(MoneyServiceImpl.class);
	
	@Resource
	private WalletDao walletDao;
	@Resource
	private WalletLockDao walletLockDao;
	@Resource
	private IBillService billService;
	@Resource
	private IWalletLockService walletLockService;
	@Resource
	private IRemoteTrsService remoteTrsService;
	@Resource
	private IUserService userService;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void recharge(String userId, BigDecimal amt, String remoteJnlNo, Integer payChannel) throws ParkspaceServiceException, Exception {
		int billType = Constants.AMTTYPE.RECHARGE.getValue();
		/**
		 * 验证资金是否到账
		 */
		boolean sucFlag = remoteTrsService.checkRemoteTrsRes(remoteJnlNo, payChannel);
		if(!sucFlag) {
			log.error("remote trs is not success");
			throw new ParkspaceServiceException(Constants.ERRORCODE.REMOTE_TRS_IS_ERROR.toString());
		}
		Bill bill = new Bill();
		bill.setPayee(userId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setPayChannel(payChannel);
		bill.setRemoteJnlNo(remoteJnlNo);
		bill.setState(Constants.BillState.REMOTE_SUCC.getValue());
		String billId = billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			walletLockService.lockWallet(userId);
			updateWallet(userId, bill, Constants.BillSide.IN.getValue());
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("recharge error :" + userId, e);
			bill.setState(Constants.BillState.INNER_ERROR.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.TRS_ERROR.toString());
		} finally {//釋放賬戶鎖
			log.debug("release lock : "+ userId);
			walletLockService.releaseLock(userId);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void withdrawCash(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		/*
		 *  體現。 調遠程接口，成功后在轉賬
		 */
		int billType = Constants.AMTTYPE.WITHDRAW.getValue();
		int payChannel = getUserPayChannel(userId);
		Bill bill = new Bill();
		bill.setPayer(userId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setState(Constants.BillState.INIT.getValue());
		billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			walletLockService.lockWallet(userId);
			updateWallet(userId, bill, Constants.BillSide.OUT.getValue());
			remoteTrsService.remoteTrans(userId, payChannel);
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("withdraw error :" + userId, e);
			bill.setState(Constants.BillState.FAILED.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.WITHDRAW_ERROR.toString());
		} finally {//釋放賬戶鎖
			log.debug("release lock : "+ userId);
			walletLockService.releaseLock(userId);
		}
	}


	@Override
	public void pledgeIn(String userId, BigDecimal amt, String remoteJnlNo, 
			Integer payChannel) throws ParkspaceServiceException, Exception {
		
		if(amt.compareTo(Constants.PLEDGEAMOUNT) < 0) {
			throw new ParkspaceServiceException(Constants.ERRORCODE.PLEDGE_AMT_NOT_ENOUPH.toString());
		}
		
		int billType = Constants.AMTTYPE.PLEDGEIN.getValue();
		/**
		 * 验证资金是否到账
		 */
		boolean sucFlag = remoteTrsService.checkRemoteTrsRes(remoteJnlNo, payChannel);
		if(!sucFlag) {
			log.error("remote trs is not success");
			throw new ParkspaceServiceException(Constants.ERRORCODE.REMOTE_TRS_IS_ERROR.toString());
		}
		Bill bill = new Bill();
		bill.setPayee(userId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setPayChannel(payChannel);
		bill.setRemoteJnlNo(remoteJnlNo);
		bill.setState(Constants.BillState.REMOTE_SUCC.getValue());
		String billId = billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			walletLockService.lockWallet(userId);
			updateWallet(userId, bill, Constants.BillSide.IN.getValue());
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("recharge error :" + userId, e);
			bill.setState(Constants.BillState.INNER_ERROR.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.PLEDGEIN_ERROR.toString());
		} finally {//釋放賬戶鎖
			log.debug("release lock : "+ userId);
			walletLockService.releaseLock(userId);
		}
	
	}

	@Override
	public void pledgeOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {

		/*
		 *  提取押金。 調遠程接口，成功后在轉賬
		 */
		int billType = Constants.AMTTYPE.PLEDGEOUT.getValue();
		int payChannel = getUserPayChannel(userId);
		Bill bill = new Bill();
		bill.setPayer(userId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setState(Constants.BillState.INIT.getValue());
		billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			walletLockService.lockWallet(userId);
			updateWallet(userId, bill, Constants.BillSide.OUT.getValue());
			remoteTrsService.remoteTrans(userId, payChannel);
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("pledgeOut error :" + userId, e);
			bill.setState(Constants.BillState.FAILED.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.PLEDGEOUT_ERROR.toString());
		} finally {//釋放賬戶鎖
			log.debug("release lock : "+ userId);
			walletLockService.releaseLock(userId);
		}
	}

	@Override
	public void payOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		
	}

	@Override
	public void order(String payer, String payee, BigDecimal amt, String remoteJnlNo, 
			Integer payChannel) throws ParkspaceServiceException, Exception {

		int billType = Constants.AMTTYPE.ORDERPAY.getValue();
		/**
		 * 验证资金是否到账
		 */
		boolean sucFlag = remoteTrsService.checkRemoteTrsRes(remoteJnlNo, payChannel);
		if(!sucFlag) {
			log.error("remote trs is not success");
			throw new ParkspaceServiceException(Constants.ERRORCODE.REMOTE_TRS_IS_ERROR.toString());
		}
		Bill bill = new Bill();
		bill.setPayee(payee);
		bill.setPayer(payer);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setPayChannel(payChannel);
		bill.setRemoteJnlNo(remoteJnlNo);
		bill.setState(Constants.BillState.REMOTE_SUCC.getValue());
		billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			walletLockService.lockWallet(payer);
			walletLockService.lockWallet(payee);
			updateWallet(payer, bill, Constants.BillSide.OUT.getValue());
			updateWallet(payee, bill, Constants.BillSide.IN.getValue());
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("order error payer:" + payer + " payee:" +payee, e);
			bill.setState(Constants.BillState.INNER_ERROR.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.INNER_TRS_ERROR.toString());
		} finally {//釋放賬戶鎖
			log.debug("release lock  payer:" + payer + " payee:" +payee);
			walletLockService.releaseLock(payer);
			walletLockService.releaseLock(payee);
		}
	}

	@Override
	public void bonusOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		/*
		 *  发放奖金。 調遠程接口，成功后在轉賬
		 */
		int billType = Constants.AMTTYPE.BONUS.getValue();
		Bill bill = new Bill();
		bill.setPayee(userId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setState(Constants.BillState.INIT.getValue());
		billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			walletLockService.lockWallet(userId);
			updateWallet(userId, bill, Constants.BillSide.IN.getValue());
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("bonusOut error :" + userId, e);
			bill.setState(Constants.BillState.FAILED.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.BONUSOUT_ERROR.toString());
		} finally {//釋放賬戶鎖
			log.debug("release lock : "+ userId);
			walletLockService.releaseLock(userId);
		}
	
	}

	@Override
	public List qryIncomeDetailList(String usrId, Date beginDate, Date endDate)
			throws ParkspaceServiceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wallet qryWallet(String userId) throws ParkspaceServiceException, Exception {
		return walletDao.getByUserId(userId);
	}
	
	@Override
	public List<Bill> qryWithdrawList(String userId, Date beginDate, Date endDate)
			throws ParkspaceServiceException, Exception {
		return null;
	}
	
	/**
	 * 更新钱包
	 * @Title: updateWallet
	 * <p>Description:</p>
	 * @param      参数 billType: 0：充值 1：提现  2：交押金  3：提取押金	
	 * 					4：分配出账 5：分配入账  6：出账（订单扣款）
						7：入账（订单收入）	8:奖金出账
						9： 奖金入账' 
	 * @param  amtType  0:押金  1：余额  2： 奖金
	 * @param  flag:  0: 加  1：减
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月2日 下午2:51:43</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private void updateWallet(String userId, Bill bill, int side) {
		/**
		 * 1.入账直接更新表
		 * 2. 出账先更新表再检查余额是否大于0
		 */
		
		/*
		 * 判断资金类型。如果是充值、交押金、入账，资金方向为+(加钱);  如果
		 */
		WalletOperation obj = new WalletOperation();
		obj.setUserId(userId);
		BigDecimal amount = bill.getAmount();
		if (side == Constants.BillSide.OUT.getValue()) {
			amount = amount.negate();
		}
		
		if(bill.getBillType() == Constants.AMTTYPE.RECHARGE.getValue() 
				|| bill.getBillType() == Constants.AMTTYPE.WITHDRAW.getValue() 
				|| bill.getBillType()==Constants.AMTTYPE.PAYOUT.getValue() 
				|| bill.getBillType() == Constants.AMTTYPE.PAYIN.getValue()
				|| bill.getBillType() == Constants.AMTTYPE.BONUS.getValue()) {
			obj.setBalance(amount);
		} else if(bill.getBillType() == Constants.AMTTYPE.PLEDGEIN.getValue()  
				|| bill.getBillType() == Constants.AMTTYPE.PLEDGEOUT.getValue()){
			obj.setPledge(amount);
		} else if (bill.getBillType() == Constants.AMTTYPE.ORDERPAY.getValue()) {
			obj.setUnclosedAmt(amount);
		} else {
			throw new ParkspaceServiceException("amt type error");
		}
		int count = walletDao.updateByBill(obj);
		if(count < 1) {
			throw new ParkspaceServiceException(Constants.ERRORCODE.INNER_TRS_ERROR.toString());
		}
	}
	
	private Integer getUserPayChannel(String userId) {
		return Constants.PayChannel.WEIXIN.getValue();
	}
	
}
