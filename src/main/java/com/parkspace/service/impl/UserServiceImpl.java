package com.parkspace.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.db.rmdb.dao.BaseUserDao;
import com.parkspace.db.rmdb.dao.IntegralDao;
import com.parkspace.db.rmdb.dao.PropertyMgmtUserDao;
import com.parkspace.db.rmdb.dao.SpaceApplyDao;
import com.parkspace.db.rmdb.dao.SpaceOwnerDao;
import com.parkspace.db.rmdb.dao.WalletDao;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.db.rmdb.entity.Integral;
import com.parkspace.db.rmdb.entity.PropertyMgmtUser;
import com.parkspace.db.rmdb.entity.SpaceApply;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.service.ISmsCodeService;
import com.parkspace.service.IUserService;
import com.parkspace.util.Constants;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Resource
	private BaseUserDao baseUserDao;
	@Resource
	private WalletDao walletDao;
	@Resource
	private ISmsCodeService smsCodeService;
	@Resource
	private IntegralDao integralDao;
	@Resource
	private SpaceApplyDao spaceApplyDao;
	@Resource
	private SpaceOwnerDao spaceOwnerDao;
	@Resource
	private PropertyMgmtUserDao propertyMgmtUserDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void registerUser(RegisterUserWapper user) throws ParkspaceServiceException, Exception {
		/**
		 * 校驗短信驗證碼
		 * 1. 檢查手機號是否被佔用
		 * 2. 记录baseuser表
		 * 3. 初始化钱包表
		 * 4. 初始化积分表
		 */
		smsCodeService.checkSmsCode(user.getTelePhone(), user.getSmsCode());
		
		if(checkTelIsExists(user.getTelePhone())) {
			throw new ParkspaceServiceException(Constants.ERRORCODE.TELEPHONE_IS_EXISTS.toString(), 
					"telephone is exists");
		}
		
		BaseUser baseUser = new BaseUser();
		baseUser.setTelePhone(user.getTelePhone());
		baseUser.setUserName(user.getUserName());
		baseUser.setWeixinAccount(user.getWeixinAccount());
		baseUser.setRealName(user.getRealName());
		
		String userId = UUID.randomUUID().toString();
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		baseUser.setUserId(userId);
		baseUser.setCreateBy(userId);
		baseUser.setCreateTime(currentTime);
		baseUser.setModifyBy(userId);
		baseUser.setModifyTime(currentTime);
		baseUser.setIsAdmin(0);//非管理员
		baseUser.setState(0);//初始注册
		baseUser.setUserPwd("123456");
		baseUserDao.save(baseUser);
		/*
		 *	初始化钱包 
		 */
		Wallet wallet = new Wallet();
		wallet.setUserId(userId);
		BigDecimal initAmt = new BigDecimal("0.00");
		wallet.setBalance(initAmt);
		wallet.setBonus(initAmt);
		wallet.setOpenTime(new Timestamp(System.currentTimeMillis()));
		wallet.setUnclosedAmt(initAmt);
		wallet.setPledge(initAmt);
		walletDao.save(wallet);
		/*
		 * 初始化积分表
		 */
		Integral integral = new Integral();
		integral.setUserId(userId);
		integral.setVal(0);
		integralDao.save(integral);
	}
	
	@Override
	public String login(HttpServletRequest req, String telePhone, String smsCode)
			throws ParkspaceServiceException, Exception {
		/*
		 * 手機號+密碼
		 * 手機號+短信驗證碼登錄
		 * 微信授權登錄
		 */
		if(telePhone == null || "".equals(telePhone)) {
			logger.error("telephone is null");
			throw new ParkspaceServiceException(Constants.ERRORCODE.PARAM_IS_NULL.toString());
		}
		smsCodeService.checkSmsCode(telePhone, smsCode);
		BaseUser user = baseUserDao.getByTelphone(telePhone);
		req.getSession().setAttribute("_USER", user);
		return req.getSession().getId();
	}
	
	@Override
	public void logout(HttpServletRequest req) throws ParkspaceServiceException, Exception {
		req.getSession().invalidate();
	}
	
	@Override
	public String getAdminUserId() throws ParkspaceServiceException, Exception {
		BaseUser adminUser = baseUserDao.getById("1");
		return adminUser.getUserId();
	}
	
	
	/**
	 * 检查手机号是否被占用
	 * @param telePhone 手机号
	 * @return
	 * @throws ParkspaceServiceException
	 * @throws Exception
	 */
	private boolean checkTelIsExists(String telePhone)  throws ParkspaceServiceException, Exception {
		BaseUser user = baseUserDao.getByTelphone(telePhone);
		if(user != null) {
			logger.info("telephone is exists");
			return true;
		}
		return false;
	}
	
	@Override
	public List<SpaceApply> getSpaceApplys(String userId, Integer state) throws ParkspaceServiceException, Exception {
		SpaceApply apply = new SpaceApply();
		PropertyMgmtUser user = propertyMgmtUserDao.getPropertyMgmtUser(userId);
		if(user != null)
			apply.setComId(user.getComid());
		apply.setState(state);
		return spaceApplyDao.qryList(apply);
	}
	
	@Override
	public void parkspaceApply(SpaceApply apply)
			throws ParkspaceServiceException, Exception {
		apply.setApplyTime(new Timestamp(System.currentTimeMillis()));
		spaceApplyDao.save(apply);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void parkspaceAuth(String applyId, String memo, Integer state)
			throws ParkspaceServiceException, Exception {
		/** 更新车位授权表、记录spaceowner表 **/
		
		SpaceApply apply = new SpaceApply();
		apply.setId(applyId);
		apply.setMemo(memo);
		apply.setState(state);
		spaceApplyDao.update(apply);
		
		SpaceApply apply2 = spaceApplyDao.getById(applyId);
		
		SpaceOwner spaceOwner = new SpaceOwner();
		spaceOwner.setSpaceno(apply2.getSpaceId());
		spaceOwner.setUserId(apply2.getUserId());
		spaceOwner.setIsauth(1);
		spaceOwner.setCertifiedTime(new Date(System.currentTimeMillis()));
		spaceOwnerDao.addSpaceOwner(spaceOwner);
		
		BaseUser user = baseUserDao.getById(apply2.getUserId());
		user.setIdNo(apply2.getIdNo());
		user.setIdType(apply2.getIdType());
		user.setRealName(apply2.getRealName());
		baseUserDao.update(user);
	}

}
