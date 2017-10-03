package com.parkspace.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.CaruserDao;
import com.parkspace.db.rmdb.entity.Caruser;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.service.ICaruserService;
import com.parkspace.service.ICommunityService;
import com.parkspace.service.IMoneyService;
import com.parkspace.util.Constants;

/**
 * @Title: CaruserServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:车主service实现类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午9:23:57</p>
*/
@Service("caruserService")
public class CaruserServiceImpl implements ICaruserService{
	@Resource
	private CaruserDao caruserDao;
	@Resource
	private IMoneyService moneyService;
	@Resource
	private ICommunityService communityService;
	/**
	 * @Title: getCaruserCount
	 * <p>Description:根据条件查询车主数量</p>
	 * @param     caruser 车主信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:10</p>
	 */
	@Override
	public int getCaruserCount(Caruser caruser) {
		if(caruser == null) {
			caruser = new Caruser();
		}
		return caruserDao.getCaruserCount(caruser);
	}
	
	/**
	 * @Title: getSpaceOwnerAllInfoList
	 * <p>Description:
	 * 根据条件查询车主信息，信息包括
	 * 小区信息、行政区域信息、用户基本信息等
	 * </p>
	 * @param     caruser 车位业主信息
	 * @return List<Caruser>    返回类型
	 * @throws Exception 
	 * @throws ParkspaceServiceException 
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
	 */
	@Override
	public List<Caruser> getCaruserAllInfoList(Caruser caruser) 
			throws ParkspaceServiceException, Exception{
		if(caruser == null) {
			caruser = new Caruser();
		}
		List<Caruser> list = caruserDao.getCaruserAllInfoList(caruser);
		if(list != null && list.size() > 0) {
			for(Caruser c : list) {
				String userId = c.getUserId();
				//处理钱包信息
				Wallet wallet = moneyService.qryWallet(userId);
				if(wallet != null) {
					c.setWallet(wallet);
				}
			}
		}
		return list;
	}
	
	/**
	 * @Title: getSpaceOwnerCount
	 * <p>Description:
	 * 根据条件查询车主数量
	 * </p>
	 * @param     caruser 车主信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
	 */
	@Override
	public int getCaruserAllInfoCount(Caruser caruser) {
		if(caruser == null) {
			caruser = new Caruser();
		}
		return caruserDao.getCaruserAllInfoCount(caruser);
	}
	/**
	 * 
	 * @Title: addCaruser
	 * <p>Description:车主认证</p>
	 * @param     spaceOwner 需要认证的用户信息
	 * @param     comid 小区id
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 上午9:35:16</p>
	 */
	@Override
	public void addCaruser(Caruser caruser, String comid) 
			throws ParkspaceServiceException{
		if(caruser == null) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.APPROVE_IS_NOT_NULL.toString(), 
					"认证信息不能为空");
		}
		if(StringUtils.isEmpty(comid)) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.COMID_IS_NOT_NULL.toString(), 
					"小区编号不能为空");
		}
		//默认0
		caruser.setParkCount(0);
		caruserDao.addCaruser(caruser);
		//保存用户与小区的关系
		communityService.addUserCommunity(comid, caruser.getUserId());
	}
}
