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
import com.parkspace.db.rmdb.dao.ParkingSpaceBillDao;
import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.dao.SpaceOwnerDao;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.db.rmdb.entity.ShareConfig;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.service.IParkingSpaceBillHisService;
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
	private ParkingSpaceBillDao parkingSpaceBillDao;
	@Resource
	private IParkingSpaceBillHisService parkingSpaceBillHisService;
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
	 * <p>Description:车位预定</p>
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
		//预定车位
		//订单状态：1、预约中，2、使用中，3.延长使用中
		parkingSpaceBill.setBillStatus(1);
		//默认设置为0
		parkingSpaceBill.setDelayParkHours(0);
		BigDecimal unitPrice = parkingSpaceBill.getUnitPrice();
		//预算：=单价*停车时长
		BigDecimal budgetPrice = unitPrice.multiply(new BigDecimal(parkHours));
		parkingSpaceBill.setBudgetPrice(budgetPrice);
		parkingSpaceBill.setCreateTime(new Date());
		parkingSpaceBill.setOrderJnlNo(UUID.randomUUID().toString());
		parkingSpaceBillDao.addParkingSpaceBill(parkingSpaceBill);
		
		//更新车位状态-占用
		ParkingSpace newParkingSpace = new ParkingSpace();
		newParkingSpace.setSpaceno(spaceno);
		newParkingSpace.setParkStatus("1");//占用
		parkingSpaceDao.updateParkingSpace(newParkingSpace);
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
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillDao.getParkingSpaceBill(orderJnlNo);
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
		parkingSpaceBillDao.deleteParkingSpaceBill(orderJnlNo);
		//更新车位状态-空闲
		ParkingSpace newParkingSpace = new ParkingSpace();
		newParkingSpace.setSpaceno(spaceno);
		newParkingSpace.setParkStatus("0");//空闲
		parkingSpaceDao.updateParkingSpace(newParkingSpace);
	}
}
