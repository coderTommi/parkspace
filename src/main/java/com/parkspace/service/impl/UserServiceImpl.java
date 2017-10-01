package com.parkspace.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.common.exception.PackspaceServiceException;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.db.rmdb.dao.BaseUserDao;
import com.parkspace.db.rmdb.dao.WalletDao;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.service.ISmsCodeService;
import com.parkspace.service.IUserService;
import com.parkspace.util.CommonUtils;
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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void registerUser(RegisterUserWapper user) throws PackspaceServiceException, Exception {
		/**
		 * 校驗短信驗證碼
		 * 1. 檢查手機號是否被佔用
		 * 2. 记录baseuser表
		 * 3. 初始化钱包表
		 */
		if(!smsCodeService.checkSmsCode(user.getTelePhone(), user.getSmsCode())) {
//			throw new PackspaceServiceException(Constants.ERRORCODE.SMSCODE_IS_ERROR.toString());
		}
		
		if(checkTelIsExists(user.getTelePhone())) {
			throw new PackspaceServiceException(Constants.ERRORCODE.TELEPHONE_IS_EXISTS.toString(), 
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
	}
	
	@Override
	public String login(HttpServletRequest req, String telePhone, String smsCode)
			throws PackspaceServiceException, Exception {
		/*
		 * 手機號+密碼
		 * 手機號+短信驗證碼登錄
		 * 微信授權登錄
		 */
		if(telePhone == null || "".equals(telePhone)) {
			logger.error("telephone is null");
			throw new PackspaceServiceException(Constants.ERRORCODE.PARAM_IS_NULL.toString());
		}
		if(!smsCodeService.checkSmsCode(telePhone, smsCode)) {
//			throw new PackspaceServiceException(Constants.ERRORCODE.SMSCODE_IS_ERROR.toString());
		}
		BaseUser user = baseUserDao.getByTelphone(telePhone);
		req.getSession().setAttribute("_USER", user);
		return req.getSession().getId();
	}
	
	
	/**
	 * 检查手机号是否被占用
	 * @param telePhone 手机号
	 * @return
	 * @throws PackspaceServiceException
	 * @throws Exception
	 */
	private boolean checkTelIsExists(String telePhone)  throws PackspaceServiceException, Exception {
		BaseUser user = baseUserDao.getByTelphone(telePhone);
		if(user != null) {
			logger.info("telephone is exists");
			return true;
		}
		return false;
	}

}
