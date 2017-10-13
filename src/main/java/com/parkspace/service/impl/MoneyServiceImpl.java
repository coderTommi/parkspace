package com.parkspace.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.BillDao;
import com.parkspace.db.rmdb.dao.PropertyMgmtUserDao;
import com.parkspace.db.rmdb.dao.WalletDao;
import com.parkspace.db.rmdb.dao.WalletLockDao;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.db.rmdb.entity.Bill;
import com.parkspace.db.rmdb.entity.ChargeRule;
import com.parkspace.db.rmdb.entity.PrivilegeTicket;
import com.parkspace.db.rmdb.entity.PropertyMgmtUser;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.db.rmdb.entity.WalletOperation;
import com.parkspace.service.IBillService;
import com.parkspace.service.IChargeRuleService;
import com.parkspace.service.IMoneyService;
import com.parkspace.service.IRemoteTrsService;
import com.parkspace.service.ITicketService;
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
	@Resource
	private IChargeRuleService chargeRuleService;
	@Resource
	private ITicketService ticketService;
	@Resource
	private PropertyMgmtUserDao propertyMgmtUserDao;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void recharge(String userId, BigDecimal amt, String remoteJnlNo, Integer payChannel) throws ParkspaceServiceException, Exception {
		int billType = Constants.AMTTYPE.RECHARGE.getValue();
		/**
		 * 验证资金是否到账
		 */
		boolean sucFlag = remoteTrsService.checkRemoteTrsRes(remoteJnlNo, payChannel, amt);
		if(!sucFlag) {
			log.error("remote trs is not success");
			throw new ParkspaceServiceException(Constants.ERRORCODE.REMOTE_TRS_IS_ERROR.toString());
		}
		Bill bill = new Bill();
		bill.setUserId(userId);
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
			updateWallet(userId, bill, Constants.BillSide.IN.getValue());
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("recharge error :" + userId, e);
			bill.setState(Constants.BillState.INNER_ERROR.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.TRS_ERROR.toString());
		} 
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void withdrawCash(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		/*
		 *  體現。先调用申请，三个工作日后成功转账
		 *  TODO 調遠程接口，成功后在轉賬
		 */
		int billType = Constants.AMTTYPE.WITHDRAW.getValue();
		int payChannel = getUserPayChannel(userId);
		Bill bill = new Bill();
		bill.setUserId(userId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setState(Constants.BillState.INIT.getValue());
		billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			updateWallet(userId, bill, Constants.BillSide.OUT.getValue());
			remoteTrsService.remoteTrans(userId, payChannel, amt);
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("withdraw error :" + userId, e);
			bill.setState(Constants.BillState.FAILED.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.WITHDRAW_ERROR.toString());
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void pledgeIn(String userId, BigDecimal amt, String remoteJnlNo, 
			Integer payChannel) throws ParkspaceServiceException, Exception {
		
		if(amt.compareTo(getPledgeAmt()) < 0) {
			throw new ParkspaceServiceException(Constants.ERRORCODE.PLEDGE_AMT_NOT_ENOUPH.toString());
		}
		
		int billType = Constants.AMTTYPE.PLEDGEIN.getValue();
		/**
		 * 验证资金是否到账
		 */
		boolean sucFlag = remoteTrsService.checkRemoteTrsRes(remoteJnlNo, payChannel, amt);
		if(!sucFlag) {
			log.error("remote trs is not success");
			throw new ParkspaceServiceException(Constants.ERRORCODE.REMOTE_TRS_IS_ERROR.toString());
		}
		Bill bill = new Bill();
		bill.setUserId(userId);
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
			updateWallet(userId, bill, Constants.BillSide.IN.getValue());
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("recharge error :" + userId, e);
			bill.setState(Constants.BillState.INNER_ERROR.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.PLEDGEIN_ERROR.toString());
		} 
	
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void pledgeOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {

		/*
		 *  提取押金。
		 *  TODO 先调用流程。三个工作日后到账
		 *   調遠程接口，成功后在轉賬
		 */
		int billType = Constants.AMTTYPE.PLEDGEOUT.getValue();
		int payChannel = getUserPayChannel(userId);
		Bill bill = new Bill();
		bill.setUserId(userId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setState(Constants.BillState.INIT.getValue());
		billService.save(bill);
		/*
		 * 先锁定账户
		 */
		try {
			updateWallet(userId, bill, Constants.BillSide.OUT.getValue());
			remoteTrsService.remoteTrans(userId, payChannel, amt);
			bill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(bill);
		} catch (Exception e) {
			log.error("pledgeOut error :" + userId, e);
			bill.setState(Constants.BillState.FAILED.getValue());
			billService.updateWithoutTransaction(bill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.PLEDGEOUT_ERROR.toString());
		}
	}

	@Override
	public void payOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void order(String payer, String payee, BigDecimal amt, String comId, String orderJnlno) 
			throws ParkspaceServiceException, Exception {

		BigDecimal payerAmt = amt;
		BigDecimal propertyAmt, payeeAmt, adminAmt, ticketAmt = null;
		PrivilegeTicket ticket = null;
		int orderInType = Constants.AMTTYPE.ORDERIN.getValue();
		int orderOutType = Constants.AMTTYPE.ORDEROUT.getValue();
		//  查询是否有可用优惠券
		List<PrivilegeTicket> tickets = ticketService.getUserTicket(payer, "amt", 0, new Date(System.currentTimeMillis()));
		if(tickets != null && tickets.size() > 0) {
			ticket = tickets.get(0);
			payerAmt = payerAmt.subtract(ticket.getAmt());
			if(payerAmt.compareTo(new BigDecimal("0.0")) < 0){
				payerAmt = new BigDecimal("0.0");
			}
			ticketAmt = ticket.getAmt();
		}
		String[] chargeRule = getChargeRule(comId);
		payeeAmt = amt.multiply(new BigDecimal(chargeRule[0]));
		propertyAmt = amt.multiply(new BigDecimal(chargeRule[1]));
		adminAmt = amt.multiply(new BigDecimal(chargeRule[2]));
		PropertyMgmtUser p_qUser = new PropertyMgmtUser();
		BaseUser propertyUser = propertyMgmtUserDao.getPropertyMgmtUserList(p_qUser).get(0);
		String propertyUserId = propertyUser.getUserId();
		String adminUserId = userService.getAdminUserId();
		/*
		 * 付款人
		 */
		Bill payerBill = saveInitBill(payer, payee, payerAmt, orderOutType, ticketAmt, orderJnlno);
		/*
		 * 收款人 
		 */
		Bill payeeBill = saveInitBill(payee, payer, payeeAmt, orderInType, null, orderJnlno);
		/*
		 * 物业
		 */
		Bill propertyBill = saveInitBill(propertyUserId, payer, propertyAmt, orderInType, null, orderJnlno);
		/*
		 * 公司
		 */
		Bill adminBill = saveInitBill(adminUserId, payer, adminAmt, orderInType, null, orderJnlno);
		try {
			updateWallet(payer, payerBill, Constants.BillSide.OUT.getValue());
			updateWallet(payee, payeeBill, Constants.BillSide.IN.getValue());
			updateWallet(adminUserId, adminBill, Constants.BillSide.IN.getValue());
			updateWallet(propertyUserId, propertyBill, Constants.BillSide.IN.getValue());
			if(ticket != null) {
				ticketService.updateTicket(ticket.getId());
			}
			payerBill.setState(Constants.BillState.SUCCESS.getValue());
			payeeBill.setState(Constants.BillState.SUCCESS.getValue());
			propertyBill.setState(Constants.BillState.SUCCESS.getValue());
			adminBill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(payerBill);
			billService.updateWithTransaction(payeeBill);
			billService.updateWithTransaction(propertyBill);
			billService.updateWithTransaction(adminBill);
		} catch (Exception e) {
			log.error("order error payer:" + payer + " payee:" +payee, e);
			payerBill.setState(Constants.BillState.INNER_ERROR.getValue());
			payeeBill.setState(Constants.BillState.INNER_ERROR.getValue());
			propertyBill.setState(Constants.BillState.INNER_ERROR.getValue());
			adminBill.setState(Constants.BillState.INNER_ERROR.getValue());
			billService.updateWithoutTransaction(payerBill);
			billService.updateWithoutTransaction(payeeBill);
			billService.updateWithoutTransaction(propertyBill);
			billService.updateWithoutTransaction(adminBill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.INNER_TRS_ERROR.toString());
		}
	}

	@Override
	public void bonusOut(String userId, BigDecimal amt) throws ParkspaceServiceException, Exception {
		/*
		 *  发放奖金。 調遠程接口，成功后在轉賬
		 */
		String adminUserId = userService.getAdminUserId();
		Bill userBill = saveInitBill(userId, adminUserId, amt, Constants.AMTTYPE.BONUSIN.getValue() , null, null); 
		Bill adminBill = saveInitBill(adminUserId, userId, amt, Constants.AMTTYPE.BONUSOUT.getValue(), null, null);
		/*
		 * 先锁定账户
		 */
		try {
			updateWallet(userId, userBill, Constants.BillSide.IN.getValue());
			updateWallet(adminUserId, adminBill, Constants.BillSide.IN.getValue());
			userBill.setState(Constants.BillState.SUCCESS.getValue());
			adminBill.setState(Constants.BillState.SUCCESS.getValue());
			billService.updateWithTransaction(userBill);
			billService.updateWithTransaction(adminBill);
		} catch (Exception e) {
			log.error("bonusOut error :" + userId, e);
			userBill.setState(Constants.BillState.FAILED.getValue());
			adminBill.setState(Constants.BillState.FAILED.getValue());
			billService.updateWithoutTransaction(userBill);
			billService.updateWithoutTransaction(adminBill);
			throw new ParkspaceServiceException(Constants.ERRORCODE.BONUSOUT_ERROR.toString());
		} 
	
	}



	@Override
	public Wallet qryWallet(String userId) throws ParkspaceServiceException, Exception {
		return walletDao.getByUserId(userId);
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
				|| bill.getBillType() == Constants.AMTTYPE.BONUSIN.getValue()
				|| bill.getBillType() == Constants.AMTTYPE.ORDERIN.getValue()
				|| bill.getBillType() == Constants.AMTTYPE.ORDEROUT.getValue()) {
			obj.setBalance(amount);
		} else if(bill.getBillType() == Constants.AMTTYPE.PLEDGEIN.getValue()  
				|| bill.getBillType() == Constants.AMTTYPE.PLEDGEOUT.getValue()){
			obj.setPledge(amount);
		}  else if(bill.getBillType() == Constants.AMTTYPE.BONUSOUT.getValue()  ){
			obj.setBonus(amount);
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
	
	private BigDecimal getPledgeAmt() throws ParkspaceServiceException, Exception{
		ChargeRule rule = chargeRuleService.getRuleByComId(null, Constants.RuleType.PLEDGE.getValue());
		BigDecimal amt = new BigDecimal(rule.getRuleDef());
		return amt;
		
	}
	
	private String[] getChargeRule(String comId) throws ParkspaceServiceException, Exception {
		ChargeRule rule = chargeRuleService.getRuleByComId(comId, Constants.RuleType.CHARGE.getValue());
		String[] ruledef = rule.getRuleDef().split(",");
		return ruledef;
	}
	
	private Bill saveInitBill(String userId, String oppUserId, BigDecimal amt, Integer billType, BigDecimal ticketAmt, String orderJnlno) 
			throws ParkspaceServiceException, Exception {
		Bill bill = new Bill();
		bill.setUserId(userId);
		bill.setOppUserId(oppUserId);
		bill.setAmount(amt);
		bill.setBillType(billType);
		bill.setOrderJnlno(orderJnlno);
		bill.setState(Constants.BillState.INIT.getValue());
		billService.save(bill);
		return bill;
	}
	
}
