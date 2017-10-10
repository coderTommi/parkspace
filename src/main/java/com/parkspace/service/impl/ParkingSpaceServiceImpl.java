package com.parkspace.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.dao.CaruserDao;
import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.dao.SpaceOwnerDao;
import com.parkspace.db.rmdb.entity.Caruser;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.db.rmdb.entity.ShareConfig;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.service.IParkingSpaceBillHisService;
import com.parkspace.service.IParkingSpaceBillService;
import com.parkspace.service.IParkingSpaceService;
import com.parkspace.service.IShareConfigService;
import com.parkspace.util.Constants;

/**
 * @Title: ParkingSpaceServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:10:31</p>
*/
@Service("parkingSpaceService")
public class ParkingSpaceServiceImpl implements IParkingSpaceService{
	@Resource
	private SpaceOwnerDao spaceOwnerDao;
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	@Resource
	private IShareConfigService shareConfigService;
	@Resource
	private IParkingSpaceBillService parkingSpaceBillService;
	@Resource
	private IParkingSpaceBillHisService parkingSpaceBillHisService;
	@Resource
	private CaruserDao caruserDao;
	/**
	 * @Title: getParkingSpace
	 * <p>Description:根据车位编号查询车位信息
	 * 包含共享信息
	 * </p>
	 * @param     spaceno 车位编号
	 * @return ParkingSpace    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:10:51</p>
	 */
	@Override
	public ParkingSpace getParkingSpace(String spaceno) {
		ParkingSpace parkingSpace = parkingSpaceDao.getParkingSpace(spaceno);
		//车主时间段信息
		List<ShareConfig> shareConfigList = shareConfigService.getShareConfigListBySpaceno(spaceno);
		if(shareConfigList != null && parkingSpace != null) {
			parkingSpace.setShareConfigList(shareConfigList);
		}
		return parkingSpace;
	}
	/**
	 * @Title: addParkingSpace
	 * <p>Description:
	 * 保存车位信息，车位编号手动输入
	 * 车位编号需要有自动生成规则
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return ParkingSpace    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:10:04</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ParkingSpace addParkingSpace(ParkingSpace parkingSpace){
		if(parkingSpace != null){
			parkingSpace.setCreateTime(new Date());
			parkingSpace.setModifyTime(new Date());
			//不对外开放
			parkingSpace.setParkStatus("N");
			//默认属于物业
			parkingSpace.setParkType("P");
			parkingSpace.setUseCount(0);
			this.parkingSpaceDao.addParkingSpace(parkingSpace);
			return parkingSpace;
		}else{
			return null;
		}
	}
	/**
	 * @Title: updateParkingSpace
	 * <p>Description:更改车位信息</p>
	 * @param     parkingSpace 车位信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:51</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateParkingSpace(ParkingSpace parkingSpace){
		if(parkingSpace != null){
			parkingSpace.setModifyTime(new Date());
			this.parkingSpaceDao.updateParkingSpace(parkingSpace);
		}
	}
	
	/**
	 * @Title: enableParkingSpace
	 * <p>Description:开放某个车位</p>
	 * @param     spaceno 车位编号
	 * @param     modifyBy 启用人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月26日 下午4:10:30</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void enableParkingSpace(String spaceno, String modifyBy) {
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setSpaceno(spaceno);
		parkingSpace.setModifyBy(modifyBy);
		//空闲
		parkingSpace.setParkStatus("0");
		this.parkingSpaceDao.updateParkingSpace(parkingSpace);
	}
	/**
	 * @Title: disableParkingSpace
	 * <p>Description:禁用某个车位</p>
	 * @param     spaceno 车位编号
	 * @param     modifyBy 禁用人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月26日 下午4:10:30</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void disableParkingSpace(String spaceno, String modifyBy) {
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setSpaceno(spaceno);
		parkingSpace.setModifyBy(modifyBy);
		//空闲
		parkingSpace.setParkStatus("N");
		this.parkingSpaceDao.updateParkingSpace(parkingSpace);
	}
	
	/**
	 * @Title: deleteParkingSpace
	 * <p>Description:
	 * 删除车位信息,修改parkStatus为N表示不对外开放）
	 * ,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:28</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteParkingSpace(ParkingSpace parkingSpace){
		if(parkingSpace != null){
			parkingSpace.setModifyTime(new Date());
			this.parkingSpaceDao.deleteParkingSpace(parkingSpace);
		}
	}
	/**
	 * @Title: getParkingSpaceList
	 * <p>Description:根据条件查询车位信息</p>
	 * @param     parkingSpace 车位信息
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:07</p>
	 */
	public List<ParkingSpace> getParkingSpaceList(ParkingSpace parkingSpace){
		if(parkingSpace == null){
			parkingSpace = new ParkingSpace();
		}
		List<ParkingSpace> list = this.parkingSpaceDao.getParkingSpaceList(parkingSpace);
		if(list != null && list.size() > 0) {
			for(ParkingSpace ps : list) {
				String spaceno = ps.getSpaceno();
				//车主时间段信息
				List<ShareConfig> shareConfigList = shareConfigService.getShareConfigListBySpaceno(spaceno);
				if(shareConfigList != null && ps != null) {
					ps.setShareConfigList(shareConfigList);
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: getParkingSpaceListIncludeComAndZone
	 * <p>Description:
	 * 查询车位信息主要包含车位的基本信息、小区信息、行政区域信息
	 * 和业主的基本信息（包括用户名和id）
	 * </p>
	 * @param     parkingSpace 查询条件
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月26日 下午4:37:47</p>
	 */
	public List<ParkingSpace> getParkingSpaceListIncludeComAndZone(ParkingSpace parkingSpace){
		if(parkingSpace == null) {
			parkingSpace = new ParkingSpace();
		}
		List<ParkingSpace> list = this.parkingSpaceDao.getParkingSpaceALLInfoList(parkingSpace);
		if(list != null && list.size() > 0) {
			for(ParkingSpace p : list) {
				String spaceno = p.getSpaceno();
				String parkStatus = p.getParkStatus(); 
				//获取用户的基本信息
				SpaceOwner so = spaceOwnerDao.getSpaceOwner(spaceno);
				if(so != null) {
//					p.setUserId(so.getUserId());
//					p.setUserName(so.getUserName());
//					p.setTelePhone(so.getTelePhone());
//					p.setIdNo(so.getIdNo());
//					p.setRealName(so.getRealName());
//					p.setNickName(so.getNickName());
//					p.setWeixinAccount(so.getWeixinAccount());
//					p.setAvator(so.getAvator());
					p.setSpaceOwnerUser(so);
				}
				//判断车位状态
				if("0".equals(parkStatus)) {//空闲
					ShareConfig shareConfig = new ShareConfig();
					shareConfig.setSpaceno(spaceno);
					shareConfig.setParkHourString("00:00:00");
					List<ShareConfig> shareConfigList = shareConfigService.getEnableShareConfigList(shareConfig);
					if(shareConfigList != null && shareConfigList.size() > 0) {
						p.setParkStatus("0");//空闲
					}else {
						p.setParkStatus("-1");//不对外开放
					}
				}
			}
		}
		return list;
	}
	/**
	 * @Title: getFreeParkingSpaceCount
	 * <p>Description:获取空闲车位数量</p>
	 * @param     参数
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:06</p>
	 */
	public int getFreeParkingSpaceCount() {
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setParkStatusQuery(new String[] {"0"});
		return this.parkingSpaceDao.getParkingSpaceCount(parkingSpace);
	}
	/**
	 * @Title: getEnableParkingSpaceCount
	 * <p>Description:获取共享的车位数量包括占用和空闲</p>
	 * @param     参数
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
	 */
	public int getEnableParkingSpaceCount(ParkingSpace parkingSpace) {
		if(parkingSpace == null) {
			parkingSpace = new ParkingSpace();
		}
		parkingSpace.setParkStatusQuery(new String[] {"0","1"});
		return this.parkingSpaceDao.getParkingSpaceCount(parkingSpace);
	}
	
	/**
	 * @Title: getNoUseingParkingSpaceCount
	 * <p>Description:获取共享的车位数量：空闲</p>
	 * @param     parkingSpace 过滤条件
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
	 */
	public int getNoUseingParkingSpaceCount(ParkingSpace parkingSpace) {
		if(parkingSpace == null) {
			parkingSpace = new ParkingSpace();
		}
		parkingSpace.setParkStatusQuery(new String[] {"0","1"});
		return this.parkingSpaceDao.getNoUseingParkingSpaceCount(parkingSpace);
	}
	
//	/**
//	 * @Title: getEnableParkingSpaceCountByComid
//	 * <p>Description:通过小区id获取共享的车位数量包括占用和空闲</p>
//	 * @param     comid 小区id
//	 * @return int    返回类型
//	 * @throws
//	 * <p>CreateDate:2017年9月27日 上午9:18:39</p>
//	 */
//	public int getEnableParkingSpaceCountByComid(String comid) {
//		ParkingSpace parkingSpace = new ParkingSpace();
//		parkingSpace.setParkStatusQuery(new String[] {"0","1"});
//		parkingSpace.setComid(comid);
//		return this.parkingSpaceDao.getParkingSpaceCount(parkingSpace);
//	}
	
	/**
	 * @Title: getParkingSpaceListBySpacenoLike
	 * <p>Description:通过车位号模糊查询车位信息</p>
	 * @param     spaceno 车位号
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午4:38:06</p>
	 */
	public List<ParkingSpace> getParkingSpaceListBySpacenoLike(String spaceno){
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setSpacenoLikeQuery(spaceno);
		return this.parkingSpaceDao.getParkingSpaceALLInfoList(parkingSpace);
	}
	
	/**
	 * @Title: getParkingSpaceOnParkingList
	 * <p>Description:查询正在被使用的车位</p>
	 * @param     参数
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午4:41:45</p>
	 */
	public List<ParkingSpace> getParkingSpaceOnParkingList(){
		return null;
	}
	/**
	 * @Title: getParkingSpaceListByComidAndParkHours
	 * <p>Description:根据小区编号和停车时长获取该小区可预订的车位信息</p>
	 * @param     comid 小区编号
	 * @param     parkHours 停车时长
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月28日 下午6:25:46</p>
	 */
	public List<ParkingSpace> getParkingSpaceListByComidAndParkHours(String comid, 
			Integer parkHours){
		ParkingSpace parkingSpace = new ParkingSpace();
		
		parkingSpace.setComid(comid);
		parkingSpace.setParkHours(parkHours);
		parkingSpace.setParkHoursString("00:00:00");
		//空闲的车位
		parkingSpace.setParkStatus("0");
		
		return this.parkingSpaceDao.getParkingSpaceEnableBillList(parkingSpace);
	}
	
	/**
	 * @Title: orderParkingSpace
	 * <p>Description:车位预定
	 * 需要增加把数据录入道闸系统，开通临时权限
	 * </p>
	 * @param     参数
	 * @return ParkingSpaceBill    返回类型
	 * @throws ParkspaceServiceException
	 * <p>CreateDate:2017年10月3日 下午2:31:54</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public ParkingSpaceBill addOrderParkingSpace(ParkingSpaceBill parkingSpaceBill) 
			throws ParkspaceServiceException{
		if(parkingSpaceBill == null) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		String spaceno = parkingSpaceBill.getSpaceno();
		Integer parkHours = parkingSpaceBill.getParkHours();
		//首先判断该车位目前是否可以空闲
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setParkHours(parkHours);
		parkingSpace.setParkHoursString("00:00:00");
		//空闲的车位
		parkingSpace.setParkStatus("0");
		parkingSpace.setSpaceno(spaceno);
		List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceEnableBillList(parkingSpace);
		if(list == null || list.size() <= 0) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.SPACE_IS_NOT_ORDER.toString(), 
					"该车位不能被预定");
		}
		//获取车位的业主信息--
		SpaceOwner spaceOwner = spaceOwnerDao.getSpaceOwner(spaceno);
		parkingSpaceBill.setSpaceOwnerUserId(spaceOwner.getUserId());
		//预定车位
		//订单状态：1、预约中，2、使用中，3.延长使用中
		parkingSpaceBill.setBillStatus(1);
		//默认设置为0
		parkingSpaceBill.setDelayParkHours(0);
		BigDecimal unitPrice = parkingSpaceBill.getUnitPrice();
		//预算：=单价*停车时长（没有减去免费时间）
		BigDecimal budgetPrice = unitPrice.multiply(new BigDecimal(parkHours));
		parkingSpaceBill.setBudgetPrice(budgetPrice);
		parkingSpaceBill.setCreateTime(new Date());
		parkingSpaceBill.setOrderJnlNo(UUID.randomUUID().toString());
		parkingSpaceBill.setPayedMoney(new BigDecimal(0));
		parkingSpaceBillService.addParkingSpaceBill(parkingSpaceBill);
		
		//更新车位状态-占用
		ParkingSpace newParkingSpace = new ParkingSpace();
		newParkingSpace.setSpaceno(spaceno);
		newParkingSpace.setParkStatus("1");//占用
		parkingSpaceDao.updateParkingSpace(newParkingSpace);
		//调用道闸系统写入数据
		
		return parkingSpaceBill;
	}
	/**
	 * 
	 * @Title: getParkingSpaceParkHoursBySpaceno
	 * <p>Description:通过车位编号查询车位可共享的最长时间
	 * </p>
	 * @param     spaceno 车位编号
	 * @return String    返回类型，格式100:20:10(小时：分钟：秒)
	 * @throws ParkspaceServiceException
	 * <p>CreateDate:2017年10月3日 下午4:05:32</p>
	 */
	@Override
	public String getParkingSpaceParkHoursBySpaceno(String spaceno) 
			throws ParkspaceServiceException{
		//首先判断该车位目前是否可以空闲
		ParkingSpace parkingSpace = new ParkingSpace();
		//空闲的车位
		parkingSpace.setParkStatus("0");
		parkingSpace.setParkHoursString("00:00:00");
		parkingSpace.setSpaceno(spaceno);
		List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceEnableBillList(parkingSpace);
		if(list == null || list.size() <= 0) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.SPACE_IS_NOT_ORDER.toString(), 
					"该车位不能被预定");
		}
		return list.get(0).getParkHoursString();
	}
	/**
	 * @Title: cancelOrderParkingSpace
	 * <p>Description:取消订单，只有预约的订单才能被取消
	 * 1.取消订单之后，订单信息写入订单历史表
	 * 2.更新车位的状态为空闲0
	 * </p>
	 * @param     orderJnlNo 订单编号
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午4:36:47</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void cancelOrderParkingSpace(String orderJnlNo)
			throws ParkspaceServiceException{
		if(StringUtils.isEmpty(orderJnlNo)) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		//判断订单的状态
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillService.getParkingSpaceBill(orderJnlNo);
		if(parkingSpaceBill == null) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		if(parkingSpaceBill.getBillStatus() != 1) {//订单状态：1、预约中，2、使用中，3.延长使用中
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_STATUS_IS_ILLLEGAL.toString(), 
					"订单状态不合法");
		}
		String spaceno = parkingSpaceBill.getSpaceno();
		//移入历史订单表中
		//订单状态：1、预约中，2、使用中，3.延长使用中，4，已结算、5取消订单
		parkingSpaceBill.setBillStatus(5);
		parkingSpaceBillHisService.addParkingSpaceBillHis(parkingSpaceBill);
		//删除订单信息
		parkingSpaceBillService.deleteParkingSpaceBill(orderJnlNo);
		//更新车位状态-空闲
		ParkingSpace newParkingSpace = new ParkingSpace();
		newParkingSpace.setSpaceno(spaceno);
		newParkingSpace.setParkStatus("0");//空闲
		parkingSpaceDao.updateParkingSpace(newParkingSpace);
		
		//删除在道闸系统中的临时权限
	}
	/**
	 * @Title: confirmOrderParkingSpace
	 * <p>Description:确认停车
	 * 停车之前需要确认车位的合法性
	 * 1.订单状态由预约中变成使用中：1--2，并且更新当前时间
	 * 2.在历史表中增加一条预约订单信息
	 * </p>
	 * @param     orderJnlNo 订单编号
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午6:01:39</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void confirmOrderParkingSpace(String orderJnlNo)
			throws ParkspaceServiceException{
		if(StringUtils.isEmpty(orderJnlNo)) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		//判断订单的状态
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillService.getParkingSpaceBill(orderJnlNo);
		if(parkingSpaceBill == null) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		if(parkingSpaceBill.getBillStatus() != 1) {//订单状态：1、预约中，2、使用中，3.延长使用中
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_STATUS_IS_ILLLEGAL.toString(), 
					"订单状态不合法");
		}
		//判断车位状态
		String spaceno = parkingSpaceBill.getSpaceno();
		Integer parkHours = parkingSpaceBill.getParkHours();
		//首先判断该车位目前是否可以空闲
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setParkHours(parkHours);
		parkingSpace.setParkHoursString("00:00:00");
		parkingSpace.setSpaceno(spaceno);
		List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceEnableBillList(parkingSpace);
		if(list == null || list.size() <= 0) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.SPACE_IS_NOT_USE.toString(), 
					"该车位不能被使用");
		}
		//订单状态由预约中变成使用中：1--2，并且更新当前时间
		ParkingSpaceBill newParkingSpaceBill = new ParkingSpaceBill();
		newParkingSpaceBill.setOrderJnlNo(parkingSpaceBill.getOrderJnlNo());
		newParkingSpaceBill.setBillStatus(2);
		newParkingSpaceBill.setCreateTime(new Date());
		
		parkingSpaceBillService.updateParkingSpaceBill(newParkingSpaceBill);
		//在历史表中增加一条预约订单信息
		parkingSpaceBillHisService.addParkingSpaceBillHis(parkingSpaceBill);
	}
	/**
	 * @Title: delayOrderParkingSpace
	 * <p>Description:延长停车</p>
	 * @param     orderJnlNo 订单号
	 * @param     delayParkHours 延长时间
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午9:52:15</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delayOrderParkingSpace(String orderJnlNo, int delayParkHours) 
			throws ParkspaceServiceException{
		if(StringUtils.isEmpty(orderJnlNo)) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		//判断订单的状态
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillService.getParkingSpaceBill(
				orderJnlNo,delayParkHours);
		if(parkingSpaceBill == null) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		if(parkingSpaceBill.getBillStatus() != 2 &&
				parkingSpaceBill.getBillStatus() != 3) {//订单状态：1、预约中，2、使用中，3.延长使用中
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_STATUS_IS_ILLLEGAL.toString(), 
					"订单状态不合法");
		}
		
		String maxDelayParkHoursString = parkingSpaceBill.getMaxDelayParkHoursString();
		String delayParkHoursString = parkingSpaceBill.getDelayParkHoursString();
		
		//比对最大预留时间是否合法
		if(StringUtils.isEmpty(maxDelayParkHoursString) ||
				maxDelayParkHoursString.compareTo(delayParkHoursString) < 0) {//不满足要求
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.SPACENO_IS_NO_DELAY.toString(), 
					"车位不可延期使用,请重新选择车位会减少延期时间");
		}
		/**
		 * 1.更改订单信息
		 * 1.1状态由使用中变成延长使用中
		 * 		//订单状态：1、预约中，2、使用中，3.延长使用中
		 * 1.2更新停车时长和延迟停车时长和对应的计算信息（预算费用）
		 */
		ParkingSpaceBill newParkingSpaceBill = new ParkingSpaceBill();
		newParkingSpaceBill.setBillStatus(3);//3.延长使用中
		newParkingSpaceBill.setOrderJnlNo(orderJnlNo);
		newParkingSpaceBill.setDelayParkHours(delayParkHours);
		parkingSpaceBillService.delayParkingSpaceBill(newParkingSpaceBill);
		/**
		 * 2.原来的使用中的订单写入历史订单中
		 */
		parkingSpaceBillHisService.addParkingSpaceBillHis(parkingSpaceBill);
	}
	
	/**
	 * @Title: payOrderParkingSpace
	 * <p>Description:结算订单，完成付款，并恢复车位状态为空闲</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月4日 上午10:35:21</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void payOrderParkingSpace(String orderJnlNo) 
			throws ParkspaceServiceException{
		if(StringUtils.isEmpty(orderJnlNo)) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		//判断订单的状态
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillService.getParkingSpaceBill(orderJnlNo);
		if(parkingSpaceBill == null) {
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_IS_NOT_NULL.toString(), 
					"订单信息不能为空");
		}
		if(parkingSpaceBill.getBillStatus() != 2 &&
				parkingSpaceBill.getBillStatus() != 3) {//订单状态：1、预约中，2、使用中，3.延长使用中
			throw new ParkspaceServiceException(
					Constants.ERRORCODE.ORDER_STATUS_IS_ILLLEGAL.toString(), 
					"订单状态不合法");
		}
		String spaceno = parkingSpaceBill.getSpaceno();
		/**
		 * 删除订单信息
		 */
		parkingSpaceBillService.deleteParkingSpaceBill(orderJnlNo);
		/**
		 * 插入历史订单
		 */
		parkingSpaceBill.setBillStatus(4);//已结算
		parkingSpaceBillHisService.addParkingSpaceBillHis(parkingSpaceBill);
		/**
		 * 更改车位状态为空闲0，并且记录车位使用次数和停车次数
		 */
		parkingSpaceDao.payOrderParkingSpace(spaceno);
		Caruser caruser = new Caruser();
		caruser.setUserId(parkingSpaceBill.getUserId());
		caruser.setCarno(parkingSpaceBill.getCarno());
		caruserDao.payOrderParkingSpace(caruser);
		/**
		 * 扣款，需要判断余额是否满足，余额不如需要抛出异常
		 */
		
		//应付金额=实际费用-免费-已付
		BigDecimal actualPrice = parkingSpaceBill.getActualPrice();
		BigDecimal freePrice = parkingSpaceBill.getFreePrice();
		BigDecimal payedMoney = parkingSpaceBill.getPayedMoney();
		BigDecimal surplusMoney = actualPrice.subtract(freePrice).subtract(payedMoney);
		
		System.out.println("=======应付金额===="+surplusMoney);
		
	}
}
